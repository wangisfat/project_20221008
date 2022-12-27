package com.wdyVBlog.web.controller.monitor;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.wdyVBlog.common.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wdyVBlog.common.annotation.Log;
import com.wdyVBlog.common.core.controller.BaseController;
import com.wdyVBlog.common.enums.BusinessType;
import com.wdyVBlog.common.utils.poi.ExcelUtil;
import com.wdyVBlog.system.domain.SysOperLog;
import com.wdyVBlog.system.service.ISysOperLogService;

/**
 * 操作日志记录
 * 
 * @author wdy
 */
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController
{
    @Autowired
    private ISysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @GetMapping("/list")
    public  PageResult<SysOperLog> list(SysOperLog operLog)
    {
        return operLogService.selectOperLogPage(operLog);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperLog operLog)
    {
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        util.exportExcel(response, list, "操作日志");
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/{operIds}")
    public void remove(@PathVariable Long[] operIds)
    {
        operLogService.deleteOperLogByIds(operIds);
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public void clean()
    {
        operLogService.cleanOperLog();
    }
}
