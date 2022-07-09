package com.example.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

    @SpringBootApplication
    @EnableJpaRepositories
    @EntityScan(basePackages = {"com.example.entities"})
    public class CommonApplication {

    //12345

        public static void main(String[] args) {
            SpringApplication.run(com.example.common.CommonApplication.class, args);
        }


    }
