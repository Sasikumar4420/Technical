package com.pack.parkingspot.entity;

import java.time.LocalDate;

import com.pack.parkingspot.enums.ApplicationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="slot_applications")
public class ParkingSpotApplications {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="emp_id")
	private Employee empid;
	private LocalDate appliedOn;
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="slot_id")
	private ParkingSlots parkingSlot;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getEmpid() {
		return empid;
	}
	public void setEmpid(Employee empid) {
		this.empid = empid;
	}
	public LocalDate getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(LocalDate appliedOn) {
		this.appliedOn = appliedOn;
	}
	public ApplicationStatus isStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	public ParkingSlots getParkingSlot() {
		return parkingSlot;
	}
	public void setParkingSlot(ParkingSlots parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	
	public ParkingSpotApplications(Integer id, Employee empid, LocalDate appliedOn, ApplicationStatus status,
			ParkingSlots parkingSlot) {
		super();
		this.id = id;
		this.empid = empid;
		this.appliedOn = appliedOn;
		this.status = status;
		this.parkingSlot = parkingSlot;
	}
	public ParkingSpotApplications() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
