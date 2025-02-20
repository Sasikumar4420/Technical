package com.demo.xyzmc.service;

import com.demo.xyzmc.dto.MobileNumbersDTO;

public interface MobileService {

	MobileNumbersDTO getMobileNumbers(Integer pageNumber, Integer pageSize);

}
