package com.demo.xyzmc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.xyzmc.dto.ApiResponse;
import com.demo.xyzmc.dto.MobileNumbersDTO;
import com.demo.xyzmc.service.MobileService;
import com.demo.xyzmc.util.Responses;

@SpringBootTest
class MobileControllerTest {

	@Mock
	private MobileService mobileService;
	@InjectMocks
	private MobileController mobileController;

	@Test
	void testGetMobileNumbers() {
		List<Long> availableNumbersList = new ArrayList<>();
		availableNumbersList.add(7036289111l);
		availableNumbersList.add(9951246595l);
		MobileNumbersDTO dto = MobileNumbersDTO.builder()
				.apiResponse(ApiResponse.builder().code(Responses.SUCCESSFULLY_MOBILENUMBER_FETCHED_CODE)
						.message(Responses.SUCCESSFULLY_MOBILENUMBER_FETCHED_MESSAGE).build())
				.mobileNumbers(new PageImpl<>(availableNumbersList)).build();
		Mockito.when(mobileService.getMobileNumbers(anyInt(), anyInt())).thenReturn(dto);
		ResponseEntity<MobileNumbersDTO> response=mobileController.getMobileNumber(1, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(dto,response.getBody());
	}
}
