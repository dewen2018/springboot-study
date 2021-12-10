package com.dewen;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinganxinxijiamiApplicationTests {
    @Autowired
    private StringEncryptor encryptor;

    @Test
    void contextLoads() {
    }


    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/dewen_examples?autoReconnect=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("1234");
        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
    }
}
