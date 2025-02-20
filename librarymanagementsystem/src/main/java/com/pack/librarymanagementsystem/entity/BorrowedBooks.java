package com.pack.librarymanagementsystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowedBooks {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long borrowedId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	@ManyToOne
	@JoinColumn(name="book_id")
	private Books book;
	private LocalDate borrowedDate;
	private LocalDate dueDate;
	private LocalDate actualReturnDate;
	private Double dueAmount;
	public Long getBorrowedId() {
		return borrowedId;
	}
	public void setBorrowedId(Long borrowedId) {
		this.borrowedId = borrowedId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Books getBook() {
		return book;
	}
	public void setBook(Books book) {
		this.book = book;
	}
	public LocalDate getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(LocalDate borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(LocalDate actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public Double getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}
	public BorrowedBooks(Long borrowedId, Users user, Books book, LocalDate borrowedDate, LocalDate dueDate,
			LocalDate actualReturnDate, Double dueAmount) {
		super();
		this.borrowedId = borrowedId;
		this.user = user;
		this.book = book;
		this.borrowedDate = borrowedDate;
		this.dueDate = dueDate;
		this.actualReturnDate = actualReturnDate;
		this.dueAmount = dueAmount;
	}
	public BorrowedBooks() {
		super();
	}
	
	
}
