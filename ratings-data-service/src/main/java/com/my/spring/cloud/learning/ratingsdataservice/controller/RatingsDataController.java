package com.my.spring.cloud.learning.ratingsdataservice.controller;


import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import com.my.spring.cloud.learning.ratingsdataservice.repository.RatingsRepository;
import com.my.spring.cloud.learning.ratingsdataservice.service.RatingsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RatingsDataController {
    @Autowired
    private RatingsDataService ratingsDataService;

    @GetMapping("/ratings")
    public List<Rating> getRatings() {
        return ratingsDataService.getRatings();
    }

    @GetMapping("/ratings/{userId}")
    public List<Rating> getRatings(@PathVariable String userId) {
        return ratingsDataService.getRatings(userId);
    }
    @GetMapping("/ratings/movie/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return ratingsDataService.getRating(movieId);
    }

}
