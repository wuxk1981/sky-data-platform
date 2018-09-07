package com.sky.integral.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.sky.common", "com.sky.integral.mall"})
public class IntegralMallServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegralMallServiceApplication.class, args);
    }

}