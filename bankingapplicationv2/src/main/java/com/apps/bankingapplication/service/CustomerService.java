package com.apps.bankingapplication.service;

import com.apps.bankingapplication.dto.CustomerRequestDto;
import com.apps.bankingapplication.dto.CustomerResponseDto;

public interface CustomerService {

	 public CustomerResponseDto authenticate(CustomerRequestDto customerRequestDto);

	public CustomerResponseDto changePassword(Long userId,String newPassword);


}
