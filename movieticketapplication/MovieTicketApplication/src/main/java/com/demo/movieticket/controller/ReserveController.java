package com.demo.movieticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.movieticket.dto.ReservedSeatRequestDto;
import com.demo.movieticket.dto.ResponseDto;
import com.demo.movieticket.service.ReserveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/reserved")
public class ReserveController {
	@Autowired
	ReserveService reserveService;

	@PostMapping
	public ResponseEntity<ResponseDto> reserved(@RequestHeader Long userId, @RequestParam Long showId,
			@RequestBody List<ReservedSeatRequestDto> reservedSeatRequestDto) {
		log.info("Inside ReserveService - reservedSeats");
		ResponseDto responseDto = reserveService.reserved(userId, showId, reservedSeatRequestDto);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

}
