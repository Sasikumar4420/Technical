package com.hcl.bankApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankApplication.dto.ResponseDto;
import com.hcl.bankApplication.service.BankService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	BankService bankService;
	@GetMapping("/status")
	public ResponseEntity<String> getStatus(@RequestParam String email,@RequestParam Double totalPrice){
		System.out.println("sucuss");
		ResponseDto rd=new ResponseDto();
		String res=bankService.saveTransaction(email,totalPrice);
		rd.setCode("200");
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
