package com.my.spring.cloud.learning.myeurekaregistrydiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class MyEurekaRegistryDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEurekaRegistryDiscoveryServerApplication.class, args);
    }

}
