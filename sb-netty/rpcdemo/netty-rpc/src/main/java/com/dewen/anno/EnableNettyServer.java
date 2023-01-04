package com.dewen.anno;

import com.dewen.server.config.ServerBeanConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * 自定义SpringBoot启动注解
 * 注入ServerBeanConfig配置类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration({ServerBeanConfig.class})
public @interface EnableNettyServer {
}
