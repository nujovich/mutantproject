package com.mutantproject.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.mutantproject.dto.DnaDto;
import com.mutantproject.service.MutantServiceIF;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MutantControllerTest {

    @InjectMocks
    private MutantController mutantControllerMock;

    @Mock
    private MutantServiceIF mutantServiceIFMock;

    private List<String> dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private List<String> dnaHuman = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isMutant_shouldThrowException() throws Exception {
        DnaDto requestBody = new DnaDto(this.dnaHuman);
        when(mutantServiceIFMock.isMutant(dnaHuman)).thenReturn(false);
        Exception ex = assertThrows(Exception.class, () -> {
            mutantControllerMock.isMutant(requestBody) ;
         });
 
        assertEquals("Not a mutant", ex.getMessage());

    }

    @Test
    public void isMutant_shouldReturnTrue() throws Exception {
        DnaDto requestBody = new DnaDto(this.dna);
        when(mutantServiceIFMock.isMutant(dna)).thenReturn(true);
        ResponseEntity<String> resp = mutantControllerMock.isMutant(requestBody);
        assertSame(resp.getStatusCode(), HttpStatus.OK);

    }


    

}