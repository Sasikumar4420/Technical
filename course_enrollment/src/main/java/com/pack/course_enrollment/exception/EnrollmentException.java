package com.pack.course_enrollment.exception;

public class EnrollmentException extends RuntimeException{

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

	public EnrollmentException(String errorCode,String message) {
		super(message);
		this.errorCode=errorCode;
	}
}
