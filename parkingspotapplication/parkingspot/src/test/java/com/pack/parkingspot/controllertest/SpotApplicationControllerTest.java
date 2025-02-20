package com.pack.parkingspot.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

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

import com.pack.parkingspot.controller.SpotApplicationController;
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
public class SpotApplicationControllerTest {
	@InjectMocks
	private SpotApplicationController spotApplicationController;
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
		Mockito.when(spotApplicationService.sendApplication(anyLong())).thenReturn("Application is sent under review");
		ResponseEntity<Object> responseEntity=spotApplicationController.spotApplication(employee.getEmployeeSapCode());
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		assertEquals("Application is sent under review",responseEntity.getBody());
	}
	
}
