package com.my.spring.cloud.learning.movieinfoservice.controller;


import com.my.spring.cloud.learning.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MovieInfoController {


    @GetMapping("/movies/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {

        List<Movie> movieDetails = Arrays.asList(
                new Movie("111", "Transformers"),
                new Movie("222", "Titanic"),
                new Movie("333", "Avatar"),
                new Movie("444", "RRR")
        );

        return movieDetails.stream().filter(movie -> movie.getMovieId().equals(movieId)).findFirst().orElse(null);
    }

}
