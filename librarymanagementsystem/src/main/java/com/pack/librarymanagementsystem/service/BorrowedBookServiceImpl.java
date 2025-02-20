package com.pack.librarymanagementsystem.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pack.librarymanagementsystem.dto.BooksDto;
import com.pack.librarymanagementsystem.dto.BorrowedBooksDto;
import com.pack.librarymanagementsystem.entity.Books;
import com.pack.librarymanagementsystem.entity.BorrowedBooks;
import com.pack.librarymanagementsystem.entity.Users;
import com.pack.librarymanagementsystem.entity.enums.BookStatus;
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
import com.pack.librarymanagementsystem.util.ErrorData;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private final BooksRepository booksRepo;
	private final UsersRepository userRepo;
	private final BorrowedBooksRepository borrowedBooksRepo;
	public BorrowedBookServiceImpl(BooksRepository booksRepo, UsersRepository userRepo,
			BorrowedBooksRepository borrowedBooksRepo) {
		super();
		this.booksRepo = booksRepo;
		this.userRepo = userRepo;
		this.borrowedBooksRepo = borrowedBooksRepo;
	}

	/**
	 * This method is used parse list of booksDto to list of books and checks
	 * weather the return books exists or not and the returner must be borrower
	 * 
	 * @param booksDtoList
	 * @param user
	 * @return list of books
	 */
	public List<Books> parseBooksDtoToBooks(List<BooksDto> booksDtoList) {
		List<Long> getBookIds=booksDtoList.stream().distinct().map(BooksDto::getBookId).toList();
		List<Books> booksList=booksRepo.findAllById(getBookIds);
		System.out.println("check size "+booksList.size());
		if(booksDtoList.stream().distinct().count()!=booksList.size()) {
			throw new BookNotFoundException(ErrorData.bookNotFoundErrorMessage);
		}
		UnaryOperator<Books> parseBooksDtoToBook = request -> {
				if (request.getStatus().equals(BookStatus.BORROWED)) {
					request.setStatus(BookStatus.AVAILABLE);
					return request;
				} else {
					log.error("the borrowed book is already returned");
					throw new BookAlreadyReturnedException(ErrorData.bookAreadyReturnedErrorMessage);
				}
			
		};
		return booksList.stream().map(parseBooksDtoToBook::apply).toList();
	}

	/**
	 * This method used to return the books to library
	 * 
	 * @param booksDtoList
	 * @param user
	 * @return list of books are returned message
	 */
	@Override
	public String returnBooksWithIdAndBooks(Long userId, List<BooksDto> booksDtoList) {
		log.info("Inside returning Books method");
		Optional<Users> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			log.error("user is not found with this id");
			throw new UserNotFoundException(ErrorData.userNotFoundErrorMessage);
		}

		List<Books> booksList = parseBooksDtoToBooks(booksDtoList);
//		List<BorrowedBooks> borrowedBooksList=booksList.stream().map(request -> {
//			borrowedBooksRepo.findAllByBookAndUserAndDueDateNull(request, user.get()).
//		}).toList();
		List<BorrowedBooks> borrowedBooks = booksList.stream().map(request -> {
			BorrowedBooks borrowedBook = borrowedBooksRepo.findByBookAndUserAndDueAmountNull(request, user.get())
					.orElseThrow(() -> new BookNotFoundException(ErrorData.borrowedBookWithUserNotFoundErrorMessage));
			if (borrowedBook.getUser().getUserId().equals(userId)) {
				borrowedBook.setActualReturnDate(LocalDate.now());
				Period period = Period.between(borrowedBook.getDueDate(), LocalDate.now());
				if (period.getDays() <= 3 && period.getDays() > 0) {
					borrowedBook.setDueAmount(20.00);
				} else if (period.getDays() > 3) {
					borrowedBook.setDueAmount(50.00);
				} else {
					borrowedBook.setDueAmount(0.00);
				}
				return borrowedBook;
			} else {
				log.error("the borrowed book must be returned by same person");
				throw new UserAndBookMismatchException(ErrorData.borrowedBookAndReturnUserMismatchErrorMessage);
			}
		}).toList();
		borrowedBooksRepo.saveAll(borrowedBooks);
		booksRepo.saveAll(booksList);
		return "Books are returned successfully";
	}

	/**
	 * This method is used to give history from database based on userId and
	 * monthAndYear
	 * 
	 * @param monthAndYear
	 * @param user
	 * @return list of BorrowedBooks by user on that particular month
	 */
	@Override
	public Page<BorrowedBooksDto> getHistory(Long userId, String monthAndYear, Integer pageNumber, Integer pageSize) {
		log.info("inside getHistory based on year and month method");
		Optional<Users> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			log.error("user with id not exists");
			throw new UserNotFoundException(ErrorData.userNotFoundErrorMessage);
		}
		String pattern = "[A-Za-z]{3}-\\d{4}";
		boolean isMatch = monthAndYear.matches(pattern);
		if (isMatch) {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yyyy");
			YearMonth yearMonth = YearMonth.parse(monthAndYear, formatter);
			int month = yearMonth.getMonthValue();
			int year = yearMonth.getYear();
			Page<BorrowedBooks> page = borrowedBooksRepo.findByUser(user.get(), pageable);
			List<BorrowedBooks> borrowedBooksList = page.getContent();
			if (borrowedBooksList.isEmpty()) {
				throw new ContentNotFoundInThisPageException(ErrorData.conentNotFoundInThisPageErrorMessage);
			}
			List<BorrowedBooks> borrowedBooksListByMonthAndYear = borrowedBooksList.stream()
					.filter(request -> request.getBorrowedDate().getMonthValue() == month
							&& request.getBorrowedDate().getYear() == year)
					.toList();
			if (borrowedBooksListByMonthAndYear.isEmpty()) {
				log.error("No borrowings in that particular month and year");
				throw new NoBorrowingHappensInThisMonthAndYear(ErrorData.noBorrowingsInThisMonthErrorMessage);
			}
			List<BorrowedBooksDto> l = borrowedBooksListByMonthAndYear.stream().map(request -> {
				BorrowedBooksDto borrowedBookDto = new BorrowedBooksDto();
				BeanUtils.copyProperties(request, borrowedBookDto);
				return borrowedBookDto;
			}).toList();
			return new PageImpl<>(l);

		} else {
			log.error("The input format error");
			throw new InvalidInputException(ErrorData.invalidInputErrorMessage);
		}
	}

}
