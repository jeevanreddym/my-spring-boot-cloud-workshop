package com.my.spring.cloud.learning.apigateway.config;


import com.my.spring.cloud.learning.apigateway.filter.MyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

//@Configuration
public class MyApiGatewayRouterConfiguration {
    @Autowired
    private MyAuthenticationFilter authFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("dummy-test-route", p -> p
                    .path("/get")
                    .filters(f ->
                            f.addRequestHeader("MyHeader", "MyURI")
                             .addRequestParameter("MyParam", "Val"))
                    .uri("http://httpbin.org:80")
            )
            .route("movie-catalog-route", r -> r
                    .path("/movie-catalog-service/**") // the inbound path in url.
                    .filters(f ->
                            f.addRequestHeader("Hello", "World")
                            .addRequestParameter("Param", "MyParam"))
                    .uri("lb://movie-catalog-service") // the outbound path api-gateway needs to redirect to that's mapped in eureka server.
            )
            .route("movie-info-route-1", r -> r.path("/movie-info-service/**")
                    //.filters(f -> f.filter(authFilter))
                    .uri("lb://movie-info-service")
            )
            .route("movie-info-route-2", r -> r.path("/movie-info-client-service-api/**")
                    .filters(f -> f.rewritePath(
                            "/movie-info-client-service-api/(?<segment>.*)",
                            "/movie-info-service/${segment}"))
                    .uri("lb://movie-info-service")
            )
            .route("ratings-data-route", r -> r.path("/ratings-data-service/**")
                    //.filters(f -> f.filter(authFilter))
                    .uri("lb://ratings-data-service")
            )
            .build();
    }
}
