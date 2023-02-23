package com.cba.exceptions;

public class CustomersNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7345299129066588157L;

	public CustomersNotFoundException(String message) {
		super(message);

	}

}
