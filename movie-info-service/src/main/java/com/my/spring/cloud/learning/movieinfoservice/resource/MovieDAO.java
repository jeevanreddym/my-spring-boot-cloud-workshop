package com.my.spring.cloud.learning.movieinfoservice.resource;

import com.my.spring.cloud.learning.movieinfoclient.model.Movie;
import com.my.spring.cloud.learning.movieinfoservice.exception.MovieNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class MovieDAO {

    // Movie DB.
    private static List<Movie> movieDetails;

    static {
        movieDetails = Arrays.asList(
            new Movie("111", "Transformers"),
            new Movie("222", "Titanic"),
            new Movie("333", "Avatar"),
            new Movie("444", "RRR")
        );
    }

    public List<Movie> findAll() {
        return movieDetails;
    }

    public Movie findById(String movieId) {
        return movieDetails.stream().filter(movie -> movie.getMovieId().equals(movieId)).findFirst().orElse(null);
    }

    public Movie findOne(String movieId) {

        Predicate<Movie> findMoviePredicate = movie -> movie.getMovieId().equals(movieId);

        Movie retrievedMovie = movieDetails.stream().filter(findMoviePredicate).findFirst().orElse(null);
        if (retrievedMovie == null)
            throw new MovieNotFoundException("movieId: " + movieId);

        return retrievedMovie;
    }

    public void deleteById(String movieId) {
        Predicate<Movie> findMoviePredicate = movie -> movie.getMovieId().equals(movieId);
        movieDetails.removeIf(findMoviePredicate);
    }

    public Movie save(Movie movie) {
        movieDetails.add(movie);
        return movie;
    }
}
