package com.pack.favouritepayee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

@SpringBootTest
class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private FeignClient feignClient;

	Customer customer = new Customer(1l, "sasi", "sasi@email.com", "sasi@123");
	ClientResponseDto clientResponseDto = new ClientResponseDto(ResponseData.FEIGN_SUCCESSFULL_CODE,
			ResponseData.FEIGN_SUCCESSFULL_MESSAGE, "nairobi");
	ResponseEntity<ClientResponseDto> responseData = new ResponseEntity<>(clientResponseDto, HttpStatus.OK);
	Account account = new Account(1l, "yash'vanth", "ES210000000000001", "DenverBank", customer);
	AccountRequestDto requestDto = new AccountRequestDto("Obleon's bank", "ES210000123456");

	@Test
	void testAddFavouriteAccount() {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(feignClient.getBankName(anyString())).thenReturn(responseData);
		Mockito.when(accountRepository.findAllByCustomer(any())).thenReturn(accountList);
		Mockito.when(accountRepository.findByAccountNameAndCustomer(anyString(), any())).thenReturn(Optional.empty());
		Mockito.when(accountRepository.findByIbanAccountNumberAndCustomer(anyString(), any()))
				.thenReturn(Optional.empty());
		ResponseDto response = customerServiceImpl.addAccount(1l, requestDto);
		assertEquals(ResponseData.SUCCESSFULLY_ADDED_CODE, response.getStatusCode());
	}

	@Test
	void testIfCustomerNotFound() {
		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
		CustomerException exception = assertThrows(CustomerException.class,
				() -> customerServiceImpl.addAccount(1l, requestDto));
		assertEquals(ResponseData.CUSTOMER_NOTFOUND_CODE, exception.getCode());
	}
	@Test
	void testIfBankNotFound() {
		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		ClientResponseDto clientResponseDto = new ClientResponseDto(ResponseData.EXCEPTION_CODE_NO_BANK_FOUND,
				ResponseData.EXCEPTION_MESSAGE_NO_BANK_FOUND, "nairobi");
		ResponseEntity<ClientResponseDto> responseData = new ResponseEntity<>(clientResponseDto, HttpStatus.OK);
		Mockito.when(feignClient.getBankName(anyString())).thenReturn(responseData);
		IbanException exception = assertThrows(IbanException.class,
				() -> customerServiceImpl.addAccount(1l, requestDto));
		assertEquals(ResponseData.EXCEPTION_CODE_NO_BANK_FOUND, exception.getCode());
	}
	@Test
	void testIfCustomerReachedLimit() {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		accountList.add(new Account(2l, "yash'vanth", "ES210000000000001", "DenverBank", customer));
		accountList.add(new Account(3l, "yash'vanth", "ES210000000000001", "DenverBank", customer));
		accountList.add(new Account(4l, "yash'vanth", "ES210000000000001", "DenverBank", customer));
		accountList.add(new Account(5l, "yash'vanth", "ES210000000000001", "DenverBank", customer));
		accountList.add(new Account(6l, "yash'vanth", "ES210000000000001", "DenverBank", customer));

		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(feignClient.getBankName(anyString())).thenReturn(responseData);
		Mockito.when(accountRepository.findAllByCustomer(any())).thenReturn(accountList);
		AccountException exception = assertThrows(AccountException.class,
				() -> customerServiceImpl.addAccount(1l, requestDto));
		assertEquals(ResponseData.FAVOURITE_ACCOUNTS_LIMIT_EXCEEDED_CODE, exception.getCode());
	}
	@Test
	void testAccountNameAlreadyAlreadyAddedForCustomer() {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(feignClient.getBankName(anyString())).thenReturn(responseData);
		Mockito.when(accountRepository.findAllByCustomer(any())).thenReturn(accountList);
		Mockito.when(accountRepository.findByAccountNameAndCustomer(anyString(), any())).thenReturn(Optional.of(account));
		AccountException exception = assertThrows(AccountException.class,
				() -> customerServiceImpl.addAccount(1l, requestDto));
		assertEquals(ResponseData.ACCOUNT_NAME_ALREADY_USED_CODE, exception.getCode());
	}
	@Test
	void testAccountAlreadyAlreadyAddedForCustomer() {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		
		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(feignClient.getBankName(anyString())).thenReturn(responseData);
		Mockito.when(accountRepository.findAllByCustomer(any())).thenReturn(accountList);
		Mockito.when(accountRepository.findByAccountNameAndCustomer(anyString(), any())).thenReturn(Optional.empty());
		Mockito.when(accountRepository.findByIbanAccountNumberAndCustomer(anyString(), any()))
		.thenReturn(Optional.of(account));
		AccountException exception = assertThrows(AccountException.class,
				() -> customerServiceImpl.addAccount(1l, requestDto));
		assertEquals(ResponseData.ACCOUNT_ALREADY_ADDED_CODE, exception.getCode());
	}
	@Test
	void testIfFeignClientReturnNull() {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		ResponseEntity<ClientResponseDto> responseData = new ResponseEntity<>( HttpStatus.OK);
		Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(feignClient.getBankName(anyString())).thenReturn(responseData);
		Mockito.when(accountRepository.findAllByCustomer(any())).thenReturn(accountList);
		Mockito.when(accountRepository.findByAccountNameAndCustomer(anyString(), any())).thenReturn(Optional.empty());
		Mockito.when(accountRepository.findByIbanAccountNumberAndCustomer(anyString(), any()))
				.thenReturn(Optional.empty());
		ResponseDto response = customerServiceImpl.addAccount(1l, requestDto);
		assertEquals(ResponseData.SUCCESSFULLY_ADDED_CODE, response.getStatusCode());
	}
	
}
