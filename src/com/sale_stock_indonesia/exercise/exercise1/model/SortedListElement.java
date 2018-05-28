package com.sale_stock_indonesia.exercise.exercise1.model;

public class SortedListElement {

	private int previous;
	private CombinedData data;
	private int next;
	
	private SortedListElement() {
		
	}

	public SortedListElement(int previous, CombinedData data, int next) {
		this.previous = previous;
		this.data = data;
		this.next = next;
	}
	
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public CombinedData getData() {
		return data;
	}

	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}

	
}
