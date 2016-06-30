package com.ensoftcorp.open.android.essentials.handlers;
import java.util.HashMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.open.android.essentials.log.Log;
import com.ensoftcorp.open.android.essentials.permissions.Permission;
import com.ensoftcorp.open.android.essentials.permissions.mappings.PermissionMapping;
import com.ensoftcorp.open.android.essentials.preferences.AndroidEssentialsPreferences;
import com.ensoftcorp.open.commons.utils.DisplayUtils;

/**
 * A menu selection handler for running permission mapping logic
 * 
 * @author Ben Holland
 */
public class ApplyPermissionMappingsHandler extends AbstractHandler {
	public ApplyPermissionMappingsHandler() {}

	/**
	 * Runs the purity analysis
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String prefix = "";
		String appliedVersions = "";
		for(Integer apiVersion : PermissionMapping.getAvailableMappings()){
			if(AndroidEssentialsPreferences.isMappingEnabled(apiVersion)){
				HashMap<Permission, AtlasHashSet<GraphElement>> result = PermissionMapping.applyTags(apiVersion);
				long totalNodesTagged = 0;
				for(AtlasHashSet<GraphElement> nodes : result.values()){
					totalNodesTagged += nodes.size();
				}
				Log.info("Applied " + totalNodesTagged + " tags for Android API version " + apiVersion);
				appliedVersions += (prefix + apiVersion);
				prefix = ", ";
			}
		}
		DisplayUtils.showMessage("Applied permission tags for Android APIs: [" + appliedVersions + "].");
		return null;
	}
	
}
