package com.mutantproject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "dna_stats")
public class Dna {
    @MongoId
    @JsonProperty("_id")
    private String id;
    private List<String> dna;
    private boolean isMutant;

    public Dna(List<String> dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }

}