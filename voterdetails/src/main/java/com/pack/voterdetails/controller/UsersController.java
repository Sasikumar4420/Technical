package com.pack.voterdetails.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.voterdetails.dto.ResponseDto;
import com.pack.voterdetails.service.UsersService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/users")
public class UsersController {

	Logger logger=LoggerFactory.getLogger(getClass());
	private final UsersService userService;

	public UsersController(UsersService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * This method is used to return list of userDto who are eligible based on give
	 * age parameter
	 * 
	 * @param userName
	 * @param requiredAge
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	public ResponseEntity<ResponseDto> getUsersByRequest(
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) @Valid @Min(value = 1, message = "please enter valid age") Long requiredAge,
			@RequestParam(required = false, defaultValue = "0") @Valid @Min(value = 0, message = "please enter valid page number") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "2") @Valid @Min(value = 1, message = "please enter valid page size") Integer pageSize) {
		logger.info("inside get users by the request controller");
		ResponseDto response = userService.validateRequest(userName, requiredAge, pageNumber, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
