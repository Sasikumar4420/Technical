package com.demo.xyzmc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.xyzmc.dto.ConnectionRequestResponseDTO;
import com.demo.xyzmc.dto.RequestDTO;
import com.demo.xyzmc.entity.PlanType;
import com.demo.xyzmc.service.RequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
@Slf4j
public class RequestController {

	private final RequestService requestService;

	/**
	 * To add a new Request
	 * 
	 * @param requestDto contains all details like personal details, contact
	 *                   details, documents
	 * @return response as request id with status CREATED
	 */
	@PostMapping
	public ResponseEntity<ConnectionRequestResponseDTO> addRequest(@Valid @RequestBody RequestDTO requestDto,
			@RequestParam PlanType planType) {
		log.info("adding new request started");
		ConnectionRequestResponseDTO response = requestService.addRequest(requestDto,planType);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		

	}
}
