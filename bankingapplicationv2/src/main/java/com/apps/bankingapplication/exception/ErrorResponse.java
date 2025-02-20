package com.apps.bankingapplication.exception;

public class ErrorResponse {
	private String code;
	private String errorMsg;

	public String getCode() {
		return code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", errorMsg=" + errorMsg + "]";
	}

	public ErrorResponse(String code, String errorMsg) {
		super();
		this.code = code;
		this.errorMsg = errorMsg;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
