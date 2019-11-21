package com.easicare.device.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器配置
 * @author df
 * @date 2019/8/6
 */
@EnableAutoConfiguration
@Configuration
public class MyHandlerInterceptorConfig implements WebMvcConfigurer {

    private final HandlerInterceptor handlerInterceptor;

    @Autowired
    public MyHandlerInterceptorConfig(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/*.html")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("//swagger-resources/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                //生产时建议开启缓存（只是缓存了资源路径而不是资源内容）,开发是可以设置为false
                .resourceChain(true);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }

}
