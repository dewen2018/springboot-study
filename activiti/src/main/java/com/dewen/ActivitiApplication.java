package com.dewen;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

//    @Bean
//    public AbstractActivitiConfigurer abstractActivitiConfigurer() {
//        return new AbstractActivitiConfigurer() {
//            @Override
//            public void postProcessSpringProcessEngineConfiguration(SpringProcessEngineConfiguration engine) {
//                engine.setActivityFontName("宋体");
//            }
//        };
//    }
}
