package toolbox.analysis;

import java.util.HashMap;
import java.util.Map.Entry;

import toolbox.android.essentials.Manifest;
import toolbox.android.essentials.permissions.Permission;
import toolbox.android.essentials.permissions.mappings.PermissionMapping;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.atlas.core.script.CommonQueries;

public class Example0 {

	/**
	 * Given an Android project in the workspace, this method applies the nearest permission mapping (rounding up if need be)
	 * to the target SDK version of the specified Android Eclipse project.
	 * Note: If the permission mapping is unsuccessful, Atlas Jar indexing may be disable.  Enabling Jar indexing is required.
	 * @param projectName An Eclipse project name of an Android application in the workspace to analyze
	 * @return True if the permission mapping was successfully applied, false otherwise
	 * @throws Exception Exception in the event that AndroidManifest.xml files is missing or corrupt
	 */
	public static boolean applyApplicationTargetSDKPermissionMapping(String projectName) throws Exception {
		// parse the manifest for the target sdk version
		Manifest manifest = new Manifest(Manifest.getManifestFile(projectName));
		int targetSDKVersion = manifest.getTargetSDKVersion();
		// check if the permission mapping has already been applied
		String tagPrefix = PermissionMapping.getTagPrefix(targetSDKVersion);
		if(!CommonQueries.nodesStartingWith(tagPrefix).eval().nodes().isEmpty()){
			return true; // permission mapping has already been applied
		}
		// need to apply permission mapping
		HashMap<Permission, AtlasHashSet<GraphElement>> permissionMapping = PermissionMapping.applyTags(targetSDKVersion);
		// permission mapping is successful if for at least one permission, a tag was applied to the graph
		boolean success = false;
		for(Entry<Permission, AtlasHashSet<GraphElement>> mapping : permissionMapping.entrySet()){
			if(mapping.getValue().size() > 0){
				success = true;
				break;
			}
		}
		return success;
	}
	
}
