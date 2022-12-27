package com.wdyVBlog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.common.utils.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import com.wdyVBlog.system.domain.SysLogininfor;
import com.wdyVBlog.system.mapper.SysLogininforMapper;
import com.wdyVBlog.system.service.ISysLogininforService;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author wdy
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService
{


    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        this.baseMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public PageResult<SysLogininfor> selectLogininforList(SysLogininfor logininfor)
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        LambdaQueryWrapper<SysLogininfor> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysLogininfor::getInfoId, SysLogininfor::getUserName, SysLogininfor::getIpaddr, SysLogininfor::getLoginLocation, SysLogininfor::getBrowser, SysLogininfor::getOs
                       ,SysLogininfor::getStatus , SysLogininfor::getMsg, SysLogininfor::getLoginTime)
                .like(StringUtils.isNotNull(logininfor.getIpaddr()), SysLogininfor::getIpaddr, logininfor.getIpaddr())
                .like(StringUtils.isNotNull(logininfor.getStatus()), SysLogininfor::getStatus, logininfor.getStatus())
                .like(StringUtils.isNotNull(logininfor.getUserName()), SysLogininfor::getUserName, logininfor.getUserName())
                .between(ObjectUtils.isNotEmpty(logininfor.getParams().get("beginTime")) && ObjectUtils.isNotEmpty(logininfor.getParams().get("endTime")),
                        SysLogininfor::getLoginTime,logininfor.getParams().get("beginTime"),logininfor.getParams().get("endTime"))
                .orderByDesc(SysLogininfor::getInfoId);
        Page<SysLogininfor> logininforPage = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        Page<SysLogininfor> page = this.page(logininforPage, wrapper);
        return new PageResult<>(page);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds)
    {
        return this.baseMapper.deleteLogininforByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor()
    {
        this.baseMapper.cleanLogininfor();
    }
}
