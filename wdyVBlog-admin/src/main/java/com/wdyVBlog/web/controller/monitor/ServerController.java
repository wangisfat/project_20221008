package com.wdyVBlog.web.controller.monitor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wdyVBlog.framework.web.domain.Server;

/**
 * 服务器监控
 * 
 * @author wdy
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController
{
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public Server getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return server;
    }
}
