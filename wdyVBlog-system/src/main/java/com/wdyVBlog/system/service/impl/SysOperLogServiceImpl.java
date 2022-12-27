package com.wdyVBlog.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.common.utils.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import com.wdyVBlog.system.domain.SysOperLog;
import com.wdyVBlog.system.mapper.SysOperLogMapper;
import com.wdyVBlog.system.service.ISysOperLogService;

/**
 * 操作日志 服务层处理
 *
 * @author wdy
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService
{

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog)
    {
        this.baseMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        return this.baseMapper.selectOperLogList(operLog);
    }

    @Override
    public PageResult<SysOperLog> selectOperLogPage(SysOperLog operLog) {
        LambdaQueryWrapper<SysOperLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysOperLog::getOperId,SysOperLog::getTitle,SysOperLog::getBusinessType,SysOperLog::getMethod,SysOperLog::getRequestMethod,
                        SysOperLog::getOperatorType,SysOperLog::getOperName,SysOperLog::getDeptName,SysOperLog::getOperUrl,SysOperLog::getOperIp,
                        SysOperLog::getOperLocation,SysOperLog::getOperParam,SysOperLog::getJsonResult,SysOperLog::getStatus,SysOperLog::getErrorMsg,SysOperLog::getOperTime)
                .like(StringUtils.isNotNull(operLog.getTitle()), SysOperLog::getTitle, operLog.getTitle())
                .eq(StringUtils.isNotNull(operLog.getBusinessType()), SysOperLog::getBusinessType, operLog.getBusinessTypes())
                .in(ObjectUtils.isNotEmpty(operLog.getBusinessTypes()) && operLog.getBusinessTypes().length > 0, SysOperLog::getBusinessType, operLog.getBusinessTypes())
                .eq(StringUtils.isNotNull(operLog.getStatus()),SysOperLog::getStatus,operLog.getStatus())
                .like(StringUtils.isNotNull(operLog.getOperName()),SysOperLog::getOperName,operLog.getOperName())
                .between(ObjectUtils.isNotEmpty(operLog.getParams().get("beginTime")) && ObjectUtils.isNotEmpty(operLog.getParams().get("endTime")),
                        SysOperLog::getOperTime,operLog.getParams().get("beginTime"),operLog.getParams().get("endTime"))
                .orderByDesc(SysOperLog::getOperId);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<SysOperLog> sysOperLogPage = new Page<>(pageDomain.getPageNum(),pageDomain.getPageSize());
        Page<SysOperLog> page = this.page(sysOperLogPage, wrapper);
        return new PageResult<SysOperLog>(page);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds)
    {
        return this.baseMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return this.baseMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        this.baseMapper.cleanOperLog();
    }


}
