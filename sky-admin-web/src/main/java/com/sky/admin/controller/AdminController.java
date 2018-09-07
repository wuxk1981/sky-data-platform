package com.sky.admin.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RefreshScope
@Api(description = "AdminController相关的API")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Integer random() {
        logger.info(" >>> random");
        Random random = new Random();
        return random.nextInt(10);
    }
}
