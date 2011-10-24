package de.vogella.rcp.jfreechart.pie;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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

public class ViewPart1 extends ViewPart {
	 private FormToolkit toolkit;
	 private ScrolledForm form;
	 
	public ViewPart1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		 toolkit = new FormToolkit(parent.getDisplay());
		  form = toolkit.createScrolledForm(parent);
		  form.setText("Hello, Eclipse Forms");
		  
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
		  
		  

		  
//		  ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), 
//				     ExpandableComposite.TREE_NODE|
//				     ExpandableComposite.CLIENT_INDENT);
//				 ec.setText("Expandable Composite title");
//				 String ctext = "We will now create a somewhat long text so that "+
//				 "we can use it as content for the expandable composite. "+
//				 "Expandable composite is used to hide or show the text using the " + 
//				 "toggle control";
//				 Label client = toolkit.createLabel(ec, ctext, SWT.WRAP);
//				 ec.setClient(client);
//				 TableWrapData td = new TableWrapData();
//				 td.colspan = 2;
//				 ec.setLayoutData(td);
//				 ec.addExpansionListener(new ExpansionAdapter() {
//				  public void expansionStateChanged(ExpansionEvent e) {
//				   form.reflow(true);
//				  }
//				 });		  
		  
		  
		  
		  
		  
		  JFreeChart chart = createChart(createDataset());

		  ChartComposite cp = new ChartComposite(form.getBody(), SWT.NONE, chart, true);
		  
		  GridData gd2 = new GridData();
		  gd2.horizontalSpan = 3;
		  gd2.minimumHeight=300;
		  gd2.minimumWidth=500;

		  cp.setLayoutData(gd2);

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
	 
	  private KeyedValues2DDataset createDataset()
	    {
	        DefaultKeyedValues2DDataset defaultkeyedvalues2ddataset = new DefaultKeyedValues2DDataset();
	        defaultkeyedvalues2ddataset.addValue(-6D, "Male", "70+");
	        defaultkeyedvalues2ddataset.addValue(-8D, "Male", "60-69");
	        defaultkeyedvalues2ddataset.addValue(-11D, "Male", "50-59");
	        defaultkeyedvalues2ddataset.addValue(-13D, "Male", "40-49");
	        defaultkeyedvalues2ddataset.addValue(-14D, "Male", "30-39");
	        defaultkeyedvalues2ddataset.addValue(-15D, "Male", "20-29");
	        defaultkeyedvalues2ddataset.addValue(-19D, "Male", "10-19");
	        defaultkeyedvalues2ddataset.addValue(-21D, "Male", "0-9");
	        defaultkeyedvalues2ddataset.addValue(10D, "Female", "70+");
	        defaultkeyedvalues2ddataset.addValue(12D, "Female", "60-69");
	        defaultkeyedvalues2ddataset.addValue(13D, "Female", "50-59");
	        defaultkeyedvalues2ddataset.addValue(14D, "Female", "40-49");
	        defaultkeyedvalues2ddataset.addValue(15D, "Female", "30-39");
	        defaultkeyedvalues2ddataset.addValue(17D, "Female", "20-29");
	        defaultkeyedvalues2ddataset.addValue(19D, "Female", "10-19");
	        defaultkeyedvalues2ddataset.addValue(20D, "Female", "0-9");
	        return defaultkeyedvalues2ddataset;
	    }
	    
		


		
	/** * Creates the Chart based on a dataset */

		private JFreeChart createChart(KeyedValues2DDataset dataset) {
			
			
			
	        KeyedValues2DDataset keyedvalues2ddataset = createDataset();
	        org.jfree.chart.JFreeChart chart = ChartFactory.createStackedBarChart("Volume Band Spread", 
	        		"Age Group", "Population (millions)", keyedvalues2ddataset, PlotOrientation.HORIZONTAL, true, true, true);
	        
	        ChartPanel chartpanel = new ChartPanel(chart);
	        
	        chartpanel.setPreferredSize(new Dimension(500, 270));
	        //setContentPane(chartpanel);
	        

			return chart;

		}	 
}
