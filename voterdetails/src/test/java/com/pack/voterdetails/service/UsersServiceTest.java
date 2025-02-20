package com.pack.voterdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pack.voterdetails.dto.ResponseDto;
import com.pack.voterdetails.dto.UserDto;
import com.pack.voterdetails.entity.Users;
import com.pack.voterdetails.exception.DataNotFoundException;
import com.pack.voterdetails.exception.InvaliInputException;
import com.pack.voterdetails.repository.UsersRepository;
import com.pack.voterdetails.util.ResponseData;

@SpringBootTest
class UsersServiceTest {

	@InjectMocks
	private UsersServiceImpl usersServiceImpl;
	@Mock
	private UsersRepository usersRepository;

	ResponseDto responseDto = new ResponseDto(ResponseData.SUCCESS_CODE, ResponseData.SUCCESS_MESSAGE, null);

	@Test
	void testValidateRequest() {
		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(new UserDto("sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com"));
		responseDto.setUserDto(new PageImpl<>(userDtoList));

		List<Users> userList = new ArrayList<>();
		userList.add(new Users(1l, "sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com", "sasi@123"));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Users> mockPage = new PageImpl<>(userList, pageable, userList.size());
		Mockito.when(usersRepository.findAllByUserNameContainingIgnoreCaseAndDobBefore(anyString(), any(),
				any(Pageable.class))).thenReturn(mockPage);
		ResponseDto response = usersServiceImpl.validateRequest("komal", 10l, 0, 2);
		assertEquals(ResponseData.SUCCESS_CODE, response.getStatusCode());
	}

	@Test
	void testValidateRequestIfUserNameIsNull() {
		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(new UserDto("sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com"));
		responseDto.setUserDto(new PageImpl<>(userDtoList));

		List<Users> userList = new ArrayList<>();
		userList.add(new Users(1l, "sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com", "sasi@123"));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Users> mockPage = new PageImpl<>(userList, pageable, userList.size());
		Mockito.when(usersRepository.findAllByDobBefore(any(), any(Pageable.class))).thenReturn(mockPage);
		ResponseDto response = usersServiceImpl.validateRequest(null, 10l, 0, 2);
		assertEquals(ResponseData.SUCCESS_CODE, response.getStatusCode());
	}

	@Test
	void testValidateRequestIfAgeIsNull() {
		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(new UserDto("sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com"));
		responseDto.setUserDto(new PageImpl<>(userDtoList));

		List<Users> userList = new ArrayList<>();
		userList.add(new Users(1l, "sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com", "sasi@123"));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Users> mockPage = new PageImpl<>(userList, pageable, userList.size());
		Mockito.when(usersRepository.findAllByUserNameContainingIgnoreCase(anyString(), any(Pageable.class)))
				.thenReturn(mockPage);
		ResponseDto response = usersServiceImpl.validateRequest("sasi", null, 0, 2);
		assertEquals(ResponseData.SUCCESS_CODE, response.getStatusCode());
	}

	@Test
	void testValidateRequestIfBothAgeAndUserNameIsNull() {
		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList.add(new UserDto("sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com"));
		responseDto.setUserDto(new PageImpl<>(userDtoList));

		List<Users> userList = new ArrayList<>();
		userList.add(new Users(1l, "sasi", LocalDate.of(2001, 12, 31), "sasi@gmail.com", "sasi@123"));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Users> mockPage = new PageImpl<>(userList, pageable, userList.size());
		Mockito.when(usersRepository.findAllByUserNameContainingIgnoreCase(anyString(), any(Pageable.class)))
				.thenReturn(mockPage);
		InvaliInputException exception = assertThrows(InvaliInputException.class,
				() -> usersServiceImpl.validateRequest(null, null, 0, 2));
		assertEquals(ResponseData.INVALID_CODE, exception.getErrorCode());
	}
	@Test
	void testIfNoContentFromDatabase() {
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Users> mockPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
		Mockito.when(usersRepository.findAllByDobBefore(any(), any(Pageable.class))).thenReturn(mockPage);
		DataNotFoundException exception = assertThrows(DataNotFoundException.class,
				() -> usersServiceImpl.validateRequest(null, 22l, 0, 2));
		assertEquals(ResponseData.NO_CONTENT_CODE, exception.getErrorCode());
	}
	@Test
	void testIfNoContentFromForUserNameDatabase() {
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Users> mockPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
		Mockito.when(usersRepository.findAllByUserNameContainingIgnoreCase(anyString(), any(Pageable.class))).thenReturn(mockPage);
		DataNotFoundException exception = assertThrows(DataNotFoundException.class,
				() -> usersServiceImpl.validateRequest("sasi", null, 0, 2));
		assertEquals(ResponseData.NO_CONTENT_CODE, exception.getErrorCode());
	}
}
