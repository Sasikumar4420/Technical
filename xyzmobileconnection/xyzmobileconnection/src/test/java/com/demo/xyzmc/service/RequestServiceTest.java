package com.demo.xyzmc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.xyzmc.dto.ConnectionRequestResponseDTO;
import com.demo.xyzmc.dto.RequestDTO;
import com.demo.xyzmc.entity.ConnectionStatus;
import com.demo.xyzmc.entity.Customer;
import com.demo.xyzmc.entity.MobileNumber;
import com.demo.xyzmc.entity.PlanType;
import com.demo.xyzmc.entity.Request;
import com.demo.xyzmc.entity.Status;
import com.demo.xyzmc.entity.TalkTimePlan;
import com.demo.xyzmc.exception.AlreadyConnectionEnabledException;
import com.demo.xyzmc.exception.AlreadyRequestedException;
import com.demo.xyzmc.exception.InvalidTalkTimePlanException;
import com.demo.xyzmc.exception.NoAvailableMobileNumberException;
import com.demo.xyzmc.repository.CustomerRepository;
import com.demo.xyzmc.repository.MobileNumberRepository;
import com.demo.xyzmc.repository.RequestRepository;
import com.demo.xyzmc.repository.TalkTimePlansRepository;
import com.demo.xyzmc.util.Responses;

@SpringBootTest
class RequestServiceTest {
	@Mock
	private RequestRepository requestRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private MobileNumberRepository mobileNumberRepository;
	@Mock
	private TalkTimePlansRepository talkTimePlansRepository;

	@InjectMocks
	private RequestServiceImpl requestServiceImpl;

	RequestDTO request = new RequestDTO("sanjay", LocalDate.of(2001, 12, 31), "sanjay@gmail.com", 123456789012l,
			7036289111l);
	Customer customer = new Customer(1l, "sasi", "sasi123", LocalDate.now(), 763986241274l);
	TalkTimePlan plan = new TalkTimePlan(1l, PlanType.ANUALLY, 1499.0, 365);
	Request requests = new Request(1l, LocalDateTime.now(), 9876598778l, plan, ConnectionStatus.PENDING, customer);
	MobileNumber mobileNumber = new MobileNumber(1l, 7036289111l, Status.AVAILABLE);

	@Test
	void testAddRequest() {
		Mockito.when(customerRepository.findByAadhar(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(mobileNumberRepository.findByMobileNumberAndNumberStatus(anyLong(), any()))
				.thenReturn(Optional.of(mobileNumber));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(requestRepository.findByCustomerAndConnectionStatusNot(any(), any())).thenReturn(Optional.empty());
		Mockito.when(talkTimePlansRepository.findByPlanType(any())).thenReturn(Optional.of(plan));
		ConnectionRequestResponseDTO response = requestServiceImpl.addRequest(request, PlanType.MONTHLY);
		assertEquals(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_CODE, response.getApiResponse().getCode());
	}

	@Test
	void testAddRequestCustomerEmpty() {
		Mockito.when(customerRepository.findByAadhar(anyLong())).thenReturn(Optional.empty());
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(mobileNumberRepository.findByMobileNumberAndNumberStatus(anyLong(), any()))
				.thenReturn(Optional.of(mobileNumber));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(requestRepository.findByCustomerAndConnectionStatusNot(any(), any())).thenReturn(Optional.empty());
		Mockito.when(talkTimePlansRepository.findByPlanType(any())).thenReturn(Optional.of(plan));
		ConnectionRequestResponseDTO response = requestServiceImpl.addRequest(request, PlanType.MONTHLY);
		assertEquals(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_CODE, response.getApiResponse().getCode());
	}

	@Test
	void testMobileNumberInvalid() {
		Mockito.when(customerRepository.findByAadhar(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(mobileNumberRepository.findByMobileNumberAndNumberStatus(anyLong(), any()))
				.thenReturn(Optional.empty());
		NoAvailableMobileNumberException exception = assertThrows(NoAvailableMobileNumberException.class,
				() -> requestServiceImpl.addRequest(request, PlanType.MONTHLY));
		assertEquals(Responses.NO_AVAILABLE_NUMBERS_MESSAGE, exception.getMessage());

	}
	@Test
	void testAlreadyRequested() {
		Mockito.when(customerRepository.findByAadhar(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(mobileNumberRepository.findByMobileNumberAndNumberStatus(anyLong(), any()))
				.thenReturn(Optional.of(mobileNumber));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.of(requests));
		AlreadyRequestedException exception=assertThrows(AlreadyRequestedException.class,
				() -> requestServiceImpl.addRequest(request, PlanType.MONTHLY));
		assertEquals(Responses.ALREADY_REQUESTED_MESSAGE, exception.getMessage());

	}
	@Test
	void testAlreadyConnectionEnabled() {
		Mockito.when(customerRepository.findByAadhar(anyLong())).thenReturn(Optional.of(customer));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(mobileNumberRepository.findByMobileNumberAndNumberStatus(anyLong(), any()))
				.thenReturn(Optional.of(mobileNumber));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(requestRepository.findByCustomerAndConnectionStatusNot(any(), any())).thenReturn(Optional.of(requests));
		AlreadyConnectionEnabledException exception=assertThrows(AlreadyConnectionEnabledException.class,
				() -> requestServiceImpl.addRequest(request, PlanType.MONTHLY));
		assertEquals(Responses.ALREADY_REQUESTED_CONNECTION_ENABLED_MESSAGE, exception.getMessage());

	}
	@Test
	void testInvalidTalkTimePlan() {
		Mockito.when(customerRepository.findByAadhar(anyLong())).thenReturn(Optional.empty());
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(mobileNumberRepository.findByMobileNumberAndNumberStatus(anyLong(), any()))
				.thenReturn(Optional.of(mobileNumber));
		Mockito.when(requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		Mockito.when(requestRepository.findByCustomerAndConnectionStatusNot(any(), any())).thenReturn(Optional.empty());
		Mockito.when(talkTimePlansRepository.findByPlanType(any())).thenReturn(Optional.empty());
		InvalidTalkTimePlanException exception=assertThrows(InvalidTalkTimePlanException.class,
				() -> requestServiceImpl.addRequest(request, PlanType.MONTHLY));
		assertEquals(Responses.INVALID_TALKTIME_PLAN_MESSAGE, exception.getMessage());
	}
}
