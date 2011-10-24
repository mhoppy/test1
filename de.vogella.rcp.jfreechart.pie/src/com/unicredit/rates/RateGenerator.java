package com.unicredit.rates;

import java.text.DecimalFormat;
import java.util.Random;

public class RateGenerator implements Runnable {
	private static final int RATE_LEN = 4;
	private Random random = new Random();
	private RateListener rateListener;
	private double rate = 1.38;
	private DecimalFormat df = new DecimalFormat("#.#####");
	private volatile boolean running=true;

	@Override
	public void run() {
		while(running) {
			double delta = random.nextDouble() / 9999;
			if ( random.nextInt(10)>5) {
				rate -= delta;
			} else {
				rate += delta;
			}
			System.out.println(getRate(rate));
			rateListener.onRateChange(getRate(rate));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private String getRate(double rate) {
		String sRate = df.format(rate);
		
		int decimalIdx = sRate.indexOf(".");
		String pips = sRate.substring(decimalIdx+ 2);
		int pad = RATE_LEN - pips.length();
		System.out.println("pips len:" + pips.length() + " pad:" + pad);
		
		for(int i=0;i<pad;++i) {
			sRate = sRate + "0";
		}
		return sRate;
	}

	public RateListener getRateListener() {
		return rateListener;
	}

	public void setRateListener(RateListener rateListener) {
		this.rateListener = rateListener;
	}
	
	public void stop() {
		running=false;
	}

}
