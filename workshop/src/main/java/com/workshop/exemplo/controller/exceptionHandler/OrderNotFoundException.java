package com.workshop.exemplo.controller.exceptionHandler;

public class OrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4491648165653486667L;
	
	public OrderNotFoundException (String msg) {
		super(msg);
	}
}
