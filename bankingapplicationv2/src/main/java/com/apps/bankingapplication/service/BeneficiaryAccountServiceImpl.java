package com.apps.bankingapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.bankingapplication.dto.BeneficiaryAccountRequestDto;
import com.apps.bankingapplication.dto.BeneficiaryAccountResponseDto;
import com.apps.bankingapplication.entity.Bank;
import com.apps.bankingapplication.entity.BeneficiaryAccount;
import com.apps.bankingapplication.entity.Customer;
import com.apps.bankingapplication.exception.InvalidDetailsException;
import com.apps.bankingapplication.repository.BankRepository;
import com.apps.bankingapplication.repository.BeneficiaryAccountRepository;
import com.apps.bankingapplication.repository.CustomerRepository;
import com.apps.bankingapplication.utils.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeneficiaryAccountServiceImpl implements BeneficiaryAccountService {
	private CustomerRepository customerRepository;
	private BeneficiaryAccountRepository beneficiaryAccountRepository;
	private BankRepository bankRepository;

	@Autowired
	public BeneficiaryAccountServiceImpl(CustomerRepository customerRepository,
			BeneficiaryAccountRepository beneficiaryAccountRepository, BankRepository bankRepository) {
		this.customerRepository = customerRepository;
		this.beneficiaryAccountRepository = beneficiaryAccountRepository;
		this.bankRepository = bankRepository;

	}

	@Override
	public BeneficiaryAccountResponseDto addBeneficiaryAccounts(Long customerId,
			BeneficiaryAccountRequestDto beneficiaryAccountRequestDto) {

		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {

			throw new InvalidDetailsException(ResponseMessage.ERROR_MESSAGE_CUSTOMER_NOT_FOUND);

		}

		Optional<BeneficiaryAccount> beneficiaryAccount = beneficiaryAccountRepository
				.findByIban(beneficiaryAccountRequestDto.getIban());

		if (beneficiaryAccount.isPresent()) {

			throw new InvalidDetailsException(ResponseMessage.ERROR_MESSAGE_ACCOUNT_ALREADY_EXISTS);
		}

		List<BeneficiaryAccount> limitcheck = beneficiaryAccountRepository.findAllByCustomer(customer.get());
		if (limitcheck.size() >= 5) {

			throw new InvalidDetailsException(ResponseMessage.ERROR_MESSAGE_ACCOUNT_LIMIT_EXCEEDED);

		}

		String formatIban = beneficiaryAccountRequestDto.getIban().substring(4, 8);
		Optional<Bank> bankName = bankRepository.findByIban(formatIban);
		if (bankName.isEmpty()) {
			throw new InvalidDetailsException(ResponseMessage.ERROR_MESSAGE_ACCOUNT_LIMIT_EXCEEDED);
		}

		BeneficiaryAccountResponseDto beneficiaryAccountResponseDto = new BeneficiaryAccountResponseDto();

		BeneficiaryAccount beneficiaryaccounts = new BeneficiaryAccount();
		beneficiaryaccounts.setAccountName(beneficiaryAccountRequestDto.getAccountName());
		beneficiaryaccounts.setIban(beneficiaryAccountRequestDto.getIban());
		beneficiaryaccounts.setBankName(bankName.get().getBankName());
		beneficiaryaccounts.setCustomer(customer.get());

		beneficiaryAccountRepository.save(beneficiaryaccounts);

		beneficiaryAccountResponseDto.setStatusCode("200");
		beneficiaryAccountResponseDto.setMessage("Status updated successfully");
		return beneficiaryAccountResponseDto;

	}

}
