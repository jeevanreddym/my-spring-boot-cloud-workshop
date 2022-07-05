package com.myspring.cloud.learning.moviecatalogservice.controller;


import com.myspring.cloud.learning.moviecatalogservice.model.CatalogItem;
import com.myspring.cloud.learning.moviecatalogservice.model.Movie;
import com.myspring.cloud.learning.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableEurekaClient

public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/catalog/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        ResponseEntity<List<Rating>> responseEntity =
                restTemplate.exchange(
                        //"http://localhost:8082/ratings/" + userId,
                        "http://ratings-data-service/ratings/" + userId, // using ureka server.
                        HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Rating>>() {});
        List<Rating> ratings = responseEntity.getBody();

        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject(
                    //"http://localhost:8083/movies/" + rating.getMovieId(),
                    "http://movie-info-service/movies/" + rating.getMovieId(), // using ureka server.
                    Movie.class);
            return new CatalogItem(movie.getName(), movie.getMovieId(), rating.getRating());
        })
        .collect(Collectors.toList());
    }

}
