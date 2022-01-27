package com.example.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class Task5Application {

//12345

	public static void main(String[] args) {
		SpringApplication.run(Task5Application.class, args);
	}


}
