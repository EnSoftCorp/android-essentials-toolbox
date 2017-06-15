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
 * Generates code to add Permission objects to their corresponding
 * ProtectionLevel object as defined in the Android Source manifest file
 * 
 * @author Ben Holland
 */
public class GenerateProtectionLevelToPermissionMapping {

	// pulls from latest raw version on master of Android github mirror
	// https://github.com/android/platform_frameworks_base/blob/master/core/res/AndroidManifest.xml
	public static String ANDROID_SOURCE_MANIFEST = "https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml";

	public static void main(String[] args) throws Exception {
		// parse manifest xml file
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document androidManifest = dBuilder.parse(new URL(ANDROID_SOURCE_MANIFEST).openStream());
		androidManifest.getDocumentElement().normalize();

		// for each permission entry record the permission
		// as a key and the protection level as a value
		HashMap<String, HashSet<String>> protectionLevelToPermissionMapping = new HashMap<String, HashSet<String>>();

		// iterate over permissions and collect attributes
		NodeList permissions = androidManifest.getElementsByTagName("permission");
		for (int i = 0; i < permissions.getLength(); i++) {
			Element permission = (Element) permissions.item(i);
			String permissionName = permission.getAttribute("android:name");
			String protectionLevelString = permission.getAttribute("android:protectionLevel");

			// add the protection level mapping for permission
			for (String protectionLevel : protectionLevelString.split("\\|")) {
				if (protectionLevelToPermissionMapping.containsKey(protectionLevel)) {
					protectionLevelToPermissionMapping.get(protectionLevel).add(permissionName);
				} else {
					HashSet<String> permissionNames = new HashSet<String>();
					permissionNames.add(permissionName);
					protectionLevelToPermissionMapping.put(protectionLevel, permissionNames);
				}
			}
		}

		for (String key : protectionLevelToPermissionMapping.keySet()) {
			System.out.println("// " + (key.equals("") ? "UNASSIGNED" : key) + " protection level mappings generated from " + ANDROID_SOURCE_MANIFEST + " on " + new Date().toString());
			for (String value : protectionLevelToPermissionMapping.get(key)) {
				System.out.println((key.equals("") ? "UNASSIGNED" : key.replaceAll("(.)([A-Z])", "$1_$2").toUpperCase()) + ".permissions.add(Permission." + getSimplePermissionName(value) + ");");
			}
			System.out.println();
		}
		
		System.out.println("-------------------");
		
		for (String key : protectionLevelToPermissionMapping.keySet()) {
			System.out.println("allProtectionLevels.add(" + (key.equals("") ? "UNASSIGNED" : key.replaceAll("(.)([A-Z])", "$1_$2").toUpperCase()) + ");");
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
