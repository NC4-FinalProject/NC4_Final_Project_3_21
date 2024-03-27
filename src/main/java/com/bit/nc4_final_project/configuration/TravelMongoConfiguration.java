package com.bit.nc4_final_project.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.bit.nc4_final_project.repository.travel", mongoTemplateRef = "mongoTemplate1")
public class TravelMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.authentication-database}")
    private String mongoAuthDatabase;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Value("${spring.data.mongodb.username}")
    private String mongoUsername;

    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;

    @Bean(name = "mongoTemplate1")
    public MongoTemplate mongoTemplate1() throws Exception {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress(mongoHost, mongoPort))))
                .credential(MongoCredential.createCredential(mongoUsername, mongoAuthDatabase, mongoPassword.toCharArray()))
                .build();

        MongoClient mongoClient = MongoClients.create(settings);

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, mongoDatabase));
    }
}

