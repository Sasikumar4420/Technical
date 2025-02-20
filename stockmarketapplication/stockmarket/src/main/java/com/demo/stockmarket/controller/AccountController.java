package com.demo.stockmarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stockmarket.dto.AccountResponseDTO;
import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.service.AccountService;

import jakarta.validation.constraints.Min;

/**
 * 
 * @author yashvanth
 *
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * add cash to user account
	 * 
	 * @param id id with type long to get user account
	 * @return ResponseEntity<ApiResponse> returns ApiResponse and status code as OK
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> addCash(@Min(value = 1) @PathVariable Long id,
			@Min(value = 1) @RequestParam Double amount) {
		logger.info("addCash method in AccountController");
		ApiResponse response = accountService.addCash(id, amount);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * view list of order,trade and balance
	 * 
	 * @param id         id with type long to get user account
	 * @param pageNumber page number to retrieve
	 * @param pageSize   size of each page
	 * @return ResponseEntity<AccountResponseDTO> returns AccountResponseDTO and
	 *         status code as OK
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AccountResponseDTO> viewData(@PathVariable Long id,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		logger.info("viewData method in AccountController");
		AccountResponseDTO response = accountService.viewData(id, pageNumber, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
