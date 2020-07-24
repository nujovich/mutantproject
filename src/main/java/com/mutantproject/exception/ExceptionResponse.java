package com.mutantproject.exception;

public class ExceptionResponse {

    private String errorMessage;
    private String resourceRequested;
    private int errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResourceRequested() {
        return resourceRequested;
    }

    public void setResourceRequested(String resourceRequested) {
        this.resourceRequested = resourceRequested;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }
    
}