package com.my.spring.cloud.learning.movieinfoservice.controller;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    @GetMapping("/static-filtered-users")
    public List<User> filterUsingStaticFiltering() {
        return Arrays.asList(
                new User(1, "Jhon Duo", LocalDate.now().minusYears(10), "MALE", 341318775),
                new User(2, "Happy Bao", LocalDate.now().minusYears(20), "MALE", 341318776),
                new User(3, "Amayra Hunt", LocalDate.now().minusYears(30), "FEMALE", 341318777),
                new User(4, "Zapin Hunt", LocalDate.now().minusYears(17), "AA", 341318778),
                new User(5, "Lona Blunt", LocalDate.now().minusYears(15), "FEMALE", 341318779)
        );
    }


    @GetMapping("/dynamic-filtered-users")
    public MappingJacksonValue filterUsingDynamicFiltering() {

        List<Customer> allCustomers = Arrays.asList(
                new Customer(1, "Jhon Duo", LocalDate.now().minusYears(10), "MALE", 341318775),
                new Customer(2, "Happy Bao", LocalDate.now().minusYears(20), "MALE", 341318776),
                new Customer(3, "Amayra Hunt", LocalDate.now().minusYears(30), "FEMALE", 341318777),
                new Customer(4, "Zapin Hunt", LocalDate.now().minusYears(17), "AA", 341318778),
                new Customer(5, "Lona Blunt", LocalDate.now().minusYears(15), "FEMALE", 341318779)
        );

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allCustomers);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "user_name");

        FilterProvider filters = new SimpleFilterProvider().addFilter("CustomerBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/dynamic-filtered-user")
    public MappingJacksonValue filterUsingDynamicFilteringOfUser() {

        Customer customer = new Customer(1, "Jhon Duo", LocalDate.now().minusYears(10), "MALE", 341318775);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customer);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("CustomerBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    //@JsonIgnoreProperties({"ssn", "gender"})
    class User {

        private Integer id;

        @Size(min = 2, message = "Name should have atleast 2 characters.")
        @JsonProperty("user_name")
        private String name;

        @Past(message = "Birth Date should be in the past.")
        @JsonProperty("birth_date")
        private LocalDate birthDate;

        @Pattern(regexp = "MALE|FEMALE")
        private String gender;

        @JsonIgnore // static filtering of fields from a bean.
        private Integer ssn;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString

    @JsonFilter("CustomerBeanFilter")
    class Customer {
        private Integer id;
        @JsonProperty("user_name")
        private String name;
        @JsonProperty("birth_date")
        private LocalDate birthDate;
        private String gender;
        private Integer ssn;
    }
}
