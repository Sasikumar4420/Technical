package com.hcl.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MonthlyTransactions {
	@JsonIgnore
	private String monthAndYear;
	private Double openingBalance;
	private Double closingBalance;
	private Double totalCredited;
	private Double totalDebited;
	public String getMonthAndYear() {
		return monthAndYear;
	}
	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
	public Double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public Double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}
	public Double getTotalCredited() {
		return totalCredited;
	}
	public void setTotalCredited(Double totalCredited) {
		this.totalCredited = totalCredited;
	}
	public Double getTotalDebited() {
		return totalDebited;
	}
	public void setTotalDebited(Double totalDebited) {
		this.totalDebited = totalDebited;
	}
	public MonthlyTransactions(String monthAndYear, Double openingBalance, Double closingBalance, Double totalCredited,
			Double totalDebited) {
		super();
		this.monthAndYear = monthAndYear;
		this.openingBalance = openingBalance;
		this.closingBalance = closingBalance;
		this.totalCredited = totalCredited;
		this.totalDebited = totalDebited;
	}
	public MonthlyTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
