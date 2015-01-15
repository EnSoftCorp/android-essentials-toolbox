package com.ensoftcorp.open;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

import com.ensoftcorp.open.android.essentials.permissions.Permission;
import com.ensoftcorp.open.android.essentials.permissions.PermissionGroup;
import com.ensoftcorp.open.android.essentials.permissions.ProtectionLevel;

public class SanityChecks {

	@Test
	public void testPermissionsAreNotDocumentedAndUndocumented() {
		Collection<Permission> intersection = Permission.allDocumentedPermissions;
		intersection.retainAll(Permission.allUndocumentedPermissions);
		if(!intersection.isEmpty()){
			System.err.println("The following permissions are classified as both documented and undocumented!\n" + intersection);
		}
		assertTrue(intersection.isEmpty());
	}
	
	@Test
	public void testUnassignedProtectionLevelPermissionsAreNotAssigned() {
		Collection<ProtectionLevel> protectionLevels = new LinkedList<ProtectionLevel>();
		protectionLevels.addAll(ProtectionLevel.allProtectionLevels);
		protectionLevels.remove(ProtectionLevel.UNASSIGNED);
		for(ProtectionLevel pl : protectionLevels){
			for(Permission permission : pl.getPermissions()){
				if(ProtectionLevel.UNASSIGNED.getPermissions().contains(permission)){
					System.err.println(permission.getQualifiedName() + " is assigned to " + pl.getName() + " but is also in the unassigned protection level!");
				}
				assertTrue(!ProtectionLevel.UNASSIGNED.getPermissions().contains(permission));
			}
		}
	}

	@Test
	public void testAllPermissionsAreAccountedForInProtectionLevels() {
		for(Permission p : Permission.allPermissions){
			boolean permissionAccountedFor = false;
			for(ProtectionLevel pl : ProtectionLevel.allProtectionLevels){
				if(pl.getPermissions().contains(p)){
					permissionAccountedFor = true;
					break;
				}
			}
			if(!permissionAccountedFor){
				System.err.println("Permission " + p.getQualifiedName() + " is unaccounted for in any protection levels.");
			}
			assertTrue(permissionAccountedFor);
		}
	}
	
	@Test
	public void testUnassignedPermissionGroupPermissionsAreNotAssigned() {
		Collection<PermissionGroup> permissionGroups = new LinkedList<PermissionGroup>();
		permissionGroups.addAll(PermissionGroup.allPermissionGroups);
		permissionGroups.remove(PermissionGroup.UNASSIGNED);
		for(PermissionGroup pg : permissionGroups){
			for(Permission permission : pg.getPermissions()){
				if(PermissionGroup.UNASSIGNED.getPermissions().contains(permission)){
					System.err.println(permission.getQualifiedName() + " is assigned to " + pg.getQualifiedName() + " but is also in the unassigned permission group!");
				}
				assertTrue(!PermissionGroup.UNASSIGNED.getPermissions().contains(permission));
			}
		}
	}
	
	@Test
	public void testAllPermissionsAreAccountedForInPermissionGroups() {
		for(Permission p : Permission.allPermissions){
			boolean permissionAccountedFor = false;
			for(PermissionGroup pg : PermissionGroup.allPermissionGroups){
				if(pg.getPermissions().contains(p)){
					permissionAccountedFor = true;
					break;
				}
			}
			if(!permissionAccountedFor){
				System.err.println("Permission " + p.getQualifiedName() + " is unaccounted for in any permission groups.");
			}
			assertTrue(permissionAccountedFor);
		}
	}

}
