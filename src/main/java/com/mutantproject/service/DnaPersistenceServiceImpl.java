package com.mutantproject.service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import com.mongodb.client.FindIterable;
import com.mutantproject.model.Dna;
import com.mutantproject.repository.DnaRepository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DnaPersistenceServiceImpl implements DnaPersistenceServiceIF {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    @Async
    public CompletableFuture<Dna> persistDna(Dna dna) {
        FindIterable<Document> dnaInDb = dnaRepository.findDnaByArray(dna);
        if(Objects.isNull(dnaInDb.first()) || dnaInDb.first().isEmpty()) {
            dnaRepository.save(dna);
        }
        return CompletableFuture.completedFuture(dna);
    }
    
}