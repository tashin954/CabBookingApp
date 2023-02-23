package com.cba.exceptions;

public class DriverIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2569915673298256059L;

	public DriverIdNotFoundException(String message) {
		super(message);
	}

}
