package com.enableconfig.baseanno;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 在启动类上标注了 @EnableHello来激活我们的 Hello模块，并且在 Spring Boot项目启动后，
 * 获取到了应用的上下文 ConfigurableApplicationContext。然后我们根据我们注入的 bean的名字 hello来获取 bean，
 * 接着打印 bean的内容，最后关闭上下文。
 */
@EnableHello
public class EnableHelloBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloBean = context.getBean("hello", String.class);
        System.out.println("hello Bean: " + helloBean);
        context.close();
    }
}  