package com.wdyVBlog.flowable.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.DateUtils;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.flowable.service.ISysFormService;
import com.wdyVBlog.system.domain.SysForm;
import com.wdyVBlog.system.mapper.SysFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程表单Service业务层处理
 * 
 * @author XuanXuan Xuan
 * @date 2021-04-03
 */
@Service
public class SysFormServiceImpl extends ServiceImpl<SysFormMapper,SysForm> implements ISysFormService
{
    @Autowired
    private SysFormMapper sysFormMapper;

    /**
     * 查询流程表单
     * 
     * @param formId 流程表单ID
     * @return 流程表单
     */
    @Override
    public SysForm selectSysFormById(Long formId)
    {
        return sysFormMapper.selectSysFormById(formId);
    }

    /**
     * 查询流程表单列表
     * 
     * @param sysForm 流程表单
     * @return 流程表单
     */
    @Override
    public PageResult<SysForm> selectSysFormList(SysForm sysForm)
    {
        PageDomain pageDomain = TableSupport.getPageDomain();
        Page<SysForm> sysFormPage = new Page<>(pageDomain.getPageNum(),pageDomain.getPageSize());
        Page<SysForm> sysForms = sysFormMapper.selectSysFormList(sysFormPage, sysForm);
        return new PageResult<SysForm>(sysForms);
    }

    /**
     * 新增流程表单
     * 
     * @param sysForm 流程表单
     * @return 结果
     */
    @Override
    public int insertSysForm(SysForm sysForm)
    {
        sysForm.setCreateTime(DateUtils.getNowDate());
        return sysFormMapper.insertSysForm(sysForm);
    }

    /**
     * 修改流程表单
     * 
     * @param sysForm 流程表单
     * @return 结果
     */
    @Override
    public int updateSysForm(SysForm sysForm)
    {
        sysForm.setUpdateTime(DateUtils.getNowDate());
        return sysFormMapper.updateSysForm(sysForm);
    }

    /**
     * 批量删除流程表单
     * 
     * @param formIds 需要删除的流程表单ID
     * @return 结果
     */
    @Override
    public int deleteSysFormByIds(Long[] formIds)
    {
        return sysFormMapper.deleteSysFormByIds(formIds);
    }

    /**
     * 删除流程表单信息
     * 
     * @param formId 流程表单ID
     * @return 结果
     */
    @Override
    public int deleteSysFormById(Long formId)
    {
        return sysFormMapper.deleteSysFormById(formId);
    }
}
