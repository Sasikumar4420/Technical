package com.apps.bankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.bankingapplication.dto.BeneficiaryAccountRequestDto;
import com.apps.bankingapplication.dto.BeneficiaryAccountResponseDto;
import com.apps.bankingapplication.service.BeneficiaryAccountServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
public class BeneficiaryAccountController {
	@Autowired
	private  BeneficiaryAccountServiceImpl beneficiaryAccountServiceImpl;
	

	@PostMapping("/customers/{customerId}/accounts")
	    public ResponseEntity<BeneficiaryAccountResponseDto> addBeneficiaryAccounts( @Valid @PathVariable Long customerId, @RequestBody BeneficiaryAccountRequestDto beneficiaryAccountRequestDto) {
		   BeneficiaryAccountResponseDto beneficiaryAccountResponseDto = beneficiaryAccountServiceImpl.addBeneficiaryAccounts(customerId,beneficiaryAccountRequestDto);
	        return new ResponseEntity<>(beneficiaryAccountResponseDto, HttpStatus.CREATED);
	    }
}
