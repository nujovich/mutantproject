package com.mutantproject.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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

    private List<String> dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private List<String> dnaHuman = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isMutant_shouldReturnTrue() {
        when(mutantValidatorMock.isMutant(dna)).thenReturn(true);

        assertTrue(mutantServiceImplMock.isMutant(dna));
    }

    @Test
    public void isMutant_shouldReturnFalse() {
        when(mutantValidatorMock.isMutant(dnaHuman)).thenReturn(false);
        
        assertFalse(mutantServiceImplMock.isMutant(dnaHuman));
    }

}