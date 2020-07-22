package com.mutantproject.evaluator;

import com.mutantproject.helper.ArrayHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MutantEvaluator {

    public static int checkMutantGen(List<String> dna) {
        int result = (!dna.isEmpty()) ? dna.stream().filter(str -> str.matches("/A{4}|T{4}|G{4}|C{4}/g"))
        .collect(Collectors.toList()).size() : 0;
        return result;
    }
}