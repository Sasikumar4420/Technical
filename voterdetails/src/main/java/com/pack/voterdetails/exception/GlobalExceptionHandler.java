package com.pack.voterdetails.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.pack.voterdetails.dto.ValidationDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> messageNotReadableHandler(HttpMessageNotReadableException ex) {
		ErrorResponse error = new ErrorResponse("CC1006", "input cannot be null");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ValidationDto> handlerMethodValidationException(HandlerMethodValidationException ex,
			HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("userId", "user id must be greater than 1");
		ValidationDto validationMessage = new ValidationDto();
		validationMessage.setValidationErrors(errorMap);
		validationMessage.setErrorCode("WP011");
		validationMessage.setErrorMessage("please add valid input data");
		return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationDto> handllerMethodAurgumentException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		ValidationDto validationMessage = new ValidationDto();
		validationMessage.setValidationErrors(errorMap);
		validationMessage.setErrorCode("CC011");
		validationMessage.setErrorMessage("please add valid input data");
		return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvaliInputException.class)
	public ResponseEntity<ErrorResponse> userExceptionHandler(InvaliInputException ex) {
		ErrorResponse error = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(DataNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}
