package toolbox.analysis;

import java.util.Collection;
import java.util.LinkedList;

import com.ensoftcorp.open.android.essentials.AndroidManifest;
import com.ensoftcorp.open.android.essentials.permissions.Permission;

public class Example3 {
	
	/**
	 * List the permissions requested in the manifest that are used by the app
	 * @param projectName An Eclipse project name of an Android application in the workspace to analyze
	 * @return A collection of used Permission objects
	 * @throws Exception Exception in the event that AndroidManifest.xml files is missing or corrupt
	 */
	public static Collection<Permission> getUsedPermissions(String projectName) throws Exception {
		// parse the manifest for the target sdk version and the requested permissions
		AndroidManifest manifest = new AndroidManifest(AndroidManifest.getManifestFile(projectName));
		int targetSDKVersion = manifest.getTargetSDKVersion();
		Collection<Permission> requestedPermissions = manifest.getUsesPermissions();
		// create a collection of unused permissions
		Collection<Permission> usedPermissions = new LinkedList<Permission>();
		for(Permission permission : requestedPermissions){
			if(Example1.isPermissionUsed(permission, targetSDKVersion)){
				usedPermissions.add(permission);
			}
		}
		return usedPermissions;
	}
	
	/**
	 * List the permissions requested in the manifest that are unused by the app
	 * @param projectName An Eclipse project name of an Android application in the workspace to analyze
	 * @return A collection of unused Permission objects
	 * @throws Exception Exception in the event that AndroidManifest.xml files is missing or corrupt
	 */
	public static Collection<Permission> getUnusedPermissions(String projectName) throws Exception {
		// parse the manifest for the target sdk version and the requested permissions
		AndroidManifest manifest = new AndroidManifest(AndroidManifest.getManifestFile(projectName));
		int targetSDKVersion = manifest.getTargetSDKVersion();
		Collection<Permission> requestedPermissions = manifest.getUsesPermissions();
		// create a collection of unused permissions
		Collection<Permission> unusedPermissions = new LinkedList<Permission>();
		for(Permission permission : requestedPermissions){
			if(!Example1.isPermissionUsed(permission, targetSDKVersion)){
				unusedPermissions.add(permission);
			}
		}
		return unusedPermissions;
	}

}
