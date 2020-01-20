package com.ren.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
public class RenExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RenException.class)
	public final ResponseEntity<RenResource> handleException(RenException ex, WebRequest request) {
		RenResource exceptionResponse = new RenResource(ex.getCode(), ex.getMessage());
		return new ResponseEntity<RenResource>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}