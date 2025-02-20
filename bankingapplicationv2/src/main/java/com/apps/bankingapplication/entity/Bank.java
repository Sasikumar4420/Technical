package com.apps.bankingapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankId;
	public Bank(Long bankId, String bankName, String iban) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.iban = iban;
	}
	private String bankName;
	private String iban;
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

}
