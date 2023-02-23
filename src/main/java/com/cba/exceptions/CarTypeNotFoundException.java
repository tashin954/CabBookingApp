package com.cba.exceptions;

public class CarTypeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2407931477738951487L;

	public CarTypeNotFoundException(String message) {
		super(message);
	}

}
