package com.pack.librarymanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.librarymanagementsystem.entity.Books;
import com.pack.librarymanagementsystem.entity.BorrowedBooks;
import com.pack.librarymanagementsystem.entity.Users;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks,Long>{

	Optional<BorrowedBooks> findByBook(Books request);

	List<BorrowedBooks> findByUser(Users user);

	Page<BorrowedBooks> findByUser(Users user, Pageable pageable);

	Optional<BorrowedBooks> findByBookAndUser(Books books, Users user);

	Optional<BorrowedBooks> findByBookAndUserAndDueAmountNull(Books books, Users user);

	List<BorrowedBooks> findAllByBookAndUserAndDueDateNull(Books books, Users user);
	
	
}
