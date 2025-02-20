package com.pack.librarymanagementsystem.servicetest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pack.librarymanagementsystem.dto.BooksDto;
import com.pack.librarymanagementsystem.dto.BorrowedBooksDto;
import com.pack.librarymanagementsystem.entity.Books;
import com.pack.librarymanagementsystem.entity.BorrowedBooks;
import com.pack.librarymanagementsystem.entity.Users;
import com.pack.librarymanagementsystem.entity.enums.BookStatus;
import com.pack.librarymanagementsystem.entity.enums.Roles;
import com.pack.librarymanagementsystem.exception.BookAlreadyReturnedException;
import com.pack.librarymanagementsystem.exception.BookNotFoundException;
import com.pack.librarymanagementsystem.exception.ContentNotFoundInThisPageException;
import com.pack.librarymanagementsystem.exception.InvalidInputException;
import com.pack.librarymanagementsystem.exception.NoBorrowingHappensInThisMonthAndYear;
import com.pack.librarymanagementsystem.exception.UserAndBookMismatchException;
import com.pack.librarymanagementsystem.exception.UserNotFoundException;
import com.pack.librarymanagementsystem.repository.BooksRepository;
import com.pack.librarymanagementsystem.repository.BorrowedBooksRepository;
import com.pack.librarymanagementsystem.repository.UsersRepository;
import com.pack.librarymanagementsystem.service.BorrowedBookServiceImpl;
import com.pack.librarymanagementsystem.util.ErrorData;

@SpringBootTest
class BorrowedBookServiceTest {
	@InjectMocks
	private BorrowedBookServiceImpl borrowedBookService;
	
	@Mock
	private BooksRepository booksRepo;
	@Mock
	private UsersRepository userRepo;
	@Mock
	private BorrowedBooksRepository borrowedBooksRepo;
	
	
	Users user=new Users(1l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123");
	Books book=new Books(1l,"harry potter","william","magic & adventure",BookStatus.BORROWED);
	BorrowedBooks borrowedBook=new BorrowedBooks(1l,user,new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),null,50.00);

	@Test
	void testReturnBooks() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.of(book));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBookAndUserAndDueAmountNull(book,user)).thenReturn(Optional.of(borrowedBook));
		String response=borrowedBookService.returnBooksWithIdAndBooks(1l, books);
		String expected="Books are returned successfully";
		assertEquals(expected,response);
	}
	@Test
	void testReturnBooksIfBookNotPresent() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.empty());
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> borrowedBookService.returnBooksWithIdAndBooks(1l, books));
		assertEquals(ErrorData.bookNotFoundErrorMessage, exception.getMessage());
	}
	@Test
	void testReturnBooksIfUserNotPresent() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.empty());
		UserNotFoundException exception = assertThrows(UserNotFoundException.class,
				() -> borrowedBookService.returnBooksWithIdAndBooks(1l, books));
		assertEquals(ErrorData.userNotFoundErrorMessage, exception.getMessage());
	}
	@Test
	void testReturnBooksIfBookAlreadyReturned() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		Books book=new Books(1l,"harry potter","william","magic & adventure",BookStatus.AVAILABLE);
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.of(book));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBookAndUserAndDueAmountNull(book,user)).thenReturn(Optional.of(borrowedBook));
		BookAlreadyReturnedException exception=assertThrows(BookAlreadyReturnedException.class,
					()-> borrowedBookService.returnBooksWithIdAndBooks(1l, books));
		assertEquals(ErrorData.bookAreadyReturnedErrorMessage,exception.getMessage());
		
	}
	@Test
	void testReturnBooksIfDueDateExceededBy3Days() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		BorrowedBooks borrowedBooks1=new BorrowedBooks(1l,user,book,LocalDate.of(2024, 02, 28),LocalDate.of(2024, 03, 8),null,20.00);
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.of(book));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBookAndUserAndDueAmountNull(book,user)).thenReturn(Optional.of(borrowedBooks1));
		borrowedBookService.returnBooksWithIdAndBooks(1l, books);
		assertEquals(50.00,borrowedBooks1.getDueAmount());
	}
	@Test
	void testReturnBooksIfNoDelay() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		BorrowedBooks borrowedBooks1=new BorrowedBooks(1l,user,book,LocalDate.of(2024, 02, 23),LocalDate.now(),null,0.00);
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.of(book));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBookAndUserAndDueAmountNull(book,user)).thenReturn(Optional.of(borrowedBooks1));
		borrowedBookService.returnBooksWithIdAndBooks(1l, books);
		assertEquals(0.00,borrowedBooks1.getDueAmount());
	}
	@Test
	void testReturnBooksIfDueDateExceedBy2Days() {
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		BorrowedBooks borrowedBooks1=new BorrowedBooks(1l,user,book,LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 13),null,0.00);
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.of(book));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBookAndUserAndDueAmountNull(book,user)).thenReturn(Optional.of(borrowedBooks1));
		borrowedBookService.returnBooksWithIdAndBooks(1l, books);
		assertEquals(20.00,borrowedBooks1.getDueAmount());
	}
	@Test
	void testCheckUserAndUserFromBorrowBookMismatch() {
		BorrowedBooks borrowedBook=new BorrowedBooks(1l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),null,50.00);
		List<BooksDto> books=new ArrayList<>();
		books.add(new BooksDto(1l));
		Mockito.when(booksRepo.findById(anyLong())).thenReturn(Optional.of(book));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBookAndUserAndDueAmountNull(book,user)).thenReturn(Optional.of(borrowedBook));
		UserAndBookMismatchException exception=assertThrows(UserAndBookMismatchException.class,
				()-> borrowedBookService.returnBooksWithIdAndBooks(1l, books));
		assertEquals(ErrorData.borrowedBookAndReturnUserMismatchErrorMessage,exception.getMessage());
	}
	
	@Test
	void testGetHistory() {
		List<BorrowedBooksDto> list=new ArrayList<>();
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),LocalDate.of(2024, 03, 12),50.00));
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2024, 01, 23),LocalDate.of(2024, 02, 8),LocalDate.now(),50.00));
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2023, 02, 23),LocalDate.of(2024, 02, 8),LocalDate.now(),50.00));
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2023, 10, 23),LocalDate.of(2024, 02, 8),LocalDate.now(),50.00));

		List<BorrowedBooks> borrowedBooksList=new ArrayList<>();
		borrowedBooksList.add(new BorrowedBooks(1l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),null,50.00));
		borrowedBooksList.add(new BorrowedBooks(2l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2023, 01, 23),LocalDate.of(2024, 03, 8),null,50.00));
		borrowedBooksList.add(new BorrowedBooks(3l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2023, 02, 23),LocalDate.of(2024, 03, 8),null,50.00));
		borrowedBooksList.add(new BorrowedBooks(4l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2024, 01, 23),LocalDate.of(2024, 03, 8),null,50.00));

		Pageable pageable=Mockito.mock(Pageable.class);
		Page<BorrowedBooks> mockPage=Mockito.mock(Page.class);
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBook(book)).thenReturn(Optional.of(borrowedBook));
		Mockito.when(mockPage.getContent()).thenReturn(borrowedBooksList);
		Mockito.when(borrowedBooksRepo.findByUser(any(Users.class), any(Pageable.class))).thenReturn(mockPage);
		Page<BorrowedBooksDto> returnList=borrowedBookService.getHistory(1l, "Feb-2024",1,1);
		assertEquals(1, returnList.getSize());
	}
	@Test
	void testHistoryIfUserNotExists() {
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.empty());
		UserNotFoundException exception = assertThrows(UserNotFoundException.class,
				() -> borrowedBookService.getHistory(1l, "Feb-2024",1,1));
		assertEquals(ErrorData.userNotFoundErrorMessage, exception.getMessage());
	}
	@Test
	void testNoBorrowingHappenOnThatMonthAndYear() {
		List<BorrowedBooksDto> list=new ArrayList<>();
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),LocalDate.of(2024, 03, 12),50.00));
		List<BorrowedBooks> borrowedBooksList=new ArrayList<>();
		borrowedBooksList.add(new BorrowedBooks(1l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),null,50.00));
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<BorrowedBooks> mockPage=Mockito.mock(Page.class);
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBook(book)).thenReturn(Optional.of(borrowedBook));
		Mockito.when(mockPage.getContent()).thenReturn(borrowedBooksList);
		Mockito.when(borrowedBooksRepo.findByUser(any(Users.class), any(Pageable.class))).thenReturn(mockPage);
		NoBorrowingHappensInThisMonthAndYear exception=assertThrows(NoBorrowingHappensInThisMonthAndYear.class,
				() -> borrowedBookService.getHistory(1l, "Mar-2024",1,1));
		assertEquals(ErrorData.noBorrowingsInThisMonthErrorMessage, exception.getMessage());
	}
	@Test
	void testInvalidInputFormat() {
		List<BorrowedBooksDto> list=new ArrayList<>();
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),LocalDate.of(2024, 03, 12),50.00));

		List<BorrowedBooks> borrowedBooksList=new ArrayList<>();
		borrowedBooksList.add(new BorrowedBooks(1l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),null,50.00));
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<BorrowedBooks> mockPage=Mockito.mock(Page.class);
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBook(book)).thenReturn(Optional.of(borrowedBook));
		Mockito.when(mockPage.getContent()).thenReturn(borrowedBooksList);
		Mockito.when(borrowedBooksRepo.findByUser(any(Users.class), any(Pageable.class))).thenReturn(mockPage);
		InvalidInputException exception=assertThrows(InvalidInputException.class,
				() -> borrowedBookService.getHistory(1l, "M-2024",1,1));
		assertEquals(ErrorData.invalidInputErrorMessage, exception.getMessage());
	}
	@Test
	void testIfNoContentInThatPage() {
		List<BorrowedBooksDto> list=new ArrayList<>();
		list.add(new BorrowedBooksDto(new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),LocalDate.of(2024, 03, 12),50.00));

		List<BorrowedBooks> borrowedBooksList=new ArrayList<>();
		borrowedBooksList.add(new BorrowedBooks(1l,new Users(2l,"sasi",Roles.MEMBER,"sasi@gmail.com","sasi@123"),new Books(),LocalDate.of(2024, 02, 23),LocalDate.of(2024, 03, 8),null,50.00));
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<BorrowedBooks> mockPage=Mockito.mock(Page.class);
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
		Mockito.when(borrowedBooksRepo.findByBook(book)).thenReturn(Optional.of(borrowedBook));
		Mockito.when(mockPage.getContent()).thenReturn(Collections.emptyList());
		Mockito.when(borrowedBooksRepo.findByUser(any(Users.class), any(Pageable.class))).thenReturn(mockPage);
		ContentNotFoundInThisPageException exception=assertThrows(ContentNotFoundInThisPageException.class,
				() -> borrowedBookService.getHistory(1l, "Feb-2024",1,1));
		assertEquals(ErrorData.conentNotFoundInThisPageErrorMessage, exception.getMessage());
	}
	
}
