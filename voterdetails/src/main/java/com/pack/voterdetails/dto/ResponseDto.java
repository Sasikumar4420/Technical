package com.pack.voterdetails.dto;

import org.springframework.data.domain.Page;

public class ResponseDto {
	private String statusCode;
	private String statusMessage;
	private Page<UserDto> userDto;

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

	public Page<UserDto> getUserDto() {
		return userDto;
	}

	public void setUserDto(Page<UserDto> userDto) {
		this.userDto = userDto;
	}

	public ResponseDto(String statusCode, String statusMessage, Page<UserDto> userDto) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.userDto = userDto;
	}

	public ResponseDto() {
		super();
	}

}
