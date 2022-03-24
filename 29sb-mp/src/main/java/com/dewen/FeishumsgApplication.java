package com.dewen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dewen.mapper")
public class FeishumsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeishumsgApplication.class, args);
    }

}
