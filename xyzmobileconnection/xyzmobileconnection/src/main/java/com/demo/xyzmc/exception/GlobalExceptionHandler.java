package com.demo.xyzmc.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.demo.xyzmc.dto.ApiResponse;
import com.demo.xyzmc.dto.ValidationDTO;
import com.demo.xyzmc.util.Responses;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationDTO> handllerMethodAurgumentException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		ValidationDTO validationMessage = new ValidationDTO();
		validationMessage.setValidationErrors(errorMap);
		validationMessage.setErrorCode("EX011");
		validationMessage.setErrorMessage("please add valid input data");
		return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ValidationDTO> handlerMethodValidationException(HandlerMethodValidationException ex,
			HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("value", "value must  be greater than 1");
		ValidationDTO validationMessage = new ValidationDTO();
		validationMessage.setValidationErrors(errorMap);
		validationMessage.setErrorCode("VE001");
		validationMessage.setErrorMessage("Valid details needed");
		return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse> messageNotReadableHandler(HttpMessageNotReadableException ex) {
		ApiResponse error = new ApiResponse("EX1006", "input cannot be null");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RequestNotFoundException.class)
	public ResponseEntity<ApiResponse> accountNotFoundExceptionHandler(RequestNotFoundException ex) {
		ApiResponse error = new ApiResponse(Responses.REQUEST_NOT_FOUND_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoAvailableMobileNumberException.class)
	public ResponseEntity<ApiResponse> noAvailableMobileNumberExceptionHandler(NoAvailableMobileNumberException ex) {
		ApiResponse error = new ApiResponse(Responses.NO_AVAILABLE_NUMBERS_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AlreadyRequestedException.class)
	public ResponseEntity<ApiResponse> alreadyRequestedExceptionHandler(AlreadyRequestedException ex) {
		ApiResponse error = new ApiResponse(Responses.NO_AVAILABLE_NUMBERS_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AlreadyConnectionEnabledException.class)
	public ResponseEntity<ApiResponse> alreadyConnectionEnabledExceptionHandler(AlreadyConnectionEnabledException ex) {
		ApiResponse error = new ApiResponse(Responses.ALREADY_REQUESTED_CONNECTION_ENABLED_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidTalkTimePlanException.class)
	public ResponseEntity<ApiResponse> invalidTalkTimePlanExceptionHandler(InvalidTalkTimePlanException ex) {
		ApiResponse error = new ApiResponse(Responses.INVALID_TALKTIME_PLAN_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
