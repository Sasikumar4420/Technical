package com.pack.favouritepayee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountRequestDto {
	@NotNull(message = "accountName is required")
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\-']+$", message = "accountName can only contain alphanumeric characters, apostrophes, and hyphens")
	private String accountName;
	@NotBlank(message = "ibanAccountNumber is required")
	@Size(min = 12, max = 20, message = "ibanAccountNumber must be between 5 and 20 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name can only contain alphanumeric characters")
	private String ibanAccountNumber;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIbanAccountNumber() {
		return ibanAccountNumber;
	}

	public void setIbanAccountNumber(String ibanAccountNumber) {
		this.ibanAccountNumber = ibanAccountNumber;
	}

	public AccountRequestDto(String accountName, String ibanAccountNumber) {
		super();
		this.accountName = accountName;
		this.ibanAccountNumber = ibanAccountNumber;
	}

	public AccountRequestDto() {
		super();
	}

}
