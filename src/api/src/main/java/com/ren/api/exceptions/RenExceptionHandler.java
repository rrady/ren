package com.ren.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RenExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RenException.class)
    public final ResponseEntity<RenResource> handleException(RenException ex) {
        RenResource exceptionResponse = new RenResource(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = filterProvidedExceptionMessage(ex.getMessage());
        RenResource exceptionResource = new RenResource(Codes.INVALID_INPUT, message);
        return new ResponseEntity<>(exceptionResource, status);
    }

    private String filterProvidedExceptionMessage(String initialMessage) {
        int startPosition = initialMessage.lastIndexOf('[') + 1;
        int endPosition = initialMessage.lastIndexOf(']') - 1;

        return "Invalid input data: " + initialMessage.substring(startPosition, endPosition);
    }
}