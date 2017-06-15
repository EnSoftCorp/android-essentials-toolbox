package com.ensoftcorp.open;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ensoftcorp.open.android.essentials.permissions.Permission;

/**
 * Generates code to add undocumented Permission objects
 * 
 * @author Ben Holland
 */
public class GenerateUndocumentedPermissions {

	// pulls from latest raw version on master of Android github mirror
	// https://github.com/android/platform_frameworks_base/blob/master/core/res/AndroidManifest.xml
	public static String ANDROID_SOURCE_MANIFEST = "https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml";

	public static void main(String[] args) throws Exception {
		// parse manifest xml file using XML parser
		String xml = getUrlContent(ANDROID_SOURCE_MANIFEST);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource stringStream = new InputSource(new StringReader(xml));
		Document androidManifest = dBuilder.parse(stringStream);
		androidManifest.getDocumentElement().normalize();

		// parse the file again using jsoup as a way to get the xml comment preceeding the xml elements which contains the permissions description
		org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(xml, "", Parser.xmlParser());
		
		// for each permission entry record the permission
		HashSet<String> qualifiedPermissionNames = new HashSet<String>();

		// iterate over permissions and collect attributes
		NodeList permissions = androidManifest.getElementsByTagName("permission");
		for (int i = 0; i < permissions.getLength(); i++) {
			Element permission = (Element) permissions.item(i);
			String permissionName = permission.getAttribute("android:name");
			qualifiedPermissionNames.add(permissionName);
		}
		
		for(String qualifiedPermissionName : qualifiedPermissionNames){
			if(isUndocumentedPermission(qualifiedPermissionName)){
				org.jsoup.nodes.Element permissionElement = jsoupDoc.select("permission[android:name=" + qualifiedPermissionName + "]").first();
				org.jsoup.nodes.Comment comment = getPreceedingComment(permissionElement);
				String commentString = (comment == null) ? "" : comment.getData().replaceAll("\\s+", " ").replace("@SystemApi ", "").replace("@hide ", "").replace("@link ", "").replace("<p> ", "").replace("</p> ", "").trim();
				String description = commentString.equals("") ? "No description provided." : ("This permission is undocumented, its description has been scraped from the Android source.\n" + commentString);
				System.out.println("public static final Permission " + getSimplePermissionName(qualifiedPermissionName) + " = new Permission(\"" + qualifiedPermissionName + "\", "
						+ "-1, "
						+ "-1, "
						+ "\"" + description.replace("\n", "\\n").replace("\"", "\\\"") + "\", "
						+ "\"Permission was found in " + ANDROID_SOURCE_MANIFEST + ", but not in " + GenerateDocumentedPermissionGroups.DOCUMENTED_PERMISSIONS_REFERENCE + ".\");");
			}
		}
		
		System.out.println("-----------------");
		
		for(String qualifiedPermissionName : qualifiedPermissionNames){
			if(isUndocumentedPermission(qualifiedPermissionName)){
				System.out.println("allUndocumentedPermissions.add(" + getSimplePermissionName(qualifiedPermissionName) + ");");
			}
		}
	}
	
	/**
	 * Gets a string containing the contents of the webpage at the given url
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static String getUrlContent(String url) throws Exception {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
	/**
	 * Searches for the preceeding sibling level comment before the given xml permission element
	 * @param permissionElement
	 * @return
	 */
	private static org.jsoup.nodes.Comment getPreceedingComment(org.jsoup.nodes.Element permissionElement){
		org.jsoup.nodes.Node node = permissionElement;
		while(true){
			node = node.previousSibling();
			if(node instanceof Comment){
				return (org.jsoup.nodes.Comment) node;
			} else if(node instanceof org.jsoup.nodes.TextNode){
				 // important, there is a trailing whitespace character after the comment that is considered as a node
				continue;
			} else if(node instanceof org.jsoup.nodes.Element){
				return null;
			}
		}
	}
	
	/**
	 * Returns true if the permission is not already contained in the Permission.allDocumentedPermissions set
	 * @param qualifiedPermissionName
	 * @return
	 */
	private static boolean isUndocumentedPermission(String qualifiedPermissionName){
		for(Permission permission : Permission.getAllDocumentedPermissions()){
			if(permission.getQualifiedName().equals(qualifiedPermissionName)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Helper method for getting a permissions simple name from a qualified permission name string
	 * @param qualifiedPermissionName
	 * @return
	 */
	private static String getSimplePermissionName(String qualifiedPermissionName){
		int pos = qualifiedPermissionName.lastIndexOf('.');
		return qualifiedPermissionName.substring(pos + 1);
	}

}
