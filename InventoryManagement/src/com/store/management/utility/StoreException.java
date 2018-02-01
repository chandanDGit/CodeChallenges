package com.store.management.utility;

public class StoreException extends Exception {

	public StoreException(String message) {
		super(message);
	}

	StoreException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
