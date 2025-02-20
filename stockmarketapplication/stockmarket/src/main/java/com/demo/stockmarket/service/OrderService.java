package com.demo.stockmarket.service;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.OrderRequestDTO;

public interface OrderService {

	ApiResponse orderStock(OrderRequestDTO orderDto);

}
