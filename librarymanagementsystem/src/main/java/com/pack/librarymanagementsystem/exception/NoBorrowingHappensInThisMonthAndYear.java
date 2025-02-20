package com.pack.librarymanagementsystem.exception;

public class NoBorrowingHappensInThisMonthAndYear extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBorrowingHappensInThisMonthAndYear(String message) {
		super(message);
	}

}
