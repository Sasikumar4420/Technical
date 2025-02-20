package com.pack.parkingspot.service;

import java.util.Optional;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.parkingspot.dto.ReleaseDto;
import com.pack.parkingspot.entity.AvailableSpots;
import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.enums.CheckAvailability;
import com.pack.parkingspot.enums.SlotStatus;
import com.pack.parkingspot.exception.EmployeeNotFoundException;
import com.pack.parkingspot.exception.ParkingSlotNotFoundException;
import com.pack.parkingspot.exception.SlotAlreadyReleasedException;
import com.pack.parkingspot.repository.AvailableSpotsRepository;
import com.pack.parkingspot.repository.EmployeeRepository;
import com.pack.parkingspot.repository.ParkingSlotsRepository;

@Service
public class SpotReleaseServiceImpl implements SpotReleaseService{
	
	private final static org.apache.juli.logging.Log logger=LogFactory.getLog(SpotReleaseServiceImpl.class);
	
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	ParkingSlotsRepository parkingSlotRepo;
	@Autowired
	AvailableSpotsRepository availableSpotRepo;
	/**
	 * @param it takes ReleaseDto as input
	 * this method used to release the allocated spot for the manager and add additional info like from and to
	 * 		and save the information in another table
	 * 	checks the exceptions based on conditions then throw exception
	 * @return string as a response
	 */
	@Override
	public String releaseSpot(ReleaseDto releaseDto)throws EmployeeNotFoundException {
		logger.info("inside Release spot method");
		Employee employee=employeeRepo.findById(releaseDto.getEmpId())
				.orElseThrow(()->new EmployeeNotFoundException("employee not found"));
		if(employee.getParkingSlot()==null) {
			throw new ParkingSlotNotFoundException("Slot not found");
		}
		ParkingSlots parkingSlot=parkingSlotRepo.findById(employee.getParkingSlot().getId())
				.orElseThrow(()->new ParkingSlotNotFoundException("Slot not found"));
		AvailableSpots availableSpots=new AvailableSpots();
		availableSpots.setParkingSlot(employee.getParkingSlot());
		availableSpots.setSlotAvailableFrom(releaseDto.getAvailableFrom());
		availableSpots.setSlotAvailableTo(releaseDto.getAvailableTo());
		availableSpots.setStatus(SlotStatus.Available);
		parkingSlot.setCheckAvailability(CheckAvailability.Available);
		Optional<AvailableSpots> availableSpot=availableSpotRepo.findByparkingSlot(parkingSlot);
		if(availableSpot.isEmpty()) {
			parkingSlotRepo.save(parkingSlot);
			availableSpotRepo.save(availableSpots);
		}else {
			throw new SlotAlreadyReleasedException("slot already released");
		}
		
		return "slot is released";
	}
	
}
