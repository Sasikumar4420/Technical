package com.example.carrental.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Long code;
	private  String message;
	
	public GlobalException(String message) {
		super(message);
	}
}
