package com.sale_stock_indonesia.exercise.exercise1.model;

public class UserPreference {
	private Integer userId;
	private Integer productId;
	private Double score;
	private Integer timestamp;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	public Integer getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return 
			"{ \"userId\" : " + ((this.getUserId() == null) ? null : this.getUserId()) + 
			", \"productId\" : " + ((this.getProductId() == null) ? null : this.getProductId()) +
			", \"score\" : " + ((this.getScore() == null) ? null : this.getScore()) +
			", \"timestamp\" : " + ((this.getTimestamp() == null) ? null : this.getTimestamp()) + 
			"}";
	}
	
}
