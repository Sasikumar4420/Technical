package com.demo.stockmarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.StockResponseDTO;
import com.demo.stockmarket.entity.DailyStockPrice;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.exception.StockNotFoundException;
import com.demo.stockmarket.repository.DailyStockPriceRepository;
import com.demo.stockmarket.repository.StockRepository;
import com.demo.stockmarket.util.Responses;

@SpringBootTest
class StockServiceimplTest {
	@Mock
	private StockRepository stockRepository;

	@Mock
	private DailyStockPriceRepository dailyStockPriceRepository;

	@InjectMocks
	private StockServiceimpl stockService;

	@Test
	void testGetStocks_Success() {
		Stock stock = new Stock(1L, "Company A", 123, 10.5, 1000000.0, 5.0);
		DailyStockPrice dailyStockPrice = new DailyStockPrice(1L, stock, LocalDate.now(), 15.0);

		when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		when(dailyStockPriceRepository.findFirstByStockOrderByDateDesc(stock)).thenReturn(Optional.of(dailyStockPrice));

		ApiResponse response = stockService.getStocks(1L);

		assertEquals(Responses.GET_CURRENT_PRICE_CODE, response.getCode());

	}

	@Test
	void testGetStocks_StockNotFound() {
		when(stockRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(StockNotFoundException.class, () -> stockService.getStocks(1L));
	}

	@Test
	void testGetStocks_DailyStockPriceNotFound() {
		Stock stock = new Stock(1L, "Company A", 123, 10.5, 1000000.0, 5.0);

		when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		when(dailyStockPriceRepository.findFirstByStockOrderByDateDesc(stock)).thenReturn(Optional.empty());

		assertThrows(StockNotFoundException.class, () -> stockService.getStocks(1L));
	}

	@Test
	void testGetStockHistory_Positive() {
		Long stockId = 1L;
		Stock stock = new Stock();
		stock.setStockId(stockId);

		LocalDate date = LocalDate.now();
		List<DailyStockPrice> dailyStockPrices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			DailyStockPrice dailyStockPrice = new DailyStockPrice();
			dailyStockPrice.setStock(stock);
			dailyStockPrice.setDate(date.minusDays(i));
			dailyStockPrice.setUnitPrice(100.0 + i);
			dailyStockPrices.add(dailyStockPrice);
		}

		when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
		when(dailyStockPriceRepository.findTop5ByStockOrderByDateDesc(stock)).thenReturn(dailyStockPrices);

		StockResponseDTO response = stockService.getStockhistory(stockId);

		assertEquals(5, response.getStockInfoList().size());
		assertEquals(Responses.GET_HISTORY_PRICE_MESSAGE, response.getMessage());
	}

	@Test
	void testGetStocks_HistoryStockPriceNotFound() {

		when(stockRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(StockNotFoundException.class, () -> stockService.getStockhistory(1l));
	}

}
