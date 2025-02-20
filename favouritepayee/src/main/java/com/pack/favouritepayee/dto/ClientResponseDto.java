package com.pack.favouritepayee.dto;

public class ClientResponseDto {
	
	private String code;
	private String message;
	private String bankName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public ClientResponseDto(String code, String message, String bankName) {
		super();
		this.code = code;
		this.message = message;
		this.bankName = bankName;
	}
	public ClientResponseDto() {
		super();
	}
	
	
}
