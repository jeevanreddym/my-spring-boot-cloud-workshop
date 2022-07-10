package com.my.spring.cloud.learning.apigateway;


import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r
//                        .path("/catalog/**")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("lb://movie-catalog-service/")
//                        //.id("movie-catalog-route")
//                )
//
//                .route(r -> r.path("/movie-info-service/movies/**")
//                        .uri("lb://movie-info-service")
//                        //.id("movie-info-route")
//                )
//                .build();
//    }
}
