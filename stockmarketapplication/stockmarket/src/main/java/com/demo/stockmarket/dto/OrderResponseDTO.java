package com.demo.stockmarket.dto;

import java.time.LocalDateTime;

public class OrderResponseDTO {
	private String stockName;
	private Integer units;
	private Double totalCost;
	private LocalDateTime orderedDate;

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

	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public OrderResponseDTO() {
		super();
	}

	public OrderResponseDTO(String stockName, Integer units, Double totalCost, LocalDateTime orderedDate) {
		super();
		this.stockName = stockName;
		this.units = units;
		this.totalCost = totalCost;
		this.orderedDate = orderedDate;
	}

}
