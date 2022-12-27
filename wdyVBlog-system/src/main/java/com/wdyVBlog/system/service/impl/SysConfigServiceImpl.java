package com.wdyVBlog.system.service.impl;

import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.domain.BaseEntity;
import com.wdyVBlog.common.core.ehcache.EhcacheUtil;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import org.springframework.stereotype.Service;
import com.wdyVBlog.common.annotation.DataSource;
import com.wdyVBlog.common.constant.CacheConstants;
import com.wdyVBlog.common.constant.UserConstants;
//import com.wdyVBlog.common.core.redis.RedisCache;
import com.wdyVBlog.common.core.text.Convert;
import com.wdyVBlog.common.enums.DataSourceType;
import com.wdyVBlog.common.exception.ServiceException;
import com.wdyVBlog.common.utils.StringUtils;
import com.wdyVBlog.system.domain.SysConfig;
import com.wdyVBlog.system.mapper.SysConfigMapper;
import com.wdyVBlog.system.service.ISysConfigService;

/**
 * 参数配置 服务层实现
 * 
 * @author wdy
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService
{


//    @Autowired
//    private RedisCache redisCache;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingConfigCache();
    }

    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    @DataSource(DataSourceType.MASTER)
    public SysConfig selectConfigById(Long configId)
    {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return this.baseMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey)
    {
//        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));

        String configValue = Convert.toStr(EhcacheUtil.getCacheObject(getCacheKey(configKey)));

        if (StringUtils.isNotEmpty(configValue))
        {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = this.baseMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig))
        {
//            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            EhcacheUtil.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取验证码开关
     * 
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaEnabled()
    {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled))
        {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config)
    {
        return this.baseMapper.selectConfigList(config);
    }

    @Override
    public PageResult<SysConfig> selectConfigPage(SysConfig config) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysConfig::getConfigId,SysConfig::getConfigName,SysConfig::getConfigKey,SysConfig::getConfigValue,SysConfig::getConfigType, BaseEntity::getCreateBy,BaseEntity::getCreateTime,BaseEntity::getUpdateBy,BaseEntity::getUpdateTime,BaseEntity::getRemark)
                .like(StringUtils.isNotNull(config.getConfigName()),SysConfig::getConfigName,config.getConfigName())
                .eq(StringUtils.isNotNull(config.getConfigType()),SysConfig::getConfigType,config.getConfigType())
                .like(StringUtils.isNotNull(config.getConfigKey()),SysConfig::getConfigKey,config.getConfigKey());
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<SysConfig> sysConfigPage = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        Page<SysConfig> page = this.page(sysConfigPage, wrapper);
        return new PageResult<SysConfig>(page);
    }

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config)
    {
        int row = this.baseMapper.insertConfig(config);
        if (row > 0)
        {
//            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
            EhcacheUtil.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config)
    {
        int row = this.baseMapper.updateConfig(config);
        if (row > 0)
        {
//            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
            EhcacheUtil.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 批量删除参数信息
     * 
     * @param configIds 需要删除的参数ID
     */
    @Override
    public void deleteConfigByIds(Long[] configIds)
    {
        for (Long configId : configIds)
        {
            SysConfig config = selectConfigById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType()))
            {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            this.baseMapper.deleteConfigById(configId);
//            redisCache.deleteObject(getCacheKey(config.getConfigKey()));
            EhcacheUtil.deleteObject(getCacheKey(config.getConfigKey()));
        }
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache()
    {
        List<SysConfig> configsList = this.baseMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configsList)
        {
//            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
            EhcacheUtil.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache()
    {
//        Collection<String> keys = redisCache.keys(CacheConstants.SYS_CONFIG_KEY + "*");
//        redisCache.deleteObject(keys);
        Collection<String> keys = EhcacheUtil.keys(CacheConstants.SYS_CONFIG_KEY + "*");
        EhcacheUtil.deleteObject(keys);
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache()
    {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config)
    {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = this.baseMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
