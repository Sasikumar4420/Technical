package com.pack.librarymanagementsystem.util;

public interface ErrorData {
	/**
	 * Error Codes
	 */
	String userNotFoundErrorCode="LIB001";
	String bookNotFoundErrorCode="LIB002";
	String bookAreadyReturnedErrorCode="LIB003";
	String borrowedBookWithUserNotFoundException="LIB004";
	String borrowedBookAndReturnUserMismatchErrorCode="LIB005";
	String conentNotFoundInThisPageErrorCode="LIB006";
	String noBorrowingsInThisMonthErrorCode="LIB007";
	String invalidInputErrorCode="LIB008";
	/**
	 * Error Messages
	 */
	
	String userNotFoundErrorMessage="user doesn't exist";
	String bookNotFoundErrorMessage="Book with this id doesnt exist";
	String bookAreadyReturnedErrorMessage="Book with id is not borrowed right now or already returned";
	String borrowedBookWithUserNotFoundErrorMessage="Book with this id and  user doesnt exist";
	String borrowedBookAndReturnUserMismatchErrorMessage="the borrowed user must return the book";
	String conentNotFoundInThisPageErrorMessage="In this page no content available";
	String noBorrowingsInThisMonthErrorMessage="no borrowings happen in this month and year";
	String invalidInputErrorMessage="the input date format must be MMM-yyyy";
	
}
