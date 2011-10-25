package com.unicredit.rates;

public class RateHolder {
	private RateListener rateListener;
	private double rate = 1.38;
	
	public RateListener getRateListener() {
		return rateListener;
	}
	public void setRateListener(RateListener rateListener) {
		this.rateListener = rateListener;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
