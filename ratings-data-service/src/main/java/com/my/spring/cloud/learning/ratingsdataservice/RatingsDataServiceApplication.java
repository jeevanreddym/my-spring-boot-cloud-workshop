package com.my.spring.cloud.learning.ratingsdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RatingsDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingsDataServiceApplication.class, args);
    }

}
