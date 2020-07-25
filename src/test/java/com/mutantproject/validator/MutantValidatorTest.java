package com.mutantproject.validator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MutantValidatorTest {

    @InjectMocks
    private MutantValidator mutantValidatorMock;
    
    @Mock
    private MutantEvaluator mutantEvaluatorMock;

    @Mock
    private MatrixBuilder matrixBuilderMock;

    @Mock
    private ListsBuilder listsBuilderMock;

    private List<String> emptyDna = new ArrayList<>();
    private List<String> dnaWrongInput = Arrays.asList("CCCT", "CTAT", "GGTT", "AANN");
    private List<String> dnaNotNxNMatrix = Arrays.asList("CCGAGG", "CTGA", "CATT", "TTGGG");

    private String[] dna = {"TCCT", "CTAT", "GGTT", "AATT"};
    private char[][] matrixDna = {{'T', 'C', 'C', 'T'},
                                  {'C', 'T', 'A', 'T'}, 
                                  {'G', 'G', 'T', 'T'}, 
                                  {'A', 'A', 'T', 'T'}};
     char[][] matrixOuput = {{'T', 'C', 'C', 'T'},
                            {'C', 'T', 'A', 'T'}, 
                            {'G', 'G', 'T', 'T'}, 
                            {'A', 'A', 'T', 'T'}};
    private int length = 4;
    private List<String> listVertical = Arrays.asList("CCGA", "CTGA", "CATT", "TTTT");
    private List<String> listDiagonal = Arrays.asList("C", "CC", "GTC", "AGAT", "ATT", "TT", "T", "A", "GA", "CGT", "CTTT", "CAT", "CT", "T");
    private List<String> listHorizontal = Arrays.asList(dna);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isMutant_withNullInput_shouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
           mutantValidatorMock.isMutant(emptyDna) ;
        });

        assertEquals("Dna Input is null or empty", ex.getMessage());
    }

    @Test
    public void isMutant_withWrongInput_shouldThrowIllegalArgumentException() {
        
        when(mutantEvaluatorMock.checkMutantInput(dnaWrongInput)).thenReturn(true);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mutantValidatorMock.isMutant(dnaWrongInput);
         });

         assertEquals("Dna Input contains characters different than A, G, T or C", ex.getMessage());
    }

    @Test
    public void isMutant_withNoNxNMatrix_shouldThrowIllegalArgumentException() {
        when(mutantEvaluatorMock.checkMatrixLength(dnaNotNxNMatrix)).thenReturn(false);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mutantValidatorMock.isMutant(dnaNotNxNMatrix) ;
         });

         assertEquals("Dna Input doesn't respond to a NxN matrix", ex.getMessage());
    }

    @Test
    public void isMutant_withCorrectDnaInput_shouldReturnTrue() {
         char[][] matrixDnaEmpty = new char[length][length];

         when(mutantEvaluatorMock.checkMatrixLength(listHorizontal)).thenReturn(true);
         when(matrixBuilderMock.buildMatrix(dna, matrixDnaEmpty, length)).thenReturn(matrixOuput);
         when(listsBuilderMock.buildVerticalListsFromMatrix(matrixDna)).thenReturn(listVertical);
         when(listsBuilderMock.buildDiagonalListsFromMatrix(matrixDna)).thenReturn(listDiagonal);
         
         when(mutantEvaluatorMock.checkMutantGen(listHorizontal)).thenReturn(0);
         when(mutantEvaluatorMock.checkMutantGen(listDiagonal)).thenReturn(1);
         when(mutantEvaluatorMock.checkMutantGen(listVertical)).thenReturn(1);
        
         assertNotEquals(1, mutantEvaluatorMock.checkMutantGen(listHorizontal));
         assertEquals(1, mutantEvaluatorMock.checkMutantGen(listDiagonal));
         assertEquals(1, mutantEvaluatorMock.checkMutantGen(listVertical));

    }
}