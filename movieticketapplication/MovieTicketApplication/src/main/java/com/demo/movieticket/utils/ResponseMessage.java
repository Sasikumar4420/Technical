package com.demo.movieticket.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseMessage {
	private static final String USER_NOT_FOUND_ERROR_CODE = "ERROR-1001";
	private final static String USER_NOT_FOUND = "User not found";
	private static final String USER_NOT_BOOKED_FOUND_ERROR_CODE = "ERROR-1005";
	private final static String USER_NOT_BOOKED_FOUND = "User not Booked any shows";
	private static final String SHOW_NOT_FOUND_ERROR_CODE = "ERROR-1002";
	private final static String SHOW_NOT_FOUND = "Show not found";
	private static final String ALREADY_BOOKED_FOUND_ERROR_CODE = "ERROR-1004";
	private final static String ALREADY_BOOKED_FOUND = "Show Already Booked";
	private static final String ALREADY_RESERVED_FOUND_ERROR_CODE = "ERROR-1003";
	private final static String ALREADY_RESERVED_FOUND = "Show Already Reserved";
	private static final String SEAT_NOT_FOUND_ERROR_CODE = "ERROR-1006";
	private final static String SEAT_NOT_FOUND = "Seat not found at this time please Enter Different Seats";
	private static final String TICKET_UNDER_RESERVATION_ERROR_CODE = "ERROR-1007";
	private final static String STICKET_UNDER_RESERVATION = "Seat are in under Reservation,Please Book after some time...";

	public String getUserNotFoundErrorCode() {
		return USER_NOT_FOUND_ERROR_CODE;
	}

	public String getUserNotFound() {
		return USER_NOT_FOUND;
	}

	public String getUserNotBookedFoundErrorCode() {
		return USER_NOT_BOOKED_FOUND_ERROR_CODE;
	}

	public String getUserNotBookedFound() {
		return USER_NOT_BOOKED_FOUND;
	}

	public String getShowNotFoundErrorCode() {
		return SHOW_NOT_FOUND_ERROR_CODE;
	}

	public String getShowNotFound() {
		return SHOW_NOT_FOUND;
	}

	public String getAlreadyBookedFoundErrorCode() {
		return ALREADY_BOOKED_FOUND_ERROR_CODE;
	}

	public String getAlreadyBookedFound() {
		return ALREADY_BOOKED_FOUND;
	}

	public String getAlreadyReservedFoundErrorCode() {
		return ALREADY_RESERVED_FOUND_ERROR_CODE;
	}

	public String getAlreadyReservedFound() {
		return ALREADY_RESERVED_FOUND;
	}

	public String getSeatNotFoundErrorCode() {
		return SEAT_NOT_FOUND_ERROR_CODE;
	}

	public String getSeatNotFound() {
		return SEAT_NOT_FOUND;
	}

	public  String getTicketUnderReservationErrorCode() {
		return TICKET_UNDER_RESERVATION_ERROR_CODE;
	}

	public  String getSticketUnderReservation() {
		return STICKET_UNDER_RESERVATION;
	}
	

}
