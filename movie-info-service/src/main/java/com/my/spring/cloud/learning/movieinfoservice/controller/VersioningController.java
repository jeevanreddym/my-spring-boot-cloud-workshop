package com.my.spring.cloud.learning.movieinfoservice.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    /**
     *  using URI versioning (used by Twitter).
     */
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Tom");
    }
    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new PersonName("Tom", "Hardy"));
    }


    /**
     *  using Request Param (used by Amazon).
     */
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getPersonUsingRequestParam1() {
        return new PersonV1("Tom - V1");
    }
    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getPersonUsingRequestParam2() {
        return new PersonV2(new PersonName("Tom", "Hardy - V2"));
    }


    /**
     *  using Custom Request Headers (used by Microsoft).
     */
    @GetMapping(value = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getPersonUsingRequestHeader1() {
        return new PersonV1("Tom - V1");
    }
    @GetMapping(value = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getPersonUsingRequestHeader2() {
        return new PersonV2(new PersonName("Tom", "Hardy - V2"));
    }


    /**
     *  using Request Headers: Media Type Versioning (used by GitHub).
     */
    @GetMapping(value = "/person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonUsingAcceptHeaderV1() {
        return new PersonV1("Tom - V1");
    }
    @GetMapping(value = "/person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonUsingAcceptHeaderV2() {
        return new PersonV2(new PersonName("Tom", "Hardy - V2"));
    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class PersonV1 {
        String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class PersonV2 {
        PersonName name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class PersonName {
        String firstName;
        String lastName;
    }
}
