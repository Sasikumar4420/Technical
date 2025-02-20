package com.pack.favouritepayee.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pack.favouritepayee.dto.ValidationDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationDto> handllerMethodAurgumentException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		ValidationDto validationMessage = new ValidationDto();
		validationMessage.setValidationErrors(errorMap);
		validationMessage.setErrorCode("EX011");
		validationMessage.setErrorMessage("please add valid input data");
		return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> messageNotReadableHandler(HttpMessageNotReadableException ex) {
		ErrorResponse error = new ErrorResponse("EX1006", "input cannot be null");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorResponse> customerExceptionHandler(CustomerException ex) {
		ErrorResponse error = new ErrorResponse(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorResponse> accountExceptionHandler(AccountException ex) {
		ErrorResponse error = new ErrorResponse(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(IbanException.class)
	public ResponseEntity<ErrorResponse> ibanExceptionHandler(IbanException ex) {
		ErrorResponse error = new ErrorResponse(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
