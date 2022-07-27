package com.my.spring.cloud.learning.ratingsdataservice.repository;


import com.my.spring.cloud.learning.ratingsdataservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUserId(String userId);
    Optional<Rating> findByMovieId(String movieId);
}
