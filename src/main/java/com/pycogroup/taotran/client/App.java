package com.pycogroup.taotran.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableCircuitBreaker
@EnableAutoConfiguration
//@PropertySource(value = "application.yml")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
