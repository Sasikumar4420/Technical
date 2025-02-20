package com.apps.bankingapplication.service;

import com.apps.bankingapplication.dto.BeneficiaryAccountRequestDto;
import com.apps.bankingapplication.dto.BeneficiaryAccountResponseDto;

public interface BeneficiaryAccountService {

	public BeneficiaryAccountResponseDto addBeneficiaryAccounts(Long customerId,
			BeneficiaryAccountRequestDto beneficiaryAccountRequestDto);
}