package com.demo.xyzmc.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.xyzmc.dto.ApiResponse;
import com.demo.xyzmc.dto.MobileNumbersDTO;
import com.demo.xyzmc.entity.Customer;
import com.demo.xyzmc.entity.MobileNumber;
import com.demo.xyzmc.entity.Status;
import com.demo.xyzmc.kafka.KafkaJsonProducer;
import com.demo.xyzmc.kafka.KafkaProducer;
import com.demo.xyzmc.repository.MobileNumberRepository;
import com.demo.xyzmc.util.Responses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {

	private final MobileNumberRepository mobileNumberRepository;
	
	private final KafkaProducer kafkaProducer;
	private final KafkaJsonProducer kafkaJsonProducer;
	/**
	 * Gets List of available mobile numbers
	 * 
	 * @param pageNumber to define pageNumber
	 * @param pageSize   to limit number of values to return
	 * @return response with list of mobile numbers with status OK
	 */
	@Override
	public MobileNumbersDTO getMobileNumbers(Integer pageNumber, Integer pageSize) {
		log.info("getiing mobile numbers method started");
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<MobileNumber> numberlist = mobileNumberRepository.findAllByNumberStatus(Status.AVAILABLE, pageable);
		if (numberlist.getContent().isEmpty()) {
			log.warn(Responses.NO_AVAILABLE_NUMBERS_MESSAGE);
//			throw new NoAvailableMobileNumberException(Responses.NO_AVAILABLE_NUMBERS_MESSAGE);
			return MobileNumbersDTO.builder().apiResponse(ApiResponse.builder().code("Ex100111").message("no content found").build()).mobileNumbers(new PageImpl<>(Collections.emptyList())).build();
		}
		List<Long> numbersList = numberlist.getContent().stream().map(MobileNumber::getMobileNumber).toList();

		ApiResponse response = ApiResponse.builder().code(Responses.SUCCESSFULLY_MOBILENUMBER_FETCHED_CODE)
				.message(Responses.SUCCESSFULLY_MOBILENUMBER_FETCHED_MESSAGE).build();
		kafkaProducer.sendMessage("mobile numbers are retrieved successfully");
		log.info("message sent -> mobile numbers are retrieved successfully");
		Customer customer=Customer.builder().customerId(1l).aadhar(977675l).dob(LocalDate.now()).email("komal@gmail.com").name("komal").build();
		kafkaJsonProducer.sendMessage(customer);
		log.info("message sent ->"+customer.toString());
		return MobileNumbersDTO.builder().apiResponse(response).mobileNumbers(new PageImpl<>(numbersList)).build();
	}

}
