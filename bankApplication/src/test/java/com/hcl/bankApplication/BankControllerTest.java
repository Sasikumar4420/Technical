package com.hcl.bankApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bankApplication.controller.BankController;
import com.hcl.bankApplication.dto.MonthlyTransactions;
import com.hcl.bankApplication.dto.TransactionDto;
import com.hcl.bankApplication.entity.Transactions;
import com.hcl.bankApplication.entity.User;
import com.hcl.bankApplication.repository.TransactionsRepository;
import com.hcl.bankApplication.repository.UserRepository;
import com.hcl.bankApplication.service.BankService;


@WebMvcTest
public class BankControllerTest {
	
	@InjectMocks
	private BankController bankController;
	@MockBean
	BankService bankService;
	@MockBean
	UserRepository userRepo;
	@MockBean
	TransactionsRepository transactionsRepo;
	@Autowired
	private MockMvc mockMvc;
	private static ObjectMapper mapper=new ObjectMapper();
	@Test
	void testAdd() {
		assertEquals(5,5);
	}
	@Test
	void testAddTransaction() throws Exception {
		TransactionDto td=new TransactionDto();
		td.setDate(null);
		td.setId(7l);
		td.setRemarks("amount credited");
		td.setTransaction_amount(1000.00);
		td.setTransactionType("credit");
		td.setUserId(2l);
		Transactions t=new Transactions(7l,null,"credit",1000.00,"amount credited",1000.00,new User());
		Mockito.when(bankService.convertTransactionDto(any(TransactionDto.class))).thenReturn(t);
		Mockito.when(bankService.addTransaction(any(Transactions.class))).thenReturn(t);
		String bankToJson=mapper.writeValueAsString(t);
		MvcResult result=mockMvc.perform(post("/bank/addTransaction")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(bankToJson).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		String res=result.getResponse().getContentAsString();
		Transactions t1=mapper.readValue(res, Transactions.class);
		assertEquals(HttpStatus.CREATED.value(),response.getStatus());
		
	}
	
	@Test
	void testAddTransactionifTransactionNull() throws Exception {
		TransactionDto td=new TransactionDto();
		td.setDate(null);
		td.setId(7l);
		td.setRemarks("amount credited");
		td.setTransaction_amount(1000.00);
		td.setTransactionType("credit");
		td.setUserId(2l);
		Transactions t=new Transactions(7l,null,"credit",1000.00,"amount credited",1000.00,new User());
		Mockito.when(bankService.convertTransactionDto(any(TransactionDto.class))).thenReturn(null);
		Mockito.when(bankService.addTransaction(any(Transactions.class))).thenReturn(null);
		String bankToJson=mapper.writeValueAsString(t);
		MvcResult result=mockMvc.perform(post("/bank/addTransaction")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(bankToJson).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		String res=result.getResponse().getContentAsString();
		Transactions t1=mapper.readValue(res, Transactions.class);
		assertEquals(HttpStatus.NO_CONTENT.value(),response.getStatus());
		
	}
	
	@Test
	void testGetMonthlyTransactions() throws Exception {
		MonthlyTransactions mt=new MonthlyTransactions("01-2024",1000.00,15000.00,2000.00,1000.00);
		Mockito.when(bankService.getMonthlyTransactions(anyString(), anyString())).thenReturn(mt);
		MvcResult result=mockMvc.perform(get("/bank/getTransactionData/01-2024")).andReturn();
		String res=result.getResponse().getContentAsString();
		MonthlyTransactions mt1=mapper.readValue(res, MonthlyTransactions.class);
		assertEquals(mt1.getClosingBalance(),mt.getClosingBalance());
	}
	@Test
	void testGetMonthlyTransactionsWithException() throws Exception {
		MonthlyTransactions mt=new MonthlyTransactions("01-2024",1000.00,15000.00,2000.00,1000.00);
//		Mockito.when(bankService.getMonthlyTransactions(anyString(), anyString())).thenThrow());
		MvcResult result=mockMvc.perform(get("/bank/getTransactionData/01-2024")).andReturn();
		String res=result.getResponse().getContentAsString();
		MonthlyTransactions mt1=mapper.readValue(res, MonthlyTransactions.class);
		assertEquals(mt1.getClosingBalance(),mt.getClosingBalance());
	}
	
}
