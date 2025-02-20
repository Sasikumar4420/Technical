package com.pack.course_enrollment.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pack.course_enrollment.entity.Trainee;
import com.pack.course_enrollment.exception.TraineeException;
import com.pack.course_enrollment.repository.TraineeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TraineeRepository traineeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trainee trainee = traineeRepo.findByEmail(username)
				.orElseThrow(() -> new TraineeException("TC003", "user not found or invalid credentials"));
		
		return new org.springframework.security.core.userdetails.User(trainee.getEmail(), trainee.getPassword(),
				Collections.emptyList());
	}

}
