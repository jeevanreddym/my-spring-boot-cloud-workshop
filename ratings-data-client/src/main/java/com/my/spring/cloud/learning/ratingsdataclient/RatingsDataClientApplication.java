package com.my.spring.cloud.learning.ratingsdataclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.my.spring.cloud.learning.ratingsdataclient")
public class RatingsDataClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingsDataClientApplication.class, args);
    }

}
