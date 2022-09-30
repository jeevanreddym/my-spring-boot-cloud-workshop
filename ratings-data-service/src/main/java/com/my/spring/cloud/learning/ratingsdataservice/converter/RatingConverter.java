package com.my.spring.cloud.learning.ratingsdataservice.converter;

import com.my.spring.cloud.learning.ratingsdataclient.model.RatingVO;
import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter {

    public RatingVO toRatingVO(Rating rating) {
        return new RatingVO(rating.getRatingId(), rating.getMovieId(), rating.getRating(), rating.getUserId());
    }

}
