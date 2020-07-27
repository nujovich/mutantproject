package com.mutantproject.repository;

import static com.mongodb.client.model.Filters.eq;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mutantproject.model.Dna;

import org.bson.Document;
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

    public FindIterable<Document> findDnaByArray(Dna dna) {
        return mongOps.getCollection("dna_stats").find(eq("dna", dna.getDna()));
    }

    public Dna save(Dna dna) {
        return mongOps.save(dna);
    }
    
}