package com.sky.add.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "SKY-RANDOM-SERVICE")
public interface RandomService {
    @RequestMapping(method = RequestMethod.GET, value = "/random")
    Integer random();

    @RequestMapping(method = RequestMethod.GET, value = "/random1/{name}")
    Integer random1(@PathVariable("name") String name);
}