package com.pack.course_enrollment.dto;

public class ResponseDto {
	
	private String statusCode;
	private String statusMessage;
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
	public ResponseDto(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	public ResponseDto() {
		super();
	}
	
	
	
	
}
