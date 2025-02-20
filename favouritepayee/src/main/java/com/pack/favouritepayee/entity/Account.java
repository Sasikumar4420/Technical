package com.pack.favouritepayee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	private String accountName;
	private String ibanAccountNumber;
	private String bank;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIbanAccountNumber() {
		return ibanAccountNumber;
	}

	public void setIbanAccountNumber(String ibanAccountNumber) {
		this.ibanAccountNumber = ibanAccountNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account(Long accountId, String accountName, String ibanAccountNumber, String bank, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.ibanAccountNumber = ibanAccountNumber;
		this.bank = bank;
		this.customer = customer;
	}

	public Account() {
		super();
	}

}
