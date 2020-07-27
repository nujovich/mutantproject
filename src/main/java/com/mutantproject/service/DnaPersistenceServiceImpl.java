package com.mutantproject.service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import com.mongodb.client.FindIterable;
import com.mutantproject.model.Dna;
import com.mutantproject.repository.DnaRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DnaPersistenceServiceImpl implements DnaPersistenceServiceIF {

    private static final Logger logger = LogManager.getLogger(DnaPersistenceServiceImpl.class);

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    @Async
    public CompletableFuture<Dna> persistDna(Dna dna) {
        FindIterable<Document> dnaInDb = dnaRepository.findDnaByArray(dna);
        if(Objects.isNull(dnaInDb.first()) || dnaInDb.first().isEmpty()) {
            logger.info("The dna doesn't exist in db. Persisting it.");
            dna = dnaRepository.save(dna);
            logger.info("Persisted dna with id: " + dna.getId().toString() 
            + " Current Thread: " + Thread.currentThread().getId());
        } else {
            logger.info("Already exists in db");
        }
        return CompletableFuture.completedFuture(dna);
    }
    
}