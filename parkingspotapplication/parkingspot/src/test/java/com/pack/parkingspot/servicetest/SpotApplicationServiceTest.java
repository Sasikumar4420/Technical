package com.pack.parkingspot.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.parkingspot.entity.AvailableSpots;
import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.entity.ParkingSpotApplications;
import com.pack.parkingspot.enums.ApplicationStatus;
import com.pack.parkingspot.enums.CheckAvailability;
import com.pack.parkingspot.enums.SlotStatus;
import com.pack.parkingspot.exception.EmployeeAlreadyAppliedException;
import com.pack.parkingspot.repository.AvailableSpotsRepository;
import com.pack.parkingspot.repository.EmployeeRepository;
import com.pack.parkingspot.repository.ParkingSlotsRepository;
import com.pack.parkingspot.repository.ParkingSpotApplicationsRepository;
import com.pack.parkingspot.service.SpotApplicationServiceImpl;

@SpringBootTest
public class SpotApplicationServiceTest {
	@InjectMocks
	private SpotApplicationServiceImpl spotApplicationServiceImpl;
	
	@Mock
	private AvailableSpotsRepository availableSpotsRepository;
	@Mock
	private EmployeeRepository employeeRepo;
	@Mock
	private ParkingSpotApplicationsRepository parkingSpotApplicationRepo;
	@Mock
	private ParkingSlotsRepository parkingSlotRepo;
	@Mock
	private Logger logger;
	@Mock
	private Mock mock;
	
	Employee employee=new Employee(1005l,"sasi","Developer",LocalDate.of(2024, 10, 10),null);
	ParkingSpotApplications parkingSpotApplication=new ParkingSpotApplications(1,employee,LocalDate.now(),ApplicationStatus.Accepted,new ParkingSlots());
	ParkingSlots parkingSlot=new ParkingSlots(1,"tower-1",1,CheckAvailability.NotAvailable);
	AvailableSpots avilableSlot=new AvailableSpots(1,parkingSlot,LocalDate.of(2022, 10, 01),LocalDate.now(),SlotStatus.Available);

	@Test
	void testSendApplication() {
		when(employeeRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));
		when(parkingSpotApplicationRepo.findByempid(employee)).thenReturn(Optional.empty());
		when(parkingSpotApplicationRepo.save(parkingSpotApplication)).thenReturn(parkingSpotApplication);
		String str=spotApplicationServiceImpl.sendApplication(1005l);
		assertEquals("Application is sent under review",str);
	}
	@Test
	void testEmployeeApplicationAlreadyExists() {
		when(employeeRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));
		when(parkingSpotApplicationRepo.findByempid(employee)).thenReturn(Optional.of(parkingSpotApplication));
		EmployeeAlreadyAppliedException exception = assertThrows(EmployeeAlreadyAppliedException.class,
                () -> spotApplicationServiceImpl.sendApplication(1005l));
        assertEquals("Your application is already received", exception.getMessage());
	}
	
	@Test
	void testCheckManagerReturnDate() {
		List<AvailableSpots> list=new ArrayList<>();
		list.add(avilableSlot);
		Mockito.when(availableSpotsRepository.findAll()).thenReturn(list);
		Mockito.when(parkingSlotRepo.save(parkingSlot)).thenReturn(parkingSlot);
		Mockito.when(availableSpotsRepository.save(avilableSlot)).thenReturn(avilableSlot);
		spotApplicationServiceImpl.checkManagerReturnDate();
		verify(parkingSlotRepo).save(any(ParkingSlots.class));
        verify(availableSpotsRepository, times(1)).save(any(AvailableSpots.class));
        verify(availableSpotsRepository, times(1)).delete(any(AvailableSpots.class));//		verify(logger).info("inside lottery checkManager return date method");
	}
	@Test
	void testUpdateEveryDay() {
		ParkingSpotApplications parkingSpotApplication=new ParkingSpotApplications(1,employee,LocalDate.now(),ApplicationStatus.Applied,new ParkingSlots());
		ParkingSpotApplications parkingSpotApplication1=new ParkingSpotApplications(2,employee,LocalDate.now(),ApplicationStatus.Accepted,parkingSlot);
		List<ParkingSpotApplications> applicationsList=new ArrayList<>();
		applicationsList.add(parkingSpotApplication);
		applicationsList.add(parkingSpotApplication1);

		Mockito.when(parkingSpotApplicationRepo.findAll()).thenReturn(applicationsList);
		spotApplicationServiceImpl.updateEveryDay();
		verify(parkingSpotApplicationRepo, times(2)).delete(any(ParkingSpotApplications.class));
	}
	@Test
	void testAllocateSlots() {
		ParkingSpotApplications parkingSpotApplication=new ParkingSpotApplications(1,employee,LocalDate.now(),ApplicationStatus.Applied,new ParkingSlots());
		List<ParkingSpotApplications> applicationsList=new ArrayList<>();
		applicationsList.add(parkingSpotApplication);
		List<AvailableSpots> list=new ArrayList<>();
		AvailableSpots availableSpots=new AvailableSpots(1,parkingSlot,LocalDate.of(2022, 10, 01),LocalDate.now(),SlotStatus.Available);
		list.add(new AvailableSpots(1,parkingSlot,LocalDate.of(2022, 10, 01),LocalDate.now(),SlotStatus.Available));
		
		Mockito.when(availableSpotsRepository.findAll()).thenReturn(list);
		Mockito.when(parkingSpotApplicationRepo.findAll()).thenReturn(applicationsList);
		Mockito.when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
		Mockito.when(parkingSpotApplicationRepo.save(parkingSpotApplication)).thenReturn(parkingSpotApplication);
		Mockito.when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
		Mockito.when(availableSpotsRepository.save(any(AvailableSpots.class))).thenReturn(availableSpots);
		spotApplicationServiceImpl.allocateSlots();
		verify(parkingSpotApplicationRepo, times(1)).save(any(ParkingSpotApplications.class));
		verify(availableSpotsRepository,times(1)).save(any(AvailableSpots.class));
		verify(employeeRepo,times(1)).save(any(Employee.class));

	}
	@Test
	void testAllocateSlotsIfStatusAccepted() {
		ParkingSpotApplications parkingSpotApplication=new ParkingSpotApplications(1,employee,LocalDate.now(),ApplicationStatus.Applied,new ParkingSlots());
		List<ParkingSpotApplications> applicationsList=new ArrayList<>();
		applicationsList.add(parkingSpotApplication);
		List<AvailableSpots> list=new ArrayList<>();
		AvailableSpots availableSpots=new AvailableSpots(1,parkingSlot,LocalDate.of(2022, 10, 01),LocalDate.now(),SlotStatus.NotAvailable);
//		list.add(new AvailableSpots(1,parkingSlot,LocalDate.of(2022, 10, 01),LocalDate.now(),SlotStatus.NotAvailable));
		
		Mockito.when(availableSpotsRepository.findByStatus(SlotStatus.Available)).thenReturn(list);
		Mockito.when(availableSpotsRepository.findAll()).thenReturn(list);
		Mockito.when(parkingSpotApplicationRepo.findAll()).thenReturn(applicationsList);
		Mockito.when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
		Mockito.when(parkingSpotApplicationRepo.save(parkingSpotApplication)).thenReturn(parkingSpotApplication);
		Mockito.when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
		Mockito.when(availableSpotsRepository.save(any(AvailableSpots.class))).thenReturn(availableSpots);
		spotApplicationServiceImpl.allocateSlots();
	
		verify(parkingSpotApplicationRepo, times(2)).findAll();
		verify(availableSpotsRepository,times(1)).findAll();
		verify(availableSpotsRepository,times(1)).findByStatus(SlotStatus.Available);
	}

}
