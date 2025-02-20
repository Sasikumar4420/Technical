package com.hcl.bankApplication.dto;

import java.time.LocalDate;

public class TransactionDto {
	
	private Long id;
	private LocalDate date;
	private String transactionType;
	private String remarks;
	private Double transaction_amount;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(Double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
