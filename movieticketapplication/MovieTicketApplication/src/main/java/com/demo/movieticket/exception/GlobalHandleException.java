package com.demo.movieticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandleException {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1001");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

	@ExceptionHandler(UserNotBookedShowException.class)
	public ResponseEntity<ErrorResponse> userNotBookedShowException(UserNotBookedShowException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1005");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

	@ExceptionHandler(ShowNotFoundException.class)
	public ResponseEntity<ErrorResponse> showNotFoundException(ShowNotFoundException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1002");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

	@ExceptionHandler(AlreadyBookedException.class)
	public ResponseEntity<ErrorResponse> alreadyBookedException(AlreadyBookedException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1004");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

	@ExceptionHandler(AlreadyReservedException.class)
	public ResponseEntity<ErrorResponse> alreadyReservedException(AlreadyReservedException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1003");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	@ExceptionHandler(SeatNotFoundException.class)
	public ResponseEntity<ErrorResponse> seatNotFoundException(SeatNotFoundException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1006");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}
	@ExceptionHandler(TicketUnderReservationException.class)
	public ResponseEntity<ErrorResponse> ticketUnderReservationException(TicketUnderReservationException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode("EX1007");
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

}
