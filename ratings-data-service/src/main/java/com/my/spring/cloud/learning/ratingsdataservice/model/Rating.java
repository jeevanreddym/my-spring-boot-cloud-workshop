package com.my.spring.cloud.learning.ratingsdataservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    private Integer ratingId;
    private String movieId;
    private int rating;
    private String userId;
}
