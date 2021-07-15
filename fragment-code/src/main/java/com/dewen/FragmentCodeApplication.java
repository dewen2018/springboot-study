package com.dewen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dewen.mybatisplus.modules.mapper")
@SpringBootApplication
public class FragmentCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FragmentCodeApplication.class, args);
    }

}
