package com.my.spring.cloud.learning.ratingsdataclient.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingVO {
    private Integer ratingId;
    private String movieId;
    private Integer rating;
    private String userId;
}
