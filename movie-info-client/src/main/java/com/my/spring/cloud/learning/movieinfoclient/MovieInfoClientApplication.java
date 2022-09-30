package com.my.spring.cloud.learning.movieinfoclient;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MovieInfoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieInfoClientApplication.class);
    }

}
