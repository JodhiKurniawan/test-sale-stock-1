package com.sale_stock_indonesia.exercise.exercise1.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectUserPreferenceException;
import com.sale_stock_indonesia.exercise.exercise1.model.UserPreference;

/// This class handle read operations to text files that stores one tab-separated information per line. 
/// The information goes in the following format
///     user_id\tproduct_id
/// where user_id is a textual representation of an integer,
/// product_id is also a textual representation of an integer,
/// score is also a textual representation of a floating-point value,
/// and timestamp is also a textual representation of an integer,
public class UserPreferenceFileReader extends TSVFileReader {
	public UserPreferenceFileReader(String filename) throws FileNotFoundException {
		super(filename);
	}
	
	public UserPreference getUserPreference() throws IOException, IncorrectUserPreferenceException {
		UserPreference result = new UserPreference(); 
		String[] line = getTSVLine();
		if (line == null) {
			return null;
		} else if (line.length != 4) {
			throw new IncorrectUserPreferenceException(line); 
		} else {
			result.setUserId(Integer.parseInt(line[0].trim()));
			result.setProductId(Integer.parseInt(line[1].trim()));
			result.setScore(Double.parseDouble(line[2].trim()));
			result.setTimestamp(Integer.parseInt(line[3].trim()));
		}
		return result;
	}
}
