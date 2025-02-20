package com.example.carrental.service;

import com.example.carrental.dto.Type;
import com.example.carrental.dto.VehicleRequestDto;
import com.example.carrental.dto.VehicleResponseDto;

public interface VehicleService {

	public VehicleResponseDto addCar( Type vehicletype, VehicleRequestDto vehicleRequestDto);

}
