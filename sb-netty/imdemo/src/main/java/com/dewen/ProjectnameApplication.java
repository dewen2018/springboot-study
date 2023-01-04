package com.dewen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectnameApplication implements CommandLineRunner {
    // @Resource
    // private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(ProjectnameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // nettyServer.start();
    }

}
