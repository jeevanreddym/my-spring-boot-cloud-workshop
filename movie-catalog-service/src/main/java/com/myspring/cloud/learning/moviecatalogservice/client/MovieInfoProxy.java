package com.myspring.cloud.learning.moviecatalogservice.client;



import com.myspring.cloud.learning.moviecatalogservice.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="movie-info-service")
public interface MovieInfoProxy {

    @GetMapping("/movies/{movieId}")
    Movie getMovieInfo(@PathVariable String movieId);

}
