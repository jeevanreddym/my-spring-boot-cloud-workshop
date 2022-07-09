package com.myspring.cloud.learning.myspringconfigserver;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties("db")
@Data
@ToString
public class DBSettings {
    private String connection;
    private String host;
    private String port;
}
