package com.demo.xyzmc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.xyzmc.dto.MobileNumbersDTO;
import com.demo.xyzmc.service.MobileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.constraints.AssertFalse.List;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/mobiles")
@RequiredArgsConstructor
@Slf4j
public class MobileController {
	private final MobileService mobileService;

	/**
	 * Gets List of available mobile numbers
	 * 
	 * @param pageNumber to define pageNumber
	 * @param pageSize   to limit number of values to return
	 * @return response with list of mobile numbers with status OK
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@GetMapping
	public ResponseEntity<MobileNumbersDTO> getMobileNumber(
			@Min(value = 0) @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@Min(value = 1) @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		log.info("Get mobile numbers started");
		MobileNumbersDTO response = mobileService.getMobileNumbers(pageNumber, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
