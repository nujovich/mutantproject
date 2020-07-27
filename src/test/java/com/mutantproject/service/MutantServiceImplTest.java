package com.mutantproject.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mutantproject.model.Dna;
import com.mutantproject.validator.MutantValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MutantServiceImplTest {
    
    @InjectMocks
    private MutantServiceImpl mutantServiceImplMock;

    @Mock
    private MutantValidator mutantValidatorMock;

    @Mock
    private DnaPersistenceServiceIF dnaPersistenceServiceIFMock;

    private List<String> dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private List<String> dnaHuman = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");
    private Dna dnaMutantEntity = new Dna(dna, true);
    private Dna dnaHumanEntity = new Dna(dna, false);

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isMutant_shouldReturnTrue() {
        when(mutantValidatorMock.isMutant(dna)).thenReturn(true);
        when(dnaPersistenceServiceIFMock.persistDna(dnaMutantEntity)).thenReturn(CompletableFuture.completedFuture(dnaMutantEntity));
        assertTrue(mutantServiceImplMock.isMutant(dna));
    }

    @Test
    public void isMutant_shouldReturnFalse() {
        when(mutantValidatorMock.isMutant(dnaHuman)).thenReturn(false);
        when(dnaPersistenceServiceIFMock.persistDna(dnaHumanEntity)).thenReturn(CompletableFuture.completedFuture(dnaHumanEntity));
        assertFalse(mutantServiceImplMock.isMutant(dnaHuman));
    }

}