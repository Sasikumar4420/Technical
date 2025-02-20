package com.demo.xyzmc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.xyzmc.dto.ApiResponse;
import com.demo.xyzmc.dto.ConnectionRequestResponseDTO;
import com.demo.xyzmc.dto.RequestDTO;
import com.demo.xyzmc.entity.PlanType;
import com.demo.xyzmc.service.RequestService;
import com.demo.xyzmc.util.Responses;

@SpringBootTest
class RequestControllerTest {

	@Mock
	private RequestService requestService;
	@InjectMocks
	private RequestController requestController;

	@Test
	void testAddRequest() {
		RequestDTO request = new RequestDTO("sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com", 123456789012l,
				7036289111l);
		ConnectionRequestResponseDTO dto = ConnectionRequestResponseDTO.builder()
				.apiResponse(ApiResponse.builder().code(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_CODE)
						.message(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_MESSAGE).build())
				.requestId(1234l).build();
		Mockito.when(requestService.addRequest(any(), any())).thenReturn(dto);
		ResponseEntity<ConnectionRequestResponseDTO> response = requestController.addRequest(request, PlanType.MONTHLY);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(Responses.SUCCESSFULLY_CONNECTION_REQUESTED_CODE, response.getBody().getApiResponse().getCode());

	}
}
