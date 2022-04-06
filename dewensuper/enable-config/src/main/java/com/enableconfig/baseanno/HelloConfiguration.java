package com.enableconfig.baseanno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解驱动方式的自定义实现
 */
@Configuration
public class HelloConfiguration {

    @Bean
    public String hello() { // method name is bean name
        System.out.println("Bean : hello is loading.");
        return "hello word !";
    }
}
