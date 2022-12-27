package com.wdyVBlog.quartz.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wdyVBlog.quartz.domain.SysJobLog;
import com.wdyVBlog.quartz.mapper.SysJobLogMapper;
import com.wdyVBlog.quartz.service.ISysJobLogService;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author wdy
 */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper,SysJobLog> implements ISysJobLogService
{
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public PageResult<SysJobLog> selectJobLogList(SysJobLog jobLog)
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<SysJobLog> sysRolePage = new Page<>(pageDomain.getPageNum(),pageDomain.getPageSize());
        Page<SysJobLog> page = this.baseMapper.selectJobLogPage(sysRolePage,jobLog);
        return new PageResult<SysJobLog>(page);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId)
    {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog)
    {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * 
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(Long[] logIds)
    {
        return jobLogMapper.deleteJobLogByIds(logIds);
    }

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId)
    {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog()
    {
        jobLogMapper.cleanJobLog();
    }
}
