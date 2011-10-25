package rcpmail.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import rcpmail.Application;

public class OpenNewMonitorHandler extends AbstractHandler implements IHandler {

	private int instanceNum = 0;
	private final String viewId = "sekoy.rcp.first";

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow activeWorkbenchWindow = HandlerUtil
				.getActiveWorkbenchWindow(event);
		if (activeWorkbenchWindow != null) {

			try {
				IAdaptable pageInput = activeWorkbenchWindow.getActivePage()
						.showView(viewId, Integer.toString(instanceNum++),
								IWorkbenchPage.VIEW_ACTIVATE);
			} catch (PartInitException e) {
				StatusManager.getManager().handle(
						new Status(IStatus.ERROR, Application.PLUGIN_ID,
								"Could not open window", e));
			}
		}
		return null;
	}

}
