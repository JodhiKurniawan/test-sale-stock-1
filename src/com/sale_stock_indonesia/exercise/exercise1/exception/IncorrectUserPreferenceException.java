package com.sale_stock_indonesia.exercise.exercise1.exception;

import com.sale_stock_indonesia.exercise.util.Common;

public class IncorrectUserPreferenceException extends Exception {
	
	static final long serialVersionUID = 19112519201531116l;

	private IncorrectUserPreferenceException() {};
	
	public IncorrectUserPreferenceException(String[] list) {
		super("Incorrect User Preference : " + Common.tabListToString(list));
	}

}
