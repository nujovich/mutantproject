package com.mutantproject.evaluator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MutantEvaluator {

    public int checkMutantGen(List<String> dna) {
        return dna.stream().filter(str -> str.matches(".*(A{4}|T{4}|G{4}|C{4}).*"))
        .collect(Collectors.toList()).size();
    }

    public boolean checkMutantInput(List<String> dna) {
        return dna.stream().anyMatch(str -> str.matches(".*[^TAGC].*")); 

    }

    public boolean checkMatrixLength(List<String> dna) {
        String matchResult = dna.stream().filter(str -> str.length() != dna.size()).findAny().orElse(null);
        return Objects.isNull(matchResult);
    }
}
