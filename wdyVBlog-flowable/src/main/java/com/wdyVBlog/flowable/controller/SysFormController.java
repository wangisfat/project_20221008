package com.wdyVBlog.flowable.controller;

import com.wdyVBlog.common.annotation.Log;
import com.wdyVBlog.common.core.controller.BaseController;
import com.wdyVBlog.common.core.domain.AjaxResult;
import com.wdyVBlog.common.enums.BusinessType;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.common.utils.poi.ExcelUtil;
import com.wdyVBlog.flowable.service.ISysDeployFormService;
import com.wdyVBlog.flowable.service.ISysFormService;
import com.wdyVBlog.system.domain.SysDeployForm;
import com.wdyVBlog.system.domain.SysForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 流程表单Controller
 *
 * @author XuanXuan
 * @date 2021-04-03
 */
@RestController
@RequestMapping("/flowable/form")
public class SysFormController extends BaseController {
    @Autowired
    private ISysFormService SysFormService;

    @Autowired
    private ISysDeployFormService sysDeployFormService;

    /**
     * 查询流程表单列表
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:list')")
    @GetMapping("/list")
    public PageResult<SysForm> list(SysForm sysForm) {
        PageResult<SysForm> list = SysFormService.selectSysFormList(sysForm);
        return list;
    }

    /**
     * 导出流程表单列表
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:export')")
    @Log(title = "流程表单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysForm sysForm) {
        PageResult<SysForm> list = SysFormService.selectSysFormList(sysForm);
        ExcelUtil<SysForm> util = new ExcelUtil<SysForm>(SysForm.class);
        return util.exportExcel(list.getRecords(), "form");
    }

    /**
     * 获取流程表单详细信息
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:query')")
    @GetMapping(value = "/{formId}")
    public AjaxResult getInfo(@PathVariable("formId") Long formId) {
        return AjaxResult.success(SysFormService.selectSysFormById(formId));
    }

    /**
     * 新增流程表单
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:add')")
    @Log(title = "流程表单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysForm sysForm) {
        return toAjax(SysFormService.insertSysForm(sysForm));
    }

    /**
     * 修改流程表单
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:edit')")
    @Log(title = "流程表单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysForm sysForm) {
        return toAjax(SysFormService.updateSysForm(sysForm));
    }

    /**
     * 删除流程表单
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:remove')")
    @Log(title = "流程表单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{formIds}")
    public AjaxResult remove(@PathVariable Long[] formIds) {
        return toAjax(SysFormService.deleteSysFormByIds(formIds));
    }


    /**
     * 挂载流程表单
     */
    @Log(title = "流程表单", businessType = BusinessType.INSERT)
    @PostMapping("/addDeployForm")
    public AjaxResult addDeployForm(@RequestBody SysDeployForm sysDeployForm) {
        return toAjax(sysDeployFormService.insertSysDeployForm(sysDeployForm));
    }
}
