package com.mutantproject.validator;

import java.util.List;

import javax.inject.Inject;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;

import org.springframework.stereotype.Component;


@Component
public class MutantValidator {

    @Inject
    private MutantEvaluator mutantEvaluator;

    @Inject
    private ListsBuilder listsBuilder;

    @Inject
    private MatrixBuilder matrixBuilder;
    
    public boolean isMutant(List<String> dna) throws IllegalArgumentException {
        int result = 0;
        if(dna.isEmpty()) {
            throw new IllegalArgumentException("Dna Input is null or empty");
        } else if(mutantEvaluator.checkMutantInput(dna)) {
            throw new IllegalArgumentException("Dna Input contains characters different than A, G, T or C");
        } else if (!mutantEvaluator.checkMatrixLength(dna)) {
            throw new IllegalArgumentException("Dna Input doesn't respond to a NxN matrix");
        } else {
            int length = dna.size();
            char[][] matrixDna = new char[length][length];
            String[] dnaArray = dna.stream().toArray(String[]::new);
            matrixDna = matrixBuilder.buildMatrix(dnaArray, matrixDna, length);

            List<String> dnaVertical = listsBuilder.buildVerticalListsFromMatrix(matrixDna);
            List<String> dnaDiagonal = listsBuilder.buildDiagonalListsFromMatrix(matrixDna);

            result += mutantEvaluator.checkMutantGen(dna);
            result += mutantEvaluator.checkMutantGen(dnaVertical);
            result += mutantEvaluator.checkMutantGen(dnaDiagonal);
            return (result > 1);
    
        }
    }
    
}