package com.demo.xyzmc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.demo.xyzmc.dto.MobileNumbersDTO;
import com.demo.xyzmc.entity.MobileNumber;
import com.demo.xyzmc.entity.Status;
import com.demo.xyzmc.exception.NoAvailableMobileNumberException;
import com.demo.xyzmc.repository.MobileNumberRepository;
import com.demo.xyzmc.util.Responses;

@SpringBootTest
class MobileServiceTest {

	@Mock
	private MobileNumberRepository mobileNumberRepository;
	@InjectMocks
	private MobileServiceImpl mobileServiceImpl;

	@Test
	void testGetAvailableNumbers() {
		List<MobileNumber> list = new ArrayList<>();
		list.add(MobileNumber.builder().id(1l).mobileNumber(7036289111l).numberStatus(Status.AVAILABLE).build());
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<MobileNumber> mockPage = new PageImpl<>(list, pageable, list.size());
		Mockito.when(mobileNumberRepository.findAllByNumberStatus(any(), any())).thenReturn(mockPage);
		MobileNumbersDTO response = mobileServiceImpl.getMobileNumbers(1, 1);
		assertEquals(Responses.SUCCESSFULLY_MOBILENUMBER_FETCHED_CODE, response.getApiResponse().getCode());
	}

	@DisplayName("No available numbers")
	@Test
	void testGetNumbers() {
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<MobileNumber> mockPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
		Mockito.when(mobileNumberRepository.findAllByNumberStatus(any(), any())).thenReturn(mockPage);
		NoAvailableMobileNumberException exception = assertThrows(NoAvailableMobileNumberException.class,
				() -> mobileServiceImpl.getMobileNumbers(1, 1));
		assertEquals(Responses.NO_AVAILABLE_NUMBERS_MESSAGE, exception.getMessage());
	}
}
