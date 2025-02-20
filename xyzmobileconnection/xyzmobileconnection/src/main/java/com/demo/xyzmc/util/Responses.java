package com.demo.xyzmc.util;

public interface Responses {
	/**
	 * Success messages
	 */
	String SUCCESSFULLY_MOBILENUMBER_FETCHED_CODE = "SUCCESS001";
	String SUCCESSFULLY_MOBILENUMBER_FETCHED_MESSAGE = "mobile numbers are successfully fetched";
	String SUCCESSFULLY_CONNECTION_REQUESTED_CODE = "SUCCESS002";
	String SUCCESSFULLY_CONNECTION_REQUESTED_MESSAGE = "connection request is sent successfully";
	String SUCCESSFYLLY_STATUS_FETCHED_CODE = "SUCCESS003";
	String SUCCESSFULLY_STATUS_FETCHED_MESSAGE = "status is fetched successfully";
	String SUCCESSFULLY_REQUEST_STATUS_UPDATED_CODE = "SUCCESS004";
	String SUCCESSFULLY_REQUEST_STATUS_UPDATED_MESSAGE = "the request status updated successfully";
	/**
	 * Error Messages
	 */
	String REQUEST_NOT_FOUND_CODE = "EX1001";
	String REQUEST_NOT_FOUND_MESSAGE = "request not found";
	String NO_AVAILABLE_NUMBERS_CODE="EX1005";
	String NO_AVAILABLE_NUMBERS_MESSAGE="there are no available numbers to request please visit after some time";
	String ALREADY_REQUESTED_CODE="EX1006";
	String ALREADY_REQUESTED_MESSAGE="your request is already recieved in pending";
	String ALREADY_REQUESTED_CONNECTION_ENABLED_CODE="EX1004";
	String ALREADY_REQUESTED_CONNECTION_ENABLED_MESSAGE="your have already requested and its status is pending or enabled";
	String INVALID_TALKTIME_PLAN_CODE="EX1007";
	String INVALID_TALKTIME_PLAN_MESSAGE="invalid talk time plan you have selected";
}
