package com.demo.stockmarket.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.stockmarket.dto.AccountResponseDTO;
import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.service.AccountService;
import com.demo.stockmarket.util.Responses;

@SpringBootTest
class AccountControllerTest {

	@Mock
	AccountService accountService;

	@InjectMocks
	AccountController accountController;

	ApiResponse apiResponse = new ApiResponse(Responses.ADD_CASH_CODE, Responses.ADD_CASH_MESSAGE);

	@Test
	void addCash() {
		when(accountService.addCash(1l, 500.0)).thenReturn(apiResponse);
		ResponseEntity<ApiResponse> response = accountController.addCash(1l, 500.0);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void viewData() {
		when(accountService.viewData(1l, 1, 1)).thenReturn(new AccountResponseDTO());
		ResponseEntity<AccountResponseDTO> responseDTO = accountController.viewData(1l, 1, 1);
		assertEquals(HttpStatus.OK, responseDTO.getStatusCode());

	}

}
