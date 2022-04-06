package com.enableconfig.baseinter;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableHellos
public class EnableHellosBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHellosBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloBean = context.getBean("hello", String.class);
        System.out.println("hello Bean: " + helloBean);
        context.close();
    }
} 