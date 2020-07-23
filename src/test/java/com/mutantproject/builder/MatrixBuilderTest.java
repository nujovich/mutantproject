package com.mutantproject.builder;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MatrixBuilderTest {

    private String[] dna = {"CCCT", "CTAT", "GGTT", "AATT"};
    private char[][] matrixDna = {{'C', 'C', 'C', 'T'},
    {'C', 'T', 'A', 'T'}, {'G', 'G', 'T', 'T'}, {'A', 'A', 'T', 'T'}};
    private int length = 4;

    @Test
    public void buildMatrix_shouldReturnTwoDimArray() {
        char[][] matrixDnaEmpty = new char[length][length];
        assertArrayEquals(matrixDna, MatrixBuilder.buildMatrix(dna, matrixDnaEmpty, length));
    }
    
}