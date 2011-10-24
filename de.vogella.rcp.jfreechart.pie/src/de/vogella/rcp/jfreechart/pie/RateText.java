package de.vogella.rcp.jfreechart.pie;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/***
 * Component
 * @author ex01487
 *
 */
public class RateText extends Canvas {

	private Font font1;
	private Font font2;
	private Font font3;
	private Color blue;
	private Color green;
	private Color yellow;
	private Color gray;
	private TextLayout layout;
	private TextStyle style1;
	private TextStyle style2;
	private TextStyle style3;
	
	public RateText(Composite parent, int style, String txt) {
		super(parent, style);
	    font1 = new Font(parent.getDisplay(), "Arial", 14, SWT.NORMAL);
	    font2 = new Font(parent.getDisplay(), "Arial", 24, SWT.NORMAL);
	    font3 = new Font(parent.getDisplay(), "Arial", 14, SWT.NORMAL);
	    
	    blue = parent.getDisplay().getSystemColor(SWT.COLOR_BLUE);
	    green = parent.getDisplay().getSystemColor(SWT.COLOR_GREEN);
	    yellow = parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
	    gray = parent.getDisplay().getSystemColor(SWT.COLOR_GRAY);

	    layout = new TextLayout(parent.getDisplay());
	    style1 = new TextStyle(font1, yellow, blue);
	    style2 = new TextStyle(font2, yellow, blue);
	    style3 = new TextStyle(font3, yellow, blue);

	    layout.setText(txt);
	    layout.setStyle(style1, 0, 3);
	    layout.setStyle(style2, 4, 5);
	    layout.setStyle(style3, 6, 7);
	    parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));


	    addDisposeListener(new DisposeListener() {
	         public void widgetDisposed(DisposeEvent e) {
	        	 blue.dispose();
	        	 green.dispose();
	        	 yellow.dispose();
	        	 gray.dispose();
	        	 
	        	 font1.dispose();
	        	 font2.dispose();
	        	 font3.dispose();
	        	 
	        	 layout.dispose();
	         }
	     });
	    
	     addPaintListener(new PaintListener() {
	         public void paintControl(PaintEvent e) {
	        	 RateText.this.paintControl(e);
	         }
	     });
	  }

	  void paintControl(PaintEvent event) {
		  System.out.println("drawing...");
		  layout.draw(event.gc, 10, 10);
	  }
	  
	  public Point computeSize(int wHint, int hHint, boolean changed) {
		     int width = 0, height = 0;
		     if (layout != null) {
		    	 
		         Rectangle bounds = layout.getBounds();
		         width = bounds.width + 100;
		         height = bounds.height + 50;
		     }
		     return new Point(width + 2, height + 2);    
		  }	  
}

