package com.pack.favouritepayee.exception;

public class CustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CustomerException(String code, String message) {
		super(message);
		this.code = code;
	}

}
