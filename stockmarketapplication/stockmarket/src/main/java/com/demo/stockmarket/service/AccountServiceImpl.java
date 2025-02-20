package com.demo.stockmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.dto.AccountResponseDTO;
import com.demo.stockmarket.dto.ApiResponse;
import com.demo.stockmarket.dto.OrderResponseDTO;
import com.demo.stockmarket.dto.TradeResponseDTO;
import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.Order;
import com.demo.stockmarket.entity.Trade;
import com.demo.stockmarket.exception.AccountNotFoundException;
import com.demo.stockmarket.exception.OrderNotFoundException;
import com.demo.stockmarket.exception.PageException;
import com.demo.stockmarket.exception.TradeNotFoundException;
import com.demo.stockmarket.repository.AccountRepository;
import com.demo.stockmarket.repository.OrderRepository;
import com.demo.stockmarket.repository.TradeRepository;
import com.demo.stockmarket.util.Responses;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final TradeRepository tradeRepository;
	private final OrderRepository orderRepository;

	public AccountServiceImpl(AccountRepository accountRepository, TradeRepository tradeRepository,
			OrderRepository orderRepository) {
		super();
		this.accountRepository = accountRepository;
		this.tradeRepository = tradeRepository;
		this.orderRepository = orderRepository;
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * add cash to user account
	 * 
	 * @param id id with type long to get the user account
	 * @return ApiResponse it contains code and message
	 */
	@Override
	public ApiResponse addCash(Long id, Double amount) {
		logger.info("addCash method in AccountServiceImpl");
		Account account = accountRepository.findById(id).orElseThrow(() -> {
			logger.error("acccount cannot be found");
			throw new AccountNotFoundException(com.demo.stockmarket.util.Responses.ACCOUNT_NOT_FOUND_MESSAGE);
		});
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);
		return new ApiResponse(Responses.ADD_CASH_CODE, Responses.ADD_CASH_MESSAGE);
	}

	/**
	 * get list of orders and trades and balance
	 * 
	 * @param id         id with type long to get user account
	 * @param pageNumber page number to retrieve
	 * @param pageSize   size of each page
	 * @return AccountResponseDTO it contains code, message, balance, list of orders
	 *         and trades
	 */
	@Override
	public AccountResponseDTO viewData(Long id, Integer pageNumber, Integer pageSize) {
		logger.info("viewData method in AccountServiceImpl");
		if (pageNumber < 0 || pageSize < 0) {
			throw new PageException(Responses.INSUFFICIENT_PAGE_VALUE_MESSAGE);
		}
		Account account = accountRepository.findById(id).orElseThrow(() -> {
			logger.error("acccount cannot be found");
			throw new AccountNotFoundException(com.demo.stockmarket.util.Responses.ACCOUNT_NOT_FOUND_MESSAGE);
		});

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Trade> tradeList = tradeRepository.findAllByAccountId(account, pageable);
		if (tradeList.isEmpty()) {
			throw new TradeNotFoundException(Responses.TRADE_NOT_FOUND_MESSAGE);
		}
		Page<Order> orderList = orderRepository.findAllByAccount(account, pageable);
		if (orderList.isEmpty()) {
			throw new OrderNotFoundException(Responses.ORDER_NOT_FOUND_MESSAGE);
		}
		List<OrderResponseDTO> orderResponseDtos = orderList.stream().map(request -> {
			OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
			orderResponseDTO.setStockName(request.getStock().getCompany());
			BeanUtils.copyProperties(request, orderResponseDTO);
			return orderResponseDTO;
		}).toList();

		List<TradeResponseDTO> tradeResponseDTOs = tradeList.stream().map(request -> {
			TradeResponseDTO tradeResponseDTO = new TradeResponseDTO();
			tradeResponseDTO.setStockName(request.getStockId().getCompany());
			BeanUtils.copyProperties(request, tradeResponseDTO);
			return tradeResponseDTO;
		}).toList();

		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		accountResponseDTO.setCode(Responses.DISPLAY_DATA_CODE);
		accountResponseDTO.setMessage(Responses.DISPLAY_DATA_MESSAGE);
		accountResponseDTO.setBalance(account.getBalance());
		accountResponseDTO.setOrderResponseDTOs(orderResponseDtos);
		accountResponseDTO.setTradeResponseDTOs(tradeResponseDTOs);
		return accountResponseDTO;
	}

}
