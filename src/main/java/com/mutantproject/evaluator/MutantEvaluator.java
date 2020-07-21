package com.mutantproject.evaluator;

import com.mutantproject.helper.ArrayHelper;

public class MutantEvaluator {

    public static char[] checkMutantHorizontal(char[][] matrixDna, int start, int index) {
        int i= index, j=start, iterator = 1;
        char[] result = new char [4];
        while (iterator < 4) {
            if(matrixDna[i][j] == matrixDna[i][j+1]) {
                j+=1;
                iterator+=1;
            } else {
                break;
            }
        }
        if(iterator == 4) {
           result =  ArrayHelper.buildResult(result, matrixDna[i][j]);
        }
        return result;
    }

    public static char[] checkMutantVertical(char[][] matrixDna, int start, int index) {
        int i= start, j=index, iterator = 1;
        char[] result = new char [4];
        while (iterator < 4) {
            if(matrixDna[i][j] == matrixDna[i+1][j]) {
                i+=1;
                iterator+=1;
            } else {
                break;
            }
        }
        if(iterator == 4) {
           result =  ArrayHelper.buildResult(result, matrixDna[i][j]);
        }
        return result;
    }

    public static char[] checkMutantOblicual(char[][] matrixDna, int startI, int startJ) {
        int i= startI, j=startJ, iterator = 1;
        char[] result = new char [4];
        while (iterator < 4) {
            if(matrixDna[i][j] == matrixDna[i+1][j+1]) {
                i+=1;
                j+=1;
                iterator+=1;
            } else {
                break;
            }
        }
        if(iterator == 4) {
           result =  ArrayHelper.buildResult(result, matrixDna[i][j]);
        }
        return result;
    }
    
}