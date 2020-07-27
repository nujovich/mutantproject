package com.mutantproject.mongo.converter;

import com.mutantproject.model.Dna;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class DnaConverter implements ConverterIF<Dna> {

    @Override
    public Dna convertToModel(Document document) {
        Dna item = new Dna();
        item.setId(document.getObjectId("_id"));
        item.setDna(document.getList("dna", String.class));
        item.setMutant(document.getBoolean("isMutant"));
        return item;
    }

    @Override
    public Document convertToMongoDocument(Dna model) {
        Document document = new Document();
        document.put("_id", model.getId());
        document.put("dna", model.getDna());
        document.put("isMutant", model.isMutant());
        return document;
    }
    
    
}