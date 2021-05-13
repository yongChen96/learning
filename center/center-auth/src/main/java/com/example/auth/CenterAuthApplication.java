package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CenterAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterAuthApplication.class, args);
    }

}
