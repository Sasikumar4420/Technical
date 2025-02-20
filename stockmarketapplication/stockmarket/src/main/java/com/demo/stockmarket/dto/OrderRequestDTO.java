package com.demo.stockmarket.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;


public class OrderRequestDTO {
	@Min(value = 1, message = "account id must be positive")
	private Long accountId;
	@Min(value = 1, message = "stock id must be positive")
	private Long stockId;
	@Min(value = 1, message = "quantity must be positive")
	private Integer units;
	
	private LocalDateTime date;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	

	public OrderRequestDTO(@Min(value = 1, message = "account id must be positive") Long accountId,
			@Min(value = 1, message = "stock id must be positive") Long stockId,
			@Min(value = 1, message = "quantity must be positive") Integer units, LocalDateTime date) {
		super();
		this.accountId = accountId;
		this.stockId = stockId;
		this.units = units;
		this.date = date;
	}

	public OrderRequestDTO() {
		super();
	}

}
