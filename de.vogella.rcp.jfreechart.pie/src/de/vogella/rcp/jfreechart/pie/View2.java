package de.vogella.rcp.jfreechart.pie;

import java.awt.Dimension;
import java.awt.Font;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.KeyedValues2DDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

public class View2 extends ViewPart {

	public void createPartControl(Composite parent) {
		JFreeChart chart = createChart(createDataset());
		
		new ChartComposite(parent, SWT.NONE, chart, true);
		
		
	}

	public void setFocus() {
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
        
        //chartpanel.setPreferredSize(new Dimension(500, 270));
        //setContentPane(chartpanel);
        

		return chart;

	}
}

