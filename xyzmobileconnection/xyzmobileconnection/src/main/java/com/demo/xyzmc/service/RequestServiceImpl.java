package com.demo.xyzmc.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.xyzmc.dto.ApiResponse;
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
import com.demo.xyzmc.kafka.KafkaJsonProducer;
import com.demo.xyzmc.repository.CustomerRepository;
import com.demo.xyzmc.repository.MobileNumberRepository;
import com.demo.xyzmc.repository.RequestRepository;
import com.demo.xyzmc.repository.TalkTimePlansRepository;
import com.demo.xyzmc.util.Responses;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {

	private final RequestRepository requestRepository;
	private final CustomerRepository customerRepository;
	private final MobileNumberRepository mobileNumberRepository;
	private final TalkTimePlansRepository talkTimePlansRepository;
	private final KafkaJsonProducer kafkaJsonProducer;

	SecureRandom random = new SecureRandom();

	/**
	 * To add a new Request
	 * 
	 * @param requestDto contains all details like personal details, contact
	 *                   details, documents
	 * @return response as request id with status CREATED
	 */
	@Transactional
	@Override
	public ConnectionRequestResponseDTO addRequest(RequestDTO requestDto, PlanType planType) {
		log.info("adding request started");
		Optional<Customer> customer = customerRepository.findByAadhar(requestDto.getAadhaarCard());
		Customer customerData1 = null;
		if (customer.isEmpty()) {
			log.info("new customer data is saved in db");
			Customer customerData = new Customer(random.nextLong(1000l), requestDto.getCustomerName(),
					requestDto.getEmail(), requestDto.getDob(), requestDto.getAadhaarCard());
			customerData1 = customerRepository.save(customerData);
		}else {
			customerData1=customer.get();
		}
	
		MobileNumber mobileNumber = mobileNumberRepository
				.findByMobileNumberAndNumberStatus(requestDto.getMobileNumber(), Status.AVAILABLE).orElseThrow(() -> {
					log.warn("requested mobile number is not available" + requestDto.getMobileNumber());
					throw new NoAvailableMobileNumberException(Responses.NO_AVAILABLE_NUMBERS_MESSAGE);
				});
		Optional<Request> request = requestRepository.findByRequestPhoneNumberAndCustomerAndConnectionStatus(
				requestDto.getMobileNumber(), customerData1, ConnectionStatus.PENDING);
		if (request.isPresent()) {
			log.error(Responses.ALREADY_REQUESTED_MESSAGE);
			throw new AlreadyRequestedException(Responses.ALREADY_REQUESTED_MESSAGE);
		}
		request = requestRepository.findByCustomerAndConnectionStatusNot(customerData1, ConnectionStatus.REJECTED);
		if (request.isPresent()) {
			log.error(Responses.ALREADY_REQUESTED_CONNECTION_ENABLED_MESSAGE);
			throw new AlreadyConnectionEnabledException(Responses.ALREADY_REQUESTED_CONNECTION_ENABLED_MESSAGE);
		}
		TalkTimePlan plan = talkTimePlansRepository.findByPlanType(planType).orElseThrow(() -> {
			throw new InvalidTalkTimePlanException(Responses.INVALID_TALKTIME_PLAN_MESSAGE);
		});
	
		Long requestId = random.nextLong(10000l);
		Request newRequest = Request.builder().requestId(requestId).requestTime(LocalDateTime.now()).talkTime(plan)
				.requestPhoneNumber(mobileNumber.getMobileNumber()).connectionStatus(ConnectionStatus.PENDING)
				.customer(customerData1).build();
		requestRepository.save(newRequest);
		mobileNumber.setNumberStatus(Status.UNAVAILABLE);
		mobileNumberRepository.save(mobileNumber);
		kafkaJsonProducer.sendMessage(newRequest);
		log.info("connection request is successfully sent with id " + requestId);
		return ConnectionRequestResponseDTO.builder()
				.apiResponse(ApiResponse.builder().code(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_CODE)
						.message(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_MESSAGE).build())
				.requestId(requestId).build();
	}

}
