package com.bit.nc4_final_project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bit.nc4_final_project.repository.chat", mongoTemplateRef = "mongoTemplate2")
public class ChatMongoConfiguration {

   @Value("${custom.mongodb.chat.uri}")
   private String mongoUri;

   @Bean(name = "mongoTemplate2")
   public MongoTemplate chatMongoTemplate() throws Exception {
       return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoUri));
   }
}

