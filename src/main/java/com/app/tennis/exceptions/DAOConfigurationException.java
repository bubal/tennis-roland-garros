package com.app.tennis.exceptions;

public class DAOConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOConfigurationException() {
		super();
	}

	public DAOConfigurationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOConfigurationException(String message) {
		super(message);
	}

	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}

}
