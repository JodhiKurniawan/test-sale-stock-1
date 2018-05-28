package com.sale_stock_indonesia.exercise.exercise1.exception;

public class IncorrectSortedListElementException extends Exception {
	
	static final long serialVersionUID = 19112519201531115l;

	private IncorrectSortedListElementException() {};
	
	public IncorrectSortedListElementException(String message) {
		super("Incorrect Serted List element: " + message);
	};

}
