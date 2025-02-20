package com.demo.movieticket.exception;

public class UserNotBookedShowException extends RuntimeException {
	public UserNotBookedShowException(String message) {
		super(message);
	}


}
