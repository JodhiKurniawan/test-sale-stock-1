package com.sale_stock_indonesia.exercise.exercise1.exception;

public class IncorrectCombinedDataException extends Exception {
	
	static final long serialVersionUID = 19112519201531112l;

	private IncorrectCombinedDataException() {};
	
	public IncorrectCombinedDataException(String message) {
		super("Incorrect Combined Data : " + message);
	};

}
