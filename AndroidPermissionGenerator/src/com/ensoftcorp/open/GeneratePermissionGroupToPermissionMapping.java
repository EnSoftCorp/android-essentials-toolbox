package com.ensoftcorp.open;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Generates code to add Permission objects to their corresponding PermissionGroup 
 * object as defined in the Android Source manifest file
 * @author Ben Holland
 */
public class GeneratePermissionGroupToPermissionMapping {

	// pulls from latest raw version on master of Android github mirror
	public static String ANDROID_SOURCE_MANIFEST = "https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml";
	
	public static void main(String[] args) throws Exception {

		// parse manifest xml file
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document androidManifest = dBuilder.parse(new URL(ANDROID_SOURCE_MANIFEST).openStream());
		androidManifest.getDocumentElement().normalize();

		// for each permission entry record the permission group
		// as a key and the permission as a value
		HashMap<String, HashSet<String>> permissionGroupToPermissionsMapping = new HashMap<String, HashSet<String>>();

		// iterate over permissions and collect attributes
		NodeList permissions = androidManifest.getElementsByTagName("permission");
		for (int i = 0; i < permissions.getLength(); i++) {
			Element permission = (Element) permissions.item(i);
			String permissionName = permission.getAttribute("android:name");
			String permissionGroup = permission.getAttribute("android:permissionGroup");

			// add the permission group mapping for permission
			if (permissionGroupToPermissionsMapping.containsKey(permissionGroup)) {
				permissionGroupToPermissionsMapping.get(permissionGroup).add(permissionName);
			} else {
				HashSet<String> permissionNames = new HashSet<String>();
				permissionNames.add(permissionName);
				permissionGroupToPermissionsMapping.put(permissionGroup, permissionNames);
			}
		}
		
		for (String key : permissionGroupToPermissionsMapping.keySet()) {
			System.out.println("// " + (key.equals("") ? "UNASSIGNED" : key) + " permission group mappings generated from " + ANDROID_SOURCE_MANIFEST + " on " + new Date().toString());
			for (String value : permissionGroupToPermissionsMapping.get(key)) {
				System.out.println((key.equals("") ? "UNASSIGNED" : getSimplePermissionName(key)) + ".permissions.add(Permission." + getSimplePermissionName(value) + ");");
			}
			System.out.println();
		}
		
		System.out.println("-------------------");
		
		for (String key : permissionGroupToPermissionsMapping.keySet()) {
			System.out.println("allPermissionGroups.add(" + (key.equals("") ? "UNASSIGNED" : getSimplePermissionName(key)) + ");");
		}
	}
	
	/**
	 * Helper method for getting a simple permission name from a qualified permission name string
	 * @param qualifiedPermissionName
	 * @return
	 */
	private static String getSimplePermissionName(String qualifiedPermissionName){
		int pos = qualifiedPermissionName.lastIndexOf('.');
		return qualifiedPermissionName.substring(pos + 1);
	}

}
