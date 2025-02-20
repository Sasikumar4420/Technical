package com.pack.favouritepayee.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pack.favouritepayee.dto.AccountRequestDto;
import com.pack.favouritepayee.dto.ResponseDto;
import com.pack.favouritepayee.service.CustomerService;
import com.pack.favouritepayee.util.ResponseData;

@SpringBootTest
class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController customerController;
	@Mock
	private CustomerService customerService;
	
	@Test
	void testAddFavouriteAccount() {
		AccountRequestDto requestDto=new AccountRequestDto("Obleon's bank","ES210000123456");
		ResponseDto responseDto= new ResponseDto(ResponseData.SUCCESSFULLY_ADDED_CODE,ResponseData.SUCCESSFULLY_ADDED_MESSAGE);
		Mockito.when(customerService.addAccount(anyLong(), any())).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response=customerController.addfavouriteAccount(1l, requestDto);
		assertEquals(ResponseData.SUCCESSFULLY_ADDED_CODE, response.getBody().getStatusCode());
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
}
