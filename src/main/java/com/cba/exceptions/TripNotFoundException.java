package com.cba.exceptions;

public class TripNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4789348953257600440L;

	public TripNotFoundException(String message) {
		super(message);
	}

}
