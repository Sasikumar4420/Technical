package com.demo.stockmarket.dto;

import java.time.LocalDateTime;

public class TradeResponseDTO {
	private String stockName;
	private Integer units;
	private Double totalPrice;
	private LocalDateTime buyDate;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(LocalDateTime buyDate) {
		this.buyDate = buyDate;
	}

	public TradeResponseDTO() {
		super();
	}

	public TradeResponseDTO(String stockName, Integer units, Double totalPrice, LocalDateTime buyDate) {
		super();
		this.stockName = stockName;
		this.units = units;
		this.totalPrice = totalPrice;
		this.buyDate = buyDate;
	}

}
