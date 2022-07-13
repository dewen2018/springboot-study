package com.dewen;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 * 方式二
 * spring:
 * mvc:
 * static-path-pattern: /mystatic/**
 * web:
 * resources:
 * static-locations: classpath:/mystatic/
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将/image/**访问映射到classpath:/image/
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/");
    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor( )
    //             .addPathPatterns("/**")
    //             .excludePathPatterns("/","/js/**","/image/**","/favicon.ico");
    //
    // }
}
