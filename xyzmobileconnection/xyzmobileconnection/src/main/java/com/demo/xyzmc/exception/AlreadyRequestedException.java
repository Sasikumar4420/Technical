package com.demo.xyzmc.exception;

public class AlreadyRequestedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyRequestedException(String message) {
		super(message);
	}

}
