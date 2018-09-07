package com.sky.capital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.sky.common", "com.sky.capital"})
public class CapitalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapitalServiceApplication.class, args);
    }

}