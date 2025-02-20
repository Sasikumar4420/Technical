package com.demo.stockmarket.util;

public interface Responses {

	/**
	 * Success Responses
	 */

	String DISPLAY_DATA_CODE="SUCCESS001";
	String DISPLAY_DATA_MESSAGE="Data is fetched successfully";
	String ADD_CASH_CODE="SUCCESS002";
	String ADD_CASH_MESSAGE="amount is successfully added";
	String ORDERING_STOCK_CODE="SUCCESS003";
	String ORDERING_STOCK_MESSAGE="stocks are ordered successfully";
	String GET_CURRENT_PRICE_CODE="SUCCESS004";
	String GET_CURRENT_PRICE_MESSAGE="currrent price is successfully fetched:-";
	String GET_HISTORY_PRICE_CODE="SUCCESS005";
	String GET_HISTORY_PRICE_MESSAGE="history of price is successfully fetched";
	

	/**
	 * Exception Codes
	 */

	String ACCOUNT_NOT_FOUND_CODE = "EX1001";
	String ACCOUNT_NOT_FOUND_MESSAGE = "account cannot be found";
	String ORDER_NOT_FOUND_CODE = "EX1002";
	String ORDER_NOT_FOUND_MESSAGE = "Orders are not found";
	String TRADE_NOT_FOUND_CODE = "EX1003";
	String TRADE_NOT_FOUND_MESSAGE = "trades not found";
	String STOCK_NOT_FOUND_CODE = "EX1004";
	String STOCK_NOT_FOUND_MESSAGE = "stocks with this id not found";
	String INSUFFICIENT_BALANCE_TO_ORDER_CODE = "EX1005";
	String INSUFFICIENT_BALANCE_TO_ORDER_MESSAGE = "you don't have enough balance to order the given units, but you can buy ";
	String INSUFFICIENT_PAGE_VALUE_MESSAGE = "Please provide valid page details";
	String INSUFFICIENT_PAGE_VALUE_CODE = "EX1006";
	String DAILY_STOCK_PRICE_NOT_FOUND="stock price is not there for the given stock";

}
