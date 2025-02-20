package com.apps.bankingapplication.dto;



import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BeneficiaryAccountRequestDto {
	@NotBlank(message = "Account name is mandatory")
	private String accountName;
	@Length(min = 9, max = 20)
	private String iban;
}
