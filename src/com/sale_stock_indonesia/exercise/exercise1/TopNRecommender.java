package com.sale_stock_indonesia.exercise.exercise1;

import java.util.ArrayList;
import java.util.List;

import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectCombinedDataException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectRecommenderIndexException;
import com.sale_stock_indonesia.exercise.exercise1.exception.IncorrectSortedListElementException;
import com.sale_stock_indonesia.exercise.exercise1.exception.TopNRecommenderException;
import com.sale_stock_indonesia.exercise.exercise1.model.CombinedData;
import com.sale_stock_indonesia.exercise.exercise1.model.SortedListElement;

/// It's a Top-N recommender. You can use it to implements Top 5 recommender, Top 10 recommender, top 100 recommender, etc
/// It stores only the highest N values of all the data inserted   
public class TopNRecommender {
	
	private int N;
	private List<SortedListElement> sortedList;
	private int head = -1;
	private int tail = -1;
	
	private TopNRecommender() {
		
	}
	
	public TopNRecommender(int N) throws TopNRecommenderException {
		if (N <= 0) {
			throw new TopNRecommenderException(N);
		}
		this.N = N;
		this.sortedList = new ArrayList<SortedListElement>(N); 
	} 
	
	public void showRecommendations() throws IncorrectRecommenderIndexException, IncorrectSortedListElementException, IncorrectCombinedDataException {
		int position = this.head;
		while (position >= 0) {
			if (position >= sortedList.size()) {
				throw new IncorrectRecommenderIndexException(position,N);
			}
			SortedListElement element = (SortedListElement)sortedList.get(position);
			if (element == null) {
				throw new IncorrectSortedListElementException("reading null");
			}
			CombinedData data = element.getData();
			if (data == null) {
				throw new IncorrectCombinedDataException("reading null");
			}
			System.out.println(data.getProductId());
			position = element.getNext();
		}
	}
	
	public void insert(CombinedData arg) throws IncorrectRecommenderIndexException, IncorrectSortedListElementException, IncorrectCombinedDataException {
		if (arg == null) {
			throw new IncorrectCombinedDataException("inserting null");
		}
		if (arg.getEffectiveProductScore() == null) {
			throw new IncorrectCombinedDataException("inserting null effective product score");
		}
		
		int position = this.head;
		int nextPosition = -1;
		int prevPosition = -1;
		SortedListElement prevElement = null;
		SortedListElement nextElement = null;
		boolean found = false;

		while (position >= 0) {
			if (position >= sortedList.size()) {
				throw new IncorrectRecommenderIndexException(position,N);
			}
			SortedListElement element = (SortedListElement)sortedList.get(position);
			if (element == null) {
				throw new IncorrectSortedListElementException("reading null");
			}
			CombinedData data = element.getData();
			if (data == null) {
				throw new IncorrectCombinedDataException("reading null");
			}
			if (data.getEffectiveProductScore() == null) {
				throw new IncorrectCombinedDataException("reading null effective product score");
			}
			if (arg.getEffectiveProductScore().doubleValue() > data.getEffectiveProductScore().doubleValue()) {
				found = true;
				prevPosition = element.getPrevious();
				if (prevPosition >= 0) {
					prevElement = (SortedListElement)sortedList.get(prevPosition);
					if (prevElement == null) {
						throw new IncorrectSortedListElementException("reading null");
					}					
				}
				if (sortedList.size() < N) { 
					nextPosition = position;
					nextElement = element;
				} else {
					if (position == this.tail) { /// Replace the last element
						nextPosition = -1;
					} else {
						nextPosition = position;
						nextElement = element;
					}
				}
				break;
			}
			position = element.getNext();
		}
		if (found) { /// Not empty
			int pos;
			SortedListElement newData = new SortedListElement(prevPosition,arg,nextPosition);
			if (sortedList.size() < N) {  
				pos = sortedList.size();
				this.sortedList.add(newData);
				if (prevElement == null) { /// First Element, Not last element
					this.head = pos;
				} else { /// Not First Element, Not last element
					prevElement.setNext(pos);
					this.sortedList.set(prevPosition,prevElement);
				}
				nextElement.setPrevious(pos);
				this.sortedList.set(nextPosition,nextElement);
			} else {
				pos = this.tail;
				if (nextElement == null) {  /// The last element, no the first element
					this.sortedList.set(pos,newData);
				} else { /// Not last element
					if (prevElement == null) { /// First Element, Not last element
						this.head = pos;
					} else { /// First Element, Not last element
						prevElement.setNext(pos);
						this.sortedList.set(prevPosition,prevElement);
					}
					nextElement.setPrevious(pos);
					this.sortedList.set(nextPosition,nextElement);
					SortedListElement currentlastElement = (SortedListElement)sortedList.get(pos);
					if (currentlastElement == null) {
						throw new IncorrectSortedListElementException("reading null");
					}
					this.tail = currentlastElement.getPrevious();
					SortedListElement newLastElement = (SortedListElement)sortedList.get(this.tail);
					if (newLastElement == null) {
						throw new IncorrectSortedListElementException("reading null");
					}
					newLastElement.setNext(-1);
					this.sortedList.set(this.tail,newLastElement);
					this.sortedList.set(pos,newData);
				} 
			} 
		} else {
			if (this.sortedList.size() < N) { /// Add as the last element
				int pos = this.sortedList.size();
				if (this.tail < 0 )  { /// First element of sorted list
					if (this.head >= 0) {
						throw new IncorrectSortedListElementException("reading head = " + this.head + " while tail = " + this.tail);
					}
					this.head = pos; // 0
				} else {
					SortedListElement currentlastElement = (SortedListElement)sortedList.get(this.tail);
					if (currentlastElement == null) {
						throw new IncorrectSortedListElementException("reading null");
					}
					currentlastElement.setNext(pos);
					this.sortedList.set(tail, currentlastElement);
				}
				SortedListElement newData = new SortedListElement(this.tail,arg,-1);
				this.sortedList.add(newData);
				this.tail = pos; 
			} else { /// The effective product score is too little to make it to Top N Recommendation 
				
			}
		} 
	}
}
