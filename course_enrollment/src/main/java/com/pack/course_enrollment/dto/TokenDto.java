package com.pack.course_enrollment.dto;

public class TokenDto {
	
	private String statusCode;
	private String statusMessage;
	private String token;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public TokenDto(String statusCode, String statusMessage, String token) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.token = token;
	}
	public TokenDto() {
		super();
	}
	
	
}
