package com.mutantproject.evaluator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MutantEvaluator {

    public static int checkMutantGen(List<String> dna) {
        int result = (!dna.isEmpty()) ? dna.stream().filter(str -> str.matches(".*(A{4}|T{4}|G{4}|C{4}).*"))
        .collect(Collectors.toList()).size() : 0;
        return result;
    }

    public static boolean checkMutantInput(List<String> dna) {
        boolean hasWrongInput = (!dna.isEmpty()) ? dna.stream().anyMatch(str -> str.matches(".*[^TAGC].*")) : false; 
        return hasWrongInput;
    }

    public static boolean checkMatrixLength(List<String> dna) {
        String matchResult = dna.stream().filter(str -> str.length() != dna.size()).findAny().orElse(null);
        boolean isMatrixNxN = Objects.isNull(matchResult) ? true : false;
        return isMatrixNxN;
    
    }
}
