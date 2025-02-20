package com.pack.voterdetails.service;

import com.pack.voterdetails.dto.ResponseDto;

public interface UsersService {

	ResponseDto validateRequest(String userName, Long requiredAge, Integer pageNumber, Integer pageSize);

}
