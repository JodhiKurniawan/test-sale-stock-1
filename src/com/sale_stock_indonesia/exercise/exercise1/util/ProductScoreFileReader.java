package com.sale_stock_indonesia.exercise.exercise1.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectProductScoreException;
import com.sale_stock_indonesia.exercise.exercise1.model.ProductScore;

/// This class handle read operations to text files that stores one tab-separated information per line. 
/// The information goes in the following format
///     product_id\tproduct_score
/// where product_id is a textual representation of an integer
/// and product_score is also a textual representation of an integer
public class ProductScoreFileReader extends TSVFileReader {
	public ProductScoreFileReader(String filename) throws FileNotFoundException {
		super(filename);
	}
	
	public ProductScore getProductScore() throws IOException, IncorrectProductScoreException {
		ProductScore result = new ProductScore(); 
		String[] line = getTSVLine();
		if (line == null) {
			return null; 
		} else if (line.length != 2) {
			throw new IncorrectProductScoreException(line); 
		} else {
			result.setProductId(Integer.parseInt(line[0].trim()));
			result.setProductScore(Integer.parseInt(line[1].trim()));
		}
		return result;
	}
}
