package com.pack.voterdetails.dto;

import java.time.LocalDate;

public class UserDto {

	private String userName;
	private LocalDate dob;
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDto(String userName, LocalDate dob, String email) {
		super();
		this.userName = userName;
		this.dob = dob;
		this.email = email;
	}

	public UserDto() {
		super();
	}

}
