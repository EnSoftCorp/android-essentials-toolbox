package com.ensoftcorp.open.android.essentials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ensoftcorp.open.android.essentials.permissions.Permission;
import com.ensoftcorp.open.android.essentials.permissions.mappings.PermissionMapping;

/**
 * This is a container object for encapsulating data and logic for parsing an Android Manifest file
 * @author Ben Holland
 */
public class AndroidManifest {

	/**
	 * Android Manifest file name
	 */
	public final static String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";

	/**
	 * Returns a File object pointing to the AndroidManifest.xml file for the given Eclipse Android workspace project
	 * @param project
	 * @return
	 * @throws Exception
	 */
	public static File getManifestFile(String project) throws Exception {
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(project).exists()) {
			if (ResourcesPlugin.getWorkspace().getRoot().getProject(project).getFile(ANDROID_MANIFEST_FILENAME).exists()) {
				return ResourcesPlugin.getWorkspace().getRoot().getProject(project).getFile(ANDROID_MANIFEST_FILENAME).getLocation().toFile();
			}
		}
		throw new IllegalArgumentException("Manifest file not found");
	}

	/**
	 * Returns File objects pointing to AndroidManifest.xml files of projects the given Eclipse Android workspace project depends upon
	 * @param project
	 * @return
	 * @throws CoreException
	 */
	public static Collection<File> getDependentManifestFiles(String project) throws CoreException {
		Collection<File> files = new LinkedList<File>();
		for (IProject dependency : ResourcesPlugin.getWorkspace().getRoot().getProject(project).getReferencedProjects()) {
			if (dependency.getFile(ANDROID_MANIFEST_FILENAME).exists()) {
				files.add(dependency.getFile(ANDROID_MANIFEST_FILENAME).getLocation().toFile());
			}
		}
		return files;
	}

	/**
	 * Returns a collection of Manifest objects for each AndroidManifest.xml file given
	 * @param files
	 * @return
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public static Collection<AndroidManifest> parseManifestFiles(Collection<File> files) throws IllegalArgumentException, IOException {
		Collection<AndroidManifest> manifests = new LinkedList<AndroidManifest>();
		for (File file : files) {
			manifests.add(new AndroidManifest(file));
		}
		return manifests;
	}

	private File projectDirectory;
	private File manifestLocation;
	private Document androidManifest;
	private Collection<Permission> manifestUsesPermissions = new LinkedList<Permission>();
	private HashSet<String> manifestUnidentifedPermissions = new HashSet<String>();
	private ArrayList<File> appIcons = new ArrayList<File>(); // sorted by best to worst resolutions
	// Google: If you do not declare this attribute, the system assumes a default value of "1",
	// which indicates that your application is compatible with all versions of Android.
	private int minSDKVersion = 1;
	// Google: Declaring this attribute is not recommended. First, there is no need to set the
	// attribute as means of blocking deployment of your application onto new versions of the
	// Android platform as they are released. By design, new versions of the platform are fully
	// backward-compatible....Future versions of Android (beyond Android 2.0.1) will no longer
	// check or enforce the maxSdkVersion attribute during installation or re-validation.
	private int maxSDKVersion = PermissionMapping.HIGHEST_AVAILABLE_MAPPING;
	// Google: If not set, the default value equals that given to minSdkVersion.
	private int targetSDKVersion = minSDKVersion;
	
	/**
	 * Constructs an AndroidManifest object from the given AndroidManifest.xml file
	 * @param androidManifestFile
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public AndroidManifest(File androidManifestFile) throws IOException, IllegalArgumentException {
		projectDirectory = androidManifestFile.getParentFile();
		manifestLocation = androidManifestFile;

		// read the manifest file into memory
		if (androidManifestFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				androidManifest = dBuilder.parse(androidManifestFile);
				androidManifest.getDocumentElement().normalize();
			} catch (Exception e){
				throw new IOException("Project manifest file is corrupted.", e);
			}
		} else {
			throw new IllegalArgumentException("Project manifest file is missing.");
		}

		// parse out the uses permissions
		parseUsesPermissions();
		
		// parse the min, max, and target sdk versions
		parseUsesSDK();

		// parse out the app icon
		locateAppIconFiles();
	}

	/**
	 * Returns a collection of requested Android Permissions (includes documented, undocumented types)
	 * @return
	 */
	public Collection<Permission> getUsesPermissions() {
		return manifestUsesPermissions;
	}
	
	/**
	 * Returns unidentified requested permissions.  These are either custom application permissions or 
	 * permissions that have not be included in the referenced permission mapping permissions list.
	 * @return
	 */
	public HashSet<String> getUnidentifedUsesPermissions(){
		return manifestUnidentifedPermissions;
	}

	/**
	 * Returns an ordered list of File objects pointing to the App icons found in the workspace
	 * List is ordered by highest to lowest app icon resolutions
	 * Collection may be empty if not app icons were located
	 * @return
	 */
	public ArrayList<File> getAppIcons() {
		return appIcons;
	}

	/**
	 * Returns the highest resolution app icon located in the workspace or null if no app icons were located
	 * @return
	 */
	public File getAppIcon() {
		if(!appIcons.isEmpty()){
			return appIcons.get(0);  // return the highest quality app icon that was discovered
		}
		return null;
	}
	
	/**
	 * Returns the location of the AndroidManifest.xml file represented by this object instance
	 * @return
	 */
	public File getManifestLocation() {
		return manifestLocation;
	}
	
	/**
	 * Returns the min supported SDK version
	 * @return
	 */
	public int getMinSDKVersion() {
		return minSDKVersion;
	}

	/**
	 * Returns the max supported SDK version (or highest available mapping known in the PermissionMapping if not set)
	 * @return
	 */
	public int getMaxSDKVersion() {
		return maxSDKVersion;
	}

	/**
	 * Returns the target supported SDK version
	 * @return
	 */
	public int getTargetSDKVersion() {
		return targetSDKVersion;
	}

	/**
	 * Helper method for parsing requested permissions
	 */
	private void parseUsesPermissions() {
		NodeList usesPermissions = androidManifest.getElementsByTagName("uses-permission");
		if (usesPermissions.getLength() != 0) {
			try {
				for (int i = 0; i < usesPermissions.getLength(); i++) {
					Node node = usesPermissions.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						String qualifiedPermissionName = element.getAttribute("android:name");
						Permission permission = Permission.getPermissionByQualifiedName(qualifiedPermissionName);
						if(permission != null){
							manifestUsesPermissions.add(permission);
						} else {
							manifestUnidentifedPermissions.add(qualifiedPermissionName);
						}
					}
				}
			} catch (Exception e) {
				// manifest is malformed
			}
		}
	}
	
	/**
	 * Helper method for parsing min, max, and target api versions
	 */
	private void parseUsesSDK(){
		NodeList usesSdk = androidManifest.getElementsByTagName("uses-sdk");
		if (usesSdk.getLength() != 0) {
			try {
				for (int i = 0; i < usesSdk.getLength(); i++) {
					Node node = usesSdk.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						
						// check for specified min sdk version
						if(element.hasAttribute("android:minSdkVersion") || element.hasAttribute("minSdkVersion")){
							String minSDK = element.getAttribute("android:minSdkVersion");
							if(minSDK == null || minSDK.equals("")){
								minSDK = element.getAttribute("minSdkVersion");
							}
							try {
								minSDKVersion = Integer.parseInt(minSDK);
								targetSDKVersion = minSDKVersion;
							} catch (Exception e){}
						}
						
						// check for specified max sdk version
						if(element.hasAttribute("android:maxSdkVersion") || element.hasAttribute("maxSdkVersion")){
							String maxSDK = element.getAttribute("android:maxSdkVersion");
							if(maxSDK == null || maxSDK.equals("")){
								maxSDK = element.getAttribute("maxSdkVersion");
							}
							try {
								maxSDKVersion = Integer.parseInt(maxSDK);
							} catch (Exception e){}
						}
						
						// check for specified target sdk version
						if(element.hasAttribute("android:targetSdkVersion") || element.hasAttribute("targetSdkVersion")){
							String targetSDK = element.getAttribute("android:targetSdkVersion");
							if(targetSDK == null || targetSDK.equals("")){
								targetSDK = element.getAttribute("targetSdkVersion");
							}
							try {
								targetSDKVersion = Integer.parseInt(targetSDK);
							} catch (Exception e){}
						}
					}
				}
			} catch (Exception e) {
				// manifest is malformed
			}
		}
	}

	/**
	 * Utility method for finding a File handle the app icon.  
	 * Gathers icons in order of highest available resolution image of the app icon first.
	 * @return
	 */
	private void locateAppIconFiles() {
		try {
			// there should only be one application element
			Element application = (Element) androidManifest.getElementsByTagName("application").item(0);
			String attribute = application.getAttribute("android:icon");

			// icon is always a png file that is sort of relative-ish
			String iconName = new File(attribute.replace("@", "")).getName() + ".png";

			// the @ means the directory could hold many different resolution
			// sizes in -hdpi, -ldpi, -mdi, -xhdpi, etc. resolutions
			// so we do a little black magic...there's probably a better way to do this...
			String iconDirectory = new File(attribute.replace("@", "")).getPath();
			iconDirectory = iconDirectory.substring(0, iconDirectory.lastIndexOf(File.separatorChar + iconName.replace(".png", "")));

			// project resources are in the /res directory
			File resourcesDirectory = new File(projectDirectory.getAbsolutePath() + File.separatorChar + "res");

			// ok...so it can be in a few places depending on resolutions...lets just
			// try them in order of best to worst...
			String[] resolutions = { "-xhdpi", "-hdpi", "-ldpi", "-mdpi", "" };
			for (String res : resolutions) {
				File icon = new File(resourcesDirectory.getAbsolutePath() + File.separatorChar + iconDirectory + res + File.separatorChar + iconName);
				if (icon.exists()) {
					appIcons.add(icon);
				}
			}
		} catch (Exception e) {
			// manifest is malformed, or the icon is in a really weird spot...so...no icons for you!
		}
	}

}