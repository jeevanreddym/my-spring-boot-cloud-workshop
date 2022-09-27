package com.my.spring.cloud.learning.apigateway.config;


import com.my.spring.cloud.learning.apigateway.filter.MyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    @Autowired
    private MyAuthenticationFilter authFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("movie-catalog-service", r -> r
                        .path("/catalog/**")
                        .filters(f ->
                                f.addRequestHeader("Hello", "World")
                                .addRequestParameter("Param", "MyParam"))
                        .uri("lb://movie-catalog-service/")
                )
                .route("movie-info-route", r -> r.path("/movies/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://movie-info-service")
                )
                .build();
    }
}
