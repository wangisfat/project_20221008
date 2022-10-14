package com.liang.wang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 　　　　　　 ＿＿＿
 * 　　　　　／＞　　フ ＼
 * 　　　　　|  　_　 _ l
 * 　 　　　／  ミ＿xノ
 * 　　 　 /　　　 　 |
 * 　　　 /　 ヽ　　 ﾉ
 * 　 　 │　　|　|　|
 * 　／￣|　　 |　|　|
 * 　| (￣ヽ＿_ヽ_)__)
 * 　＼二つ
 *
 * @author wangl
 * @title: SystemWebApplication
 * @projectName project_20221008
 * @description: 项目启动类
 * @date 2022-10-1015:34
 */
@SpringBootApplication
@Slf4j
@EnableAsync
@EnableScheduling
public class SystemWebApplication  extends SpringBootServletInitializer {
    static {
        System.setProperty("log4j2.formatMsgNoLookups", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        log.info("SystemWebApplication start succeed !!!!!!!!!");
        return builder.sources(SystemWebApplication.class);
    }
}
