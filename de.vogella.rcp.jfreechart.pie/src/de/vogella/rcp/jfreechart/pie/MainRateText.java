package de.vogella.rcp.jfreechart.pie;

import org.eclipse.swt.widgets.Composite;

public class MainRateText extends RateText {

	public MainRateText(Composite parent, int style, String initialValue) {
		super(parent, style, initialValue);
	}
	
	protected  void styleText() {
		textLayout.setStyle(style1, 0, 3);
	}

}
