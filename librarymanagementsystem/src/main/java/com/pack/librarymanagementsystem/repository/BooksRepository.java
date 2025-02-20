package com.pack.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.librarymanagementsystem.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books,Long>{

}
