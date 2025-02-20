package com.pack.favouritepayee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.favouritepayee.dto.AccountRequestDto;
import com.pack.favouritepayee.dto.ResponseDto;
import com.pack.favouritepayee.service.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(getClass());
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	/**
	 * This method is used to add favorite account to the customer
	 * 
	 * @param customerId
	 * @param accountRequestDto
	 * @return responseDto with status as created
	 */
	@PostMapping("/accounts")
	public ResponseEntity<ResponseDto> addfavouriteAccount(
			@Min(value = 1, message = "customer id must be more than 1") @RequestHeader Long customerId,
			@RequestBody @Valid AccountRequestDto accountRequestDto) {
		logger.info("Inside add favourite account controller");
		ResponseDto response = customerService.addAccount(customerId, accountRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
