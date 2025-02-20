package com.pack.parkingspot.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pack.parkingspot.controller.SpotReleaseController;
import com.pack.parkingspot.dto.ReleaseDto;
import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.enums.CheckAvailability;
import com.pack.parkingspot.repository.AvailableSpotsRepository;
import com.pack.parkingspot.repository.EmployeeRepository;
import com.pack.parkingspot.repository.ParkingSlotsRepository;
import com.pack.parkingspot.repository.ParkingSpotApplicationsRepository;
import com.pack.parkingspot.service.SpotApplicationService;
import com.pack.parkingspot.service.SpotReleaseService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class SpotReleaseControllerTest {
	@InjectMocks
	private SpotReleaseController spotReleaseController;
	@MockBean
	private SpotApplicationService spotApplicationService;
	@MockBean
	private SpotReleaseService spotReleaseService;
	@MockBean
	private AvailableSpotsRepository availableSpotsRepo;
	@MockBean
	private EmployeeRepository employeeRepo;
	@MockBean
	private ParkingSlotsRepository parkingSlotsRepo;
	@MockBean
	private ParkingSpotApplicationsRepository parkingApplicationRepo;
	@Test
	void testAdd() {
		assertEquals(5,5);
	}
	@Test
	void testReleaseController() {
		ParkingSlots parkingSlot=new ParkingSlots(1,"tower-1",1,CheckAvailability.NotAvailable);
		Employee employee=new Employee(1005l,"komal","Developer",LocalDate.of(2024, 10, 10),parkingSlot);
		ReleaseDto releaseDto=new ReleaseDto(employee.getEmployeeSapCode(),LocalDate.now(),LocalDate.of(2024, 02, 024));
		Mockito.when(spotReleaseService.releaseSpot(releaseDto)).thenReturn("slot is released");
		ResponseEntity<Object> responseEntity=spotReleaseController.releaseParkingSlot(releaseDto);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		assertEquals("slot is released",responseEntity.getBody());
	}
}
