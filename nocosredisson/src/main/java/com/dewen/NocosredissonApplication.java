package com.dewen;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@NacosPropertySource(dataId = "n0001", groupId = "n0001-group", autoRefreshed = true)
public class NocosredissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(NocosredissonApplication.class, args);
    }

}
