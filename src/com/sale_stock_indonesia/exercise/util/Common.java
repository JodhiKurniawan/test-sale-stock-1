package com.sale_stock_indonesia.exercise.util;

public class Common {

	/// Combine a list of string to a a single tab-separated string  
	public static String tabListToString(String[] list) {
		String s = "";
		if (list != null) {
			for (int i = 0; i < list.length; i++ ) {
				s += (list[i] == null) ? "" : list[i];
				s += (i == list.length-1) ? "" : "\t";
			}
		}
		return s;
	}
	
}
