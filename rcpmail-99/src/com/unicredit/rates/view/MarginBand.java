package com.unicredit.rates.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


class MarginBandLayout extends Layout {
	Point iExtent; // the cached sizes

	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean changed) {
		Control[] children = composite.getChildren();
		System.out.println("children found:" + children.length);

		if (changed || iExtent == null ) {
			iExtent = children[0].computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		}

		int width = iExtent.x;
		int height = iExtent.y + 5;

		return new Point(width + 2, height + 2);
	}

	protected void layout(Composite composite, boolean changed) {
		Control[] children = composite.getChildren();
		System.out.println("band layout: children found:" + children.length);
		if (changed || iExtent == null ) {
			iExtent = children[0].computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		}
		

		
		// setBounds (int x, int y, int width, int height)
		children[0].setBounds(0, 0,             iExtent.x, iExtent.y);
	
		int width = iExtent.x;
		int height = iExtent.y + 5;

		new Point(width + 2, height + 2);
	}
}

public class MarginBand extends Composite {
	MarginBand self;
	private Table table;
	private TableColumn column1;
	private TableColumn column2;
	private TableColumn column3;
	
	/***
	 * Constructor
	 * @param parent
	 * @param style
	 */
	public MarginBand(Composite parent, int style) {
		super(parent, style);
		self = this;
		setBackground(getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		table = new Table(this, SWT.BORDER);
		table.setHeaderVisible(false);
		table.setLinesVisible(false);
		column1 = new TableColumn(table, SWT.NONE);
		//column1.setText("Bug Status");
		column1.setWidth(50);
		column2 = new TableColumn(table, SWT.NONE);
		//column2.setText("Percent");
		column2.setWidth(140);
		column3 = new TableColumn(table, SWT.NONE);
		//column2.setText("Percent");
		column3.setWidth(50);

		// TableItem item = new TableItem(table, SWT.NONE);
		// item.set

		String[] labels = new String[] { "1.44513", "1.44499", "1.44486", "1.44497" };
		for (int i = 0; i < labels.length; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(labels[i]);
		}

		/*
		 * NOTE: MeasureItem, PaintItem and EraseItem are called repeatedly.
		 * Therefore, it is critical for performance that these methods be as
		 * efficient as possible.
		 */
		table.addListener(SWT.PaintItem, new Listener() {
			int[] percents = new int[] { 50, 60, 75, 85 };

			public void handleEvent(Event event) {
				if (event.index == 1) {
					GC gc = event.gc;
					TableItem item = (TableItem) event.item;
					int index = table.indexOf(item);
					int percent = percents[index];
					Color foreground = gc.getForeground();
					Color background = gc.getBackground();
					gc.setForeground(self.getDisplay().getSystemColor(SWT.COLOR_RED));
					gc.setBackground(self.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
					//
					int width = (column2.getWidth() - 1) * percent / 100;
					int centre = (column2.getWidth() - 1) / 2;
					int offsetX = centre - (width / 2);
					int startX = event.x + offsetX;
					;

					System.out.println("full width:" + column2.getWidth()
							+ "percent:" + percent + " width:" + width
							+ " centre:" + centre + " offset:" + offsetX
							+ " startX:" + startX);

					gc.fillGradientRectangle(startX, event.y, width,
							event.height, true);
					Rectangle rect2 = new Rectangle(startX, event.y, width - 1,
							event.height - 1);
					gc.drawRectangle(rect2);
					gc.setForeground(self.getDisplay()
							.getSystemColor(SWT.COLOR_LIST_FOREGROUND));
					String text = percent + "%";
					Point size = event.gc.textExtent(text);
					int offset = Math.max(0, (event.height - size.y) / 2);

					// gc.drawText(text, event.x + 2, event.y + offset, true);
					gc.drawText(text, event.x + centre, event.y + offset, true);

					gc.setForeground(background);
					gc.setBackground(foreground);
				}
			}
		});
		
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				table.dispose();
			}
		});
		
		setLayout(new MarginBandLayout());
	}

}
