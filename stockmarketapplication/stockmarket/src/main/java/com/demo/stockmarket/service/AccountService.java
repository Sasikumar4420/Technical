package com.demo.stockmarket.service;

import com.demo.stockmarket.dto.AccountResponseDTO;
import com.demo.stockmarket.dto.ApiResponse;

public interface AccountService {

	ApiResponse addCash(Long id, Double amount);

	AccountResponseDTO viewData(Long id, Integer pageNumber, Integer pageSize);

}
