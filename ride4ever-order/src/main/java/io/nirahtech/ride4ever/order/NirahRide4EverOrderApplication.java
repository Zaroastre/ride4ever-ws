/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NirahRide4EverOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(NirahRide4EverOrderApplication.class, args);
     }
}