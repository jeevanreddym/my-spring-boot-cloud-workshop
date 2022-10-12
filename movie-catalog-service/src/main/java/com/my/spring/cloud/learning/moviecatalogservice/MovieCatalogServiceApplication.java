package com.my.spring.cloud.learning.moviecatalogservice;

//import com.my.spring.cloud.learning.movieinfoclient.client.MovieInfoProxy;
//import com.my.spring.cloud.learning.ratingsdataclient.client.RatingsDataProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients({
//        "com.my.spring.cloud.learning.movieinfoclient.client",
//        "com.my.spring.cloud.learning.ratingsdataclient.client"
//})
//@EnableFeignClients(basePackages = "com.my.spring.cloud.learning")
//@EnableFeignClients(basePackageClasses = {RatingsDataProxy.class, MovieInfoProxy.class})
//@EnableFeignClients(clients = {RatingsDataProxy.class, MovieInfoProxy.class})
public class MovieCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(3000); // 3 secs timeout.
        return new RestTemplate(clientHttpRequestFactory);
    }

}
