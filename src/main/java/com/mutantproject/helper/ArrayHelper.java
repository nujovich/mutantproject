package com.mutantproject.helper;

public class ArrayHelper {
    public static char[] buildResult (char[] result, char c) {
        for (int i = 0; i < result.length; i++) {
            result[i] = c;
        }
        return result;
    }
}