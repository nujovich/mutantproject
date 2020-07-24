package com.mutantproject.builder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class MatrixBuilderTest {

    @InjectMocks
    private MatrixBuilder matrixBuilder;

    private String[] dna = {"CCCT", "CTAT", "GGTT", "AATT"};
    private char[][] matrixDna = {{'C', 'C', 'C', 'T'},
    {'C', 'T', 'A', 'T'}, {'G', 'G', 'T', 'T'}, {'A', 'A', 'T', 'T'}};
    private int length = 4;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void buildMatrix_shouldReturnTwoDimArray() {
        char[][] matrixDnaEmpty = new char[length][length];
        assertArrayEquals(matrixDna, matrixBuilder.buildMatrix(dna, matrixDnaEmpty, length));
    }
    
}