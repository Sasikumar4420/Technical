package com.example.employeemanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class EmployeePersonalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Name cannot be empty")
	private String name;
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String mail;
	@NotBlank(message="Please enter a valid date")
	private LocalDate DOB;
	@DecimalMin(value = "1000000000", message = "please enter correct mobile number")
	@DecimalMax(value = "9999999999", message = "please enter correct mobile number")
	private Long phoneNumber;
	@DecimalMin(value = "100000000000", message = "please enter correct aadhar number")
	@DecimalMax(value = "999999999999", message = "please enter correct aadhar number")
	private Long aadharNumber;
	@NotBlank(message="Please enter a valid PAN Number")
	private String panNumber;
	@NotBlank(message="Experience cannot be empty")
	private Integer yearsOfExperience;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(Long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public EmployeePersonalDetails(Long id, String name, String mail, LocalDate dOB, Long phoneNumber,
			Long aadharNumber, String panNumber, Integer yearsOfExperience) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		DOB = dOB;
		this.phoneNumber = phoneNumber;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.yearsOfExperience = yearsOfExperience;
	}

	public EmployeePersonalDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
