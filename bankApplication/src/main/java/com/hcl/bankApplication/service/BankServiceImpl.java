package com.hcl.bankApplication.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankApplication.dto.MonthlyTransactions;
import com.hcl.bankApplication.dto.TransactionDto;
import com.hcl.bankApplication.entity.Transactions;
import com.hcl.bankApplication.entity.User;
import com.hcl.bankApplication.repository.TransactionsRepository;
import com.hcl.bankApplication.repository.UserRepository;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	TransactionsRepository transactionsRepo;
	@Override
	public Transactions addTransaction(Transactions transaction) {
		User u=transaction.getUser();
		if(transaction.getTransactionType().equalsIgnoreCase("credit")) {
			Double addAmount=transaction.getAmount()+transaction.getTransaction_amount();
			transaction.setAmount(addAmount);
			u.setAccount_balance(addAmount);
			userRepo.save(u);
			return transactionsRepo.save(transaction);
		}
		else if(transaction.getTransactionType().equalsIgnoreCase("debit")) {
			if(transaction.getAmount() >= transaction.getTransaction_amount()) {
				Double subAmount=transaction.getAmount()- transaction.getTransaction_amount();
				transaction.setAmount(subAmount);
				u.setAccount_balance(subAmount);
				userRepo.save(u);
				return transactionsRepo.save(transaction);
			}
			else {
				return null;
			}

		}
		return null;
	}
	@Override
	public Transactions convertTransactionDto(TransactionDto transactionDto) {
		Transactions t=new Transactions();
		Optional<User> u=userRepo.findById(transactionDto.getUserId());
		t.setAmount(u.get().getAccount_balance());
		t.setDate(transactionDto.getDate());
		t.setId(transactionDto.getId());
		t.setRemarks(transactionDto.getRemarks());
		t.setTransaction_amount(transactionDto.getTransaction_amount());
		t.setTransactionType(transactionDto.getTransactionType());
		t.setUser(u.get());
		return t;
	}
	@Override
	public List<Transactions> getTransactionsByMonthAndYear(String month, String year) {
		List<Transactions> list=transactionsRepo.findAll();
		List<Transactions> newList=new ArrayList<>();
		LocalDate ld=LocalDate.parse(year+"-"+month+"-"+"01");
		for(Transactions t:list) {
			if(t.getDate().getMonth().equals(ld.getMonth()) && t.getDate().getYear() == Integer.parseInt(year)) {
				newList.add(t);
			}
		}
		return newList;
	}
	@Override
	public MonthlyTransactions getMonthlyTransactions(String month, String year) {
		List<Transactions> list=getTransactionsByMonthAndYear(month,year);
		MonthlyTransactions mt=new MonthlyTransactions();
		if(list.size() !=0) {
			mt.setOpeningBalance(list.get(0).getAmount());
			mt.setClosingBalance(list.get(list.size()-1).getAmount());
			Double totalAmountCredited=0.00;
			Double totalAmountDebited=0.00;
			for(Transactions t:list) {
				if(t.getTransactionType().equals("credit")) {
					totalAmountCredited +=t.getTransaction_amount();
				}
				else if(t.getTransactionType().equals("debit")) {
					totalAmountDebited +=t.getTransaction_amount();
				}
			}
			mt.setTotalCredited(totalAmountCredited);
			mt.setTotalDebited(totalAmountDebited);
			return mt;
		}else {
			return null;
		}
	}
	@Override
	public String saveTransaction(String email, Double totalPrice) {
		// TODO Auto-generated method stub
		User emp=userRepo.findByEmail(email).orElse(null);
		if(emp.getAccount_balance()>=totalPrice) {
			Transactions transaction=new Transactions();
			transaction.setAmount(emp.getAccount_balance()-totalPrice);
			transaction.setDate(LocalDate.now());
			transaction.setRemarks("no remarks");
			transaction.setTransaction_amount(totalPrice);
			transaction.setTransactionType("Debit");
			transaction.setUser(emp);
			transactionsRepo.save(transaction);
			emp.setAccount_balance(emp.getAccount_balance()-totalPrice);
			userRepo.save(emp);
			System.out.println("inside banking");
			return "success";
		}else {
			return "failed";
		
		
	}
	
	}

}
