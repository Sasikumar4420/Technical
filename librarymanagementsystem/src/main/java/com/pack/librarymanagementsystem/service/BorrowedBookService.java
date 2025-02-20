package com.pack.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pack.librarymanagementsystem.dto.BooksDto;
import com.pack.librarymanagementsystem.dto.BorrowedBooksDto;

public interface BorrowedBookService {

	String returnBooksWithIdAndBooks(Long userId, List<BooksDto> booksList);

	Page<BorrowedBooksDto> getHistory(Long userId, String monthAndYear,Integer pageNumber,Integer pageSize);

}
