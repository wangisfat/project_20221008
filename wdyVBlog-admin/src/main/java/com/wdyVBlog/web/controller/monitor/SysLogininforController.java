package com.wdyVBlog.web.controller.monitor;

import com.wdyVBlog.common.annotation.Log;
import com.wdyVBlog.common.core.controller.BaseController;
import com.wdyVBlog.common.enums.BusinessType;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.common.utils.poi.ExcelUtil;
import com.wdyVBlog.framework.web.service.SysPasswordService;
import com.wdyVBlog.system.domain.SysLogininfor;
import com.wdyVBlog.system.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 系统访问记录
 *
 * @author wdy
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {
    @Autowired
    private ISysLogininforService logininforService;

    @Autowired
    private SysPasswordService passwordService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public PageResult<SysLogininfor> list(SysLogininfor logininfor) {
        return logininforService.selectLogininforList(logininfor);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininfor logininfor) {
        PageResult<SysLogininfor> pageResult = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(response, pageResult.getRecords(), "登录日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public void remove(@PathVariable Long[] infoIds) {
        logininforService.deleteLogininforByIds(infoIds);
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public void clean() {
        logininforService.cleanLogininfor();
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:unlock')")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public void unlock(@PathVariable("userName") String userName) {
        passwordService.clearLoginRecordCache(userName);
    }
}
