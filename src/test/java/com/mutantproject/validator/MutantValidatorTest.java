package com.mutantproject.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class) 
public class MutantValidatorTest {
    @InjectMocks
    private MutantValidator mutantValidatorMock;
    
    @Mock
    private MutantEvaluator mutantEvaluatorMock;

    @Mock
    private MatrixBuilder matrixBuilderMock;

    @Mock
    private ListsBuilder listsBuilderMock;

    private final List<String> emptyDna = new ArrayList<>();
    private final List<String> dnaWrongInput = Arrays.asList("CCCT", "CTAT", "GGTT", "AANN");
    private final List<String> dnaNotNxNMatrix = Arrays.asList("CCGAGG", "CTGA", "CATT", "TTGGG");

    private final String[] dnaMutant = { "TCCT", "CTAT", "GGTT", "AATT" };
    private final String[] dnaHuman = { "CCCT", "CTAT", "GGTT", "AATT" };

    private final List<String> listDnaMutant = Arrays.asList(dnaMutant);
    private final List<String> listDnaHuman = Arrays.asList(dnaHuman);
    private List<String> listVerticalMutant;
    private List<String> listDiagonalMutant;

    private List<String> listVerticalHuman;
    private List<String> listDiagonalHuman;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listVerticalMutant = Arrays.asList("TCGA", "CTGA", "CATT", "TTTT");
        listDiagonalMutant = Arrays.asList("T", "CC", "GTC", "AGAT", "ATT", "TT", "T", "A", "GA",
            "CGT", "TTTT", "CAT", "CT", "T");
        listVerticalMutant = Arrays.asList("CCGA", "CTGA", "CATT", "TTTT");
        listDiagonalHuman = Arrays.asList("C", "CC", "GTC", "AGAT", "ATT", "TT", "T", "A", "GA",
            "CGT", "CTTT", "CAT", "CT", "T");
        
    }

    @Test
    public void isMutant_withNullInput_shouldThrowIllegalArgumentException() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mutantValidatorMock.isMutant(emptyDna);
        });

        assertEquals("Dna Input is null or empty", ex.getMessage());
    }

    @Test
    public void isMutant_withWrongInput_shouldThrowIllegalArgumentException() {

        when(mutantEvaluatorMock.checkMutantInput(dnaWrongInput)).thenReturn(true);
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mutantValidatorMock.isMutant(dnaWrongInput);
        });

        assertEquals("Dna Input contains characters different than A, G, T or C", ex.getMessage());
    }

    @Test
    public void isMutant_withNoNxNMatrix_shouldThrowIllegalArgumentException() {
        when(mutantEvaluatorMock.checkMatrixLength(dnaNotNxNMatrix)).thenReturn(false);

        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mutantValidatorMock.isMutant(dnaNotNxNMatrix);
        });

        assertEquals("Dna Input doesn't respond to a NxN matrix", ex.getMessage());
    }

    @Test
    public void isMutant_withCorrectDnaInput_shouldReturnTrue() {
        int length = listDiagonalMutant.size();
        char[][] empty = new char[length][length];
        char[][] matrixDna = { { 'T', 'C', 'C', 'T' }, 
                                { 'C', 'T', 'A', 'T' }, 
                                { 'G', 'G', 'T', 'T' },
                                { 'A', 'A', 'T', 'T' } };

        given(mutantEvaluatorMock.checkMatrixLength(listDnaMutant)).willReturn(true);
        matrixDna = matrixBuilderMock.buildMatrix(dnaMutant, empty, length);
        given(listsBuilderMock.buildDiagonalListsFromMatrix(matrixDna)).willReturn(listDiagonalMutant);
        given(listsBuilderMock.buildVerticalListsFromMatrix(matrixDna)).willReturn(listVerticalMutant);
        given(mutantEvaluatorMock.checkMutantGen(listDnaMutant)).willReturn(0);
        given(mutantEvaluatorMock.checkMutantGen(listVerticalMutant)).willReturn(1);
        given(mutantEvaluatorMock.checkMutantGen(listDiagonalMutant)).willReturn(1);

        boolean isMutant = mutantValidatorMock.isMutant(listDnaMutant);

        assertEquals(0, mutantEvaluatorMock.checkMutantGen(listDnaMutant));
        assertEquals(1, mutantEvaluatorMock.checkMutantGen(listDiagonalMutant));
        assertEquals(1, mutantEvaluatorMock.checkMutantGen(listVerticalMutant));
        assertTrue(isMutant);
        assertTrue(length > 0);
    }

    @Test
    public void isMutant_withCorrectDnaInput_shouldReturnFalse() {
        int length = listDnaHuman.size();
        char[][] empty = new char[length][length];
        char[][] matrixDna = { { 'C', 'C', 'C', 'T' }, 
                                { 'C', 'T', 'A', 'T' }, 
                                { 'G', 'G', 'T', 'T' },
                                { 'A', 'A', 'T', 'T' } };

        given(mutantEvaluatorMock.checkMatrixLength(listDnaHuman)).willReturn(true);
        matrixDna = matrixBuilderMock.buildMatrix(dnaHuman, empty, length);
        given(listsBuilderMock.buildDiagonalListsFromMatrix(matrixDna)).willReturn(listDiagonalHuman);
        given(listsBuilderMock.buildVerticalListsFromMatrix(matrixDna)).willReturn(listVerticalHuman);
        given(mutantEvaluatorMock.checkMutantGen(listDnaHuman)).willReturn(0);
        given(mutantEvaluatorMock.checkMutantGen(listVerticalHuman)).willReturn(1);
        given(mutantEvaluatorMock.checkMutantGen(listDiagonalHuman)).willReturn(0);

        boolean isMutant = mutantValidatorMock.isMutant(listDnaHuman);

        assertEquals(0, mutantEvaluatorMock.checkMutantGen(listDnaHuman));
        assertEquals(0, mutantEvaluatorMock.checkMutantGen(listDiagonalHuman));
        assertEquals(1, mutantEvaluatorMock.checkMutantGen(listVerticalHuman));
        assertFalse(isMutant);
        assertTrue(length > 0);

    }
}