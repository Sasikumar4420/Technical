package com.demo.stockmarket.exception;

public class StockNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotFoundException(String message) {
		super(message);
	}

}
