package com.demo.movieticket.exception;

public class ShowNotFoundException extends RuntimeException {
	public ShowNotFoundException(String message) {
		super(message);
	}

}
