package com.pack.favouritepayee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customeriD;
	private String customerName;
	private String email;
	private String password;

	public Long getCustomeriD() {
		return customeriD;
	}

	public void setCustomeriD(Long customeriD) {
		this.customeriD = customeriD;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer(Long customeriD, String customerName, String email, String password) {
		super();
		this.customeriD = customeriD;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
	}

	public Customer() {
		super();
	}

}
