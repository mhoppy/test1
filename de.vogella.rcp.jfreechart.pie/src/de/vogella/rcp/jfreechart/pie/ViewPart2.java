package de.vogella.rcp.jfreechart.pie;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.data.general.KeyedValues2DDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

public class ViewPart2 extends ViewPart {
	 private FormToolkit toolkit;
	 private ScrolledForm form;
	 
	public ViewPart2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		 toolkit = new FormToolkit(parent.getDisplay());
		  form = toolkit.createScrolledForm(parent);
		  form.setText("Trade Form 1");
		  
		  GridLayout layout = new GridLayout();
		  form.getBody().setLayout(layout);
		  Hyperlink link = toolkit.createHyperlink(form.getBody(), 
		    "Click here.", SWT.WRAP);
		  
		  link.addHyperlinkListener(new HyperlinkAdapter() {
		   public void linkActivated(HyperlinkEvent e) {
		    System.out.println("Link activated!");
		   }
		  });
		  
		  layout.numColumns = 2;
		  GridData gd = new GridData();
		  gd.horizontalSpan = 2;
		  link.setLayoutData(gd);
		  //Label label = new Label(form.getBody(), SWT.NULL);
		  //label.setText("Text field label:");
		  Label label = toolkit.createLabel(form.getBody(), "Text field label:");
		  
		  
		  //Text text = new Text(form.getBody(), SWT.BORDER);
		  Text text = toolkit.createText(form.getBody(), "");
		  
		  text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  
		  
		  //Button button = new Button(form.getBody(), SWT.CHECK);
		  //button.setText("An example of a checkbox in a form");
		  Button button = toolkit.createButton(form.getBody(), "A checkbox in a form", SWT.CHECK);
		  
		  gd = new GridData();
		  gd.horizontalSpan = 3;
		  button.setLayoutData(gd);
		  
		  
		  
		  //TextLayout rate = createRate(form.getBody());
		  RateText rt = new RateText(form.getBody(), SWT.NONE, "99.6321");
		  
		  gd = new GridData();
		  gd.horizontalSpan = 3;
		  rt.setLayoutData(gd);
		  
		  
		   final Table table = new Table(form.getBody(), SWT.BORDER);
		    table.setHeaderVisible(true);
		    table.setLinesVisible(true);
		    TableColumn column1 = new TableColumn(table, SWT.NONE);
		    column1.setText("Bug Status");
		    column1.setWidth(100);
		    final TableColumn column2 = new TableColumn(table, SWT.NONE);
		    column2.setText("Percent");
		    column2.setWidth(200);
		    
		    //TableItem item = new TableItem(table, SWT.NONE);
		    //item.set
		    
		    String[] labels = new String[] { "1.44513", "1.44499", "144486", "Invalid" };
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
		      int[] percents = new int[] { 50, 30, 5, 15 };

		      public void handleEvent(Event event) {
		        if (event.index == 1) {
		          GC gc = event.gc;
		          TableItem item = (TableItem) event.item;
		          int index = table.indexOf(item);
		          int percent = percents[index];
		          Color foreground = gc.getForeground();
		          Color background = gc.getBackground();
		          gc.setForeground(form.getBody().getDisplay().getSystemColor(SWT.COLOR_RED));
		          gc.setBackground(form.getBody().getDisplay().getSystemColor(SWT.COLOR_YELLOW));
		          //
		          int width = (column2.getWidth() - 1) * percent / 100;
		          int centre = (column2.getWidth() - 1) / 2;
		          int offsetX = centre - ( width / 2);
		          int startX = event.x + offsetX;;
		          
		          System.out.println("full width:" + column2.getWidth()
		        		  + "percent:" + percent
		        		  + " width:" + width 
		        		  + " centre:" + centre 
		        		  + " offset:" + offsetX
		        		  + " startX:" + startX);
		          
		          gc.fillGradientRectangle(startX, event.y, width, event.height, true);
		          Rectangle rect2 = new Rectangle(startX, event.y, width - 1, event.height - 1);
		          gc.drawRectangle(rect2);
		          gc.setForeground(form.getBody().getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND));
		          String text = percent + "%";
		          Point size = event.gc.textExtent(text);
		          int offset = Math.max(0, (event.height - size.y) / 2);
		          
		          //gc.drawText(text, event.x + 2, event.y + offset, true);
		          gc.drawText(text, event.x+centre, event.y + offset, true);
		          
		          gc.setForeground(background);
		          gc.setBackground(foreground);
		        }
		      }
		    });

		 }

	@Override
	public void setFocus() {
		form.setFocus();
	}
	
	 /**
	  * Disposes the toolkit
	  */
	 public void dispose() {
	  toolkit.dispose();
	  super.dispose();
	 }
	 
	 
	 private TextLayout createRate(final Composite parent) {
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
		    return layout;
	 }
}