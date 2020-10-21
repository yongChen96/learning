package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yongchen
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigServiceApplication.class, args);
    }

}
