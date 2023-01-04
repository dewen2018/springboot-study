package com.dewen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// @EnableNettyClient
@SpringBootApplication
public class AppApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // nettyServer.start();
    }

}
