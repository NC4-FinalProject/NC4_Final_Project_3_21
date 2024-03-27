// package com.bit.nc4_final_project.configuration;

// import java.util.Collections;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
// import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// import com.mongodb.MongoClientSettings;
// import com.mongodb.MongoCredential;
// import com.mongodb.ServerAddress;
// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;

// @Configuration
// @EnableMongoRepositories(basePackages = "com.bit.nc4_final_project.repository.chat", mongoTemplateRef = "mongoTemplate2")
// public class ChatMongoConfiguration {
//     @Value("${custom.mongodb.chat.host}")
//     private String mongoHost;

//     @Value("${custom.mongodb.chat.port}")
//     private int mongoPort;

//     @Value("${custom.mongodb.chat.authentication-database}")
//     private String mongoAuthDatabase;

//     @Value("${custom.mongodb.chat.database}")
//     private String mongoDatabase;

//     @Value("${custom.mongodb.chat.username}")
//     private String mongoUsername;

//     @Value("${custom.mongodb.chat.password}")
//     private String mongoPassword;

//     @Bean(name = "mongoTemplate2")
//     public MongoTemplate mongoTemplate2() throws Exception {
//         MongoClientSettings settings = MongoClientSettings.builder()
//                 .applyToClusterSettings(builder ->
//                         builder.hosts(Collections.singletonList(new ServerAddress(mongoHost, mongoPort))))
//                 .credential(MongoCredential.createCredential(mongoUsername, mongoAuthDatabase, mongoPassword.toCharArray()))
//                 .build();

//         MongoClient mongoClient = MongoClients.create(settings);

//         return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, mongoDatabase));
//     }

// }
