package com.pack.voterdetails.exception;

public class InvaliInputException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public InvaliInputException(String errorCode,String message) {
		super(message);
		this.errorCode=errorCode;
		
	}
	
}
