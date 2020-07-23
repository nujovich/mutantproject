package com.mutantproject.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MutantEvaluatorTest {

    private List<String> dnaNotAMatch;
    private List<String> dnaMatch;
    private List<String> dnaNotNxNMatrix;
    private List<String> dnaWrongInput;

    @BeforeEach
    public void setUp() {
        dnaNotAMatch = Arrays.asList("CCCT", "CTAT", "GGTT", "AATT");
        dnaMatch = Arrays.asList("CCCC", "CTAT", "GGTT", "AATT");
        dnaNotNxNMatrix = Arrays.asList("CCCT", "CTA", "GGTT", "AATT");
        dnaWrongInput = Arrays.asList("CCCT", "CTAT", "GGTT", "AANN");
    }

    @Test 
    public void checkMutantGen_shouldReturnZeroMatchResult() {
       assertEquals(0, MutantEvaluator.checkMutantGen(dnaNotAMatch));
    } 

    @Test
    public void checkMutantGen_shouldReturnMatchResult() {
    assertEquals(1, MutantEvaluator.checkMutantGen(dnaMatch));
    }

    @Test
    public void checkMatrixLength_shouldReturnTrue() {
        assertTrue(MutantEvaluator.checkMatrixLength(dnaMatch));
    }

    @Test
    public void checkMatrixLength_shouldReturnFalse() {
        assertFalse(MutantEvaluator.checkMatrixLength(dnaNotNxNMatrix));
    }

    private void assertFalse(boolean checkMatrixLength) {
    }

    @Test
    public void checkMutantInput_shouldReturnTrue() {
        assertTrue(MutantEvaluator.checkMutantInput(dnaWrongInput));
    }

    @Test
    public void checkMutantInput_shouldReturnFalse() {
        assertFalse(MutantEvaluator.checkMutantInput(dnaMatch));
    }
    
}