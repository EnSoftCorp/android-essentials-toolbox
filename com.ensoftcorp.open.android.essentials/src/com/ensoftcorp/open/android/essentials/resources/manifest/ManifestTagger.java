package com.ensoftcorp.open.android.essentials.resources.manifest;

import static com.ensoftcorp.atlas.core.script.Common.empty;
import static com.ensoftcorp.atlas.core.script.CommonQueries.TraversalDirection.FORWARD;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.query.Attr;
import com.ensoftcorp.atlas.core.query.Attr.Edge;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.java.core.script.Common;
import com.ensoftcorp.atlas.java.core.script.CommonQueries;
import com.ensoftcorp.open.android.essentials.AndroidManifest;
import com.ensoftcorp.open.android.essentials.permissions.Permission;
import com.ensoftcorp.open.android.essentials.permissions.mappings.PermissionMapping;
import com.ensoftcorp.open.android.essentials.resources.AndroidManifestTags;

public class ManifestTagger {

	private static final int HIGH_PRIORITY_THRESHOLD = 100;
	private static ArrayList<Document> androidManifests = null;

	private void init() {
		androidManifests = new ArrayList<Document>();
		Q projects = Common.universe().nodesTaggedWithAny(Attr.Node.PROJECT);

		Iterator<GraphElement> projectsIter = projects.eval().nodes().iterator();
		while (projectsIter.hasNext()) {
			GraphElement project = projectsIter.next();
			String projectName = project.attr().get("name").toString();
			try {
				AndroidManifest manifest = new AndroidManifest(AndroidManifest.getManifestFile(projectName));
				// read the manifest file into memory and then add to the
				if (manifest.getManifestLocation().exists()) {
					try {
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document androidManifest = dBuilder.parse(manifest.getManifestLocation());
						androidManifest.getDocumentElement().normalize();
						androidManifests.add(androidManifest);
					} catch (Exception e) {
						// project manifest file is corrupt, skipping
					}
				} else {
					// project manifest file is missing, skipping
				}
			} catch (Exception e) {
				// invalid or missing manifest (project may not be in
				// workspace), skipping
			}
		}
	}

	public Q tagAppliesTo(String key) {
		if (androidManifests == null) {
			init();
		}

		if (key.equals(AndroidManifestTags.MANIFEST_SERVICE)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag all registered services
				NodeList services = androidManifest.getElementsByTagName("service");
				result = result.union(getServices(services, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_PROVIDER)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag all registered content providers
				NodeList providers = androidManifest.getElementsByTagName("provider");
				result = result.union(getProviders(providers, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_ACTIVITY)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tags all activities found in the manifest
				NodeList activities = androidManifest.getElementsByTagName("activity");
				result = result.union(getActivities(activities, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_RECEIVER)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag all registered receivers
				NodeList receivers = androidManifest.getElementsByTagName("receiver");
				result = result.union(getReceivers(receivers, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_SMS_RECEIVER)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag all registered receivers that also register for
				// SMS_RECEIVED actions
				NodeList receivers = androidManifest.getElementsByTagName("receiver");

				for (int i = 0; i < receivers.getLength(); i++) {
					NodeList intentFilters = ((Element) receivers.item(i)).getElementsByTagName("intent-filter");
					for (int j = 0; j < intentFilters.getLength(); j++) {
						NodeList actions = ((Element) intentFilters.item(j)).getElementsByTagName("action");
						for (int k = 0; k < actions.getLength(); k++) {
							Element action = (Element) actions.item(k);
							if (action.getAttribute("android:name").equalsIgnoreCase("android.provider.telephony.SMS_RECEIVED")) {
								result = result.union(getReceiver((Element) receivers.item(i), getManifestPackageName(androidManifest)));
							}
						}
					}
				}
			}
			return result;
		}

		// TODO: developer declared permissions
		// else if (key.equals(AndroidManifest.PERMISSION)) {
		// // TODO
		// }

		else if (key.equals(AndroidManifestTags.MANIFEST_USES_PERMISSION)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tags all permission-to-api mapped API's found in manifest
				NodeList usesPermissions = androidManifest.getElementsByTagName("uses-permission");
				result = result.union(getUsesPermissions(usesPermissions));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_DEBUG_ENABLED)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag debug methods mode with enabled state
				NodeList applications = androidManifest.getElementsByTagName("application");
				result = result.union(getDebugMode(applications));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_UNPROTECTED_SERVICE)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag unprotected services (exported service without an intent
				// filter or permission)
				NodeList services = androidManifest.getElementsByTagName("service");
				result = result.union(getUnprotectedServices(services, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_UNPROTECTED_PROVIDER)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag unprotected content providers
				NodeList grantUriPermissions = androidManifest.getElementsByTagName("grant-uri-permission");
				result = result.union(getUnprotectedContentProviders(grantUriPermissions, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_HIDDEN_DIALER_CODE)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag hidden dialer codes
				NodeList data = androidManifest.getElementsByTagName("data");
				result = result.union(getHiddenDialerCodes(data, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_BINARY_SMS_RECEIVER)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag binary sms receivers
				NodeList data = androidManifest.getElementsByTagName("data");
				result = result.union(getBinarySMSReceivers(data, getManifestPackageName(androidManifest)));
			}
			return result;
		} else if (key.equals(AndroidManifestTags.MANIFEST_HIGH_PRIORITY)) {
			Q result = Common.empty();
			for (Document androidManifest : androidManifests) {
				// tag high priority actions
				NodeList actions = androidManifest.getElementsByTagName("action");
				result = result.union(getHighPriorityActions(actions, getManifestPackageName(androidManifest)));

				// tag high priority intent filters
				NodeList intentFilters = androidManifest.getElementsByTagName("intent-filter");
				result = result.union(getHighPriorityIntentFilters(intentFilters, getManifestPackageName(androidManifest)));
			}
			return result;
		}
		return empty();
	}

	// gets the manifests declared default package
	private String getManifestPackageName(Document androidManifest) {
		// parse out the application package name
		NodeList manifest = androidManifest.getElementsByTagName("manifest");
		String packageName = "";
		try {
			packageName = ((Element) manifest.item(0)).getAttribute("package");
		} catch (Exception e) {
			if (manifest.getLength() == 0) {
				// project manifest is missing the manifest element, return
				// empty string
			}
			if (manifest.getLength() > 1) {
				// project manifest has multiple manifest elements, return empty
				// string
			}
		}
		return packageName;
	}

	// gets all services found in the manifest
	private Q getServices(NodeList services, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < services.getLength(); i++) {
			Element service = (Element) services.item(i);
			String serviceName = service.getAttribute("android:name");
			try {
				if (serviceName.startsWith(".")) {
					serviceName = packageName + serviceName;
				}

				String servicePackage = "";
				if (serviceName.contains(".")) {
					servicePackage = serviceName.substring(0, serviceName.lastIndexOf("."));
					serviceName = serviceName.substring(serviceName.lastIndexOf(".") + 1, serviceName.length());
				}

				result = result.union(Common.typeSelect(servicePackage, serviceName));
			} catch (Exception e) {
				// invalid service entry, skipping
			}
		}
		return result;
	}

	// gets all providers found in the manifest
	private Q getProviders(NodeList providers, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < providers.getLength(); i++) {
			Element provider = (Element) providers.item(i);
			String providerName = provider.getAttribute("android:name");
			try {
				if (providerName.startsWith(".")) {
					providerName = packageName + providerName;
				}

				String providerPackage = "";
				if (providerName.contains(".")) {
					providerPackage = providerName.substring(0, providerName.lastIndexOf("."));
					providerName = providerName.substring(providerName.lastIndexOf(".") + 1, providerName.length());
				}

				result = result.union(Common.typeSelect(providerPackage, providerName));
			} catch (Exception e) {
				// invalid service entry, skipping
			}
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// Debugging was enabled on the app which makes it easier for reverse
	// engineers to hook a debugger to it.
	// This allows dumping a stack trace and accessing debugging helper classes.
	private Q getDebugMode(NodeList applications) {
		Q result = Common.empty();
		boolean debugEnabled = false;
		try {
			if (((Element) applications.item(0)).getAttribute("android:debuggable").equalsIgnoreCase("true")) {
				debugEnabled = true;
			}
		} catch (Exception e) {
			if (applications.getLength() == 0) {
				// project manifest is missing the application element, skipping
			}
			if (applications.getLength() > 1) {
				// project manifest has multiple application elements, skiping
			}
		}

		Q debugMethods = CommonQueries.declarations(Common.edges(Edge.SUPERTYPE).reverse(Common.typeSelect("android.os", "Debug")), FORWARD);
		debugMethods = debugMethods.union(CommonQueries.declarations(Common.typeSelect("android.util", "Log"), FORWARD).intersection(Common.methodSelect("Log", "d")));

		if (debugEnabled) {
			result = result.union(debugMethods);
		}

		return result;
	}

	// gets all the manifests declared activities
	private Q getActivities(NodeList activities, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < activities.getLength(); i++) {
			Element activity = (Element) activities.item(i);
			String activityName = activity.getAttribute("android:name");

			if (activityName.startsWith(".")) {
				activityName = packageName + activityName;
			}

			String activityPackage = "";
			if (activityName.contains(".")) {
				activityPackage = activityName.substring(0, activityName.lastIndexOf("."));
				activityName = activityName.substring(activityName.lastIndexOf(".") + 1, activityName.length());
			}

			result = result.union(Common.typeSelect(activityPackage, activityName));
		}
		return result;
	}

	// gets all permission-to-api mapped API's found in manifest
	private Q getUsesPermissions(NodeList usesPermissions) {
		Q result = Common.empty();
		for (int i = 0; i < usesPermissions.getLength(); i++) {
			Element usesPermission = (Element) usesPermissions.item(i);
			String usesPermissionValue = usesPermission.getAttribute("android:name");
			Permission permission = Permission.getPermissionByQualifiedName(usesPermissionValue); // TODO:
																									// is
																									// this
																									// qualified
																									// or
																									// unqualified?
																									// ~BH
			result = result.union(PermissionMapping.getMethods(permission, PermissionMapping.HIGHEST_AVAILABLE_MAPPING)); // TODO: replace with manifest version..
		}
		return result;
	}

	// gets all receivers found in the manifest
	private Q getReceivers(NodeList receivers, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < receivers.getLength(); i++) {
			Element receiver = (Element) receivers.item(i);
			String receiverName = receiver.getAttribute("android:name");
			try {
				if (receiverName.startsWith(".")) {
					receiverName = packageName + receiverName;
				}

				String receiverPackage = "";
				if (receiverName.contains(".")) {
					receiverPackage = receiverName.substring(0, receiverName.lastIndexOf("."));
					receiverName = receiverName.substring(receiverName.lastIndexOf(".") + 1, receiverName.length());
				}

				result = result.union(Common.typeSelect(receiverPackage, receiverName));
			} catch (Exception e) {
				// invalid service entry, skipping
			}
		}
		return result;
	}

	private Q getReceiver(Element receiver, String packageName) {
		Q result = Common.empty();
		String receiverName = receiver.getAttribute("android:name");
		try {
			if (receiverName.startsWith(".")) {
				receiverName = packageName + receiverName;
			}

			String receiverPackage = "";
			if (receiverName.contains(".")) {
				receiverPackage = receiverName.substring(0, receiverName.lastIndexOf("."));
				receiverName = receiverName.substring(receiverName.lastIndexOf(".") + 1, receiverName.length());
			}

			result = result.union(Common.typeSelect(receiverPackage, receiverName));
		} catch (Exception e) {
			// invalid service entry, skipping
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// A content provider permission was set to allows access from any other app
	// on the device.
	// Content providers may contain sensitive information about an app and
	// therefore should not
	// be shared.
	private Q getUnprotectedContentProviders(NodeList grantUriPermissions, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < grantUriPermissions.getLength(); i++) {
			Element grantUriPermission = (Element) grantUriPermissions.item(i);
			Element provider = (Element) grantUriPermission.getParentNode();
			String providerName = provider.getAttribute("android:name");
			try {
				if (providerName.startsWith(".")) {
					providerName = packageName + providerName;
				}

				String providerPackage = "";
				if (providerName.contains(".")) {
					providerPackage = providerName.substring(0, providerName.lastIndexOf("."));
					providerName = providerName.substring(providerName.lastIndexOf(".") + 1, providerName.length());
				}

				if (grantUriPermission.getAttribute("android:pathPrefix").equals("/")) {
					result = result.union(Common.typeSelect(providerPackage, providerName));
				} else if (grantUriPermission.getAttribute("android:path").equals("/")) {
					result = result.union(Common.typeSelect(providerPackage, providerName));
				} else if (grantUriPermission.getAttribute("android:pathPattern").equals("*")) {
					result = result.union(Common.typeSelect(providerPackage, providerName));
				}
			} catch (Exception e) {
				// invalid service entry, skipping
			}
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// A service was found to be shared with other apps on the device without an
	// intent
	// filter or a permission requirement therefore leaving it accessible to any
	// other
	// application on the device.
	private Q getUnprotectedServices(NodeList services, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < services.getLength(); i++) {
			Element service = (Element) services.item(i);
			String isExported = service.getAttribute("android:exported");
			if (isExported.equalsIgnoreCase("true")) {
				String serviceName = service.getAttribute("android:name");
				try {
					if (serviceName.startsWith(".")) {
						serviceName = packageName + serviceName;
					}

					String servicePackage = "";
					if (serviceName.contains(".")) {
						servicePackage = serviceName.substring(0, serviceName.lastIndexOf("."));
						serviceName = serviceName.substring(serviceName.lastIndexOf(".") + 1, serviceName.length());
					}

					if (service.getChildNodes().getLength() == 0) {
						result = result.union(Common.typeSelect(servicePackage, serviceName));
					} else {
						for (int j = 0; j < service.getChildNodes().getLength(); j++) {
							Node child = (Node) service.getChildNodes().item(j);
							String childName = child.getNodeName();
							if (!childName.equalsIgnoreCase("intent-filter") && service.getAttribute("android:permission").equals("")) {
								result = result.union(Common.typeSelect(servicePackage, serviceName));
							}
						}
					}
				} catch (Exception e) {
					// invalid service entry, skipping
				}
			}
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// A binary SMS recevier is configured to listen on a port. Binary SMS
	// messages sent to a
	// device are processed by the application in whichever way the developer
	// choses. The data
	// in this SMS should be properly validated by the application. Furthermore,
	// the application
	// should assume that the SMS being received is from an untrusted source.
	private Q getBinarySMSReceivers(NodeList data, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < data.getLength(); i++) {
			Element datum = (Element) data.item(i);
			if (!datum.getAttribute("android:port").equals("")) {
				// intent filter container could be an activity, activity-alias,
				// service, or a receiver
				Element intentFilterContainer = (Element) datum.getParentNode().getParentNode();
				String containerType = intentFilterContainer.getTagName();
				if (containerType.equalsIgnoreCase("receiver")) {
					String containerName = intentFilterContainer.getAttribute("android:name");
					try {
						if (containerName.startsWith(".")) {
							containerName = packageName + containerName;
						}

						String containerPackage = "";
						if (containerName.contains(".")) {
							containerPackage = containerName.substring(0, containerName.lastIndexOf("."));
							containerName = containerName.substring(containerName.lastIndexOf(".") + 1, containerName.length());
						}

						// String port = datum.getAttribute("android:port");
						// System.out.println("Binary SMS Receiver: " + port +
						// " - Resolving to " + containerName + " (receiver)");
						result = result.union(Common.typeSelect(containerPackage, containerName));
					} catch (Exception e) {
						// invalid service entry, skipping
					}
				}
			}
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// A secret code was found in the manifest. These codes, when entered into
	// the dialer
	// grant access to hidden content that may contain sensitive information.
	private Q getHiddenDialerCodes(NodeList data, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < data.getLength(); i++) {
			Element datum = (Element) data.item(i);
			if (datum.getAttribute("android:scheme").equalsIgnoreCase("android_secret_code")) {
				// intent filter container could be an activity, activity-alias,
				// service, or a receiver
				Element intentFilterContainer = (Element) datum.getParentNode().getParentNode();
				String containerName = intentFilterContainer.getAttribute("android:name");
				try {
					if (containerName.startsWith(".")) {
						containerName = packageName + containerName;
					}

					String containerPackage = "";
					if (containerName.contains(".")) {
						containerPackage = containerName.substring(0, containerName.lastIndexOf("."));
						containerName = containerName.substring(containerName.lastIndexOf(".") + 1, containerName.length());
					}

					// String code = datum.getAttribute("android:host");
					// String containerType =
					// intentFilterContainer.getTagName();
					// System.out.println("Hidden Dialer Code: " + code +
					// " - Resolving to " + containerName + " (" + containerType
					// + ")");
					result = result.union(Common.typeSelect(containerPackage, containerName));
				} catch (Exception e) {
					// invalid service entry, skipping
				}
			}
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// By setting an intent priority higher than another intent, the app
	// effectively
	// overrides other requests. This is commonly associated with malware.
	private Q getHighPriorityIntentFilters(NodeList intentFilters, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < intentFilters.getLength(); i++) {
			Element intentFilter = (Element) intentFilters.item(i);
			try {
				int priority = Integer.parseInt(intentFilter.getAttribute("android:priority"));
				if (priority > HIGH_PRIORITY_THRESHOLD) {
					// intent filter container could be an activity,
					// activity-alias, service, or a receiver
					Element intentFilterContainer = (Element) intentFilter.getParentNode();
					String containerName = intentFilterContainer.getAttribute("android:name");
					try {
						if (containerName.startsWith(".")) {
							containerName = packageName + containerName;
						}

						String containerPackage = "";
						if (containerName.contains(".")) {
							containerPackage = containerName.substring(0, containerName.lastIndexOf("."));
							containerName = containerName.substring(containerName.lastIndexOf(".") + 1, containerName.length());
						}

						// String containerType =
						// intentFilterContainer.getTagName();
						// System.out.println("High Priority Intent Filter: " +
						// priority + " - Resolving to " + containerName + " ("
						// + containerType + ")");
						result = result.union(Common.typeSelect(containerPackage, containerName));
					} catch (Exception e) {
						// invalid service entry, skipping
					}
				}
			} catch (Exception e) {
				// priority value not an integer or no priority was set
			}
		}
		return result;
	}

	// Inspired by Manitree project
	// Manitree Comment:
	// By setting an action priority higher than another action, the app
	// effectively overrides other requests.
	// This is commonly associated with malware.
	private Q getHighPriorityActions(NodeList actions, String packageName) {
		Q result = Common.empty();
		for (int i = 0; i < actions.getLength(); i++) {
			Element action = (Element) actions.item(i);
			try {
				int priority = Integer.parseInt(action.getAttribute("android:priority"));
				if (priority > HIGH_PRIORITY_THRESHOLD) {
					// intent filter container could be an activity,
					// activity-alias, service, or a receiver
					Element intentFilter = (Element) action.getParentNode();
					Element intentFilterContainer = (Element) intentFilter.getParentNode();
					String containerName = intentFilterContainer.getAttribute("android:name");
					try {
						if (containerName.startsWith(".")) {
							containerName = packageName + containerName;
						}

						String containerPackage = "";
						if (containerName.contains(".")) {
							containerPackage = containerName.substring(0, containerName.lastIndexOf("."));
							containerName = containerName.substring(containerName.lastIndexOf(".") + 1, containerName.length());
						}

						// String containerType =
						// intentFilterContainer.getTagName();
						// System.out.println("High Priority Action: " +
						// priority + " - Resolving to " + containerName + " ("
						// + containerType + ")");
						result = result.union(Common.typeSelect(containerPackage, containerName));
					} catch (Exception e) {
						// invalid service entry, skipping
					}
				}
			} catch (Exception e) {
				// priority value not an integer or no priority was set
			}
		}
		return result;
	}

}