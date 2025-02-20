package com.demo.stockmarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.OrderRequestDTO;
import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.DailyStockPrice;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.entity.Trade;
import com.demo.stockmarket.exception.AccountNotFoundException;
import com.demo.stockmarket.exception.InsufficientBalanceException;
import com.demo.stockmarket.exception.StockNotFoundException;
import com.demo.stockmarket.repository.AccountRepository;
import com.demo.stockmarket.repository.DailyStockPriceRepository;
import com.demo.stockmarket.repository.OrderRepository;
import com.demo.stockmarket.repository.StockRepository;
import com.demo.stockmarket.repository.TradeRepository;
import com.demo.stockmarket.util.Responses;

@SpringBootTest
class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private DailyStockPriceRepository dailyStockPriceRepository;
	@Mock
	private StockRepository stockRepository;
	@Mock
	private TradeRepository tradeRepository;
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	OrderRequestDTO requestDto = new OrderRequestDTO(1l, 1l, 10);
	ApiResponse response = new ApiResponse(Responses.ORDERING_STOCK_CODE, Responses.ORDERING_STOCK_MESSAGE);
	Account account = new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 1000.0);
	Stock stock = new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0);
	DailyStockPrice stockPrice = new DailyStockPrice(1l, stock, LocalDate.now().minusDays(1), 50.0);
	Trade trade=new Trade(1l, stock, LocalDateTime.now(), account, 2, 200.00);
	@Test
	void testOrderStocks() {
		Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
		Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(dailyStockPriceRepository.findFirstByStockOrderByDateDesc(any())).thenReturn(Optional.of(stockPrice));
		Mockito.when(tradeRepository.findByStockId(any())).thenReturn(Optional.of(trade));
		ApiResponse serviceResponse = orderServiceImpl.orderStock(requestDto);
		assertEquals(Responses.ORDERING_STOCK_CODE, serviceResponse.getCode());
		assertEquals(Responses.ORDERING_STOCK_MESSAGE, serviceResponse.getMessage());
	}

	@Test
	void testOrderStocksIfAccountNotFound() {
		Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());
		AccountNotFoundException exception=assertThrows(AccountNotFoundException.class, ()->orderServiceImpl.orderStock(requestDto));
		assertEquals(Responses.ACCOUNT_NOT_FOUND_MESSAGE, exception.getMessage());
	}
	@Test
	void testOrderStocksIfStockNotFound() {
		Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
		Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());
		StockNotFoundException exception=assertThrows(StockNotFoundException.class, ()->orderServiceImpl.orderStock(requestDto));
		assertEquals(Responses.STOCK_NOT_FOUND_MESSAGE, exception.getMessage());
	}
	@Test
	void testOrderStocksIfInsufficientBalance() {
		Account account = new Account(1l, "sasi", "sasi@gmail.com", "sasi@123", "JUI1234", 10.0);
		Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
		Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(dailyStockPriceRepository.findFirstByStockOrderByDateDesc(any())).thenReturn(Optional.of(stockPrice));
		InsufficientBalanceException exception=assertThrows(InsufficientBalanceException.class, ()->orderServiceImpl.orderStock(requestDto));
		assertEquals(Responses.INSUFFICIENT_BALANCE_TO_ORDER_MESSAGE+"0.2", exception.getMessage());
	}
	@Test
	void testOrderStocksIfSameStockAgainBought() {
		Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
		Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(dailyStockPriceRepository.findFirstByStockOrderByDateDesc(any())).thenReturn(Optional.of(stockPrice));
		ApiResponse serviceResponse = orderServiceImpl.orderStock(requestDto);
		assertEquals(Responses.ORDERING_STOCK_CODE, serviceResponse.getCode());
		assertEquals(Responses.ORDERING_STOCK_MESSAGE, serviceResponse.getMessage());
	}
	@Test
	void testOrderStocksIfDailyPriceNotFound() {
		Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
		Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(dailyStockPriceRepository.findFirstByStockOrderByDateDesc(any())).thenReturn(Optional.empty());
		Mockito.when(tradeRepository.findByStockId(any())).thenReturn(Optional.of(trade));
		StockNotFoundException exception=assertThrows(StockNotFoundException.class, ()->orderServiceImpl.orderStock(requestDto));
		assertEquals(Responses.STOCK_NOT_FOUND_MESSAGE, exception.getMessage());
	}
}
