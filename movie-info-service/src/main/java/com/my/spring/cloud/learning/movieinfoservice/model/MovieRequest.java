package com.my.spring.cloud.learning.movieinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    @Size(min=2, message = "Name should have atlease 2 characters.")
    private String name;

    @Past(message = "Date should be in the past.")
    private LocalDate releaseDate;
}
