package com.mutantproject.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mutantproject.model.Dna;
import com.mutantproject.mongo.codec.DnaCodec;
import com.mutantproject.repository.DnaRepository;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = DnaRepository.class)
public class MongoClientConfig {

    @Bean
    public MongoClient mongo() {
        CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        Codec<Document> documentCodec = codecRegistry.get(Document.class);
        Codec<Dna> dnaCodec = new DnaCodec(documentCodec);
        ConnectionString connectionString = new ConnectionString("mongodb+srv://nujovich:Cassandra.999@cluster0.xuefr.mongodb.net/MutantProject?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(codecRegistry, CodecRegistries.fromCodecs(documentCodec,dnaCodec));
        MongoClientSettings settings = MongoClientSettings.builder()
        .codecRegistry(pojoCodecRegistry)
        .applyConnectionString(connectionString)
        .build();
        return MongoClients.create(settings);
    }

    @Bean
	public MongoOperations mongoTemplate() {
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongo(), "MutantProject"));
		return mongoOps;
	}
}