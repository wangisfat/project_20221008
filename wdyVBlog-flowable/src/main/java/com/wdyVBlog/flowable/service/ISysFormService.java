package com.wdyVBlog.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.system.domain.SysForm;

/**
 * 表单
 * @author XuanXuan Xuan
 * @date 2021-04-03
 */
public interface ISysFormService extends IService<SysForm>
{
    /**
     * 查询流程表单
     * 
     * @param formId 流程表单ID
     * @return 流程表单
     */
    public SysForm selectSysFormById(Long formId);

    /**
     * 查询流程表单列表
     * 
     * @param sysForm 流程表单
     * @return 流程表单集合
     */
    public PageResult<SysForm> selectSysFormList(SysForm sysForm);

    /**
     * 新增流程表单
     * 
     * @param sysForm 流程表单
     * @return 结果
     */
    public int insertSysForm(SysForm sysForm);

    /**
     * 修改流程表单
     * 
     * @param sysForm 流程表单
     * @return 结果
     */
    public int updateSysForm(SysForm sysForm);

    /**
     * 批量删除流程表单
     * 
     * @param formIds 需要删除的流程表单ID
     * @return 结果
     */
    public int deleteSysFormByIds(Long[] formIds);

    /**
     * 删除流程表单信息
     * 
     * @param formId 流程表单ID
     * @return 结果
     */
    public int deleteSysFormById(Long formId);
}
