package com.mutantproject;

import java.util.Arrays;
import java.util.List;

import com.mutantproject.builder.ListsBuilder;
import com.mutantproject.builder.MatrixBuilder;
import com.mutantproject.evaluator.MutantEvaluator;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String[] dna =  {"ATGCGACAGTGGATGCGACAGTGG","CAGTGCTCACTGATGCGACAGTGG","TTATGTAGGGGGATGCGACAGTGG","AGAAGGTTATTTATGCGACAGTGG","CCCCTACAGTGCTCACTGCCCCTA","TCACTGCCCCTATCACTGCCCCTA", "ATGCGACAGTGCTCACTGCCCCTA","CAGTGCTCACTG","TTATGTAGGGGG","AGAAGGTTATTT","CCCCTACAGTGC","TCACTGCCCCTA"};
        //String[] dna =  {"ATGCGA","CAGTGC","TTATTT","AGAGGG","GCGTCA","TCACTG"};
        int dnaLength = dna.length;
        boolean matrixNxN = MutantEvaluator.checkMatrixLength(Arrays.asList(dna));
        if(!matrixNxN) {
            throw new IllegalArgumentException("Not a matrix NxN");
        } 
        boolean hasWrongInput = MutantEvaluator.checkMutantInput(Arrays.asList(dna));
        if(hasWrongInput) {
            throw new IllegalArgumentException("The input contains characters distinct from A G C T");
        }
        char[][] matrixDna = new char[dnaLength][dnaLength];
        matrixDna = MatrixBuilder.buildMatrix(dna, matrixDna, dnaLength);
        List<String> dnaHorizontal = Arrays.asList(dna);
        List<String> dnaVertical = ListsBuilder.buildVerticalListsFromMatrix(matrixDna);
        List<String> dnaDiagonal = ListsBuilder.buildDiagonalListsFromMatrix(matrixDna);
        int resultHorizontal = MutantEvaluator.checkMutantGen(dnaHorizontal);
        int resultVertical = MutantEvaluator.checkMutantGen(dnaVertical);
        int resultDiagonal = MutantEvaluator.checkMutantGen(dnaDiagonal);
        boolean isMutant = (resultHorizontal >= 1 | resultDiagonal >= 1 | resultVertical >= 1) ? true : false;
        System.out.println("The result is: " + isMutant);

    }
}
