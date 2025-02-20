package com.demo.stockmarket.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.OrderRequestDTO;
import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.DailyStockPrice;
import com.demo.stockmarket.entity.Order;
import com.demo.stockmarket.entity.OrderStatus;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.entity.Trade;
import com.demo.stockmarket.entity.TransactionType;
import com.demo.stockmarket.exception.AccountNotFoundException;
import com.demo.stockmarket.exception.InsufficientBalanceException;
import com.demo.stockmarket.exception.StockNotFoundException;
import com.demo.stockmarket.repository.AccountRepository;
import com.demo.stockmarket.repository.DailyStockPriceRepository;
import com.demo.stockmarket.repository.OrderRepository;
import com.demo.stockmarket.repository.StockRepository;
import com.demo.stockmarket.repository.TradeRepository;
import com.demo.stockmarket.util.Responses;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	Logger logger = LoggerFactory.getLogger(getClass());

	private final OrderRepository orderRepository;
	private final AccountRepository accountRepository;
	private final DailyStockPriceRepository dailyStockPriceRepository;
	private final StockRepository stockRepository;
	private final TradeRepository tradeRepository;

	public OrderServiceImpl(OrderRepository orderRepository, AccountRepository accountRepository,
			DailyStockPriceRepository dailyStockPriceRepository, StockRepository stockRepository,
			TradeRepository tradeRepository) {
		super();
		this.orderRepository = orderRepository;
		this.accountRepository = accountRepository;
		this.dailyStockPriceRepository = dailyStockPriceRepository;
		this.stockRepository = stockRepository;
		this.tradeRepository = tradeRepository;
	}

	/**
	 * Order Stock based on stock id and quantity
	 * 
	 * @param orderDto contains AccountId, StockId and quantity
	 * @return success code and message
	 */
	@Transactional
	@Override
	public ApiResponse orderStock(OrderRequestDTO orderDto) {
		logger.info("ordering stock - orderStockService");
		Account account = accountRepository.findById(orderDto.getAccountId()).orElseThrow(() -> {
			logger.error(Responses.ACCOUNT_NOT_FOUND_MESSAGE);
			throw new AccountNotFoundException(Responses.ACCOUNT_NOT_FOUND_MESSAGE);
		});
		Stock stock = stockRepository.findById(orderDto.getStockId()).orElseThrow(() -> {
			logger.error(Responses.STOCK_NOT_FOUND_MESSAGE);
			throw new StockNotFoundException(Responses.STOCK_NOT_FOUND_MESSAGE);
		});
		DailyStockPrice stockPrice = dailyStockPriceRepository.findFirstByStockOrderByDateDesc(stock).orElseThrow(()->{
			logger.error(Responses.STOCK_NOT_FOUND_MESSAGE);
			throw new StockNotFoundException(Responses.STOCK_NOT_FOUND_MESSAGE);
		});
		Double stockCurrentPrice = stockPrice.getUnitPrice();
		Double totalPrice = orderDto.getUnits() * stockCurrentPrice;
		Double eligibleToBuy = account.getBalance() / stockCurrentPrice;
		if (account.getBalance() < totalPrice) {
			logger.warn(Responses.INSUFFICIENT_BALANCE_TO_ORDER_MESSAGE);
			Order order = new Order();
			BeanUtils.copyProperties(orderDto, order);
			order.setTotalCost(totalPrice);
			order.setAccount(account);
			order.setOrderedDate(LocalDateTime.now());
			order.setOrderStatus(OrderStatus.REJECTED);
			order.setStock(stock);
			order.setTransactionStatus(TransactionType.BUY);
			orderRepository.save(order);
			throw new InsufficientBalanceException(Responses.INSUFFICIENT_BALANCE_TO_ORDER_MESSAGE + eligibleToBuy);
		}
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		order.setTotalCost(totalPrice);
		order.setAccount(account);
		order.setOrderedDate(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.EXECUTED);
		order.setTransactionStatus(TransactionType.BUY);
		order.setStock(stock);
		account.setBalance(account.getBalance() - totalPrice);
		accountRepository.save(account);
		orderRepository.save(order);
		Optional<Trade> trade = tradeRepository.findByStockId(stock);
		Trade updateUnits = null;
		if (trade.isEmpty()) {
			updateUnits = setTradeDetails(order);
		} else {
			updateUnits = trade.get();
			updateUnits.setBuyDate(LocalDateTime.now());
			updateUnits.setUnits(updateUnits.getUnits() + orderDto.getUnits());
			updateUnits.setTotalPrice(updateUnits.getTotalPrice() + totalPrice);
		}
		tradeRepository.save(updateUnits);
		return new ApiResponse(Responses.ORDERING_STOCK_CODE, Responses.ORDERING_STOCK_MESSAGE);
	}

	public Trade setTradeDetails(Order order) {
		Trade trade = new Trade();
		BeanUtils.copyProperties(order, trade);
		trade.setBuyDate(LocalDateTime.now());
		trade.setTotalPrice(order.getTotalCost());
		trade.setAccountId(order.getAccount());
		trade.setStockId(order.getStock());
		return trade;

	}

}
