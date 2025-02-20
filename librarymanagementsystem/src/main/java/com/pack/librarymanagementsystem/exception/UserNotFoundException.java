package com.pack.librarymanagementsystem.exception;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String errorCode="LIB003";

	public UserNotFoundException(String message) {
		super(message);
		
	}
	

}
