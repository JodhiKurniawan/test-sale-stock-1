package com.sale_stock_indonesia.exercise.exercise1.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sale_stock_indonesia.exercise.util.LineBasedTextFileReader;

/// This class handle read operations to text files that stores one tab-separated information per line. 
public class TSVFileReader extends LineBasedTextFileReader {

	public TSVFileReader(String filename) throws FileNotFoundException {
		super(filename);
	}
	
	public String[] getTSVLine() throws IOException {
		String line = getLine();
		return (line == null) ? null : line.split("\t");
	}
}
