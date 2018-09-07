package com.sky.data.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.sky.common", "com.sky.data.process"})
public class DataProcessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessServiceApplication.class, args);
    }

}