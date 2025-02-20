package com.demo.stockmarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.demo.stockmarket.dto.AccountResponseDTO;
import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.Order;
import com.demo.stockmarket.entity.OrderStatus;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.entity.Trade;
import com.demo.stockmarket.entity.TransactionType;
import com.demo.stockmarket.exception.AccountNotFoundException;
import com.demo.stockmarket.exception.OrderNotFoundException;
import com.demo.stockmarket.exception.PageException;
import com.demo.stockmarket.exception.TradeNotFoundException;
import com.demo.stockmarket.repository.AccountRepository;
import com.demo.stockmarket.repository.OrderRepository;
import com.demo.stockmarket.repository.TradeRepository;
import com.demo.stockmarket.util.Responses;

@SpringBootTest
class AccountServiceTest {
	@Mock
	AccountRepository accountRepository;

	@Mock
	TradeRepository tradeRepository;

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	Account account = new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0);

	@Test
	void addCash() {
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		ApiResponse response = accountServiceImpl.addCash(1l, 100.0);
		assertEquals(Responses.ADD_CASH_CODE, response.getCode());
	}

	@Test
	void addCash_NoAccountFound() {
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
				() -> accountServiceImpl.addCash(1l, 100.0));
		assertEquals(Responses.ACCOUNT_NOT_FOUND_MESSAGE, exception.getMessage());

	}

	@Test
	void viewData() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(new Trade(1l, new Stock(2l, "ICICI", 2001, 5.0, 2.0, 50.0), LocalDateTime.now().minusDays(2),
				new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0), 5, 500.0));
		tradeList.add(new Trade(2l, new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0), LocalDateTime.now().minusDays(1),
				new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0), 5, 500.0));
		List<Order> orderList = new ArrayList<>();
		orderList.add(new Order(1l, new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0), 5, 500.0, OrderStatus.EXECUTED,
				LocalDateTime.now(), TransactionType.BUY,
				new Account(2l, "vijay", "vijay@gmail.com", "vijay@xyz", "JUI12323444", 1000.0)));
		orderList.add(new Order(2l, new Stock(2l, "ICICI", 2001, 5.0, 2.0, 50.0), 5, 500.0, OrderStatus.PENDING,
				LocalDateTime.now(), TransactionType.SELL,
				new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0)));
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Trade> mockPage = new PageImpl<>(tradeList, pageable, tradeList.size());
		Page<Order> mockPage1 = new PageImpl<>(orderList, pageable, orderList.size());
		when(tradeRepository.findAllByAccountId(Mockito.any(), Mockito.any())).thenReturn(mockPage);
		when(orderRepository.findAllByAccount(Mockito.any(), Mockito.any())).thenReturn(mockPage1);
		AccountResponseDTO response = accountServiceImpl.viewData(1l, 1, 1);
		assertEquals(Responses.DISPLAY_DATA_CODE, response.getCode());

	}

	@Test
	void viewData_NoAccountFound() {
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
				() -> accountServiceImpl.viewData(1l, 1, 1));
		assertEquals(Responses.ACCOUNT_NOT_FOUND_MESSAGE, exception.getMessage());
	}

	@Test
	void viewData_NoTradeFound() {
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Trade> mockPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
		when(tradeRepository.findAllByAccountId(Mockito.any(), Mockito.any())).thenReturn(mockPage);
		TradeNotFoundException exception = assertThrows(TradeNotFoundException.class,
				() -> accountServiceImpl.viewData(1l, 1, 1));
		assertEquals(Responses.TRADE_NOT_FOUND_MESSAGE, exception.getMessage());
	}

	@Test
	void viewdata_NoOrderFound() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(new Trade(1l, new Stock(2l, "ICICI", 2001, 5.0, 2.0, 50.0), LocalDateTime.now().minusDays(2),
				new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0), 5, 500.0));
		tradeList.add(new Trade(2l, new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0), LocalDateTime.now().minusDays(1),
				new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0), 5, 500.0));
		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		Pageable pageable = Mockito.mock(Pageable.class);
		Page<Trade> mockPage = new PageImpl<>(tradeList, pageable, tradeList.size());
		Page<Order> mockPage1 = new PageImpl<>(Collections.emptyList(), pageable, 0);
		when(tradeRepository.findAllByAccountId(Mockito.any(), Mockito.any())).thenReturn(mockPage);
		when(orderRepository.findAllByAccount(Mockito.any(), Mockito.any())).thenReturn(mockPage1);
		OrderNotFoundException exception = assertThrows(OrderNotFoundException.class,
				() -> accountServiceImpl.viewData(1l, 1, 1));
		assertEquals(Responses.ORDER_NOT_FOUND_MESSAGE, exception.getMessage());

	}

	@Test
	void viewdata_NegativePageDetails() {
		PageException exception = assertThrows(PageException.class, () -> accountServiceImpl.viewData(1l, -1, 1));
		assertEquals(Responses.INSUFFICIENT_PAGE_VALUE_MESSAGE, exception.getMessage());

	}

	@Test
	void viewdata_NegativePageDetails1() {
		PageException exception = assertThrows(PageException.class, () -> accountServiceImpl.viewData(1l, 1, -1));
		assertEquals(Responses.INSUFFICIENT_PAGE_VALUE_MESSAGE, exception.getMessage());

	}
}
