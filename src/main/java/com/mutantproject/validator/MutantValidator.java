package com.mutantproject.validator;

import java.util.List;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MutantValidator {

    private static final Logger logger = LogManager.getLogger(MutantValidator.class);

    @Autowired
    private MutantEvaluator mutantEvaluator;

    @Autowired
    private ListsBuilder listsBuilder;

    @Autowired
    private MatrixBuilder matrixBuilder;
    
    public boolean isMutant(List<String> dna) throws IllegalArgumentException {
        int result = 0;
        if(dna.isEmpty()) {
            logger.error("Dna input is empty.");
            throw new IllegalArgumentException("Dna Input is null or empty");
        } else if(mutantEvaluator.checkMutantInput(dna)) {
            logger.error("Dna input contains different characters than A, G, T, C.");
            throw new IllegalArgumentException("Dna Input contains characters different than A, G, T or C");
        } else if (!mutantEvaluator.checkMatrixLength(dna)) {
            logger.error("Dna Input doesn't respond to a NxN matrix");
            throw new IllegalArgumentException("Dna Input doesn't respond to a NxN matrix");
        } else {
            int length = dna.size();
            char[][] matrixDna = new char[length][length];
            String[] dnaArray = dna.stream().toArray(String[]::new);
            logger.info("Building matrix.");
            matrixDna = matrixBuilder.buildMatrix(dnaArray, matrixDna, length);

            logger.info("Building vertical & diagonal lists for checks.");
            List<String> dnaVertical = listsBuilder.buildVerticalListsFromMatrix(matrixDna);
            List<String> dnaDiagonal = listsBuilder.buildDiagonalListsFromMatrix(matrixDna);

            result += mutantEvaluator.checkMutantGen(dna);
            result += mutantEvaluator.checkMutantGen(dnaVertical);
            result += mutantEvaluator.checkMutantGen(dnaDiagonal);
  
            logger.info("Number of sequences found: " + result);
            return (result > 1);
    
        }
    }
    
}