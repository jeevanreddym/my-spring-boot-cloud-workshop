package com.my.spring.cloud.learning.ratingsdataservice.controller;


import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RatingsDataController {

    @GetMapping("/ratings/{userId}")
    public List<Rating> getRatings(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("111", 8),
                new Rating("222", 10),
                new Rating("333", 10),
                new Rating("444", 9)
        );
        return ratings;
    }

    @GetMapping("/ratings/movie/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating("Transformers", 8);
    }

}
