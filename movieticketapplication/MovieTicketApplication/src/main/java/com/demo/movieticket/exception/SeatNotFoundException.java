package com.demo.movieticket.exception;

public class SeatNotFoundException extends RuntimeException {
	public SeatNotFoundException(String message) {
		super(message);
	}
}
