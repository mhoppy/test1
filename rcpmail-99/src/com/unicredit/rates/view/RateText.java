package com.unicredit.rates.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/***
 * Component
 * 
 * @author ex01487
 * 
 */
public abstract class RateText extends Canvas {

	private Font font1;
	private Font font2;
	private Font font3;
	private Color blue;
	private Color green;
	private Color yellow;
	private Color gray;

	protected TextLayout textLayout;

	protected TextStyle style1;
	protected TextStyle style2;
	private TextStyle style3;

	public RateText(Composite parent, int style, String initialValue) {
		super(parent, style);

		font1 = new Font(parent.getDisplay(), "Arial", 14, SWT.NORMAL);
		font2 = new Font(parent.getDisplay(), "Arial", 28, SWT.NORMAL);
		font3 = new Font(parent.getDisplay(), "Arial", 14, SWT.NORMAL);

		blue = parent.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE);
		green = parent.getDisplay().getSystemColor(SWT.COLOR_GREEN);
		yellow = parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
		gray = parent.getDisplay().getSystemColor(SWT.COLOR_GRAY);

		textLayout = new TextLayout(parent.getDisplay());

		// GridLayout layout = new GridLayout();

		style1 = new TextStyle(font1, yellow, blue);
		style2 = new TextStyle(font2, yellow, blue);
		style3 = new TextStyle(font3, yellow, blue);

		textLayout.setText(initialValue);
		styleText();

		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));

		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				blue.dispose();
				green.dispose();
				yellow.dispose();
				gray.dispose();

				font1.dispose();
				font2.dispose();
				font3.dispose();

				textLayout.dispose();
			}
		});

		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				RateText.this.paintControl(e);
			}
		});
	}

	protected abstract void styleText();

	void paintControl(PaintEvent event) {
		System.out.println("drawing...");
		// draw (GC gc, int x, int y)
		// textLayout.draw(event.gc, 10, 10);
		textLayout.draw(event.gc, 0, 0);
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		int width = 0, height = 0;
		if (textLayout != null) {

			Rectangle bounds = textLayout.getBounds();
			width = bounds.width + 0;
			height = bounds.height + 0;
		}
		return new Point(width + 0, height + 0);
	}

	public String getText() {
		return textLayout.getText();
	}

	public void setText(String text) {
		textLayout.setText(text);
		styleText();
		redraw();
		getParent().layout(true, true);

		//update();
	}
	
	
}
