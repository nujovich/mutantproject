package com.mutantproject.builder;

import org.springframework.stereotype.Component;

@Component
public class MatrixBuilder {
    public char[][] buildMatrix(String[] dna, char[][] matrixDna, int matrixLength) {
        int i = 0;
        do {
            for (String str : dna) {
                char[] strChar = str.toCharArray();
                for (int j = 0; j < strChar.length; j++) {
                    matrixDna[i][j] = strChar[j];
                }
                i++;
            }
        } while (i < matrixLength);
        return matrixDna;
    }
}