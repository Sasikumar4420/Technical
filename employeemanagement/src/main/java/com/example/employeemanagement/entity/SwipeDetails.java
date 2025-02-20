package com.example.employeemanagement.entity;

import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SwipeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long swipeId;
	private String accessCard;
	private String location;
	private String facility;
	private Integer tower;
	private SwipeType swipeType;
	private LocalTime inTime;
	private LocalTime outTime;
	private Float hoursWorked;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sapId")
	private EmployeeOfficialDetails sapId;

	public Long getSwipeId() {
		return swipeId;
	}

	public void setSwipeId(Long swipeId) {
		this.swipeId = swipeId;
	}

	public String getAccessCard() {
		return accessCard;
	}

	public void setAccessCard(String accessCard) {
		this.accessCard = accessCard;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Integer getTower() {
		return tower;
	}

	public void setTower(Integer tower) {
		this.tower = tower;
	}

	public SwipeType getSwipeType() {
		return swipeType;
	}

	public void setSwipeType(SwipeType swipeType) {
		this.swipeType = swipeType;
	}

	public LocalTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}

	public Float getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Float hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public EmployeeOfficialDetails getSapId() {
		return sapId;
	}

	public void setSapId(EmployeeOfficialDetails sapId) {
		this.sapId = sapId;
	}

	public SwipeDetails(Long swipeId, String accessCard, String location, String facility, Integer tower,
			SwipeType swipeType, LocalTime inTime, LocalTime outTime, Float hoursWorked,
			EmployeeOfficialDetails sapId) {
		super();
		this.swipeId = swipeId;
		this.accessCard = accessCard;
		this.location = location;
		this.facility = facility;
		this.tower = tower;
		this.swipeType = swipeType;
		this.inTime = inTime;
		this.outTime = outTime;
		this.hoursWorked = hoursWorked;
		this.sapId = sapId;
	}

	public SwipeDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
