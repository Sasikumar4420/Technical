package com.demo.stockmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.StockInfo;
import com.demo.stockmarket.dto.StockResponseDTO;
import com.demo.stockmarket.entity.DailyStockPrice;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.exception.StockNotFoundException;
import com.demo.stockmarket.repository.DailyStockPriceRepository;
import com.demo.stockmarket.repository.StockRepository;
import com.demo.stockmarket.util.Responses;

@Service
public class StockServiceimpl implements StockService {

	Logger logger = LoggerFactory.getLogger(getClass());
	private final StockRepository stockRepository;

	private final DailyStockPriceRepository dailyStockPriceRepository;

	public StockServiceimpl(StockRepository stockRepository, DailyStockPriceRepository dailyStockPriceRepository) {
		this.stockRepository = stockRepository;
		this.dailyStockPriceRepository = dailyStockPriceRepository;
	}

	/**
	 * Get current price of stock
	 * 
	 * @param id get stock with id
	 * @return current price of stock with response code and message
	 */
	@Override
	public ApiResponse getStocks(Long id) {
		Stock stock = stockRepository.findById(id).orElseThrow(() -> {
			logger.info(Responses.STOCK_NOT_FOUND_MESSAGE);
			throw new StockNotFoundException(Responses.STOCK_NOT_FOUND_MESSAGE);
		});

		DailyStockPrice dailyStockPrice = dailyStockPriceRepository.findFirstByStockOrderByDateDesc(stock)
				.orElseThrow(() -> {
					logger.info(Responses.DAILY_STOCK_PRICE_NOT_FOUND);
					throw new StockNotFoundException(Responses.STOCK_NOT_FOUND_MESSAGE);
				});

		return new ApiResponse(Responses.GET_CURRENT_PRICE_CODE,
				Responses.GET_CURRENT_PRICE_MESSAGE + dailyStockPrice.getUnitPrice());
	}

	/**
	 * 
	 * Get previous 5 days price of stock
	 * 
	 * @param id get stock with id
	 * @return response message and code with list of previous 5 days price
	 */
	@Override
	public StockResponseDTO getStockhistory(Long id) {

		Stock stock = stockRepository.findById(id).orElseThrow(() -> {
			logger.info(Responses.STOCK_NOT_FOUND_MESSAGE);
			throw new StockNotFoundException(Responses.STOCK_NOT_FOUND_MESSAGE);
		});

		List<DailyStockPrice> dailyStockPrice = dailyStockPriceRepository.findTop5ByStockOrderByDateDesc(stock);
		StockResponseDTO stockResponseDto = new StockResponseDTO(Responses.GET_HISTORY_PRICE_MESSAGE,
				Responses.GET_HISTORY_PRICE_CODE, null);
		List<StockInfo> stockInfo = dailyStockPrice.stream().map(request -> {
			StockInfo info = new StockInfo();
			info.setDate(request.getDate());
			info.setStockId(request.getStock().getStockId());
			info.setUnitPrice(request.getUnitPrice());
			return info;
		}).toList();
		stockResponseDto.setStockInfoList(stockInfo);
		return stockResponseDto;
	}

}
