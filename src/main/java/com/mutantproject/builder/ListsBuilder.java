package com.mutantproject.builder;

import java.util.List;
import java.util.ArrayList;

public class ListsBuilder {

    public static List<String> buildVerticalListsFromMatrix(char[][] matrixDna) {
        List<String> partialDna = new ArrayList<>();
        for (int i = 0; i < matrixDna.length; i++) {
            int iterator = 0;
            StringBuilder sb = new StringBuilder(matrixDna.length);
            while(iterator < matrixDna.length) {
                sb.append(matrixDna[iterator][i]);
                iterator+=1;
           } 
           partialDna.add(sb.toString());  
        }
        return partialDna;
    }

    public static List<String> buildDiagonalListsFromMatrix(char[][] matrixDna) {
        List<String> partialDna = new ArrayList<>();
        int length = matrixDna.length;
        int diagonalLines = (length*2) - 1;
        int itemsInDiagonal = 0;
        int midPoint = (diagonalLines / 2) + 1;

        //Run the matrix from top to bottom starting at index[0,0]
        for (int i = 1; i <= diagonalLines; i++) {
            StringBuilder sb = new StringBuilder();
            int rowIndex;
            int columnIndex;
            if (i <= midPoint) {
                itemsInDiagonal++;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (i - j) - 1;
                    columnIndex = j;
                    sb.append(matrixDna[rowIndex][columnIndex]);
                }
            } else {
                itemsInDiagonal--;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (length - 1) - j;
                    columnIndex = (i - length) + j;
                    sb.append(matrixDna[rowIndex][columnIndex]);
                }
            }
            if (i <= diagonalLines) {
                partialDna.add(sb.toString());
            } 
        }

        itemsInDiagonal = 0;

        //Run the matrix from bottom to top starting at index [length, 0]
        for (int i = 1; i <= diagonalLines; i++) {
            StringBuilder sb = new StringBuilder();
            int rowIndex;
            int columnIndex;
            if (i <= midPoint) {
                itemsInDiagonal++;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (length - i) + j;
                    columnIndex = j;
                    sb.append(matrixDna[rowIndex][columnIndex]);
                }
            } else {
                itemsInDiagonal--;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = j;
                    columnIndex = (i - length) + j;
                    sb.append(matrixDna[rowIndex][columnIndex]);
                }
            }
            if (i <= diagonalLines) {
                partialDna.add(sb.toString());
            } 
        }
        
        return partialDna;
    }
}