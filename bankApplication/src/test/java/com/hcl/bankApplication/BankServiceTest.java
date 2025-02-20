package com.hcl.bankApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.hcl.bankApplication.dto.TransactionDto;
import com.hcl.bankApplication.entity.Transactions;
import com.hcl.bankApplication.entity.User;
import com.hcl.bankApplication.repository.TransactionsRepository;
import com.hcl.bankApplication.repository.UserRepository;
import com.hcl.bankApplication.service.BankServiceImpl;

@SpringBootTest
public class BankServiceTest {
	
	@InjectMocks
	private BankServiceImpl bankService;
	@Mock
	private TransactionsRepository transactionRepo;
	@Mock
	private UserRepository userRepo;
	@Test
	void testSaveTransactionWhenCredited() {
		User u=new User(2l,"sasi",765454443l,0.0,"sasi@gmail.com");
		LocalDate d=LocalDate.parse("2024-01-01");
		Transactions t=new Transactions(5l,d,"credit",1000.00,"amount credited",500.00,u);
		Mockito.when(transactionRepo.save(any(Transactions.class))).thenReturn(t);
		Mockito.when(userRepo.save(any(User.class))).thenReturn(u);
		Transactions t1=bankService.addTransaction(t);
		assertEquals(t1.getId(),t.getId());
	}
	
	@Test
	void testSaveTransactionWhenDebited() {
		User u=new User(2l,"sasi",765454443l,0.0,"sasi@gmail.com");
		LocalDate d=LocalDate.parse("2024-01-01");
		Transactions t=new Transactions(6l,d,"debit",1000.00,"amount debited",500.00,u);
		Mockito.when(transactionRepo.save(any(Transactions.class))).thenReturn(t);
		Mockito.when(userRepo.save(any(User.class))).thenReturn(u);
		Transactions t1=bankService.addTransaction(t);
		assertEquals(t1.getId(),t.getId());
	}
	@Test
	void testIfDebitedAmountLessThanBalance() {
		User u=new User(2l,"sasi",765454443l,0.0,"sasi@gmail.com");
		LocalDate d=LocalDate.parse("2024-01-01");
		Transactions t=new Transactions(7l,d,"debit",1000.00,"amount debited",1500.00,u);
		Mockito.when(transactionRepo.save(any(Transactions.class))).thenReturn(null);
		Mockito.when(userRepo.save(any(User.class))).thenReturn(null);
		Transactions t1=bankService.addTransaction(t);
		assertNull(t1);
	}
	@Test
	void testInValidInput() {
		User u=new User(2l,"sasi",765454443l,0.0,"sasi@gmail.com");
		LocalDate d=LocalDate.parse("2024-01-01");
		Transactions t=new Transactions(7l,d,"debited",1000.00,"amount debited",1500.00,u);
		Mockito.when(transactionRepo.save(any(Transactions.class))).thenReturn(null);
		Mockito.when(userRepo.save(any(User.class))).thenReturn(null);
		Transactions t1=bankService.addTransaction(t);
		assertNull(t1);
	}
	@Test
	void testConvertTransactionDtoToTransactions() {
		User u=new User(2l,"sasi",765454443l,0.0,"sasi@gmail.com");
		LocalDate d=LocalDate.parse("2024-01-01");
		Transactions t=new Transactions(7l,d,"debited",1000.00,"amount debited",1500.00,u);
		TransactionDto td=new TransactionDto();
		td.setDate(null);
		td.setId(7l);
		td.setRemarks("amount credited");
		td.setTransaction_amount(1500.00);
		td.setTransactionType("credit");
		td.setUserId(u.getId());
		Mockito.when(userRepo.findById(anyLong())).thenReturn(Optional.of(u));
		Transactions t1=bankService.convertTransactionDto(td);
		assertEquals(td.getTransaction_amount(),t1.getTransaction_amount());
	}
	@Test
	void testGetTransactionsByMonthAndYear() {
		List<Transactions> list=bankService.getTransactionsByMonthAndYear("01", "2024");
		Mockito.when(userRepo.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(list);	
	}
//	@Test
//	void testGetMonthlyTransactions() {
//		String month="01";
//		String year="2024";
//		MonthlyTransactions mt=bankService.getMonthlyTransactions(month, year);
//		assertNotNull(mt);
//	}
//	@Test
//	void testGetMonthlyTransactionsIfNoTransactions() {
//		String month="03";
//		String year="2024";
//		MonthlyTransactions mt=bankService.getMonthlyTransactions(month, year);
//		assertNull(mt);
//	}

}
