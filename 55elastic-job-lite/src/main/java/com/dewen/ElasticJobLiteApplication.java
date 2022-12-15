package com.dewen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableAsync
//@EnableScheduling
//@EnableCaching
//@ImportResource(locations = {"classpath:config/jobs.xml"})
public class ElasticJobLiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticJobLiteApplication.class, args);
    }

}
