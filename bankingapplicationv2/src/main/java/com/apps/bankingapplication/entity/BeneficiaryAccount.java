package com.apps.bankingapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class BeneficiaryAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

	public BeneficiaryAccount(Long accountId, String accountName, String iban, String bankName, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.iban = iban;
		this.bankName = bankName;
		this.customer = customer;
	}

	private String accountName;
	private String iban;
	
	private String bankName;
 
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	public BeneficiaryAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


}
