package com.gec.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gec.shopping.mapper")
public class Appshop {
    public static void main(String[] args) {
        SpringApplication.run(Appshop.class,args);
    }
}