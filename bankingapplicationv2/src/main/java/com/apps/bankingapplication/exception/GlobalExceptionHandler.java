package com.apps.bankingapplication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException ex) {

		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {

			errorMap.put(error.getField(), error.getDefaultMessage());

		});

		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidDetailsException.class)
	public ResponseEntity<ErrorResponse> InvalidDetailsException(InvalidDetailsException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1001");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

}
