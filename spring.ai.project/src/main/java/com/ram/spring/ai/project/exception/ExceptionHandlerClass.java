package com.ram.spring.ai.project.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException(Exception exception, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorMessage, HttpStatus.OK);
	}

}