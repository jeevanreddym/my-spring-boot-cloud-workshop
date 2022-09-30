package com.my.spring.cloud.learning.moviecatalogservice.controller;


import com.my.spring.cloud.learning.moviecatalogservice.model.CatalogItem;
import com.my.spring.cloud.learning.movieinfoclient.client.MovieInfoProxy;
import com.my.spring.cloud.learning.movieinfoclient.model.Movie;
import com.my.spring.cloud.learning.ratingsdataclient.client.RatingsDataProxy;
import com.my.spring.cloud.learning.ratingsdataclient.model.RatingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieCatalogFeignController {
    @Autowired
    private RatingsDataProxy ratingsDataProxy;
    @Autowired
    private MovieInfoProxy movieInfoProxy;


    @GetMapping("/catalog-feign/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<RatingVO> ratings = getUserRatingsFeign(userId);
        return ratings.stream().map(rating -> getCatalogItem(rating)).collect(Collectors.toList());
    }

    private List<RatingVO> getUserRatingsFeign(String userId) {
        List<RatingVO> ratings = ratingsDataProxy.getRatings(userId);
        return ratings;
    }

    private CatalogItem getCatalogItem(RatingVO rating) {
        Movie movie = movieInfoProxy.getMovieInfo(rating.getMovieId());
        return new CatalogItem(movie.getName(), movie.getMovieId(), rating.getRating());
    }
}
