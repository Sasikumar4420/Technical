package com.demo.stockmarket.dto;

import java.util.List;

public class StockResponseDTO {
	private String message;
	private String code;
	private List<StockInfo> stockInfoList;

	// Getters and Setters

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<StockInfo> getStockInfoList() {
		return stockInfoList;
	}

	public void setStockInfoList(List<StockInfo> stockInfoList) {
		this.stockInfoList = stockInfoList;
	}

	public StockResponseDTO(String message, String code, List<StockInfo> stockInfoList) {
		super();
		this.message = message;
		this.code = code;
		this.stockInfoList = stockInfoList;
	}

	public StockResponseDTO() {
		super();
	}

}
