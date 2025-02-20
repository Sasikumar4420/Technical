package com.demo.movieticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findById(Long userId);
}
