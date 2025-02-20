package com.demo.stockmarket.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.ValidationDTO;
import com.demo.stockmarket.util.Responses;

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

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ApiResponse> accountNotFoundExceptionHandler(AccountNotFoundException ex) {
		ApiResponse error = new ApiResponse(Responses.ACCOUNT_NOT_FOUND_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<ApiResponse> stockNotFoundExceptionHandler(StockNotFoundException ex) {
		ApiResponse error = new ApiResponse(Responses.STOCK_NOT_FOUND_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ApiResponse> insufficientBalanceExceptionHandler(InsufficientBalanceException ex) {
		ApiResponse error = new ApiResponse(Responses.INSUFFICIENT_BALANCE_TO_ORDER_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TradeNotFoundException.class)
	public ResponseEntity<ApiResponse> tradeNotFoundExceptionHandler(TradeNotFoundException ex) {
		ApiResponse error = new ApiResponse(Responses.TRADE_NOT_FOUND_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ApiResponse> orderNotFoundExceptionHandler(OrderNotFoundException ex) {
		ApiResponse error = new ApiResponse(Responses.ORDER_NOT_FOUND_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PageException.class)
	public ResponseEntity<ApiResponse> pageExceptionHandler(PageException ex) {
		ApiResponse error = new ApiResponse(Responses.INSUFFICIENT_PAGE_VALUE_CODE, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
