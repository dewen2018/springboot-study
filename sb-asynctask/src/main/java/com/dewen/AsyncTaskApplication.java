package com.dewen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 使 @Async 生效
 */
@EnableAsync
@SpringBootApplication
public class AsyncTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncTaskApplication.class, args);
	}

}
