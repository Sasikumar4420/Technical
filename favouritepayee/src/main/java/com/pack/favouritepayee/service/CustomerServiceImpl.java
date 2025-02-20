package com.pack.favouritepayee.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pack.favouritepayee.dto.AccountRequestDto;
import com.pack.favouritepayee.dto.ClientResponseDto;
import com.pack.favouritepayee.dto.ResponseDto;
import com.pack.favouritepayee.entity.Account;
import com.pack.favouritepayee.entity.Customer;
import com.pack.favouritepayee.exception.AccountException;
import com.pack.favouritepayee.exception.CustomerException;
import com.pack.favouritepayee.exception.IbanException;
import com.pack.favouritepayee.repository.AccountRepository;
import com.pack.favouritepayee.repository.CustomerRepository;
import com.pack.favouritepayee.util.ResponseData;

@Service
public class CustomerServiceImpl implements CustomerService {
	Logger logger = LoggerFactory.getLogger(getClass());

	private final CustomerRepository customerRepository;
	private final AccountRepository accountRepository;
	private final FeignClient feignClient;

	public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository,
			FeignClient feignClient) {
		super();
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.feignClient = feignClient;
	}

	/**
	 * This method is used to add favourite account to the customer into the
	 * database
	 * 
	 * @param customerId
	 * @param accountRequestDto
	 * @return returns success message
	 */
	@Override
	public ResponseDto addAccount(Long customerId, AccountRequestDto accountRequestDto) {
		logger.info("Inside add account service class");
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
			logger.error("customer is not found");
			throw new CustomerException(ResponseData.CUSTOMER_NOTFOUND_CODE, ResponseData.CUSTOMER_NOTFOUND_MESSAGE);
		});
		Account favouriteAccount = new Account();
		String iban = accountRequestDto.getIbanAccountNumber().substring(4, 8);
		ClientResponseDto clientResponse = feignClient.getBankName(iban).getBody();
		if (clientResponse!= null) {
			if (clientResponse.getCode().equals(ResponseData.EXCEPTION_CODE_NO_BANK_FOUND)) {
				throw new IbanException(ResponseData.IBAN_NOTFOUND_CODE, ResponseData.IBAN_NOTFOUND_MESSAGE);
			}
			List<Account> getAccountsList = accountRepository.findAllByCustomer(customer);
			if (getAccountsList.size() > 5) {
				logger.error("the customer has reached his limit");
				throw new AccountException(ResponseData.FAVOURITE_ACCOUNTS_LIMIT_EXCEEDED_CODE,
						ResponseData.FAVOURITE_ACCOUNTS_LIMIT_EXCEEDED_MESSAGE);
			} else {
				Optional<Account> account = accountRepository
						.findByIbanAccountNumberAndCustomer(accountRequestDto.getIbanAccountNumber(), customer);
				if (account.isPresent()) {
					logger.error("Account is already added to the customer");
					throw new AccountException(ResponseData.ACCOUNT_ALREADY_ADDED_CODE,
							ResponseData.ACCOUNT_ALREADY_ADDED_MESSAGE);
				}

				account = accountRepository.findByAccountNameAndCustomer(accountRequestDto.getAccountName(), customer);
				if (account.isPresent()) {
					logger.error("account name is already exists");
					throw new AccountException(ResponseData.ACCOUNT_NAME_ALREADY_USED_CODE,
							ResponseData.ACCOUNT_NAME_ALREADY_USED_MESSAGE);
				} else {
					BeanUtils.copyProperties(accountRequestDto, favouriteAccount);
					favouriteAccount.setBank(clientResponse.getBankName());
					favouriteAccount.setCustomer(customer);

				}

			}
		}
		accountRepository.save(favouriteAccount);
		logger.info("account is successfully added");
		return new ResponseDto(ResponseData.SUCCESSFULLY_ADDED_CODE, ResponseData.SUCCESSFULLY_ADDED_MESSAGE);
	}

}
