package com.pack.voterdetails.util;

public interface ResponseData {
	
	/**
	 * success messages
	 */
	String SUCCESS_CODE="200";
	String SUCCESS_MESSAGE="The data is successfully retrieved";
	/**
	 * Exception codes
	 */
	String INVALID_CODE="EX001";
	String INVALID_ERROR_MESSAGE="please provide any one input data";
	/**
	 * 	no content codes
	 */
	String NO_CONTENT_CODE="EX002";
	String NO_CONTENT_MESSAGE="no content found from database based on details";
}
