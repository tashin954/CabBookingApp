package com.cba.exceptions;

public class CustomerNotFoundInDatabaseException extends RuntimeException {

	private static final long serialVersionUID = -3763345785019208627L;

	public CustomerNotFoundInDatabaseException(String message) {
		super(message);
	}

}
