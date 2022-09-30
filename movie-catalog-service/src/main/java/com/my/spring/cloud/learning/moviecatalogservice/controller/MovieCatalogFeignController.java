package com.my.spring.cloud.learning.moviecatalogservice.controller;



import com.my.spring.cloud.learning.movieinfoclient.client.MovieInfoProxy;
import com.my.spring.cloud.learning.movieinfoclient.model.Movie;
import com.my.spring.cloud.learning.moviecatalogservice.model.CatalogItem;


import com.my.spring.cloud.learning.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MovieCatalogFeignController {

//    @Autowired
//    private RatingsDataProxy ratingsDataProxy;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MovieInfoProxy movieInfoProxy;


    @GetMapping("/catalog-feign/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = getUserRatingsFeign(userId);
        return ratings.stream().map(rating -> getCatalogItem(rating)).collect(Collectors.toList());
    }

    private List<Rating> getUserRatingsFeign(String userId) {

        ResponseEntity<List<Rating>> responseEntity =
                restTemplate.exchange(
                        //"http://localhost:8082/ratings/"
                        "http://ratings-data-service/ratings/"  // using eureka registry/discovery server.
                                + userId,
                        HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Rating>>() {});
        List<Rating> ratings = responseEntity.getBody();

//        List<Rating> ratings = ratingsDataProxy.getRatings(userId);

        return ratings;
    }

    private CatalogItem getCatalogItem(Rating rating) {
        Movie movie = movieInfoProxy.getMovieInfo(rating.getMovieId());
        return new CatalogItem(movie.getName(), movie.getMovieId(), rating.getRating());
    }
}
