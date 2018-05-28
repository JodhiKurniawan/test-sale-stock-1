package com.sale_stock_indonesia.exercise.exercise1.exception;

public class IncorrectRecommenderIndexException extends Exception {
	
	static final long serialVersionUID = 19112519201531114l;

	private IncorrectRecommenderIndexException() {};
	
	public IncorrectRecommenderIndexException(int x, int N) {
		super("Incorrect index for a top-" + N + " recommender: " + x);
	};

}
