package com.example.carrental.exception;

import org.springframework.http.HttpStatus;

public class GlobalErrorCode {

	public static final  Long ERROR_RESOURCE_NOT_FOUND = 404L;
	public static final HttpStatus ERROR_RESOURCE_CONFLICT_EXISTS = HttpStatus.CONFLICT;
	public static final Long ERROR_UNAUTHOURIZED_USER = 401l;

}
