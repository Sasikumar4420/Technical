package com.pack.favouritepayee.service;

import com.pack.favouritepayee.dto.AccountRequestDto;
import com.pack.favouritepayee.dto.ResponseDto;

public interface CustomerService {

	ResponseDto addAccount(Long customerId, AccountRequestDto accountRequestDto);

}
