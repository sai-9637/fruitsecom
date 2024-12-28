package com.fruitsecommerce.exceptions;

@SuppressWarnings("serial")
public class DDLException extends Exception {
	public DDLException(String message,Throwable th) {
		super(message,th);
	}

}
