package com.pack.course_enrollment.dto;

import java.time.LocalDate;

public class TraineeDto {
	private String traineeName;
	private LocalDate joinDate;
	private String email;
	private String password;
	public String getTraineeName() {
		return traineeName;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
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
	public TraineeDto(String traineeName, LocalDate joinDate, String email, String password) {
		super();
		this.traineeName = traineeName;
		this.joinDate = joinDate;
		this.email = email;
		this.password = password;
	}
	public TraineeDto() {
		super();
	}
	
	
}
