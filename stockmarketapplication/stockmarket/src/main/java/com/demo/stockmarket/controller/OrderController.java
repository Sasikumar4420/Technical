package com.demo.stockmarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.OrderRequestDTO;
import com.demo.stockmarket.service.OrderService;

import jakarta.validation.Valid;

/**
 * 
 * @author mylapuru.komalnadh
 *
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Order Stock based on stock id and quantity
	 * 
	 * @param orderDto contains AccountId, StockId and quantity
	 * @return success code and message
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> orderStock(@RequestBody @Valid OrderRequestDTO orderDto) {
		logger.info("ordering the stock - Order Controller");
		ApiResponse response = orderService.orderStock(orderDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

}
