package com.apps.bankingapplication.dto;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class ResponseDto {
	private String statusCode;
	private String message;
	private Page<CustomerResponseDto> userResponseDto;

}
