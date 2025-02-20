package com.pack.librarymanagementsystem.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.pack.librarymanagementsystem.util.ErrorData;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> messageNotReadableHandler(HttpMessageNotReadableException ex){
		ErrorResponse errorResponse=new ErrorResponse("404", "please fill the valid data");
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<Map<String,String>> handlerMethodValidationException(HandlerMethodValidationException ex) {
		Map<String,String> errorMap=new HashMap<>();
		 errorMap.put("input data", "invalid format");
		 return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handllerMethodAurgumentException(MethodArgumentNotValidException ex){
		Map<String,String> errorMap=new HashMap<>();
		 ex.getBindingResult().getFieldErrors().forEach(error ->errorMap.put(error.getField(), error.getDefaultMessage())
		 );
		 return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> userNotFoundExceptionHandller(UserNotFoundException ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.userNotFoundErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> bookNotFoundExceptionHandller(BookNotFoundException ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.bookNotFoundErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BookAlreadyReturnedException.class)
	public ResponseEntity<ErrorResponse> bookAlreadyReturnedExceptionHandller(BookAlreadyReturnedException ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.bookAreadyReturnedErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserAndBookMismatchException.class)
	public ResponseEntity<ErrorResponse> userAndBookMismatchExceptionHandller(UserAndBookMismatchException ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.borrowedBookAndReturnUserMismatchErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(NoBorrowingHappensInThisMonthAndYear.class)
	public ResponseEntity<ErrorResponse> noBorrowingHappensInThisMonthAndYearExceptionHandller(NoBorrowingHappensInThisMonthAndYear ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.noBorrowingsInThisMonthErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
	}
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> invalidInputExceptionHandller(InvalidInputException ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.invalidInputErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ContentNotFoundInThisPageException.class)
	public ResponseEntity<ErrorResponse> contentNotFoundInThisPageExceptionHandller(ContentNotFoundInThisPageException ex){
		ErrorResponse errorResponse=new ErrorResponse(ErrorData.conentNotFoundInThisPageErrorCode, ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
	}
}
