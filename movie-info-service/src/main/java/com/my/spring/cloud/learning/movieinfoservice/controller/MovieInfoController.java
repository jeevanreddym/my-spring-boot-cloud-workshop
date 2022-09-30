package com.my.spring.cloud.learning.movieinfoservice.controller;



import com.my.spring.cloud.learning.movieinfoclient.model.Movie;
import com.my.spring.cloud.learning.movieinfoservice.model.MovieRequest;
import com.my.spring.cloud.learning.movieinfoservice.resource.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class MovieInfoController {
    @Autowired
    private MovieDAO movieDAO;
    @Autowired
    private Environment env;
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/movies/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        String port = env.getProperty("local.server.port");
        return movieDAO.findById(movieId);
    }

    @GetMapping("/movie-detail/{movieId}")
    public Movie getMovieDetail(@PathVariable String movieId) {
        return movieDAO.findOne(movieId);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody MovieRequest movieRequest) {

        Movie savedMovie = movieDAO.save(new Movie(movieRequest.getName(), movieRequest.getName()));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{movieId}")
                .buildAndExpand(savedMovie.getMovieId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/movie/{movieId}")
    public void deleteMovie(@PathVariable String movieId) {
        movieDAO.deleteById(movieId);
    }


    @GetMapping("/message")
    public String internationalizedMessage() {
        return messageSource.getMessage("good.morning.message", null, "Default Msg", LocaleContextHolder.getLocale());
    }

}
