package com.mutantproject.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private final String[] dna = { "TCCT", "CTAT", "GGTT", "AATT" };

    private final List<String> dnaMutant = Arrays.asList(dna);
    private List<String> listVertical;
    private List<String> listDiagonal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listVertical = Arrays.asList("TCGA", "CTGA", "CATT", "TTTT");
        listDiagonal = Arrays.asList("T", "CC", "GTC", "AGAT", "ATT", "TT", "T", "A", "GA",
            "CGT", "TTTT", "CAT", "CT", "T");
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
        int length = dnaMutant.size();
        char[][] empty = new char[length][length];
        char[][] matrixDna = { { 'T', 'C', 'C', 'T' }, { 'C', 'T', 'A', 'T' }, { 'G', 'G', 'T', 'T' },
        { 'A', 'A', 'T', 'T' } };

        given(mutantEvaluatorMock.checkMatrixLength(dnaMutant)).willReturn(true);
        matrixDna = matrixBuilderMock.buildMatrix(dna, empty, length);
        given(listsBuilderMock.buildDiagonalListsFromMatrix(matrixDna)).willReturn(listDiagonal);
        given(listsBuilderMock.buildVerticalListsFromMatrix(matrixDna)).willReturn(listVertical);
        given(mutantEvaluatorMock.checkMutantGen(dnaMutant)).willReturn(0);
        given(mutantEvaluatorMock.checkMutantGen(listVertical)).willReturn(1);
        given(mutantEvaluatorMock.checkMutantGen(listDiagonal)).willReturn(1);

        boolean isMutant = mutantValidatorMock.isMutant(dnaMutant);

        assertEquals(0, mutantEvaluatorMock.checkMutantGen(dnaMutant));
        assertEquals(1, mutantEvaluatorMock.checkMutantGen(listDiagonal));
        assertEquals(1, mutantEvaluatorMock.checkMutantGen(listVertical));
        assertTrue(isMutant);
        assertTrue(length > 0);

    }
}