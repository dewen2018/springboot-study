package com.dewen.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置分页插件
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 打印 完整sql
     */
    // @Bean
    // public PerformanceInterceptor performanceInterceptor() {
    //     PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    //     //格式化sql语句
    //     Properties properties = new Properties();
    //     properties.setProperty("format", "false");
    //     performanceInterceptor.setProperties(properties);
    //     return performanceInterceptor;
    // }
}
