package com.sale_stock_indonesia.exercise.exercise1;

import java.io.DataInputStream;
import java.io.IOException;

import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectCombinedDataException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectProductScoreException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectRecommenderIndexException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectSortedListElementException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectUserPreferenceException;
import com.sale_stock_indonesia.exercise.exercise1.exception.TopNRecommenderException;

public class RecommendationMain {
	
	private static String INITIALIZE_COMMAND = "initialize";
	private static String RECOMMEND_PRODUCTS_COMMAND = "recommend-products";
	
	public static void displayMessage() {
		System.out.println("\nCommands:");
		System.out.println("  initialize [user_preference_file] [product_score_file]: ");
		System.out.println("              Enables you to choose user_preference_file and product_score file");
		System.out.println("  recommend-products [user_id]");
		System.out.println("              Enables you to choose recommmend products to a specific user_id\n");
	}

	public static void main(String[] args) {
		RecommendationAPI recommendationAPI = new RecommendationAPI();

		DataInputStream dataInputStream = new DataInputStream(System.in); 
		try {
			for (;;) {
				displayMessage();
				String lineRead = dataInputStream.readLine();
				String[] list = lineRead.split(" ");
				System.out.println();
				
				String command = "";
				String arg1 = "";
				String arg2 = "";
				int index = 0;
				while (index < list.length) {
					String data = list[index++];
					if (!data.isEmpty()) {
						command = data;
						break;
					}
				}
				while (index < list.length) {
					String data = list[index++];
					if (!data.isEmpty()) {
						arg1 = data;
						break;
					}
				}
				while (index < list.length) {
					String data = list[index++];
					if (!data.isEmpty()) {
						arg2 = data;
						break;
					}
				}
				
				if (command.equalsIgnoreCase(INITIALIZE_COMMAND)) {
					if (arg1.isEmpty()) {
						System.err.println("user_preference_file argument must not be empty");
						break;
					} else if (arg2.isEmpty()) {
						System.err.println("product_score_file argument must not be empty");
						break;
					} else {
						recommendationAPI.initialize(arg1,arg2);
						if (recommendationAPI.isInitialized()) {
							System.out.println("initialized successfully");
						} else {
							System.err.println("Failed to initialize");
							break;
						}
					}
				} else if (command.equalsIgnoreCase(RECOMMEND_PRODUCTS_COMMAND)) {
					if (!recommendationAPI.isInitialized()) {
						System.out.println("The application is not yet initialized. You must call the initialize command first.");
					} else {
						if (arg1.isEmpty()) {
							System.err.println("user_id argument must not be empty");
							break;
						}
						Integer userId = Integer.parseInt(arg1); 
						try {
							recommendationAPI.recommendProducts(userId);
							System.out.println("\n--Done--"); 
						} catch (IncorrectUserPreferenceException e) {
							e.printStackTrace();
						} catch (IncorrectProductScoreException e) {
							e.printStackTrace();
						} catch (TopNRecommenderException e) {
							e.printStackTrace();
						} catch (IncorrectRecommenderIndexException e) {
							e.printStackTrace();
						} catch (IncorrectSortedListElementException e) {
							e.printStackTrace();
						} catch (IncorrectCombinedDataException e) {
							e.printStackTrace();
						}
						break;
					}
				} else {
					System.err.println("Unknown Command");
					break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
