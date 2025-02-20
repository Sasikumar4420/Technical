package com.apps.bankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.bankingapplication.dto.CustomerRequestDto;
import com.apps.bankingapplication.dto.CustomerResponseDto;
import com.apps.bankingapplication.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/login")

	public ResponseEntity<CustomerResponseDto> authenticate(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
		CustomerResponseDto customerResponseDto = customerService.authenticate(customerRequestDto);
		return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
	}

	@PostMapping("/change password")
	public ResponseEntity<CustomerResponseDto> changePassword(@RequestParam Long userId,
			@RequestParam String newPassword) {
		CustomerResponseDto customerResponseDto = customerService.changePassword(userId, newPassword);
		return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
	}

}