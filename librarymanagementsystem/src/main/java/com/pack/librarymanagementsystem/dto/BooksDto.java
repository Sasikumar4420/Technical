package com.pack.librarymanagementsystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BooksDto {
	@NotNull
	@Min(value=0,message="minumum value is 0")
	@Max(value=10,message="maximum value dor book id is 10")
	private Long bookId;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public BooksDto(Long bookId) {
		super();
		this.bookId = bookId;
	}

	public BooksDto() {
		super();
	}
	
}


