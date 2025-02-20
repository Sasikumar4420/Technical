package com.pack.parkingspot.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.parkingspot.dto.ReleaseDto;
import com.pack.parkingspot.entity.AvailableSpots;
import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.enums.CheckAvailability;
import com.pack.parkingspot.enums.SlotStatus;
import com.pack.parkingspot.exception.ParkingSlotNotFoundException;
import com.pack.parkingspot.exception.SlotAlreadyReleasedException;
import com.pack.parkingspot.repository.AvailableSpotsRepository;
import com.pack.parkingspot.repository.EmployeeRepository;
import com.pack.parkingspot.repository.ParkingSlotsRepository;
import com.pack.parkingspot.repository.ParkingSpotApplicationsRepository;
import com.pack.parkingspot.service.SpotReleaseServiceImpl;

@SpringBootTest
public class SpotReleaseServiceTest {
	@InjectMocks
	private SpotReleaseServiceImpl spotReleaseServiceImpl;
	
	@Mock
	private AvailableSpotsRepository availableSpotsRepository;
	@Mock
	private EmployeeRepository employeeRepo;
	@Mock
	private ParkingSpotApplicationsRepository parkingSpotApplicationRepo;
	@Mock
	private ParkingSlotsRepository parkingSlotRepo;
	@Test
	void testAdd() {
		assertEquals(5,5);
	}
	ParkingSlots parkingSlot=new ParkingSlots(1,"tower-1",1,CheckAvailability.NotAvailable);

	Employee employee=new Employee(1005l,"sasi","Developer",LocalDate.of(2024, 10, 10),parkingSlot);
	AvailableSpots availableSpot=new AvailableSpots(1,parkingSlot,LocalDate.of(2022, 10, 01),LocalDate.of(2023, 01, 01),SlotStatus.Available);
	ReleaseDto releaseDto=new ReleaseDto(employee.getEmployeeSapCode(),LocalDate.now(),LocalDate.of(2024, 02, 024));
	
	@Test
	void testReleaseSpot() {
		Mockito.when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
		Mockito.when(parkingSlotRepo.findById(anyInt())).thenReturn(Optional.of(parkingSlot));
		Mockito.when(availableSpotsRepository.findByparkingSlot(parkingSlot)).thenReturn(Optional.empty());
		String str=spotReleaseServiceImpl.releaseSpot(releaseDto);
		assertEquals("slot is released",str);
	}
	@Test
	void testParkingSlotNotFound() {
		Employee employee=new Employee(1005l,"sasi","Developer",LocalDate.of(2024, 10, 10),null);
		Mockito.when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
		ParkingSlotNotFoundException exception = assertThrows(ParkingSlotNotFoundException.class,
                () -> spotReleaseServiceImpl.releaseSpot(releaseDto));
        assertEquals("Slot not found", exception.getMessage());
	}
	@Test
	void testSlotAlreadyReleased() {
		Mockito.when(parkingSlotRepo.findById(anyInt())).thenReturn(Optional.of(parkingSlot));
		Mockito.when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
		Mockito.when(availableSpotsRepository.findByparkingSlot(parkingSlot)).thenReturn(Optional.of(availableSpot));
		SlotAlreadyReleasedException exception = assertThrows(SlotAlreadyReleasedException.class,
                () -> spotReleaseServiceImpl.releaseSpot(releaseDto));
        assertEquals("slot already released", exception.getMessage());
	}
}
