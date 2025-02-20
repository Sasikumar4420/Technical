package com.apps.bankingapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.bankingapplication.dto.CustomerRequestDto;
import com.apps.bankingapplication.dto.CustomerResponseDto;
import com.apps.bankingapplication.entity.Customer;
import com.apps.bankingapplication.exception.InvalidDetailsException;
import com.apps.bankingapplication.repository.CustomerRepository;
import com.apps.bankingapplication.utils.ResponseMessage;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;

	}

	Customer customer = new Customer();
	CustomerResponseDto customerResponseDto = new CustomerResponseDto();

	@Override

	public CustomerResponseDto authenticate(CustomerRequestDto customerRequestDto) {
		Optional<Customer> customers = customerRepository.findById(customerRequestDto.getCustomerId());
		if (customers.isEmpty()) {

			throw new InvalidDetailsException(ResponseMessage.ERROR_MESSAGE_CUSTOMER_NOT_FOUND);

		}

		int mismatch = customers.get().getMismatch();
		if (mismatch > 3) {
			throw new InvalidDetailsException(ResponseMessage.ERROR_MESSAGE_PASSWORD_ATTEMPT_EXCEEDED);

		}

		if ((customerRequestDto.getPassword()) != (customers.get().getOldpassword())) {
			customers.get().setMismatch(mismatch + 1);
			if (mismatch <= 3) {
				customerResponseDto.setStatusCode("400");
				customerResponseDto.setMessage("Password incorrect");
			}

		}
		 else {

			customerResponseDto.setStatusCode("200");
			customerResponseDto.setMessage("Logged in successfully");
		}
		customerRepository.save(customers.get());

		return customerResponseDto;

	}

	public CustomerResponseDto changePassword(Long userId, String newPassword) {

		Optional<Customer> customers = customerRepository.findById(userId);

		if (customers.get().getMismatch() > 3 && newPassword != null) {

			String newpwd = customers.get().getNewpassword();
			customers.get().setOldpassword(newpwd);
			customers.get().setNewpassword(newPassword);
			customers.get().setMismatch(0);
			customerRepository.save(customers.get());

		}
		customerResponseDto.setStatusCode("200");
		customerResponseDto.setMessage("Password changed successfully");

		return customerResponseDto;
	}
}
