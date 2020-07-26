package com.mutantproject.repository;

import java.util.List;

import com.mutantproject.model.Dna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class DnaRepository {
    
    @Autowired
    private MongoOperations mongOps;

    public List<Dna> getAllDna() {
        return mongOps.findAll(Dna.class);
    }
    
}