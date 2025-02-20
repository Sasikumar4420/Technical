package com.example.carrental.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carrental.dto.Type;
import com.example.carrental.dto.VehicleRequestDto;
import com.example.carrental.dto.VehicleResponseDto;
import com.example.carrental.service.VehicleService;

@SpringBootTest
public class VehicleControllerTest {
	@Mock
	VehicleService vehicleService;

	@InjectMocks
	VehicleController vehicleController;

	@Test
	void testAddcar() {

		VehicleRequestDto vehicleRequestDto = new VehicleRequestDto();
		vehicleRequestDto.setBrand("HONDA");
		vehicleRequestDto.setModel("CIVIC");
		vehicleRequestDto.setPricePerDay(new BigDecimal("1000.00"));
		vehicleRequestDto.setRegNo("12345");
		
		VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
		vehicleResponseDto.setMessage("Car details added successfully");
		vehicleResponseDto.setStatusCode(200L);
		
		when(vehicleService.addCar(Type.SEDAN,vehicleRequestDto)).thenReturn(vehicleResponseDto);
		
		ResponseEntity<VehicleResponseDto> responseEntity = vehicleController.addCar(Type.SEDAN, vehicleRequestDto);
		 assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(vehicleResponseDto, responseEntity.getBody());

	}

}
