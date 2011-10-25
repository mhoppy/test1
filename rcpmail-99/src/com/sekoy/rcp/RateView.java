package com.sekoy.rcp;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.unicredit.rates.RateGenerator;
import com.unicredit.rates.view.MarginBand;
import com.unicredit.rates.view.RateText2;

import rcpmail.MessageTableView;
import rcpmail.handlers.DeleteViewMessageHandler;
import rcpmail.handlers.MarkAsSpamAndMoveHandler;
import rcpmail.handlers.MarkViewAsSpamAndMoveHandler;
import rcpmail.model.Message;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
//import org.jfree.experimental.chart.swt.ChartComposite;

public class RateView extends ViewPart {
	public static final String ID = "sekoy.rcp.first";

	private Control bodyText;
	private Composite messageComposite;
	private Composite emptyComposite;

	private RateGenerator rateGenerator;
	private Thread ratethread;

	public void createPartControl(Composite parent) {
		// Top level with parent as the parent
		setPartName("partName");
		//
		messageComposite = new Composite(parent, SWT.NONE);
		/***
		 * GridLayout an SWT layout manager (i.e., it controls the layout of the
		 * form). GridData is a part of GridLayout and enables you to set layout
		 * properties on the SWT components participating in the layout.
		 */
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 1;
		messageComposite.setLayout(layout);

		// top banner with message composite as its parent
		Composite banner = new Composite(messageComposite, SWT.NONE);
		banner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 3;
		banner.setLayout(layout);

		// Label l = new Label(banner, SWT.WRAP);
		// l.setText("Subject:");
		// l.setFont(boldFont);

		String[] currencies = { "EURUSD", "EURGBP", "GBPUSD" };
		Combo currency = new Combo(banner, SWT.DEFAULT);
		currency.setItems(currencies);
		currency.select(0);

		String[] modes = { "NORMAL", "VOLATILE" };
		Combo mode = new Combo(banner, SWT.DEFAULT);
		mode.setItems(modes);
		mode.select(1);

		final Button resetButton = new Button(banner, SWT.BUTTON1);
		resetButton.setLayoutData(new GridData());
		resetButton.setText("Reset");

		// Try and make this span the three cols
		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		banner.setLayoutData(gd);
		// New composite to hold button group +/-
		Composite widen = new Composite(messageComposite, SWT.NONE);
		widen.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		layout = new GridLayout();
		layout.marginHeight = 2;
		layout.marginWidth = 5;
		layout.numColumns = 2;
		widen.setLayout(layout);

		final Button plusButton = new Button(widen, SWT.BUTTON2);
		plusButton.setLayoutData(new GridData());
		plusButton.setText(" + ");

		final Button minusButton = new Button(widen, SWT.BUTTON2);
		minusButton.setLayoutData(new GridData());
		minusButton.setText(" - ");

		/***
		 * The rates
		 */
		// New composite to hold rates group
		Composite rateComposite = new Composite(messageComposite, SWT.NONE);
		/***
		 * * @param horizontalAlignment how control will be positioned
		 * horizontally within a cell, one of: SWT.BEGINNING (or SWT.LEFT),
		 * SWT.CENTER, SWT.END (or SWT.RIGHT), or SWT.FILL
		 * 
		 * @param verticalAlignment
		 *            how control will be positioned vertically within a cell,
		 *            one of: SWT.BEGINNING (or SWT.TOP), SWT.CENTER, SWT.END
		 *            (or SWT.BOTTOM), or SWT.FILL
		 * @param grabExcessHorizontalSpace
		 *            whether cell will be made wide enough to fit the remaining
		 *            horizontal space
		 * @param grabExcessVerticalSpace
		 *            whether cell will be made high enough to fit the remaining
		 *            vertical space
		 */
		rateComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false));
		/***
		 * Whereas the org.eclipse.swt.layout.Griddata is the attribute of the
		 * control, describing its span, indentation, margin etc for each
		 * gird(cell). Each component should have a unique Griddata.
		 */
		layout = new GridLayout();
		layout.marginHeight = 2;
		layout.marginWidth = 5;
		layout.numColumns = 4;
		rateComposite.setLayout(layout);

		final Button skewButton1 = new Button(rateComposite, SWT.BUTTON2);
		skewButton1.setLayoutData(new GridData());
		skewButton1.setText("<skew");

		RateText2 rateText1 = new RateText2(rateComposite, SWT.NONE, "1.38621");

		RateText2 rateText2 = new RateText2(rateComposite, SWT.NONE, "1.38621");

		final Button skewButton2 = new Button(rateComposite, SWT.BUTTON2);
		skewButton2.setLayoutData(new GridData());
		skewButton2.setText("skew>");

		MarginBand marginBand = new MarginBand(messageComposite, SWT.NONE);

		gd = new GridData();
		gd.horizontalSpan = 3;
		marginBand.setLayoutData(gd);

		rateGenerator = new RateGenerator();
		rateGenerator.addRateListener(rateText1);
		rateGenerator.addRateListener(rateText2);

		// l = new Label(banner, SWT.WRAP);
		// l.setText("Date:");
		// l.setFont(boldFont);

		// message contents is a field so that it can be referenced from
		// setFocus()
		// bodyText = new Text(messageComposite, SWT.MULTI | SWT.WRAP);

		generateRates();
	}

	// it is important to implement setFocus()!
	public void setFocus() {
		// bodyText.setFocus();
	}

	private WritableValue messageValue = new WritableValue();
	private MarkViewAsSpamAndMoveHandler markAsSpamHandler;
	private DeleteViewMessageHandler deleteHandler;

	public void setMessage(Message message) {
		messageValue.setValue(message);
		markAsSpamHandler.setEnabled(message != null);
		deleteHandler.setEnabled(message != null);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof MessageTableView
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection treeSelection = (IStructuredSelection) selection;
			Object message = treeSelection.getFirstElement();
			if (message instanceof Message) {
				setMessage((Message) message);
			} else {
				setMessage(null);
			}
		} else {
			setMessage(null);
		}
	}

	public Message getMessage() {
		return (Message) messageValue.getValue();
	}

	public void dispose() {
		super.dispose();
		rateGenerator.stop();
		try {
			ratethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateRates() {
		ratethread = new Thread(rateGenerator);
		ratethread.start();
	}
}
