package com.my.spring.cloud.learning.ratingsdataclient.client;


import com.my.spring.cloud.learning.ratingsdataclient.model.RatingVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ratings-data-service")
public interface RatingsDataProxy {

    @GetMapping("/ratings/{userId}")
    List<RatingVO> getRatings(@PathVariable String userId);

    @GetMapping("/ratings/movie/{movieId}")
    RatingVO getRating(@PathVariable String movieId);

}
