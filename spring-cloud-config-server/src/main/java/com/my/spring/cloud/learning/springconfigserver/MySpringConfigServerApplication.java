package com.my.spring.cloud.learning.springconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MySpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringConfigServerApplication.class, args);
	}

}
