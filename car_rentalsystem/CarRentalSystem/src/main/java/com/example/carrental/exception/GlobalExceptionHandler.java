package com.example.carrental.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(GlobalException.class)
	protected ResponseEntity<ErrorResponse> handleGlobalException(GlobalException globalException, Locale locale) {
		return ResponseEntity.badRequest().body(
				ErrorResponse.builder().code(globalException.getCode()).message(globalException.getMessage()).build());
	}

	@ExceptionHandler(InvalidDetailsException.class)
	public ResponseEntity<ErrorResponse> InvalidDetailsException(InvalidDetailsException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(4001L);
		error.setMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
}
