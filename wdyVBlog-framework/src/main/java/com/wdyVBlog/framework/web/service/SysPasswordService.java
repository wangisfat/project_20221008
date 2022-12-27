package com.wdyVBlog.framework.web.service;

import com.wdyVBlog.common.core.ehcache.EhcacheUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.wdyVBlog.common.constant.CacheConstants;
import com.wdyVBlog.common.constant.Constants;
import com.wdyVBlog.common.core.domain.entity.SysUser;
//import com.wdyVBlog.common.core.redis.RedisCache;
import com.wdyVBlog.common.exception.user.UserPasswordNotMatchException;
import com.wdyVBlog.common.exception.user.UserPasswordRetryLimitExceedException;
import com.wdyVBlog.common.utils.MessageUtils;
import com.wdyVBlog.common.utils.SecurityUtils;
import com.wdyVBlog.framework.manager.AsyncManager;
import com.wdyVBlog.framework.manager.factory.AsyncFactory;
import com.wdyVBlog.framework.security.context.AuthenticationContextHolder;

/**
 * 登录密码方法
 * 
 * @author wdy
 */
@Component
public class SysPasswordService
{
//    @Autowired
//    private RedisCache redisCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    /**
     * 登录账户密码错误次数缓存键名
     * 
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username)
    {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(SysUser user)
    {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();

//        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));
        Object cacheObject = EhcacheUtil.getCacheObject(getCacheKey(username));
        Integer retryCount = null;
        if (ObjectUtils.isNotEmpty(cacheObject)) {
            retryCount = Integer.parseInt(String.valueOf(cacheObject));
        }

        if (retryCount == null)
        {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue())
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount, lockTime)));
            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
        }

        if (!matches(user, password))
        {
            retryCount = retryCount + 1;
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.retry.limit.count", retryCount)));
//            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);

            EhcacheUtil.setCacheObject(getCacheKey(username), retryCount, lockTime * 60);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(SysUser user, String rawPassword)
    {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName)
    {
//        if (redisCache.hasKey(getCacheKey(loginName)))
//        {
//            redisCache.deleteObject(getCacheKey(loginName));
//        }

        if (EhcacheUtil.hasKey(getCacheKey(loginName)))
        {
            EhcacheUtil.deleteObject(getCacheKey(loginName));
        }
    }
}
