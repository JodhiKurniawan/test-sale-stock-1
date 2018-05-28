package com.sale_stock_indonesia.exercise.exercise1.model;

public class CombinedData extends UserPreference {
	
	private static final double DIMINISH_VALUE = 0.95;

	private Integer rawProductScore;
	private Double effectiveProductScore;
	private Integer calculateTime;

	public CombinedData(UserPreference userPreference) {
		if (userPreference != null) {
			this.setUserId(userPreference.getUserId());
			this.setProductId(userPreference.getProductId());
			this.setScore(userPreference.getScore());
			this.setTimestamp(userPreference.getTimestamp());
		}
	}
	
	public void processProductScore(int rawProductScore) {
		this.rawProductScore = rawProductScore;
		this.calculateTime = (int)(System.currentTimeMillis()/1000);
		int daysPassed = (calculateTime - this.getTimestamp()) / 86400;
		/// To calculate productScoreAddition without iteration, we'll follow the following equations
		/// productScoreAddition = rawProductScore * DIMINISH_VALUE ^ daysPassed;
		/// ln(productScoreAddition) = ln(rawProductScore) + daysPassed*ln(DIMINISH_VALUE)
		/// productScoreAddition = exp(ln(rawProductScore)+daysPassed*ln(DIMINISH_VALUE));
		double calculatedValue = Math.exp(Math.log(this.getScore())+daysPassed*Math.log(DIMINISH_VALUE));
		this.effectiveProductScore = this.rawProductScore * (1+calculatedValue); 
	}
	
	public Integer getRawProductScore() {
		return rawProductScore;
	}

	public Double getEffectiveProductScore() {
		return effectiveProductScore;
	}
	
	public Integer getCalculateTime() {
		return calculateTime;
	}

	@Override
	public String toString() {
		return 
			"{ \"userId\" : " + ((this.getUserId() == null) ? null : this.getUserId()) + 
			", \"productId\" : " + ((this.getProductId() == null) ? null : this.getProductId()) +
			", \"score\" : " + ((this.getScore() == null) ? null : this.getScore()) +
			", \"timestamp\" : " + ((this.getTimestamp() == null) ? null : this.getTimestamp()) + 
			", \"rawProductScore\" : " + ((this.getRawProductScore() == null) ? null : this.getRawProductScore()) +
			", \"effectiveProductScore\" : " + ((this.getEffectiveProductScore() == null) ? null : this.getEffectiveProductScore()) +
			", \"calculateTime\" : " + ((this.getCalculateTime() == null) ? null : this.getCalculateTime()) +
			"}";
	}
}
