package com.sale_stock_indonesia.exercise.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/// This class handle read operations to text files that stores one data element per line
public class LineBasedTextFileReader {

	private BufferedReader reader = null;

	// Set Default constructor to be private, to that it can't be called 
	private LineBasedTextFileReader() {}
	
	// Caller must specifies the line-base text file name they want to read 
	public LineBasedTextFileReader(String filename) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(new File(filename)));
	}
	
	public String getLine() throws IOException {
		return reader.readLine();
	}
	
	public void close() throws IOException {
		// Reader is activated in constructor, so we are sure it's not NULL  
		reader.close();
	}
	
}
