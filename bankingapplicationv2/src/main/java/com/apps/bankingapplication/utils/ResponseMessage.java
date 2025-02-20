package com.apps.bankingapplication.utils;

public interface ResponseMessage {
	
	String ERROR_MESSAGE_NO_VALID_DETAILS = "Please check your credentials";
	String SUCCESS_CODE = "Success";
	String ERROR_MESSAGE_INVALID_ACCOUNT_NUMBER = "Please enter the valid account number";
	String ERROR_MESSAGE_NO_MATCH_FOUND = "Data not found";
	String ERROR_MESSAGE_ACCOUNT_LIMIT_EXCEEDED = "Beneficiary account limit exceeded";
	String ERROR_MESSAGE_BANK_NOT_FOUND = "Please enter the valid account number";
	String ERROR_MESSAGE_CUSTOMER_NOT_FOUND = "Please enter a valid Customer Id";
	String ERROR_MESSAGE_ACCOUNT_ALREADY_EXISTS = "Account number exists already.Please check the account number entered";
	String ERROR_MESSAGE_PASSWORD_ATTEMPT_EXCEEDED = "You have exceeded  limits of incorrect password. Kindly update Password";
	String ERROR_MESSAGE_PASSWORD_INCORRECT = "Incorrect password";



	

}
