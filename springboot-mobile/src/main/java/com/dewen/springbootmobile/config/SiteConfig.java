package com.dewen.springbootmobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.switcher.SiteSwitcherHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SiteConfig implements WebMvcConfigurer {

    /**
     * 设备解析处理
     *
     * @return
     */
    @Bean
    public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }

    /**
     * 请求中Device参数的处理
     *
     * @return
     */
    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    /**
     * 在预处理中，管理用户的站点首选项
     *
     * @return
     */
    @Bean
    public SitePreferenceHandlerInterceptor sitePreferenceHandlerInterceptor() {
        return new SitePreferenceHandlerInterceptor();
    }

    /**
     * 首选项参数解析
     *
     * @return
     */
    @Bean
    public SitePreferenceHandlerMethodArgumentResolver sitePreferenceHandlerMethodArgumentResolver() {
        return new SitePreferenceHandlerMethodArgumentResolver();
    }

    /**
     * 自定义首选项
     *
     * @return
     */
//    @Bean
//    public SiteSwitcherHandlerInterceptor siteSwitcherHandlerInterceptor() {
//        /*SiteSwitcherHandlerInterceptor.standard("app.com","mobile.app.com","tablet.app.com","app.com");*/
//        // 手机、平板、默认
//        return SiteSwitcherHandlerInterceptor.urlPath("/m", "/t", "/showcase");
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceResolverHandlerInterceptor());
        // 注册首选项
        registry.addInterceptor(sitePreferenceHandlerInterceptor());
        // registry.addInterceptor(siteSwitcherHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sitePreferenceHandlerMethodArgumentResolver());
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }

}