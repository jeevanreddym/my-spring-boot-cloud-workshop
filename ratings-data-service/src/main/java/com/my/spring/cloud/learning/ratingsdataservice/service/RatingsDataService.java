package com.my.spring.cloud.learning.ratingsdataservice.service;


import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import com.my.spring.cloud.learning.ratingsdataservice.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingsDataService {

    @Autowired
    private RatingsRepository ratingsRepo;

    public List<Rating> getRatings(String userId) {
        List<Rating> ratings = ratingsRepo.findByUserId(userId);
        return ratings;
    }

    public Rating getRating(String movieId) {
        Optional<Rating> rating = ratingsRepo.findByMovieId(movieId);
        return rating.orElse(null);
    }



    public List<Rating> getRatings() {
        List<Rating> ratings = ratingsRepo.findAll();
        return ratings;
    }
}
