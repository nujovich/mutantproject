package com.mutantproject.builder;

public class MatrixBuilder {
    public static char[][] buildMaxtrix(String[] dna, char[][] matrixDna, int matrixLength) {
        int i = 0;
        do {
            for (String str : dna) {
                char[] strChar = str.toCharArray();
                for (int j = 0; j < strChar.length; j++) {
                    matrixDna[i][j] = strChar[j];
                }
                i=+1;
            }
        } while (i < matrixLength);
        return matrixDna;
    }
}