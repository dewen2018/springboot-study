package com.enableconfig.baseinter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloDewenConfiguration {

    @Bean
    public String hello() { // method name is bean name  
        System.out.println("Bean : hello is loading.");
        return "hello dewen";
    }

} 