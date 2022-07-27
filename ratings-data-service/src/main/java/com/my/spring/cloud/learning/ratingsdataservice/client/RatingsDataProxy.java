package com.my.spring.cloud.learning.ratingsdataservice.client;


import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ratings-data-service")
public interface RatingsDataProxy {

    @GetMapping("/ratings/{userId}")
    public List<Rating> getRatings(@PathVariable String userId);

    @GetMapping("/ratings/movie/{movieId}")
    public Rating getRating(@PathVariable String movieId);

}
