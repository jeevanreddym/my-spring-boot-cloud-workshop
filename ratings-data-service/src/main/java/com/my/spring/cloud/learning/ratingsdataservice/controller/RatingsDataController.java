package com.my.spring.cloud.learning.ratingsdataservice.controller;


import com.my.spring.cloud.learning.ratingsdataclient.model.RatingVO;
import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import com.my.spring.cloud.learning.ratingsdataservice.service.RatingsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingsDataController {
    @Autowired
    private RatingsDataService ratingsDataService;

    @GetMapping("/ratings")
    public List<RatingVO> getRatings() {
        return ratingsDataService.getRatings();
    }

    @GetMapping("/ratings/{userId}")
    public List<RatingVO> getRatings(@PathVariable String userId) {
        return ratingsDataService.getRatings(userId);
    }
    @GetMapping("/ratings/movie/{movieId}")
    public RatingVO getRating(@PathVariable String movieId) {
        return ratingsDataService.getRating(movieId);
    }

}
