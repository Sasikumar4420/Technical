package com.demo.stockmarket.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.OrderRequestDTO;
import com.demo.stockmarket.service.OrderService;
import com.demo.stockmarket.util.Responses;

@SpringBootTest
class OrderControllerTest {
	
	@Mock
	private OrderService orderService;
	@InjectMocks
	private OrderController orderController;
	
	
	OrderRequestDTO requestDto=new OrderRequestDTO(1l, 1l, 10);
	ApiResponse response=new ApiResponse(Responses.ORDERING_STOCK_CODE,Responses.ORDERING_STOCK_MESSAGE);
	@Test
	void testOrderStock() {
		Mockito.when(orderService.orderStock(requestDto)).thenReturn(response);
		ResponseEntity<ApiResponse> responses=orderController.orderStock(requestDto);
		assertEquals(HttpStatus.CREATED, responses.getStatusCode());
		assertEquals(Responses.ORDERING_STOCK_CODE,responses.getBody().getCode());
	}
}
