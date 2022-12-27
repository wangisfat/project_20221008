package com.wdyVBlog.framework.config;


import com.wdyVBlog.common.annotation.IgnoreWrap;
import com.wdyVBlog.common.core.domain.AjaxResult;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description
 * @author wdy
 * @Date 2021-10-25
 */
@EnableWebMvc
@Configuration
@RestControllerAdvice
public class GlobalReturnConfig implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        IgnoreWrap methodAnnotation = methodParameter.getMethodAnnotation(IgnoreWrap.class);
        if (ObjectUtils.isNotEmpty(methodAnnotation)) {
            return false;
        }

        Class<?> declaringClass = methodParameter.getDeclaringClass();
        if (declaringClass.getName().startsWith("org.jeecg.modules.jmreport")) {
            return false;
        }

        return !ResponseEntity.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof AjaxResult) {
            return body;
        }
        return AjaxResult.success(body);
    }
}
