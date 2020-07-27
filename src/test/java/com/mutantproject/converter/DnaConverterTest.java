package com.mutantproject.converter;

import java.util.List;

import com.mutantproject.model.Dna;
import com.mutantproject.mongo.converter.DnaConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class DnaConverterTest {

    @InjectMocks
    private DnaConverter dnaConverterMock;

    private Dna dna;
    private Document doc;

    private List<String> listDna = Arrays.asList("TCGA", "CTGA", "CATT", "TTTT");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dna = new Dna(listDna, true);
        ObjectId id = new ObjectId("5f1ec11325accd038981dcbb");
        dna.setId(id);
        doc = new Document();
        doc.put("_id", dna.getId());
        doc.put("dna", dna.getDna());
        doc.put("isMutant", dna.isMutant());
        doc.put("_class", "com.mutantproject.model.Dna");
    }

    @Test
    public void convertToModel_shouldReturnDna() {
        Dna output = dnaConverterMock.convertToModel(doc);
        assertEquals(dna.getId(), output.getId());
    }

    @Test
    public void converToDocument_shouldReturnMongoDocument() {
        Document output = dnaConverterMock.convertToMongoDocument(dna);
        assertEquals(doc.get("_id"), output.get("_id"));
    }
    
}