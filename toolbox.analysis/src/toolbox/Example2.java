package toolbox;

import java.util.Collection;
import java.util.LinkedList;

import toolbox.android.essentials.permissions.Permission;
import toolbox.android.essentials.permissions.PermissionGroup;
import toolbox.android.essentials.permissions.ProtectionLevel;

public class Example2 {

	/**
	 * Returns a collection of Permissions that are used in the permission group
	 * @param permissionGroup The PermissionGroup to query against
	 * @param apiVersion The Android API level to query against
	 * @return A collection of used permissions
	 */
	public static Collection<Permission> getUsedPermissionGroupPermissions(PermissionGroup permissionGroup, int apiVersion) {
		Collection<Permission> usedPermissions = new LinkedList<Permission>();
		// for each permission in the given permission group
		for(Permission permission : permissionGroup.getPermissions()){
			// check and add permission if it is used
			if(Example1.isPermissionUsed(permission, apiVersion)){
				usedPermissions.add(permission);
			}
		}
		return usedPermissions;
	}
	
	/**
	 * Returns true if the given permission group is used in the Android app
	 * @param permissionGroup The PermissionGroup to query against
	 * @param apiVersion The Android API level to query against
	 * @return Returns true if the permission group is used, false otherwise
	 */
	public static boolean isPermissionGroupUsed(PermissionGroup permissionGroup, int apiVersion) {
		return !getUsedPermissionGroupPermissions(permissionGroup, apiVersion).isEmpty();
	}
	
	/**
	 * Returns a collection of Permissions that are used in the protection level
	 * @param protectionLevel The ProtectionLevel to query against
	 * @param apiVersion The Android API level to query against
	 * @return A collection of used permissions
	 */
	public static Collection<Permission> getUsedProtectionLevelPermissions(ProtectionLevel protectionLevel, int apiVersion) {
		Collection<Permission> usedPermissions = new LinkedList<Permission>();
		// for each permission in the given protection level
		for(Permission permission : protectionLevel.getPermissions()){
			// check and add permission if it is used
			if(Example1.isPermissionUsed(permission, apiVersion)){
				usedPermissions.add(permission);
			}
		}
		return usedPermissions;
	}
	
	/**
	 * Returns true if the given protection level is used in the Android app
	 * @param protectionLevel The ProtectionLevel to query against
	 * @param apiVersion The Android API level to query against
	 * @return Returns true if the permission group is used, false otherwise
	 */
	public static boolean isProtectionLevelUsed(ProtectionLevel protectionLevel, int apiVersion) {
		return !getUsedProtectionLevelPermissions(protectionLevel, apiVersion).isEmpty();
	}
}
