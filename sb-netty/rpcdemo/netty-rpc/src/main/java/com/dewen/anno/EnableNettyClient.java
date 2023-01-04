package com.dewen.anno;

import com.dewen.client.config.ClientBeanConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * 注入ClientBeanConfig配置类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration({ClientBeanConfig.class})
public @interface EnableNettyClient {
}