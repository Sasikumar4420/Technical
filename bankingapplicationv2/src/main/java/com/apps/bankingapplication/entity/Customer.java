package com.apps.bankingapplication.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Customer(Long customerId, String userName, String oldpassword, String newpassword, int mismatch,
			List<BeneficiaryAccount> beneficiaryAccounts) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
		this.mismatch = mismatch;
		this.beneficiaryAccounts = beneficiaryAccounts;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String userName;
	private String oldpassword;
	private String newpassword;
	private int mismatch;



	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<BeneficiaryAccount> beneficiaryAccounts = new ArrayList<>();

}
