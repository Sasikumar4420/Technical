package com.demo.movieticket.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.movieticket.controller.ReserveController;
import com.demo.movieticket.dto.ReservedSeatRequestDto;
import com.demo.movieticket.dto.ResponseDto;
import com.demo.movieticket.service.ReserveService;

@SpringBootTest
public class ReserveControllerTest {
	@Mock
	ReserveService reserveService;
	@InjectMocks
	ReserveController reserveController;
	@Test
	void reservedSeat() {
		List<ReservedSeatRequestDto> requestDtos=new ArrayList<>();
		ReservedSeatRequestDto reservedSeatRequestDto=new ReservedSeatRequestDto();
		reservedSeatRequestDto.setSeatName("A1");
		requestDtos.add(reservedSeatRequestDto);
		List<ResponseDto> dtos=new ArrayList<>();
		ResponseDto responseDto=new ResponseDto();
		responseDto.setCode("1001");
		responseDto.setMessage("Successfully Ticket Reserved...");
		dtos.add(responseDto);
		when(reserveService.reserved(1l,1l,requestDtos)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result=reserveController.reserved(1l,1l,requestDtos);
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}

}
