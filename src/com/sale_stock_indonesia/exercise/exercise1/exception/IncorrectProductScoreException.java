package com.sale_stock_indonesia.exercise.exercise1.exception;

import com.sale_stock_indonesia.exercise.util.Common;

public class IncorrectProductScoreException extends Exception {
	
	static final long serialVersionUID = 19112519201531113l;

	private IncorrectProductScoreException() {};

	public IncorrectProductScoreException(String[] list) {
		super("Incorrect Product Score : " + Common.tabListToString(list));
	};

}
