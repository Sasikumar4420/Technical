package com.apps.bankingapplication.dto;

import lombok.Data;

@Data
public class CustomerResponseDto {

	/*
	 * private Long accountId;
	 * 
	 * private String accountName; private String iban;
	 * 
	 * private String bankName;
	 * 
	 * private Long customerId;
	 */
	
	private String statusCode;
    private String message;

}
