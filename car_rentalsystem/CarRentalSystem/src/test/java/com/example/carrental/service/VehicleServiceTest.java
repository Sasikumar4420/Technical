package com.example.carrental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carrental.dto.Status;
import com.example.carrental.dto.Type;
import com.example.carrental.dto.VehicleRequestDto;
import com.example.carrental.dto.VehicleResponseDto;
import com.example.carrental.entity.Vehicle;
import com.example.carrental.exception.InvalidDetailsException;
import com.example.carrental.repository.VehicleRepository;
import com.example.carrental.serviceImpl.VehicleServiceImpl;
import com.example.carrental.utils.ResponseMessage;

@SpringBootTest
	public class VehicleServiceTest {
		@Mock
		VehicleRepository vehicleRepository;

		@InjectMocks
		VehicleServiceImpl vehicleServiceImpl;

		@Test
		void testAddcar() {

			Vehicle vehicle = new Vehicle();
			vehicle.setBrand(null);;
			vehicle.setModel("CIVIC");
			vehicle.setPricePerDay(new BigDecimal("1000.00"));
			vehicle.setRegNo("12345");
			vehicle.setType(Type.SEDAN);
			vehicle.setStatus(Status.AVAILABLE);
			
			
			VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
			vehicleResponseDto.setMessage("Car details added successfully");
			vehicleResponseDto.setStatusCode(200L);
			
			when(vehicleRepository.findByRegNoIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(vehicle));
			VehicleRequestDto vehicleRequestDto = new VehicleRequestDto();
			vehicleRequestDto.setBrand("HONDA");
			vehicleRequestDto.setModel("CIVIC");
			vehicleRequestDto.setPricePerDay(new BigDecimal("1000.00"));
			vehicleRequestDto.setRegNo("12345");
			
			VehicleResponseDto response = vehicleServiceImpl.addCar(Type.SEDAN,vehicleRequestDto);
			assertEquals(vehicleResponseDto, response);
			

		}

		
		@Test
		void testAddcarNegative() {

			Vehicle vehicle = new Vehicle();
			vehicle.setBrand(null);;
			vehicle.setModel("CIVIC");
			vehicle.setPricePerDay(new BigDecimal("1000.00"));
			vehicle.setRegNo("12345");
			vehicle.setType(Type.SEDAN);
			vehicle.setStatus(Status.AVAILABLE);
			
			
			VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
			vehicleResponseDto.setMessage("Car details added successfully");
			vehicleResponseDto.setStatusCode(200L);
			
			when(vehicleRepository.findByRegNoIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(vehicle));
			VehicleRequestDto vehicleRequestDto = new VehicleRequestDto();
			vehicleRequestDto.setBrand("HONDA");
			vehicleRequestDto.setModel("CIVIC");
			vehicleRequestDto.setPricePerDay(new BigDecimal("1000.00"));
			vehicleRequestDto.setRegNo("12345");
			
			InvalidDetailsException exception = assertThrows(InvalidDetailsException.class,
					() -> vehicleServiceImpl.addCar(Type.SEDAN,vehicleRequestDto));
			assertEquals(ResponseMessage.ERROR_REGISTRATION_ALREADY_EXISTS, exception.getMessage());
			

		}

	}



