package com.mutantproject.validator;

import java.util.List;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;

public class MutantValidator {

    public static boolean isMutant(List<String> dna) throws IllegalArgumentException {
        int result = 0;
        if(dna.isEmpty()) {
            throw new IllegalArgumentException("Dna Input is null or empty");
        } else if(MutantEvaluator.checkMutantInput(dna)) {
            throw new IllegalArgumentException("Dna Input contains characters different than A, G, T or C");
        } else if (!MutantEvaluator.checkMatrixLength(dna)) {
            throw new IllegalArgumentException("Dna Input doesn't respond to a NxN matrix");
        } else {
            int length = dna.size();
            char[][] matrixDna = new char[length][length];
            String[] dnaArray = dna.stream().toArray(String[]::new);
            matrixDna = MatrixBuilder.buildMatrix(dnaArray, matrixDna, length);

            List<String> dnaVertical = ListsBuilder.buildVerticalListsFromMatrix(matrixDna);
            List<String> dnaDiagonal = ListsBuilder.buildDiagonalListsFromMatrix(matrixDna);

            result += MutantEvaluator.checkMutantGen(dna);
            result += MutantEvaluator.checkMutantGen(dnaVertical);
            result += MutantEvaluator.checkMutantGen(dnaDiagonal);
            return (result > 1);
    
        }
    }
    
}