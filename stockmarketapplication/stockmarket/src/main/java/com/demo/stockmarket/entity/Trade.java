package com.demo.stockmarket.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trades")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tradeId;
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stockId;
	private LocalDateTime buyDate;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account accountId;
	private Integer units;
	private Double totalPrice;
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public Stock getStockId() {
		return stockId;
	}
	public void setStockId(Stock stockId) {
		this.stockId = stockId;
	}
	public LocalDateTime getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(LocalDateTime buyDate) {
		this.buyDate = buyDate;
	}
	public Account getAccountId() {
		return accountId;
	}
	public void setAccountId(Account accountId) {
		this.accountId = accountId;
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
	public Trade(Long tradeId, Stock stockId, LocalDateTime buyDate, Account accountId, Integer units,
			Double totalPrice) {
		super();
		this.tradeId = tradeId;
		this.stockId = stockId;
		this.buyDate = buyDate;
		this.accountId = accountId;
		this.units = units;
		this.totalPrice = totalPrice;
	}
	public Trade() {
		super();
	}
	
	
}
