package com.dewen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dewen.upload.dao")
public class ProjectnameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectnameApplication.class, args);
	}

}
