package com.fruitsecommerce.exceptions;



@SuppressWarnings("serial")
public class EcommerceSystemException extends Exception {
	public EcommerceSystemException(String message,Throwable th) {
		super(message,th);
	}

}

