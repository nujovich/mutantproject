package com.mutantproject.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ListsBuilderTest {

    @InjectMocks
    private ListsBuilder listsBuilder;

    private char[][] matrixDna = {{'C', 'C', 'C', 'T'},
                                  {'C', 'T', 'A', 'T'}, 
                                  {'G', 'G', 'T', 'T'}, 
                                  {'A', 'A', 'T', 'T'}};
    private List<String> dnaVertical;
    private List<String> dnaDiagonal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dnaVertical = Arrays.asList("CCGA", "CTGA", "CATT", "TTTT");
        dnaDiagonal = Arrays.asList("C", "CC", "GTC", "AGAT", "ATT", "TT", "T", "A", "GA", "CGT", "CTTT", "CAT", "CT", "T");
    }

    @Test
    public void buildVerticalListsFromMatrix_shouldReturnVerticalList() {
        List<String> output = listsBuilder.buildVerticalListsFromMatrix(matrixDna);
        assertEquals(dnaVertical.size(), output.size());
        assertEquals(dnaVertical, output);
    }

    @Test
    public void buildDiagonalListsFromMatrix_shouldReturnDiagonalList() {
        List<String> output = listsBuilder.buildDiagonalListsFromMatrix(matrixDna);
        assertEquals(dnaDiagonal.size(), output.size());
        assertEquals(dnaDiagonal, output);
    }
    
}