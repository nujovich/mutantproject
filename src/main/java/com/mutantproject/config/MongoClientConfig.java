package com.mutantproject.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mutantproject.repository.DnaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = DnaRepository.class)
public class MongoClientConfig {

    @Bean
    public MongoClient mongo() {
        return MongoClients.create("mongodb+srv://nujovich:Cassandra.999@cluster0.xuefr.mongodb.net/MutantProject?retryWrites=true&w=majority");
    }

	@Bean
	public MongoOperations mongoTemplate() {
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongo(), "MutantProject"));
		return mongoOps;
	}
}