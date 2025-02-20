package com.pack.parkingspot.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> EmployeeNotFoundExceptionHandller(EmployeeNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handllerMethodAurgumentException(MethodArgumentNotValidException ex){
		Map<String,String> errorMap=new HashMap<>();
		 ex.getBindingResult().getFieldErrors().forEach(error ->{
			 errorMap.put(error.getField(), error.getDefaultMessage());
		 });
		 return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> messageNotReadableHandler(HttpMessageNotReadableException ex){
		return new ResponseEntity<>("Please fill valid data",HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(SlotAlreadyReleasedException.class)
	public ResponseEntity<String> SlotAlreadyReleasedExceptionHandller(SlotAlreadyReleasedException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ParkingSlotNotFoundException.class)
	public ResponseEntity<String> ParkingSlotNotFoundExceptionHandller(ParkingSlotNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmployeeAlreadyAppliedException.class)
	public ResponseEntity<String> EmployeeAlreadyAppliedExceptionHandller(EmployeeAlreadyAppliedException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
