package com.mutantproject.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class MutantEvaluatorTest {

    private List<String> dnaNotAMatch;
    private List<String> dnaMatch;
    private List<String> dnaNotNxNMatrix;
    private List<String> dnaWrongInput;

    @InjectMocks
    private MutantEvaluator mutantEvaluator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dnaNotAMatch = Arrays.asList("CCCT", "CTAT", "GGTT", "AATT");
        dnaMatch = Arrays.asList("CCCC", "CTAT", "GGTT", "AATT");
        dnaNotNxNMatrix = Arrays.asList("CCCT", "CTA", "GGTT", "AATT");
        dnaWrongInput = Arrays.asList("CCCT", "CTAT", "GGTT", "AANN");
    }

    @Test 
    public void checkMutantGen_shouldReturnZeroMatchResult() {
       assertEquals(0, mutantEvaluator.checkMutantGen(dnaNotAMatch));
    } 

    @Test
    public void checkMutantGen_shouldReturnMatchResult() {
    assertEquals(1, mutantEvaluator.checkMutantGen(dnaMatch));
    }

    @Test
    public void checkMatrixLength_shouldReturnTrue() {
        assertTrue(mutantEvaluator.checkMatrixLength(dnaMatch));
    }

    @Test
    public void checkMatrixLength_shouldReturnFalse() {
        assertFalse(mutantEvaluator.checkMatrixLength(dnaNotNxNMatrix));
    }

    @Test
    public void checkMutantInput_shouldReturnTrue() {
        assertTrue(mutantEvaluator.checkMutantInput(dnaWrongInput));
    }

    @Test
    public void checkMutantInput_shouldReturnFalse() {
        assertFalse(mutantEvaluator.checkMutantInput(dnaMatch));
    }
    
}