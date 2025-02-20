package com.demo.xyzmc.exception;

public class RequestNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestNotFoundException(String message) {
		super(message);
	}

}
