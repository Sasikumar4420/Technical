package com.demo.stockmarket.dto;

import java.time.LocalDate;

public class StockInfo {
    private Long stockId;
    private LocalDate date;
    private Double unitPrice;

    // Getters and Setters

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

	public StockInfo(Long stockId, LocalDate date, Double unitPrice) {
		super();
		this.stockId = stockId;
		this.date = date;
		this.unitPrice = unitPrice;
	}

	public StockInfo() {
		super();
	}
    
}

