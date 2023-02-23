package com.cba.exceptions;

public class CustomerIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7464246217636192513L;

	public CustomerIdNotFoundException(String message) {
		super(message);
	}

}
