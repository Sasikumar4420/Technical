package com.pack.parkingspot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pack.parkingspot.entity.AvailableSpots;
import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.entity.ParkingSpotApplications;
import com.pack.parkingspot.enums.ApplicationStatus;
import com.pack.parkingspot.enums.CheckAvailability;
import com.pack.parkingspot.enums.SlotStatus;
import com.pack.parkingspot.exception.EmployeeAlreadyAppliedException;
import com.pack.parkingspot.exception.EmployeeNotFoundException;
import com.pack.parkingspot.repository.AvailableSpotsRepository;
import com.pack.parkingspot.repository.EmployeeRepository;
import com.pack.parkingspot.repository.ParkingSlotsRepository;
import com.pack.parkingspot.repository.ParkingSpotApplicationsRepository;

@Service
public class SpotApplicationServiceImpl implements SpotApplicationService{
	
	private final static org.apache.juli.logging.Log logger=LogFactory.getLog(SpotApplicationServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	ParkingSlotsRepository parkingSlotRepo;
	@Autowired
	AvailableSpotsRepository availableSpotRepo;
	@Autowired
	ParkingSpotApplicationsRepository parkingSpotApplicationRepo;
	/**
	 * @param employee id
	 * in this method if conditions satisfy returns Application under review message else throw exceptions
	 */
	@Override
	public String sendApplication(Long empid) throws EmployeeNotFoundException,EmployeeAlreadyAppliedException{
		logger.info("inside Sending Application method");
		Employee employee=employeeRepo.findById(empid)
				.orElseThrow(()->new EmployeeNotFoundException("employee not found"));
		Optional<ParkingSpotApplications> applicant =parkingSpotApplicationRepo.findByempid(employee);
		if(applicant.isPresent()) {
			throw new EmployeeAlreadyAppliedException("Your application is already received");
		}
		ParkingSpotApplications application=new ParkingSpotApplications();
		application.setEmpid(employee);
		application.setAppliedOn(LocalDate.now());
		application.setStatus(ApplicationStatus.Applied);
		parkingSpotApplicationRepo.save(application);
		return "Application is sent under review";
	}
	/**
	 * this lottery method automatically checks with manager return date and 
	 * removes his slot from released spots
	 */
	@Scheduled(fixedRate=100000)
	public void checkManagerReturnDate() {
		logger.info("inside lottery checkManager return date method");
		List<AvailableSpots> availableSpotsList=availableSpotRepo.findAll();
		for(AvailableSpots spot:availableSpotsList) {
			if(spot.getSlotAvailableTo().equals(LocalDate.now())) {
				ParkingSlots parkingSlot=spot.getParkingSlot();
				parkingSlot.setCheckAvailability(CheckAvailability.NotAvailable);
				parkingSlotRepo.save(parkingSlot);
				
				spot.setParkingSlot(null);
				availableSpotRepo.save(spot);
				availableSpotRepo.delete(spot);
				logger.info("inside for");
			}
		}
	}
	/**
	 * this method checks every morning at 8'O clock and removes all temporary assigned slots to employees
	 * deletes all applications for the day
	 */
	@Scheduled(cron="0 0 8 * * *")
	public void updateEveryDay() {
		logger.info("it will update every day at 8'0 clock morning");
		List<ParkingSpotApplications> applicationsList=parkingSpotApplicationRepo.findAll();
//		if(LocalTime.now().equals(LocalTime.of(9, 31))) {
//			List<Employee> employeeList=employeeRepo.findAll();
//			LocalDate curDate=LocalDate.now();
//			
//			for(Employee employee:employeeList) {
//				Period period=Period.between(employee.getJoiningDate(), curDate);
//				if(employee.getEmployeeRole().equalsIgnoreCase("manager")|| period.getYears()>=15) {
//					continue;
//				}
//				else {
//					employee.setParkingSlot(null);
//					employeeRepo.save(employee);
//				}
//			}
//		}
//		System.out.println(LocalTime.now().(LocalTime.of(9, 40)));
		if(applicationsList.size()>0) {
			for(ParkingSpotApplications applicants: applicationsList) {
				applicants.setEmpid(null);
				applicants.setParkingSlot(null);
				parkingSpotApplicationRepo.delete(applicants);
			}
		}
//		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm");
//		String currentTime=LocalTime.now().format(formatter);
//		LocalTime specificTime=LocalTime.of(9, 52);
//		if(LocalTime.parse(currentTime).compareTo(specificTime)==0) {
//			logger.info("inside 9 36");
//			for(ParkingSpotApplications applicants: applicationsList) {
//				applicants.setParkingSlot(null);
//				parkingSpotApplicationRepo.delete(applicants);
//			}
//		}
	
	}
	/**
	 * this method allocates slots automatically to the applicants and remaining applicants will get rejected
	 */
	@Scheduled(fixedRate=100000)
	public void allocateSlots() {
		logger.info("allocate slots automatically till slots available then rejects remaining employees");
		List<ParkingSpotApplications> applicationsList=parkingSpotApplicationRepo.findAll();
		List<AvailableSpots> availableSpotsList=availableSpotRepo.findAll();
		if(applicationsList.size()!=0) {
			int randomIndex=new Random().nextInt(applicationsList.size());
			for(AvailableSpots spot:availableSpotsList) {
				Employee employee=employeeRepo.findById(applicationsList.get(randomIndex).getEmpid().getEmployeeSapCode())
						.orElseThrow(()->new EmployeeNotFoundException("employee not found"));
				if(applicationsList.get(randomIndex).isStatus().equals(ApplicationStatus.Accepted) || spot.getStatus().equals(SlotStatus.NotAvailable)) {
					continue;
				}else {
					applicationsList.get(randomIndex).setStatus(ApplicationStatus.Accepted);
					applicationsList.get(randomIndex).setParkingSlot(spot.getParkingSlot());
					parkingSpotApplicationRepo.save(applicationsList.get(randomIndex));
					spot.setStatus(SlotStatus.NotAvailable);
					availableSpotRepo.save(spot);
					employeeRepo.save(employee);
				}
		}
		}
		//this is used to reject the employee application
		List<AvailableSpots> availableSlots=availableSpotRepo.findByStatus(SlotStatus.Available);
			if(availableSlots.size()==0) {
			List<ParkingSpotApplications> applications=parkingSpotApplicationRepo.findAll();
			for(ParkingSpotApplications p:applications) {
				if(p.isStatus().equals(ApplicationStatus.Applied)) {
					p.setStatus(ApplicationStatus.Rejected);
					parkingSpotApplicationRepo.save(p);
				}
			}
		}
	}
}
