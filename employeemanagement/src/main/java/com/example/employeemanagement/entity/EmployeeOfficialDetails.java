package com.example.employeemanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeOfficialDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sapId;
	private String employeeMailId;
	private String accessCard;
	private String baseLocation;
	private String role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private EmployeePersonalDetails personalUserId;

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}

	public String getEmployeeMailId() {
		return employeeMailId;
	}

	public void setEmployeeMailId(String employeeMailId) {
		this.employeeMailId = employeeMailId;
	}

	public String getAccessCard() {
		return accessCard;
	}

	public void setAccessCard(String accessCard) {
		this.accessCard = accessCard;
	}

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public EmployeePersonalDetails getPersonalUserId() {
		return personalUserId;
	}

	public void setPersonalUserId(EmployeePersonalDetails personalUserId) {
		this.personalUserId = personalUserId;
	}

	public EmployeeOfficialDetails(Long sapId, String employeeMailId, String accessCard, String baseLocation,
			String role, EmployeePersonalDetails personalUserId) {
		super();
		this.sapId = sapId;
		this.employeeMailId = employeeMailId;
		this.accessCard = accessCard;
		this.baseLocation = baseLocation;
		this.role = role;
		this.personalUserId = personalUserId;
	}

	public EmployeeOfficialDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
