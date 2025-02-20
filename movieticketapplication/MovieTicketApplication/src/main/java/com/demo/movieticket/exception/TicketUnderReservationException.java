package com.demo.movieticket.exception;

public class TicketUnderReservationException extends RuntimeException {
	public TicketUnderReservationException(String message) {
		super(message);
	}

}
