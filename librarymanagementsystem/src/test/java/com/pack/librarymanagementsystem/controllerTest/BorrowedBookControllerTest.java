package com.pack.librarymanagementsystem.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pack.librarymanagementsystem.controller.BorrowedBookController;
import com.pack.librarymanagementsystem.dto.BooksDto;
import com.pack.librarymanagementsystem.dto.BorrowedBooksDto;
import com.pack.librarymanagementsystem.entity.Books;
import com.pack.librarymanagementsystem.repository.BooksRepository;
import com.pack.librarymanagementsystem.repository.BorrowedBooksRepository;
import com.pack.librarymanagementsystem.repository.UsersRepository;
import com.pack.librarymanagementsystem.service.BorrowedBookService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class BorrowedBookControllerTest {

	@InjectMocks
	private BorrowedBookController borrowedBookController;

	@Autowired
	BorrowedBookControllerTest(BorrowedBookController borrowedBookController) {
		this.borrowedBookController = borrowedBookController;
	}

	@MockBean
	private BorrowedBookService borrowedBookService;
	@MockBean
	private BooksRepository booksRepository;
	@MockBean
	private UsersRepository usersRepository;
	@MockBean
	private BorrowedBooksRepository borrowedBooksRepository;

	@Test
	void testReturnBook() {
		List<BooksDto> books = new ArrayList<>();
		books.add(new BooksDto(1l));
		Mockito.when(borrowedBookService.returnBooksWithIdAndBooks(1l, books))
				.thenReturn("Books are returned successfully");
		ResponseEntity<Object> response = borrowedBookController.returnBorrowedBooks(1l, books);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Books are returned successfully", response.getBody());
	}

	@Test
	void testGetHistoryOfBooksByUserId() {
		List<BorrowedBooksDto> borrowedBooks = new ArrayList<>();
		borrowedBooks.add(new BorrowedBooksDto(new Books(), LocalDate.of(2024, 02, 23), LocalDate.of(2024, 03, 8),
				LocalDate.of(2024, 03, 12), 50.00));
		Mockito.when(borrowedBookService.getHistory(anyLong(), any(), anyInt(), anyInt())).thenReturn(new PageImpl<>(borrowedBooks));
		ResponseEntity<Page<BorrowedBooksDto>> response = borrowedBookController.historyOfBooksByUser(1l, "Feb-2024", 1,
				1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(borrowedBooks.get(0).getDueAmount(), response.getBody().get(0).getDueAmount());
	}

}
