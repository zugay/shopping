package com.gec.shopping;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppSearch {

    public static void main(String[] args) {
        SpringApplication.run(AppSearch.class,args);
    }
}
