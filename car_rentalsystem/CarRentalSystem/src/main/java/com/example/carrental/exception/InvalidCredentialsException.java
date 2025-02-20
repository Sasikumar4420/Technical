package com.example.carrental.exception;

public class InvalidCredentialsException extends GlobalException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Invalid Password");
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(Long code, String message) {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(String message) {
		super(GlobalErrorCode.ERROR_UNAUTHOURIZED_USER, message);// TODO Auto-generated constructor stub
	}


	
	

}
