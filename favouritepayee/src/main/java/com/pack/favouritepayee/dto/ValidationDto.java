package com.pack.favouritepayee.dto;

import java.util.Map;

public class ValidationDto {
	private Map<String, String> validationErrors;
	private String errorCode;
	private String errorMessage;

	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ValidationDto(Map<String, String> validationErrors, String errorCode, String errorMessage) {
		super();
		this.validationErrors = validationErrors;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ValidationDto() {
		super();
	}

}
