package com.demo.stockmarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockId;
	private String company;
	private Integer bseCode;
	private Double pe;
	private Double marketCap;
	private Double changePct;
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getBseCode() {
		return bseCode;
	}
	public void setBseCode(Integer bseCode) {
		this.bseCode = bseCode;
	}
	public Double getPe() {
		return pe;
	}
	public void setPe(Double pe) {
		this.pe = pe;
	}
	public Double getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}
	public Double getChangePct() {
		return changePct;
	}
	public void setChangePct(Double changePct) {
		this.changePct = changePct;
	}
	public Stock(Long stockId, String company, Integer bseCode, Double pe, Double marketCap, Double changePct) {
		super();
		this.stockId = stockId;
		this.company = company;
		this.bseCode = bseCode;
		this.pe = pe;
		this.marketCap = marketCap;
		this.changePct = changePct;
	}
	public Stock() {
		super();
	}
	
	
}
