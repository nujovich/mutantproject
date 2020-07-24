package com.mutantproject.exception;

public class ExceptionResponse {

    private String errorMessage;
    private String resourceRequested;

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
    
}