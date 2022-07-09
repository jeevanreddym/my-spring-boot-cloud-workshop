package com.my.spring.cloud.learning.movieinfoservice.controller;

import com.my.spring.cloud.learning.movieinfoservice.model.DBSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope // to reload dynamically changed config props on the fly when changed.
public class GreetingsController {

    @Value("${my.greeting}")
    private String greetingMsg;

    @Value("some static value")
    private String staticMsg;

    @Value("${my.list.values}")
    private List<String> msgs;

    @Value("#{${my.dbValues}}")
    private Map<String, String> dbValues;

    @Autowired
    private DBSettings dbSettings;

    @Autowired
    private Environment env;

    @GetMapping("/greeting")
    public String greeting() {
        return greetingMsg + staticMsg + msgs + dbValues + dbSettings;
    }

    @GetMapping("/env-details")
    public String envDetails() {
        return env.toString();
    }
}
