package com.liang.wang.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Scanner;

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
 * @title: CodeGenerator
 * @projectName project_20221008
 * @description: mybatis-plus 代码生成器
 * @date 2022-10-2017:24
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String sqlurl="jdbc:mysql://localhost:3306/wangl20220927?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true";     //数据库的url
        String sqlusername="root";  //数据库的登录名
        String sqlpassword="root";  //数据库的登录密码
        String outputDir="D:\\java\\code\\myCode\\project_20221008\\system-service\\src\\main\\java";  //生成代码的指定目录，一般配置成自己的工程目录下的java路径，即包名“com.example.demo”的目录
        String packageName="com.liang.wang";  //配置包名“com.example.demo”
        String mapperXmlOutFilePath="D:\\java\\code\\myCode\\project_20221008\\system-service\\src\\main\\resources\\mapper\\";  //mapperXml文件的输出目录
        String sqlTableName="sys_user_role_rel";  //要对应的数据库中的表名

        generate(sqlurl,
                sqlusername,
                sqlpassword,
                outputDir,
                packageName,
                mapperXmlOutFilePath,
                sqlTableName);  //调用代码生成器函数

    }

    //生成代码方法
    private static void generate(String sqlurl,
                                 String sqlusername,
                                 String sqlpassword,
                                 String outputDir,
                                 String packageName,
                                 String mapperXmlOutFilePath,
                                 String sqlTableName){
        FastAutoGenerator.create(sqlurl, sqlusername, sqlpassword)  //数据库连接配置，必不可少的一个配置
                .globalConfig(builder -> {  //全局配置
                    builder.author("wl") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {   //包配置
                    builder.parent(packageName) // 设置父包名
                            .moduleName(null) // 设置父包模块名,可以设置为空，默认在包名之下,设置成null，防止生成双斜杠问题
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlOutFilePath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(sqlTableName); // 设置需要生成的表名
                            //.addTablePrefix("t_", "sys"); // 设置过滤表前缀,忽略一些表头，如“sys_user”,填写了sys，就会忽略sys，生成user
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
    //public static String scanner(String tip) {
    //    Scanner scanner = new Scanner(System.in);
    //    StringBuilder help = new StringBuilder();
    //    help.append("请输入" + tip + "：");
    //    System.out.println(help.toString());
    //    if (scanner.hasNext()) {
    //        String ipt = scanner.next();
    //        if (StringUtils.isNotBlank(ipt)) {
    //            return ipt;
    //        }
    //    }
    //    throw new MybatisPlusException("请输入正确的" + tip + "！");
    //}
}
