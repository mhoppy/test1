package de.vogella.rcp.jfreechart.pie;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

import playground.RateValue;

class PictureLabelLayout extends Layout {
	Point iExtent, tExtent; // the cached sizes

	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean changed) {
		Control [] children = composite.getChildren();
		System.out.println("children found:" + children.length);

		if (changed || iExtent == null || tExtent == null) {
			iExtent = children[0].computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
			tExtent = children[1].computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		}

		//int width = iExtent.x + 5 + tExtent.x;
		//int height = Math.max(iExtent.y, tExtent.y);
		int width = Math.max(iExtent.x ,tExtent.x);
		int height = iExtent.y + tExtent.y + 5;

		return new Point(width + 2, height + 2);
	}


	protected void layout(Composite composite, boolean changed) {
		Control [] children = composite.getChildren();
		if (changed || iExtent == null || tExtent == null) {
			iExtent = children[0].computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
			tExtent = children[1].computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		}
		//children[0].setBounds(1, 1, iExtent.x, iExtent.y);
		//children[1].setBounds(iExtent.x + 5, 1, tExtent.x, tExtent.y);
		children[0].setBounds(1, 1, iExtent.x, iExtent.y);
		children[1].setBounds(1, iExtent.y + 5, tExtent.x, tExtent.y);

	}
}

/***
 * Component 
 * @author ex01487
 *
 */
public class RateText2 extends Composite {
	private MainRateText highOrderLayout;
	private PipRateText pipOrderLayout;
	private RateValue rate;



	public RateText2(Composite parent, int style, String initialValue) {
		super(parent, style);
		rate = new RateValue(initialValue);

		highOrderLayout = new MainRateText(this, style, rate.getMain());
		pipOrderLayout = new PipRateText(this, style, rate.getPips());

		setBackground(getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));

		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				highOrderLayout.dispose();
				pipOrderLayout.dispose();
			}
		});

		setLayout(new PictureLabelLayout());
	}

	public String getText() {
		return rate.getText();
	}

	public void setText(String text) {
		rate = new RateValue(text);
		highOrderLayout.setText(rate.getMain());
		pipOrderLayout.setText(rate.getPips());
		layout(true);
	}
}
