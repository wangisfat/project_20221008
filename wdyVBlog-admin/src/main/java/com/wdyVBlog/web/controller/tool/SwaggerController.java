package com.wdyVBlog.web.controller.tool;

import com.wdyVBlog.common.annotation.IgnoreWrap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wdyVBlog.common.core.controller.BaseController;

/**
 * swagger 接口
 * 
 * @author wdy
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController
{
    @PreAuthorize("@ss.hasPermi('tool:swagger:view')")
    @GetMapping()
    @IgnoreWrap
    public String index()
    {
//        return redirect("/swagger-ui.html");
        return redirect("/doc.html");
    }
}
