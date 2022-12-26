package com.wdyVBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author wdy
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class WdyVBlogApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WdyVBlogApplication.class, args);
        System.out.println("启动成功\n" +
                " * 　　　　　　 ＿＿＿\n" +
                " * 　　　　　／＞　　フ ＼\n" +
                " * 　　　　　|  　_　 _ l\n" +
                " * 　 　　　／  ミ＿xノ\n" +
                " * 　　 　 /　　　 　 |\n" +
                " * 　　　 /　 ヽ　　 ﾉ\n" +
                " * 　 　 │　　|　|　|\n" +
                " * 　／￣|　　 |　|　|\n" +
                " * 　| (￣ヽ＿_ヽ_)__)\n" +
                " * 　＼二つ\n" +
                " *");
    }
}
