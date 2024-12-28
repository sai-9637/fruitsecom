package com.fruitsecommerce.exceptions;
@SuppressWarnings("serial")
public class DataException extends Exception {
	public DataException(String message,Throwable th) {
		super(message,th);
	}

}
