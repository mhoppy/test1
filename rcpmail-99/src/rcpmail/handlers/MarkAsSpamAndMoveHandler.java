package rcpmail.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import rcpmail.model.Folder;
import rcpmail.model.Message;

public class MarkAsSpamAndMoveHandler extends AbstractHandler implements
		IHandler {
	
	public static final String MARK_AS_SPAM_COMMAND_ID = "rcpmail.markAsSpamAndMove";

	
	static void markAndMoveMessage(Message msg) {
		msg.setSpam(true);
		Folder junk = msg.getFolder().getServer().getJunkFolder();
		Folder current = msg.getFolder();
		if (current != junk) {
			current.removeMessage(msg);
			junk.addMessage(msg);
		}
	}

	@SuppressWarnings("unchecked")
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection sel = HandlerUtil.getCurrentSelection(event);
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) sel;
			Iterator i = selection.iterator();
			while (i.hasNext()) {
				Message msg = (Message) i.next();
				markAndMoveMessage(msg);
			}
		}
		return null;
	}

}
