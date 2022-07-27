package com.myspring.cloud.learning.moviecatalogservice.controller;

import com.myspring.cloud.learning.moviecatalogservice.model.CatalogItem;
//import com.my.spring.cloud.learning.movieinfoservice.model.Movie;
//import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
//import com.my.spring.cloud.learning.movieinfoservice.client.MovieInfoProxy;
//import com.my.spring.cloud.learning.ratingsdataservice.client.RatingsDataProxy;
import com.myspring.cloud.learning.moviecatalogservice.model.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieCatalogFeignController {
    /*@Autowired
    private RatingsDataProxy ratingsDataProxy;
    @Autowired
    private MovieInfoProxy movieInfoProxy;


    @GetMapping("/catalog-feign/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = getUserRatingsFeign(userId);
        return ratings.stream().map(rating -> getCatalogItem(rating)).collect(Collectors.toList());
    }

    private List<Rating> getUserRatingsFeign(String userId) {
        List<Rating> ratings = ratingsDataProxy.getRatings(userId);
        return ratings;
    }

    private CatalogItem getCatalogItem(Rating rating) {
        Movie movie = movieInfoProxy.getMovieInfo(rating.getMovieId());
        return new CatalogItem(movie.getName(), movie.getMovieId(), rating.getRating());
    }*/
}
