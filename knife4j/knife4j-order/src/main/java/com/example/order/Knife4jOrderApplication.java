package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yongchen
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Knife4jOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Knife4jOrderApplication.class, args);
    }

}