package com.my.spring.cloud.learning.apigateway.filter;

import com.my.spring.cloud.learning.apigateway.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@RefreshScope
@Component
public class MyAuthenticationFilter implements GatewayFilter {
    @Autowired
    private MyRouterValidator routerValidator; //custom route validator
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            final String token = getAuthHeader(request);

            if (jwtUtil.isTokenExpired(token))
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

            this.populateRequestWithHeaders(exchange, token);
        }

        return chain.filter(exchange);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }


    @Component
    public class MyRouterValidator {
        public final List<String> openApiEndpoints= List.of(
                "/auth/register",
                "/auth/login"
        );
        public Predicate<ServerHttpRequest> isSecured =
                request -> openApiEndpoints
                        .stream()
                        .noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

}


