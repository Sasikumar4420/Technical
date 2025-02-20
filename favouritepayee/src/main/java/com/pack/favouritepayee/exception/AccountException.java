package com.pack.favouritepayee.exception;

public class AccountException extends RuntimeException {

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

	public AccountException(String code,String message) {
		super(message);
		this.code=code;
	}
	
}
