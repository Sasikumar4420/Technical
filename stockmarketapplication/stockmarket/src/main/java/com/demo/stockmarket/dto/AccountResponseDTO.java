package com.demo.stockmarket.dto;

import java.util.List;

public class AccountResponseDTO {
	private String code;
	private String message;
	private Double balance;
	private List<OrderResponseDTO> orderResponseDTOs;
	private List<TradeResponseDTO> tradeResponseDTOs;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderResponseDTO> getOrderResponseDTOs() {
		return orderResponseDTOs;
	}

	public void setOrderResponseDTOs(List<OrderResponseDTO> orderResponseDTOs) {
		this.orderResponseDTOs = orderResponseDTOs;
	}

	public List<TradeResponseDTO> getTradeResponseDTOs() {
		return tradeResponseDTOs;
	}

	public void setTradeResponseDTOs(List<TradeResponseDTO> tradeResponseDTOs) {
		this.tradeResponseDTOs = tradeResponseDTOs;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountResponseDTO() {
		super();
	}

	public AccountResponseDTO(String code, String message, Double balance, List<OrderResponseDTO> orderResponseDTOs,
			List<TradeResponseDTO> tradeResponseDTOs) {
		super();
		this.code = code;
		this.message = message;
		this.balance = balance;
		this.orderResponseDTOs = orderResponseDTOs;
		this.tradeResponseDTOs = tradeResponseDTOs;
	}

}
