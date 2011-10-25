package rcpmail;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonNavigator;

import rcpmail.model.Model;

public class Navigator extends CommonNavigator {

	public static String VIEW_ID = "rcpmail.navigatorView";
	
	public Object getInitialInput() {
		return Model.getInstance();
	}
	
	public void createPartControl(Composite aParent) {
		super.createPartControl(aParent);
		TreeViewer treeViewer = getCommonViewer();
		Object defaultSelection = Model.getInstance().getDefaultSelection();
		if (defaultSelection != null) {
			treeViewer.setSelection(new StructuredSelection(defaultSelection));
		}
	}
}
