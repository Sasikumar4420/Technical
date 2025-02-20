package com.example.carrental.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.carrental.dto.Status;
import com.example.carrental.dto.Type;
import com.example.carrental.dto.VehicleRequestDto;
import com.example.carrental.dto.VehicleResponseDto;
import com.example.carrental.entity.Vehicle;
import com.example.carrental.exception.InvalidDetailsException;
import com.example.carrental.repository.VehicleRepository;
import com.example.carrental.service.VehicleService;
import com.example.carrental.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;

	/**
	 * adds the vehicle details to the database
	 */
	@Override
	public VehicleResponseDto addCar(Type vehicletype, VehicleRequestDto vehicleRequestDto) {
		log.info("Inside Vehicle Service Impl....");
		Optional<Vehicle> vehicles = vehicleRepository.findByRegNoIgnoreCase(vehicleRequestDto.getRegNo());
		if (vehicles.isPresent()) {
			log.warn(ResponseMessage.ERROR_REGISTRATION_ALREADY_EXISTS);
			throw new InvalidDetailsException(ResponseMessage.ERROR_REGISTRATION_ALREADY_EXISTS);

		}

		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(vehicleRequestDto.getBrand());
		vehicle.setModel(vehicleRequestDto.getModel());
		vehicle.setPricePerDay(vehicleRequestDto.getPricePerDay());
		vehicle.setRegNo(vehicleRequestDto.getRegNo());
		vehicle.setType(vehicletype);
		vehicle.setStatus(Status.AVAILABLE);
		vehicleRepository.save(vehicle);

		VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();

		vehicleResponseDto.setMessage("Car details added successfully");
		vehicleResponseDto.setStatusCode(200L);
		log.info("Successfully executed");
		return vehicleResponseDto;

	}

}
