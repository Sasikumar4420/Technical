package com.apps.bankingapplication.dto;

import lombok.Data;

@Data
public class CustomerRequestDto {

	private Long customerId;
	private String password;
}
