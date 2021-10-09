package com.dewen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;


@Slf4j
@EnableScheduling
@SpringBootApplication
public class AdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminClientApplication.class, args);
    }

    @Scheduled(cron = "0/20 * * * * ?")
    public void run20s() {
        log.info("定时任务:{}", LocalDateTime.now());
    }
}
