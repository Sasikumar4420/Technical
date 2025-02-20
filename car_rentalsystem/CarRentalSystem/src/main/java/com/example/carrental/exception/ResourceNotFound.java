package com.example.carrental.exception;

public class ResourceNotFound extends GlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ResourceNotFound() {
        super("resource not found");
    }

    public ResourceNotFound(String message) {
        super(GlobalErrorCode.ERROR_RESOURCE_NOT_FOUND, message);
    }

	
}
