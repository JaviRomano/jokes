package com.fdez_rumi_jokes.app.exception;

public class DataIntegrityException extends RuntimeException{
	
	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}
}