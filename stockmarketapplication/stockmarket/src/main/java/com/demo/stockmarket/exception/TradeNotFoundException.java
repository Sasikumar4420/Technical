package com.demo.stockmarket.exception;

public class TradeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeNotFoundException(String message) {
		super(message);
	}

}
