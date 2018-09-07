package com.sky.add;

import com.sky.add.entity.Customers;
import com.sky.add.mapper.CustormersMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *  * @ClassName SwaggerDemoController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 10:23
 *  * @Version 1.0.0
 *  
 **/
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.sky.common", "com.sky.add"})
@EnableFeignClients
@ServletComponentScan
@MapperScan(value = "com.sky.add.mapper")
@EnableTransactionManagement // 开始事务支持
public class AddServiceApplication {
    @Autowired
    private CustormersMapper mapper;

    @PostConstruct
    public void test() {
        List<Customers> customers = mapper.selectList();
        System.out.println(customers);
    }

    public static void main(String[] args) {
        SpringApplication.run(AddServiceApplication.class, args);
    }

}