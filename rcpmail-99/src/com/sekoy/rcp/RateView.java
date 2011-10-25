package com.sekoy.rcp;



import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
//import org.jfree.experimental.chart.swt.ChartComposite;


public class RateView extends ViewPart {
	public static final String ID = "sekoy.rcp.first";
	
	GridLayout layout;
	  // the label used to display the text.
	  Label label;
	  
	  Button buttonColor;
	  Button buttonFont;
	  
	  // current selected color.
	  Color color;
	  // current selected font.
	  Font font;	
	
	public RateView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {


		
// Layout that does not work		
//		Shell shell = parent.getShell();
//		layout = new GridLayout();
//		
//		layout.marginHeight = 5;
//		layout.marginWidth = 10;
//		layout.numColumns = 3;
//		
//		shell.setLayout(layout);

		
		/*
		 * Variable sized fonts
		 */
	    Font font1 = new Font(parent.getDisplay(), "Arial", 14, SWT.NORMAL);
	    Font font2 = new Font(parent.getDisplay(), "Arial", 24, SWT.NORMAL);
	    Font font3 = new Font(parent.getDisplay(), "Arial", 14, SWT.NORMAL);

	    Color blue = parent.getDisplay().getSystemColor(SWT.COLOR_BLUE);
	    Color green = parent.getDisplay().getSystemColor(SWT.COLOR_GREEN);
	    Color yellow = parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
	    Color gray = parent.getDisplay().getSystemColor(SWT.COLOR_GRAY);

	    final TextLayout layout = new TextLayout(parent.getDisplay());
	    TextStyle style1 = new TextStyle(font1, yellow, blue);
	    TextStyle style2 = new TextStyle(font2, yellow, blue);
	    TextStyle style3 = new TextStyle(font3, yellow, blue);

	    layout.setText("89.6321");
	    layout.setStyle(style1, 0, 3);
	    layout.setStyle(style2, 4, 5);
	    layout.setStyle(style3, 6, 7);
	    
	    
	    parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	    parent.addListener(SWT.Paint, new Listener() {
	      public void handleEvent(Event event) {
	        layout.draw(event.gc, 10, 10);
	      }
	    });		    
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
}
