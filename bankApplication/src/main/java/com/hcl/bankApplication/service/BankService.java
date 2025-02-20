package com.hcl.bankApplication.service;

import java.util.List;

import com.hcl.bankApplication.dto.MonthlyTransactions;
import com.hcl.bankApplication.dto.TransactionDto;
import com.hcl.bankApplication.entity.Transactions;


public interface BankService {

	Transactions addTransaction( Transactions transaction);

	Transactions convertTransactionDto(TransactionDto transactionDto);

	List<Transactions> getTransactionsByMonthAndYear(String month, String year);

	MonthlyTransactions getMonthlyTransactions(String month, String year);

	String saveTransaction(String email, Double totalPrice);
	
}
