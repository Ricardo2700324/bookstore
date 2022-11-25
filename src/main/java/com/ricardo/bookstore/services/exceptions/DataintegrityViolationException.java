package com.ricardo.bookstore.services.exceptions;

public class DataintegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataintegrityViolationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataintegrityViolationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
