package com.ensoftcorp.open.android.essentials.permissions.mappings;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.java.core.script.Common;
import com.ensoftcorp.open.android.essentials.permissions.Permission;
import com.ensoftcorp.open.android.essentials.permissions.PermissionGroup;
import com.ensoftcorp.open.android.essentials.permissions.ProtectionLevel;

/**
 * @author Ben Holland
 *
 */
public class PermissionMapping {
	
	/**
	 * Defines the highest available permission mapping, if no mapping can be found 
	 * for the requested version this is the default.
	 */
	public static final int HIGHEST_AVAILABLE_MAPPING = 21;
	
	private static final String MAPPING_FILENAME_PREFIX = "API";
	private static final ArrayList<InputStream> PERMISSION_MAPPING_RESOURCES = findPermissionMappingResources();
	
	// helper method to locate all available mappings up to the highest available mapping
	private static ArrayList<InputStream> findPermissionMappingResources(){
		ArrayList<InputStream> permissionMappingResources = new ArrayList<InputStream>();
		for(int apiVersion = 1; apiVersion <= HIGHEST_AVAILABLE_MAPPING; apiVersion++){
			String xmlFile = MAPPING_FILENAME_PREFIX + apiVersion + ".xml";
			permissionMappingResources.add(PermissionMapping.class.getResourceAsStream(xmlFile));
		}
		return permissionMappingResources;
	}
	
	/**
	 * Returns the permission tagged methods for the permission instance
	 * Assumes the permission mapping tags have already been applied
	 * @param apiVersion
	 * @return
	 */
	public static Q getMethods(Permission permission, int apiVersion){
		return PermissionMapping.getPermissionTaggedMethods(permission, apiVersion);
	}
	
	
	/**
	 * Returns the permission tagged methods for the permissions in the permission group
	 * Assumes the permission mapping tags have already been applied
	 * @param apiVersion
	 * @return
	 */
	public static Q getMethods(PermissionGroup permissionGroup, int apiVersion){
		AtlasHashSet<GraphElement> methods = new AtlasHashSet<GraphElement>();
		for(Permission permission : permissionGroup.getPermissions()){
			methods.addAll(getMethods(permission, apiVersion).eval().nodes());
		}
		return Common.toQ(Common.toGraph(methods));
	}
	
	/**
	 * Returns the permission tagged methods for the permissions in the protection level
	 * Assumes the permission mapping tags have already been applied
	 * @param apiVersion
	 * @return
	 */
	public static Q getMethods(ProtectionLevel protectionLevel, int apiVersion){
		AtlasHashSet<GraphElement> methods = new AtlasHashSet<GraphElement>();
		for(Permission permission : protectionLevel.getPermissions()){
			methods.addAll(getMethods(permission, apiVersion).eval().nodes());
		}
		return Common.toQ(Common.toGraph(methods));
	}
	
	/**
	 * Returns the tagging prefix for a given android api version
	 * @param apiVersion
	 * @return
	 */
	public static String getTagPrefix(int apiVersion) {
		return getBestAvailableMapping(apiVersion) + "-";
	}
	
	/**
	 * Returns the api version of the closest available permission mapping to a given android api version
	 * Searches for forward for next higher available mapping
	 * If no mapping is found, defaults to the highest available mapping
	 * @param apiVersion
	 * @return
	 */
	public static int getBestAvailableMapping(int apiVersion) {
		int version = -1;
		// search for the requested mapping or next available mapping
		if(apiVersion > 0){
			for(int i=apiVersion; i<PERMISSION_MAPPING_RESOURCES.size(); i++){
				if(PERMISSION_MAPPING_RESOURCES.get(i) != null){
					version = i;
					break;
				}
			}
		}
		// if mapping is still not found, just default to the highest available mapping
		if(version == -1){
			version = HIGHEST_AVAILABLE_MAPPING;
		}
		return version;
	}
	
	/**
	 * Returns a Q of permission tagged nodes for the given API version
	 * @param permission
	 * @param apiVersion
	 * @return
	 */
	public static Q getPermissionTaggedMethods(Permission permission, int apiVersion){
		return Common.universe().nodesTaggedWithAll(getPermissionTag(permission, apiVersion));
	}
	
	/**
	 * Returns the tag string for a given permission and Android api version
	 * @param permission
	 * @param apiVersion
	 * @return
	 */
	private static String getPermissionTag(Permission permission, int apiVersion){
		return Integer.toString(apiVersion) + "-" + permission.getQualifiedName();
	}
	
	/**
	 * This method applies tags to the Android api's. The versions with no
	 * Permission mapping will switch to the next available version.
	 */
	public static HashMap<Permission, AtlasHashSet<GraphElement>> applyTags(int apiVersion) {
		// based on the input API version, round up to the nearest available API mapping
		apiVersion = getBestAvailableMapping(apiVersion);
		
		// apply the mapping tags
		HashMap<Permission, AtlasHashSet<GraphElement>> permissionMap = new HashMap<Permission, AtlasHashSet<GraphElement>>();
		try {
			// read xml file that is under the same package of this class
			String xmlFile = MAPPING_FILENAME_PREFIX + apiVersion + ".xml";
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(PermissionMapping.class.getResourceAsStream(xmlFile));

			// get the permission tag list
			NodeList permissionList = doc.getElementsByTagName("permission");

			for (int permissionNum = 0; permissionNum < permissionList.getLength(); permissionNum++) {
				Node permissionNode = permissionList.item(permissionNum);

				if (permissionNode.getNodeType() == Node.ELEMENT_NODE) {
					// select the current permission tag
					Element permission = (Element) permissionNode;
					String permissionName = permission.getAttribute("name");

					// get the call tag list under current permission tag
					NodeList callList = permission.getElementsByTagName("call");

					// initialize the hashset for storing found method
					AtlasHashSet<GraphElement> methods = new AtlasHashSet<GraphElement>();

					for (int callNum = 0; callNum < callList.getLength(); callNum++) {
						Node callNode = callList.item(callNum);
						if (callNode.getNodeType() == Node.ELEMENT_NODE) {
							// select current call tag
							Element call = (Element) callNode;

							// get package, class, method, return type
							// under this call tag
							String packageName = call.getElementsByTagName("package").item(0).getTextContent();
							String className = call.getElementsByTagName("class").item(0).getTextContent();
							String methodName = call.getElementsByTagName("method").item(0).getTextContent();

//							// TODO: optionally use return type to enhance method check
//							String returnType = call.getElementsByTagName("returnType").item(0).getTextContent();

							// get parameters tag under call tag
							Node parameterNodes = call.getElementsByTagName("parameters").item(0);
							Element parameters = (Element) parameterNodes;
							// get parameter names under parameters tags
							NodeList parameterList = parameters.getElementsByTagName("parameter");
							int parametersNum = parameterList.getLength();
							ArrayList<String> paramList = new ArrayList<String>();
							for (int parameterNum = 0; parameterNum < parametersNum; parameterNum++) {
								Element parameter = (Element) parameterList.item(parameterNum);
								paramList.add(parameter.getTextContent());
							}

							// find Method to search inside index workspace and apply tag
							String apiWithQualifiedPermissionTag = apiVersion + "-" + permissionName;
							String qualifiedPermissionTag = permissionName;
							GraphElement methodNode = PermissionUtils.tagMethod(packageName, className, methodName, paramList.toArray(new String[0]), apiWithQualifiedPermissionTag, qualifiedPermissionTag);

							// add method node to hashset
							if (methodNode != null){
								methods.add(methodNode);
							}
						}
					}// end the for loop for each call(method)

					// add permission->methods to the map for each permission
					permissionMap.put(Permission.getPermissionByQualifiedName(permissionName), methods);
				}// end the if for each permission
			}// end the for loop for permission
		} catch (Exception e) {
			throw new RuntimeException("Tagging permissions failed", e);
		}
		return permissionMap;
	}
}