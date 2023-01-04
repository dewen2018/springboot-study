package com.dewen;

import com.dewen.anno.EnableNettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableNettyServer
@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // nettyServer.start();
    }

}
