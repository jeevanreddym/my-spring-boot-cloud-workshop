package com.my.spring.cloud.learning.ratingsdataservice.service;


import com.my.spring.cloud.learning.ratingsdataclient.model.RatingVO;
import com.my.spring.cloud.learning.ratingsdataservice.converter.RatingConverter;
import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import com.my.spring.cloud.learning.ratingsdataservice.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingsDataService {

    @Autowired
    private RatingsRepository ratingsRepo;
    @Autowired
    private RatingConverter ratingConverter;


    public List<RatingVO> getRatings(String userId) {
        List<Rating> ratings = ratingsRepo.findByUserId(userId);
        return ratings.stream().map(r -> ratingConverter.toRatingVO(r)).collect(Collectors.toList());
    }

    public RatingVO getRating(String movieId) {
        Optional<Rating> ratingOp = ratingsRepo.findByMovieId(movieId);
        if (ratingOp.isPresent()) {
            return ratingConverter.toRatingVO(ratingOp.get());
        }
        return null;
    }

    public List<RatingVO> getRatings() {
        List<Rating> ratings = ratingsRepo.findAll();
        return ratings.stream().map(r -> ratingConverter.toRatingVO(r)).collect(Collectors.toList());
    }
}
