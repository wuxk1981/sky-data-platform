package com.sky.log.persist.config;

import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 * Created by lh on 2016/10/27.
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoClientUri;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        SimpleMongoDbFactory mongoDbFactory = null;
        try {
            mongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI(mongoClientUri));
            mongoDbFactory.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return mongoDbFactory;
    }
}
