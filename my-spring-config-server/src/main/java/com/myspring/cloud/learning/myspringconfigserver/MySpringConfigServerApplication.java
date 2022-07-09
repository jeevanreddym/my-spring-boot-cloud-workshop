package com.myspring.cloud.learning.myspringconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigurationProperties
@EnableConfigServer
public class MySpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringConfigServerApplication.class, args);
	}

}
