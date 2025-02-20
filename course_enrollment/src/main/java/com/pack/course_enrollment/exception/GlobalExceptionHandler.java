package com.pack.course_enrollment.exception;

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
	public ResponseEntity<Map<String,String>> handllerMethodAurgumentException(MethodArgumentNotValidException ex){
		Map<String,String> errorMap=new HashMap<>();
		 ex.getBindingResult().getFieldErrors().forEach(error ->errorMap.put(error.getField(), error.getDefaultMessage())
		 );
		 return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TraineeException.class)
	public ResponseEntity<ErrorResponse> traineeExceptionExceptionHandler(
			TraineeException ex) {
		ErrorResponse error=new ErrorResponse(ex.getErrorCode(),ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<ErrorResponse> courseExceptionExceptionHandler(
			CourseException ex) {
		ErrorResponse error=new ErrorResponse(ex.getErrorCode(),ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);	
	}
	@ExceptionHandler(EnrollmentException.class)
	public ResponseEntity<ErrorResponse> enrollmentExceptionHandler(
			EnrollmentException ex) {
		ErrorResponse error=new ErrorResponse(ex.getErrorCode(),ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);	
	}
}
