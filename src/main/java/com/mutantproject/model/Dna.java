package com.mutantproject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dna_stats")
public class Dna {
    @JsonProperty("_id")
    private ObjectId id;
    private List<String> dna;
    private boolean isMutant;

    public Dna(List<String> dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public Dna() {
        super();
	}

	public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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