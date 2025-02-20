package com.demo.stockmarket.service;



import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.StockResponseDTO;


public interface StockService {

	ApiResponse getStocks(Long id);

	StockResponseDTO getStockhistory(Long id);
	

}
