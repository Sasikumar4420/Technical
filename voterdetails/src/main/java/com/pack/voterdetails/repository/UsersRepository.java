package com.pack.voterdetails.repository;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.voterdetails.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	
	Page<Users> findAllByDobBefore(LocalDate date,Pageable pageable);

//	List<Users> findAllByUserNameAndDobBefore(String userName, LocalDate date, Pageable pageable);

	Page<Users> findAllByUserNameContainingIgnoreCaseAndDobBefore(String userName, LocalDate date, Pageable pageable);

	Page<Users> findAllByUserNameContainingIgnoreCase(String userName, Pageable pageable);
}
