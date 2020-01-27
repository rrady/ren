package com.ren.api.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RenExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RenException.class)
    public final ResponseEntity<RenResource> handleException(RenException ex) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());
        RenResource exceptionResponse = new RenResource(ex.getCode(), errorMessages);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        RenResource exceptionResource = new RenResource(Codes.INVALID_INPUT, errorMessages);

        return new ResponseEntity<>(exceptionResource, status);
    }
}