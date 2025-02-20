package com.pack.voterdetails.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private LocalDate dob;
	private String email;
	private String password;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Users(Long userId, String userName, LocalDate dob, String email, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}
	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", dob=" + dob + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	
	
}
