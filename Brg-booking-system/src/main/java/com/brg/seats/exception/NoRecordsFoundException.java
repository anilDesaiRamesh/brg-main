package com.brg.seats.exception;

public class NoRecordsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoRecordsFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
