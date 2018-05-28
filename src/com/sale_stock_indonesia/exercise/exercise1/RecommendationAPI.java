package com.sale_stock_indonesia.exercise.exercise1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectCombinedDataException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectProductScoreException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectRecommenderIndexException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectSortedListElementException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectUserPreferenceException;
import com.sale_stock_indonesia.exercise.exercise1.exception.TopNRecommenderException;
import com.sale_stock_indonesia.exercise.exercise1.model.CombinedData;
import com.sale_stock_indonesia.exercise.exercise1.model.ProductScore;
import com.sale_stock_indonesia.exercise.exercise1.model.UserPreference;
import com.sale_stock_indonesia.exercise.exercise1.util.ProductScoreFileReader;
import com.sale_stock_indonesia.exercise.exercise1.util.UserPreferenceFileReader;

public class RecommendationAPI {
	
	private final int N = 5;
	
	private String userPreferenceFile; 
	private String productScoreFile;
	
	private boolean initialized = false;
	
	public RecommendationAPI()  {
	}
	
	public void initialize(String userPreferenceFile, String productScoreFile) {
		this.userPreferenceFile = userPreferenceFile;
		this.productScoreFile = productScoreFile;
		
		this.initialized = true;
		
		System.out.println("User Preference File: " + this.userPreferenceFile);
		System.out.println("productScoreFile: " + this.productScoreFile);
		
	}
	
	public void recommendProducts(int userId) throws FileNotFoundException, IncorrectUserPreferenceException, IncorrectProductScoreException, TopNRecommenderException, IncorrectRecommenderIndexException, IncorrectSortedListElementException, IncorrectCombinedDataException, IOException {
		List<UserPreference> userPreferences = retrieveUserPreferences(userId);
		
		List<CombinedData> combinedDataList = combineData(userPreferences);
		
		TopNRecommender topNRecommender = new TopNRecommender(N);

		for (CombinedData combinedData : combinedDataList) {
			topNRecommender.insert(combinedData);
		}

		topNRecommender.showRecommendations();
	}
	
	private List<UserPreference> retrieveUserPreferences(int userId) throws FileNotFoundException, IncorrectUserPreferenceException, IOException {

		List<UserPreference> result = new ArrayList<UserPreference>();
		
		UserPreferenceFileReader userPreferenceFileReader = new UserPreferenceFileReader(this.userPreferenceFile);
		
		UserPreference userPreference;
		while ((userPreference = userPreferenceFileReader.getUserPreference()) != null) {
			if (userId == userPreference.getUserId()) {
				result.add(userPreference);
			}
		}
		
		userPreferenceFileReader.close();
		
		return result;
	}
	
	private List<CombinedData> combineData(List<UserPreference> userPreferences) throws FileNotFoundException, IncorrectProductScoreException, IOException {

		List<CombinedData> result = new ArrayList<CombinedData>();
		
		if (userPreferences == null) {
			return result;
		}

		if (userPreferences.isEmpty()) {
			return result;
		}
		
		ProductScoreFileReader productScoreFileReader = new ProductScoreFileReader(this.productScoreFile);
		
		ProductScore productScore;
		while ((productScore = productScoreFileReader.getProductScore()) != null) {
			for (UserPreference userPreference : userPreferences) {
				if (userPreference.getProductId().intValue() == productScore.getProductId().intValue()) {
					CombinedData combinedData = new CombinedData(userPreference);
					combinedData.processProductScore(productScore.getProductScore());
					result.add(combinedData);
				}
			}
		}
		
		productScoreFileReader.close();
		
		return result;
	}
	
	public String getUserPreferenceFile() {
		return userPreferenceFile;
	}

	public void setUserPreferenceFile(String userPreferenceFile) {
		this.userPreferenceFile = userPreferenceFile;
	}

	public String getProductScoreFile() {
		return productScoreFile;
	}

	public void setProductScoreFile(String productScoreFile) {
		this.productScoreFile = productScoreFile;
	}

	public boolean isInitialized() {
		return this.initialized;
	}
}
