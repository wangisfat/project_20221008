package com.wdyVBlog.system.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * jackson全局配置java8 LocalDateTime的序列化 全局配置时间返回格式
 */
//@Configuration
public class JacksonCustomizerConfig {


    /**
     * description:LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     * 接收毫秒级时间戳字符串——>LocalDateTime
     */
    @Bean
    public Converter<Long, LocalDateTime> localDateTimeConverter() {

        return new Converter<Long, LocalDateTime>() {
            @Override
            public LocalDateTime convert(Long source) {
                //毫秒级时间戳转LocalDateTime
                return LocalDateTimeUtil.of(source*1000, ZoneOffset.of("+8"));
            }
        };
    }
}