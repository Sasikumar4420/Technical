package com.pack.course_enrollment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.course_enrollment.dto.CredentialsDto;
import com.pack.course_enrollment.dto.TokenDto;
import com.pack.course_enrollment.dto.TraineeDto;
import com.pack.course_enrollment.service.DemoService;

@RestController
@RequestMapping("/trainees")
public class DemoController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DemoService demoService;
	
	/**
	 * This method is used to save the trainee data
	 * @param trainee
	 * @return
	 */
	@PostMapping
	public ResponseEntity<TokenDto> saveTrainee(@RequestBody TraineeDto trainee){
		logger.info("inside save trainee controller");
		TokenDto token=demoService.saveTrainee(trainee);
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
	/**
	 * This method is used to check the login credentials
	 * @param credentials
	 * @return status message with token
	 */
	@PostMapping(value="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDto> checkLogin(@RequestBody CredentialsDto credentials){
		logger.info("inside login checking credentials");
		TokenDto token=demoService.checkCredentials(credentials);
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<String> checking(){
		return new ResponseEntity<>("komal",HttpStatus.OK);
	}
}
