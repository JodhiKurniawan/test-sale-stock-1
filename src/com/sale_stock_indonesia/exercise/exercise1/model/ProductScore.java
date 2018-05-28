package com.sale_stock_indonesia.exercise.exercise1.model;

public class ProductScore {
	
	private Integer productId;
	private Integer productScore;

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getProductScore() {
		return productScore;
	}
	public void setProductScore(Integer productScore) {
		this.productScore = productScore;
	}

	@Override
	public String toString() {
		return 
			"{ \"productId\" : " + ((this.getProductId() == null) ? "null" : this.getProductId()) +
			", \"productScore\" : " + ((this.getProductScore() == null) ? "null" : this.getProductScore()) +
			"}";
	}
}
