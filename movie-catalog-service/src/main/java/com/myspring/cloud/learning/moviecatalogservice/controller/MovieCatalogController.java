package com.myspring.cloud.learning.moviecatalogservice.controller;


import com.myspring.cloud.learning.moviecatalogservice.model.CatalogItem;
import com.myspring.cloud.learning.moviecatalogservice.model.Movie;
import com.myspring.cloud.learning.moviecatalogservice.model.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieCatalogController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory cbFactory;


    @GetMapping("/catalog/{userId}")
    @Retry(name="MOVIE_CATALOG_CB")
    @RateLimiter(name="MOVIE_CATALOG_CB")
    @CircuitBreaker(name="MOVIE_CATALOG_CB", fallbackMethod = "getFalbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = getUserRatings(userId);
        return ratings.stream().map(rating -> getCatalogItem(rating)).collect(Collectors.toList());
    }

    private List<Rating> getUserRatings(String userId) {
        ResponseEntity<List<Rating>> responseEntity =
                restTemplate.exchange(
                        //"http://localhost:8082/ratings/"
                        "http://ratings-data-service/ratings/"  // using eureka registry/discovery server.
                                + userId,
                        HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Rating>>() {});
        List<Rating> ratings = responseEntity.getBody();
        return ratings;
    }

    private CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject(
                //"http://localhost:8083/movies/"
                "http://movie-info-service/movies/"  // using eureka registry/discovery server.
                        + rating.getMovieId(),
                Movie.class);
        return new CatalogItem(movie.getName(), movie.getMovieId(), rating.getRating());
    }

    public List<CatalogItem> getFalbackCatalog(Exception ex) {
        return Arrays.asList(
                new CatalogItem("Fallback", "Fallback", 0)
        );
    }
}
