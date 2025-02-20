package com.example.carrental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrental.dto.Type;
import com.example.carrental.dto.VehicleRequestDto;
import com.example.carrental.dto.VehicleResponseDto;
import com.example.carrental.service.VehicleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class VehicleController {

	private final VehicleService vehicleService;

	/**
	 * Adds new car details and stores in the system.
	 * 
	 * @param vehicletype       gets the vehicle type
	 * @param vehicleRequestDto gets the vehicle's required details
	 * @return status code and status message
	 */
	@PostMapping("/vehicles")
	public ResponseEntity<VehicleResponseDto> addCar(@RequestParam Type vehicletype,
			@RequestBody @Valid VehicleRequestDto vehicleRequestDto) {
		VehicleResponseDto vehicleResponseDto = vehicleService.addCar(vehicletype, vehicleRequestDto);
		log.info("Inside VehicleController..  adding cars to the system..");
		return new ResponseEntity<>(vehicleResponseDto, HttpStatus.CREATED);
	}

}
