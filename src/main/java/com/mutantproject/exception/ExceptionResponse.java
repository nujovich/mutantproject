package com.mutantproject.exception;

public class ExceptionResponse {

    private String errorMessage;
    private int errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }
    
}