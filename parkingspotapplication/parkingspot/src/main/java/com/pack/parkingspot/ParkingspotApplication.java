package com.pack.parkingspot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.enums.CheckAvailability;
import com.pack.parkingspot.repository.EmployeeRepository;
import com.pack.parkingspot.repository.ParkingSlotsRepository;

@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
public class ParkingspotApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ParkingspotApplication.class, args);
	}
	
	@Autowired
	ParkingSlotsRepository parkingSlotRepo;
	@Autowired
	EmployeeRepository empRepo;
	@Override
	public void run(String... args) throws Exception {
//		saveParkingSpots();
//		saveEmployee();
		
	}

	private void saveEmployee() {
		// TODO Auto-generated method stub
		List<Employee> empList=new ArrayList<>();
		empList.add(new Employee(1007l,"komal","Developer",LocalDate.of(2024, 10, 10),null));
		empList.add(new Employee(1008l,"varun","Tester",LocalDate.of(2021, 02, 01),null));
		empList.add(new Employee(1009l,"saran","Developer",LocalDate.of(2017, 04, 14),null));
		empList.add(new Employee(1010l,"suresh","Manager",LocalDate.of(2020, 10, 11),null));
		empRepo.saveAll(empList);
		
	}

	private void saveParkingSpots() {
		// TODO Auto-generated method stub
		List<ParkingSlots> parkingSlotsList=new ArrayList<>();
		parkingSlotsList.add(new ParkingSlots(1,"Tower-1",1,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(2,"Tower-1",2,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(3,"Tower-1",3,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(4,"Tower-1",4,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(5,"Tower-1",5,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(6,"Tower-2",1,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(7,"Tower-2",2,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(8,"Tower-2",3,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(9,"Tower-2",4,CheckAvailability.NotAvailable));
		parkingSlotsList.add(new ParkingSlots(10,"Tower-2",5,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(11,"Tower-3",1,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(12,"Tower-3",2,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(13,"Tower-3",3,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(14,"Tower-3",4,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(15,"Tower-3",5,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(16,"Tower-4",1,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(17,"Tower-4",2,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(18,"Tower-4",3,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(19,"Tower-4",4,CheckAvailability.NotAvailable));
//		parkingSlotsList.add(new ParkingSlots(20,"Tower-4",5,CheckAvailability.NotAvailable));
		parkingSlotRepo.saveAll(parkingSlotsList);
	}
	

}
