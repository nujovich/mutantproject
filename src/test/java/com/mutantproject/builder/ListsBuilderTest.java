package com.mutantproject.builder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ListsBuilderTest {

    private char[][] matrixDna = {{'C', 'C', 'C', 'T'},
                                  {'C', 'T', 'A', 'T'}, 
                                  {'G', 'G', 'T', 'T'}, 
                                  {'A', 'A', 'T', 'T'}};
    private List<String> dnaVertical;
    private List<String> dnaDiagonal;

    @Before
    public void setUp() {
        dnaVertical = Arrays.asList("CCGA", "CTGA", "CATT", "TTTT");
        dnaDiagonal = Arrays.asList("C", "CC", "GTC", "AGAT", "ATT", "TT", "T", "A", "GA", "CGT", "CTTT", "CAT", "CT", "T");
    }

    @Test
    public void buildVerticalListsFromMatrix_shouldReturnVerticalList() {
        List<String> output = ListsBuilder.buildVerticalListsFromMatrix(matrixDna);
        assertEquals(dnaVertical.size(), output.size());
        assertEquals(dnaVertical, output);
    }

    @Test
    public void buildDiagonalListsFromMatrix_shouldReturnDiagonalList() {
        List<String> output = ListsBuilder.buildDiagonalListsFromMatrix(matrixDna);
        assertEquals(dnaDiagonal.size(), output.size());
        assertEquals(dnaDiagonal, output);
    }
    
}