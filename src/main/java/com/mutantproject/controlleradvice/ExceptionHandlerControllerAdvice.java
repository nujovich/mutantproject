package com.mutantproject.controlleradvice;

import com.mutantproject.exception.ExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleIllegalArgumentException(final IllegalArgumentException exception, 
            final WebRequest request) {
        final ExceptionResponse ex = new ExceptionResponse();
        ex.setErrorMessage(exception.getMessage());
        ex.setResourceRequested(request.getContextPath());
        ex.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ex;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody ExceptionResponse handleException(final Exception exception, final WebRequest request) {
        final ExceptionResponse ex = new ExceptionResponse();
            ex.setErrorMessage(exception.getMessage());
            ex.setResourceRequested(request.getContextPath());
            return ex;
    }
    
}