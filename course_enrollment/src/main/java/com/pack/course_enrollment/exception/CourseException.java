package com.pack.course_enrollment.exception;

public class CourseException extends RuntimeException {

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

	public CourseException(String errorCode,String message) {
		super(message);
		this.errorCode=errorCode;
	}
}
