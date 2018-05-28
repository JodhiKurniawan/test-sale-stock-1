package com.sale_stock_indonesia.exercise.exercise1.exception;

public class TopNRecommenderException extends Exception {
	
	static final long serialVersionUID = 19112519201531117l;

	private TopNRecommenderException() {};
	
	public TopNRecommenderException(int N) {
		super("Incorrect number of recommendation : " + N);
	}
}
