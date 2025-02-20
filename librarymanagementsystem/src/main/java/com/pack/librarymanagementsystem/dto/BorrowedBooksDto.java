package com.pack.librarymanagementsystem.dto;

import java.time.LocalDate;

import com.pack.librarymanagementsystem.entity.Books;


public class BorrowedBooksDto {
	private Books book;
	private LocalDate borrowedDate;
	private LocalDate dueDate;
	private LocalDate actualReturnDate;
	private Double dueAmount;
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
	public BorrowedBooksDto(Books book, LocalDate borrowedDate, LocalDate dueDate, LocalDate actualReturnDate,
			Double dueAmount) {
		super();
		this.book = book;
		this.borrowedDate = borrowedDate;
		this.dueDate = dueDate;
		this.actualReturnDate = actualReturnDate;
		this.dueAmount = dueAmount;
	}
	public BorrowedBooksDto() {
		super();
	}
	
	
}
