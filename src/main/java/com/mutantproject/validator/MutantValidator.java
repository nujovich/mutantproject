package com.mutantproject.validator;

import java.util.List;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;

public class MutantValidator {

    public static boolean isMutant(List<String> dna) throws IllegalArgumentException {
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

            int resultHorizontal = MutantEvaluator.checkMutantGen(dna);
            int resultVertical = MutantEvaluator.checkMutantGen(dnaVertical);
            int resultDiagonal = MutantEvaluator.checkMutantGen(dnaDiagonal);

            boolean isMutant = (resultHorizontal >= 1 | resultDiagonal >= 1 | resultVertical >= 1) ? true : false;
            return isMutant;
        }
    }
    
}