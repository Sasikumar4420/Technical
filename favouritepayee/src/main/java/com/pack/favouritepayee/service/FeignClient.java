package com.pack.favouritepayee.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.favouritepayee.dto.ClientResponseDto;


@org.springframework.cloud.openfeign.FeignClient(name="BankService",url="http://localhost:1001/banking/banks")
public interface FeignClient {
	@GetMapping()
	public ResponseEntity<ClientResponseDto> getBankName(@RequestParam String iban);
}
