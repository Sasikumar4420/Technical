package com.hcl.bankApplication.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate date;
	private String transactionType;
	@JsonIgnore
	private Double accountBalance;
	private String remarks;
	private Double transaction_amount;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
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
	public Double getAmount() {
		return accountBalance;
	}
	public void setAmount(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(Double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public Transactions(Long id, LocalDate date, String transactionType, Double accountBalance, String remarks,Double transaction_amount, User user) {
		super();
		this.id = id;
		this.date = date;
		this.transactionType = transactionType;
		this.accountBalance = accountBalance;
		this.remarks = remarks;
		this.transaction_amount=transaction_amount;
		this.user = user;
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
