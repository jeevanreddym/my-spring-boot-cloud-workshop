package com.my.spring.cloud.learning.movieinfoservice.model;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties("my-database")
@Data
public class DBSettings {
    private String connection;
    private String host;
    private String port;
}
