package com.bit.nc4_final_project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bit.nc4_final_project.repository.travel.mongo", mongoTemplateRef = "mongoTemplate1")
public class TravelMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Primary
    @Bean(name = "mongoTemplate1")
    public MongoTemplate mongoTemplate1() throws Exception {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoUri));
    }
}

