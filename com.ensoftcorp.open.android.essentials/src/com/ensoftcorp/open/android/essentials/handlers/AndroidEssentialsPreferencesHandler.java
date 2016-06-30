package com.ensoftcorp.open.android.essentials.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * A menu selection handler for opening the preferences page
 * 
 * @author Ben Holland
 */
public class AndroidEssentialsPreferencesHandler extends AbstractHandler {
	public AndroidEssentialsPreferencesHandler() {}

	/**
	 * Runs the purity analysis
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String id = "com.ensoftcorp.open.android.essentials.ui.preferences";
		return PreferencesUtil.createPreferenceDialogOn(Display.getDefault().getActiveShell(), id, new String[] {id}, null).open();
	}
	
}

