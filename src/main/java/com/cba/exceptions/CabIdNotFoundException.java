package com.cba.exceptions;

public class CabIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CabIdNotFoundException(String message) {
		super(message);
	}

}
