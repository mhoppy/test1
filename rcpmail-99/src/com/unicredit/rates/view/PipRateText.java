package com.unicredit.rates.view;

import org.eclipse.swt.widgets.Composite;

public class PipRateText extends RateText {

	public PipRateText(Composite parent, int style, String initialValue) {
		super(parent, style, initialValue);
	}
	
	protected  void styleText() {
		textLayout.setStyle(style2, 0, 1);
	    textLayout.setStyle(style1, 2, 2);
	}

}
