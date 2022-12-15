package com.dewen;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import net.hasor.web.startup.RuntimeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@EnableHasor()
@EnableHasorWeb()
@SpringBootApplication
//        (scanBasePackages = { "net.example.hasor" })
public class DatawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatawayApplication.class, args);
    }

//    @Bean
//    public FilterRegistrationBean runtimeFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new RuntimeFilter());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("runtimeFilter");
//        registration.setOrder(1);
//        return registration;
//    }
}
