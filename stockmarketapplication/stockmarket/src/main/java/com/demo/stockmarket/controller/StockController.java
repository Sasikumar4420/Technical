package com.demo.stockmarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.StockResponseDTO;
import com.demo.stockmarket.service.StockService;
import com.demo.stockmarket.util.Responses;

/**
 * 
 * @author sooraj
 *
 */
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

	Logger logger = LoggerFactory.getLogger(getClass());
	private final StockService stockservice;

	public StockController(StockService stockservice) {
		super();
		this.stockservice = stockservice;
	}

	/**
	 * Get current price of stock
	 * 
	 * @param id get stock with id
	 * @return current price of stock with response code and message
	 */
	@GetMapping("/currentprice/{stock-id}")
	public ResponseEntity<ApiResponse> getStock(@PathVariable("stock-id") Long id) {
		logger.info(Responses.GET_CURRENT_PRICE_MESSAGE);
		return new ResponseEntity<>(stockservice.getStocks(id), HttpStatus.OK);
	}

	/**
	 * 
	 * Get previous 5 days price of stock
	 * 
	 * @param id get stock with id
	 * @return response message and code with list of previous 5 days price
	 */
	@GetMapping("/history/{stock-id}")
	public ResponseEntity<StockResponseDTO> getStocksHistory(@PathVariable("stock-id") Long id) {
		logger.info(Responses.GET_HISTORY_PRICE_MESSAGE);
		return new ResponseEntity<>(stockservice.getStockhistory(id), HttpStatus.OK);
	}

}
