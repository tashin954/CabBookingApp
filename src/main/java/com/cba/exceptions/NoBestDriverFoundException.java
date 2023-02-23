package com.cba.exceptions;

public class NoBestDriverFoundException extends RuntimeException {

	private static final long serialVersionUID = -6038023136303900085L;

	public NoBestDriverFoundException(String message) {
		super(message);
	}

}
