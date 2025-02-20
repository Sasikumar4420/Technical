package com.apps.bankingapplication.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apps.bankingapplication.controller.BeneficiaryAccountController;
import com.apps.bankingapplication.dto.BeneficiaryAccountRequestDto;
import com.apps.bankingapplication.dto.BeneficiaryAccountResponseDto;
import com.apps.bankingapplication.entity.Bank;
import com.apps.bankingapplication.entity.BeneficiaryAccount;
import com.apps.bankingapplication.entity.Customer;
import com.apps.bankingapplication.service.BeneficiaryAccountService;

@SpringBootTest
public class BeneficiaryAccountControllerTest {
@Mock
BeneficiaryAccountService beneficiaryAccountService;
@InjectMocks
BeneficiaryAccountController beneficiaryAccountController;


@Test
void testAddAccounts() {
	Bank bank= new Bank();
	bank.setBankId(1L);
	bank.setBankName("RBSI");
	bank.setIban("123111111111");
	BeneficiaryAccount beneficiaryAccount= new BeneficiaryAccount();
	
	Customer  customer = new Customer();
	customer.setCustomerId(1l);
	customer.setUserName("Sasi");
	customer.setPassword("Sasi");
	
	BeneficiaryAccountRequestDto beneficiaryAccountRequestDto= new BeneficiaryAccountRequestDto();
	beneficiaryAccountRequestDto.setAccountName("Suresh");
	beneficiaryAccountRequestDto.setIban("123111111111");
	
	beneficiaryAccount.setAccountId(1L);
	beneficiaryAccount.setAccountName("Suresh");
	beneficiaryAccount.setBankName("RBSI");
	beneficiaryAccount.setIban("123111111111");
	beneficiaryAccount.setCustomer(customer);
	List<BeneficiaryAccount>beneficiaryAccounts = new ArrayList<>();
	beneficiaryAccounts.add(beneficiaryAccount);
	
	
	
	BeneficiaryAccountResponseDto beneficiaryAccountResponseDto=new BeneficiaryAccountResponseDto();
	beneficiaryAccountResponseDto.setMessage("Status updated successfully");
    beneficiaryAccountResponseDto.setStatusCode("200");
    when(beneficiaryAccountService.addBeneficiaryAccounts(1l, beneficiaryAccountRequestDto)).thenReturn(beneficiaryAccountResponseDto);
	ResponseEntity<BeneficiaryAccountResponseDto> result = beneficiaryAccountController.addBeneficiaryAccounts(1L, beneficiaryAccountRequestDto);
	assertEquals(HttpStatus.OK, result.getStatusCode());
	
	
	
	
	
	
	
	
}


	
}
