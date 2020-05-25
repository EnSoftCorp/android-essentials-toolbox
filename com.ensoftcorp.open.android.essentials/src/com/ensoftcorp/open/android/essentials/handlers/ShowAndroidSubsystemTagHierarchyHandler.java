package com.ensoftcorp.open.android.essentials.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.ensoftcorp.atlas.ui.viewer.graph.XCSGHierarchyGraphUtil;
import com.ensoftcorp.open.android.essentials.subsystems.AndroidSubsystem;
import com.ensoftcorp.open.commons.ui.utilities.DisplayUtils;

/**
 * A menu handler for the Android subsystem tag hierarchy
 * 
 * @author Ben Holland
 */
public class ShowAndroidSubsystemTagHierarchyHandler extends AbstractHandler {
	public ShowAndroidSubsystemTagHierarchyHandler() {}

	/**
	 * Opens a prompt to enter a graph element address to show
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// check to see if the subsystems have been registered yet
		boolean subsystemTagHierarchyExists = false;
		for(String tag : XCSG.HIERARCHY.registeredTags()){
			if(tag.equals(AndroidSubsystem.TAG)){
				subsystemTagHierarchyExists = true;
			}
		}
		
		// show the subsystem hierarchy
		if(subsystemTagHierarchyExists){
			Q hierarchy = XCSGHierarchyGraphUtil.getXCSGHiearchyQ(AndroidSubsystem.TAG);
			DisplayUtils.show(hierarchy, "Android Subsystem Hierarchy");
		} else {
			DisplayUtils.showError("Android subsystems have not been registered yet!");
		}

		// returns the result of the execution (reserved for future use, must be null)
		return null;
	}
	
}