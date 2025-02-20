package com.pack.voterdetails.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pack.voterdetails.dto.ResponseDto;
import com.pack.voterdetails.dto.UserDto;
import com.pack.voterdetails.entity.Users;
import com.pack.voterdetails.exception.DataNotFoundException;
import com.pack.voterdetails.exception.InvaliInputException;
import com.pack.voterdetails.repository.UsersRepository;
import com.pack.voterdetails.util.ResponseData;

@Service
public class UsersServiceImpl implements UsersService {

	Logger logger = LoggerFactory.getLogger(getClass());
	private final UsersRepository usersRepository;

	public UsersServiceImpl(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	/**
	 * This method is used to validate the data based on the request
	 * 
	 * @param userName
	 * @param requiredAge
	 * @param pageNumber
	 * @param pageSize    return responseDto data based on the request
	 */
	@Override
	public ResponseDto validateRequest(String userName, Long requiredAge, Integer pageNumber, Integer pageSize) {
		logger.info("Inside validate request service method");
		LocalDate date = null;
		if (requiredAge != null) {
			date = LocalDate.now().minusYears(requiredAge);
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		if (userName != null && requiredAge != null) {
			logger.info("Inside validate request if both values given");
			Page<Users> usersListBynameAndAge = usersRepository
					.findAllByUserNameContainingIgnoreCaseAndDobBefore(userName, date, pageable);
			return new ResponseDto(ResponseData.SUCCESS_CODE, ResponseData.SUCCESS_MESSAGE,
					convertUsersToUserDto(usersListBynameAndAge));
		} else if (userName == null && requiredAge != null) {
			logger.info("Inside validate request if required age is given");
			Page<Users> usersListByAge = usersRepository.findAllByDobBefore(date, pageable);
			return new ResponseDto(ResponseData.SUCCESS_CODE, ResponseData.SUCCESS_MESSAGE,
					convertUsersToUserDto(usersListByAge));
		} else if (userName != null ) {
			logger.info("Inside validate request if required username is given");
			Page<Users> usersListByname = usersRepository.findAllByUserNameContainingIgnoreCase(userName, pageable);
			return new ResponseDto(ResponseData.SUCCESS_CODE, ResponseData.SUCCESS_MESSAGE,
					convertUsersToUserDto(usersListByname));
		} else {
			logger.error("both the input values are null");
			throw new InvaliInputException(ResponseData.INVALID_CODE, ResponseData.INVALID_ERROR_MESSAGE);
		}
	}

	/**
	 * This method is used to convert users data to userDto
	 * 
	 * @param userList
	 * @return list of userDto values
	 */
	public Page<UserDto> convertUsersToUserDto(Page<Users> userList) {
		if (userList.getContent().isEmpty()) {
			logger.warn("no data retrived from database");
			throw new DataNotFoundException(ResponseData.NO_CONTENT_CODE, ResponseData.NO_CONTENT_MESSAGE);
		}
		List<UserDto> userDtoList = userList.getContent().stream().map(request -> {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(request, userDto);
			return userDto;
		}).toList();
		return new PageImpl<>(userDtoList);

	}

}
