package com.pack.parkingspot.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class ReleaseDto {
	@NotNull(message="employee id can't be null")
	private Long empId;
	@Future
	@NotNull(message="date can't be null")
	private LocalDate availableFrom;
	@Future
	@NotNull(message="date can't be null")
	private LocalDate availableTo;
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public LocalDate getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}
	public LocalDate getAvailableTo() {
		return availableTo;
	}
	public void setAvailableTo(LocalDate availableTo) {
		this.availableTo = availableTo;
	}
	public ReleaseDto(Long empId, LocalDate availableFrom, LocalDate availableTo) {
		super();
		this.empId = empId;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
	}
	public ReleaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
