package com.dewen;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableNacosConfig
//@NacosPropertySource(name = "dewen", dataId = "n0001",groupId = "group0001",autoRefreshed = true)
public class NocosredissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(NocosredissonApplication.class, args);
    }

}
