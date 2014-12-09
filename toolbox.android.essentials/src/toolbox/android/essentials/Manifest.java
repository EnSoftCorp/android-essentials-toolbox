package toolbox.android.essentials;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is a container object for encapsulating data and logic for parsing an Android Manifest file
 */
public class Manifest {

	public final static String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";

	public static File getManifestFile(String project) throws Exception {
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(project).exists()) {
			if (ResourcesPlugin.getWorkspace().getRoot().getProject(project).getFile(ANDROID_MANIFEST_FILENAME).exists()) {
				return ResourcesPlugin.getWorkspace().getRoot().getProject(project).getFile(ANDROID_MANIFEST_FILENAME).getLocation().toFile();
			}
		}
		throw new IllegalArgumentException("Manifest file not found");
	}

	public static Collection<File> getDependentManifestFiles(String project) throws Exception {
		Collection<File> files = new LinkedList<File>();
		for (IProject dependency : ResourcesPlugin.getWorkspace().getRoot().getProject(project).getReferencedProjects()) {
			if (dependency.getFile(ANDROID_MANIFEST_FILENAME).exists()) {
				files.add(dependency.getFile(ANDROID_MANIFEST_FILENAME).getLocation().toFile());
			}
		}
		return files;
	}

	public static Collection<Manifest> parseManifestFiles(Collection<File> files) throws Exception {
		Collection<Manifest> manifests = new LinkedList<Manifest>();
		for (File file : files) {
			manifests.add(new Manifest(file));
		}
		return manifests;
	}

	private File projectDirectory;
	private File manifestLocation;
	private Document androidManifest;
	private HashSet<String> androidUsesPermissions = new HashSet<String>();
	private File appIcon;

	public Manifest(File androidManifestFile) throws Exception {
		projectDirectory = androidManifestFile.getParentFile();
		manifestLocation = androidManifestFile;

		// read the manifest file into memory
		if (androidManifestFile.exists()) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			androidManifest = dBuilder.parse(androidManifestFile);
			androidManifest.getDocumentElement().normalize();
		} else {
			throw new IllegalArgumentException("Project manifest file is missing.");
		}

		// parse out the uses permissions
		parseUsesPermissions();

		// parse out the app icon
		appIcon = getAppIconFile();
	}

	public Collection<String> getUsesPermissions() {
		return androidUsesPermissions;
	}

	public File getAppIcon() {
		return appIcon;
	}

	public File getManifestLocation() {
		return manifestLocation;
	}

	private void parseUsesPermissions() {
		NodeList usesPermissions = androidManifest.getElementsByTagName("uses-permission");
		if (usesPermissions.getLength() != 0) {
			try {
				for (int i = 0; i < usesPermissions.getLength(); i++) {
					Node node = usesPermissions.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						androidUsesPermissions.add(element.getAttribute("android:name"));
					}
				}
			} catch (Exception e) {
				// manifest is malformed
			}
		}
	}

	/**
	 * Utility method for finding a File handle the app icon.  
	 * Tries to return the highest available resoultion image of the app icon.
	 * @return
	 */
	private File getAppIconFile() {
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
					return icon;
				}
			}

			// hmm....no idea where that file is...
			return null;

		} catch (Exception e) {
			// manifest is malformed, or the icon is in a really weird spot...so...no picture for you!
			return null;
		}
	}

}