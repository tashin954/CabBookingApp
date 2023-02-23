package com.cba.exceptions;

public class NoTripsOnThisDateException extends RuntimeException {

	private static final long serialVersionUID = 6820317150237545444L;

	public NoTripsOnThisDateException(String message) {
		super(message);
	}

}
