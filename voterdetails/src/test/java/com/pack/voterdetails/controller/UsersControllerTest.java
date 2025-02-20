package com.pack.voterdetails.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
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

import com.pack.voterdetails.dto.ResponseDto;
import com.pack.voterdetails.dto.UserDto;
import com.pack.voterdetails.service.UsersService;
import com.pack.voterdetails.util.ResponseData;

@SpringBootTest
class UsersControllerTest {

	@InjectMocks
	private UsersController usersController;
	@Mock
	private UsersService usersService;

	@Test
	void testGetUsersByRequest() {
		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(new UserDto("komal", LocalDate.of(2001, 12, 31), "komal@gmail.com"));
		ResponseDto responseDto = new ResponseDto(ResponseData.SUCCESS_CODE, ResponseData.SUCCESS_MESSAGE,
				new PageImpl<>(userDtoList));
		Mockito.when(usersService.validateRequest(anyString(), anyLong(), anyInt(), anyInt())).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response = usersController.getUsersByRequest("komal", 20l, 0, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("200",response.getBody().getStatusCode());
	}
}
