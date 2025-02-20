package com.pack.parkingspot.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@Column(name="sap_code")
	private Long employeeSapCode;
	private String employeeName;
	private String employeeRole;
	private LocalDate joiningDate;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="parking_slot")
	private ParkingSlots parkingSlot;
	
	public Long getEmployeeSapCode() {
		return employeeSapCode;
	}
	public void setEmployeeSapCode(Long employeeSapCode) {
		this.employeeSapCode = employeeSapCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	public ParkingSlots getParkingSlot() {
		return parkingSlot;
	}
	public void setParkingSlot(ParkingSlots parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Long employeeSapCode, String employeeName, String employeeRole, LocalDate joiningDate,
			ParkingSlots parkingSlot) {
		super();
		this.employeeSapCode = employeeSapCode;
		this.employeeName = employeeName;
		this.employeeRole = employeeRole;
		this.joiningDate = joiningDate;
		this.parkingSlot = parkingSlot;
	}
	
	
}
