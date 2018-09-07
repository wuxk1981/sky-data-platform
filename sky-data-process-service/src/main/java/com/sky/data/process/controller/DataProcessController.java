package com.sky.data.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RefreshScope
public class DataProcessController {
    private static final Logger logger = LoggerFactory.getLogger(DataProcessController.class);

    @GetMapping(value = "/random")
    public Integer random() {
        logger.info(" >>> random");
        Random random = new Random();
        return random.nextInt(10);
    }
}
