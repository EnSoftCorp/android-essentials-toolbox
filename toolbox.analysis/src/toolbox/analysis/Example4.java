package toolbox.analysis;

import java.util.Collection;

import com.ensoftcorp.open.android.essentials.AndroidManifest;
import com.ensoftcorp.open.android.essentials.permissions.Permission;

public class Example4 {
	
	/**
	 * Returns true if the app is overprivileged (requests permissions it does not use)
	 * @param projectName An Eclipse project name of an Android application in the workspace to analyze
	 * @return True if overprivileged, false otherwise
	 * @throws Exception Exception in the event that AndroidManifest.xml files is missing or corrupt
	 */
	public static boolean isOverprivileged(String projectName) throws Exception {
		return !Example3.getUnusedPermissions(projectName).isEmpty();
	}
	
	/**
	 * Returns true if the app is underprivileged (used permission protected methods the app does not request permission for)
	 * @param projectName An Eclipse project name of an Android application in the workspace to analyze
	 * @return True if underprivileged, false otherwise
	 * @throws Exception Exception in the event that AndroidManifest.xml files is missing or corrupt
	 */
	public static boolean isUnderprivileged(String projectName) throws Exception {
		// parse the manifest for the target sdk version and the requested permissions
		AndroidManifest manifest = new AndroidManifest(AndroidManifest.getManifestFile(projectName));
		int targetSDKVersion = manifest.getTargetSDKVersion();
		Collection<Permission> requestedPermissions = manifest.getUsesPermissions();
		// search for permissions that are used but not requested
		for(Permission permission : Permission.allPermissions){
			if(Example1.isPermissionUsed(permission, targetSDKVersion)){
				if(!requestedPermissions.contains(permission)){
					return true;
				}
			}
		}
		return false;
	}

}
