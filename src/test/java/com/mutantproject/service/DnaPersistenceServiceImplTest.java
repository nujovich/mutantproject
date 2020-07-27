package com.mutantproject.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;

import com.mongodb.client.FindIterable;
import com.mutantproject.model.Dna;
import com.mutantproject.repository.DnaRepository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DnaPersistenceServiceImplTest {

    @InjectMocks
    private DnaPersistenceServiceImpl dnaPersistenceServiceImplMock;

    @Mock
    private DnaRepository dnaRepositoryMock;

    private Dna dna;
    
    private List<String> listDna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private Document doc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dna = new Dna(listDna, true);
        ObjectId id = new ObjectId();
        dna.setId(id);
        doc = new Document();
        doc.put("_id", id);
        doc.put("dna", listDna);
        doc.put("isMutant", true);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void persistDna_shouldFindDocumentAndRetrieveDna() {
        FindIterable<Document> iterable = mock(FindIterable.class);
        when(dnaRepositoryMock.findDnaByArray(dna)).thenReturn(iterable);
        when(iterable.first()).thenReturn(doc);
        CompletableFuture<Dna> actual = dnaPersistenceServiceImplMock.persistDna(dna);
        assertNotNull(actual);
        assertFalse(iterable.first().isEmpty());

    }

    @Test
    @SuppressWarnings("unchecked")
    public void persistDna_shouldNotFindDocumentAndSaveDna() {
        FindIterable<Document> iterable = mock(FindIterable.class);
        when(dnaRepositoryMock.findDnaByArray(dna)).thenReturn(iterable);
        when(iterable.first()).thenReturn(null);
        when(dnaRepositoryMock.save(dna)).thenReturn(dna);
        CompletableFuture<Dna> actual = dnaPersistenceServiceImplMock.persistDna(dna);
        assertNotNull(actual);
        assertNull(iterable.first());

    }
}