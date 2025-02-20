package com.hcl.bankApplication.controller;

import java.util.List;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankApplication.dto.MonthlyTransactions;
import com.hcl.bankApplication.dto.TransactionDto;
import com.hcl.bankApplication.entity.Transactions;
import com.hcl.bankApplication.service.BankService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	BankService bankService;
	private final static org.apache.juli.logging.Log logger=LogFactory.getLog(BankController.class);
	/**
	 * 
	 * @param transactionDto
	 * @return it takes transactionDetails and returns the addedTransaction
	 */
	@PostMapping("/addTransaction")
	public ResponseEntity<Transactions> addTransaction( @RequestBody TransactionDto transactionDto){		
		try {
			Transactions transaction=bankService.convertTransactionDto(transactionDto);
			Transactions t=bankService.addTransaction(transaction);
			if(t !=null) {
				return new ResponseEntity<>(t,HttpStatus.CREATED);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
//		Transactions transaction=bankService.convertTransactionDto(transactionDto);
//		
//		try { 
//			Transactions t=bankService.addTransaction(transaction);
//			return new ResponseEntity<>(t,HttpStatus.CREATED);
//		}
//		catch(Exception e) {
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
	}
	/**
	 * 
	 * @param monthAndYear
	 * @return that particular monthAndYear data
	 */
	@GetMapping("/getTransactionData/{monthAndYear}")
	public ResponseEntity<MonthlyTransactions> getMonthlyTransactions(@Valid @PathVariable String monthAndYear){
		String[] data=monthAndYear.split("-");
		String month=data[0];
		String year=data[1];
		MonthlyTransactions mt=bankService.getMonthlyTransactions(month,year);
		if(mt !=null) {
			return new ResponseEntity<>(mt,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
}
