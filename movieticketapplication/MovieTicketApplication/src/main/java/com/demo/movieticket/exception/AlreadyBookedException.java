package com.demo.movieticket.exception;

public class AlreadyBookedException extends RuntimeException {
	public AlreadyBookedException(String message) {
		super(message);
	}

}
