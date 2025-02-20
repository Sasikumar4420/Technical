package com.pack.favouritepayee.util;

public interface ResponseData {

	/**
	 * Success data
	 */
	String SUCCESSFULLY_ADDED_CODE = "200";
	String SUCCESSFULLY_ADDED_MESSAGE = "your favourite account is successfully added";

	/**
	 * Error codes
	 */
	String CUSTOMER_NOTFOUND_CODE = "EX1001";
	String IBAN_NOTFOUND_CODE = "EX1002";
	String ACCOUNT_ALREADY_ADDED_CODE = "EX1003";
	String ACCOUNT_NAME_ALREADY_USED_CODE = "EX1004";
	String FAVOURITE_ACCOUNTS_LIMIT_EXCEEDED_CODE="EX1005";

	/**
	 * Error message
	 */
	String CUSTOMER_NOTFOUND_MESSAGE = "customer with this id is not found";
	String IBAN_NOTFOUND_MESSAGE = "given iban number is invalid";
	String ACCOUNT_ALREADY_ADDED_MESSAGE = "you have already added this account";
	String ACCOUNT_NAME_ALREADY_USED_MESSAGE = "the given account name is already used please enter unique one";
	String FAVOURITE_ACCOUNTS_LIMIT_EXCEEDED_MESSAGE="you have exceeded your limit of adding accounts";
	
	/**
	 * FienClient codes
	 */
	String FEIGN_SUCCESSFULL_CODE="200";
	String FEIGN_SUCCESSFULL_MESSAGE="Bank name was successfully fetched";
	String EXCEPTION_CODE_NO_BANK_FOUND="EX1002";
	String EXCEPTION_MESSAGE_NO_BANK_FOUND="Bank was not found";

}
