package com.wdyVBlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 启动程序
 * 
 * @author wdy
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class , MongoAutoConfiguration.class},scanBasePackages = {"org.jeecg.modules.jmreport","com.wdyVBlog"})
@Slf4j
public class MyApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MyApplication.class, args);
        log.info("start success!");
    }
}
