package com.pack.course_enrollment.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pack.course_enrollment.config.JwtService;
import com.pack.course_enrollment.dto.CredentialsDto;
import com.pack.course_enrollment.dto.TokenDto;
import com.pack.course_enrollment.dto.TraineeDto;
import com.pack.course_enrollment.entity.Trainee;
import com.pack.course_enrollment.repository.TraineeRepository;

@Service
public class DemoServiceImpl implements DemoService{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TraineeRepository traineeRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;
	
	/**
	 * This method is used to save trainee details
	 * @param traineeDto
	 * @return token
	 */
	@Override
	public TokenDto saveTrainee(TraineeDto traineeDto) {
		logger.info("inside save trainee service method");
		Trainee trainee = new Trainee();
		BeanUtils.copyProperties(traineeDto, trainee);
		trainee.setPassword(passwordEncoder.encode(traineeDto.getPassword()));
		traineeRepo.save(trainee);
		TokenDto token=new TokenDto("200","successfully generated token",jwtService.generateToken(trainee));
		return token;
	}
	/**
	 * This method is used to check the user credentials
	 * @param credentials
	 * @return token
	 */
	@Override
	public TokenDto checkCredentials(CredentialsDto credentials) {
		logger.info("inside chcek login credentials service method");
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
		Optional<Trainee> trainee=traineeRepo.findByEmail(credentials.getEmail());
		String token=jwtService.generateToken(trainee.get());
		TokenDto tokenDto=new TokenDto("200","successfully generated token",token);
		return tokenDto;
	}
}
