package com.pack.librarymanagementsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.librarymanagementsystem.dto.BooksDto;
import com.pack.librarymanagementsystem.dto.BorrowedBooksDto;
import com.pack.librarymanagementsystem.service.BorrowedBookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/borrowed-books")
public class BorrowedBookController {
	private final BorrowedBookService borrowedBookService;

	@Autowired
	public BorrowedBookController(BorrowedBookService borrowedBookService) {
		this.borrowedBookService = borrowedBookService;
	}

	Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * This method is used to return the borrowed books to library
	 * 
	 * @param userId
	 * @param booksList
	 * @return string message that the books are returned successfully
	 */
	@PutMapping
	public ResponseEntity<Object> returnBorrowedBooks(@RequestHeader Long userId,
			@Valid @RequestBody List<BooksDto> booksList) {
		log.info("inside return books method");
		String message = borrowedBookService.returnBooksWithIdAndBooks(userId, booksList);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * This method is used to view history of borrowed books by the user based on
	 * monthAndYear
	 * 
	 * @param userId
	 * @param monthAndYear
	 * @return list of borrowed books
	 */
	@GetMapping
	public ResponseEntity<Page<BorrowedBooksDto>> historyOfBooksByUser(@RequestHeader Long userId,
			@RequestParam String monthAndYear, @RequestParam(required = false,defaultValue="0") Integer pageNumber,
			@RequestParam(required = false,defaultValue="2") Integer pageSize) {
		log.info("inside history of books method");
		Page<BorrowedBooksDto> borrowedBooks = borrowedBookService.getHistory(userId, monthAndYear, pageNumber,
				pageSize);
		return new ResponseEntity<>(borrowedBooks, HttpStatus.OK);

	}
}
