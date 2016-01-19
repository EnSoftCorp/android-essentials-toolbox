package com.ensoftcorp.open.android.essentials.resources;

/**
 * Tags to apply context given by the Android Manifest to source code
 */
public enum AndroidManifestTags {
	MANIFEST_SERVICE("MANIFEST_SERVICE"),
	MANIFEST_PROVIDER("MANIFEST_PROVIDER"),
	MANIFEST_ACTIVITY("MANIFEST_ACTIVITY"),
	MANIFEST_RECEIVER("MANIFEST_RECEIVER"),
	MANIFEST_SMS_RECEIVER("MANIFEST_SMS_RECEIVER"),
	// MANIFEST_PERMISSION("MANIFEST_PERMISSION"),
	MANIFEST_USES_PERMISSION("MANIFEST_USES_PERMISSION"),
	MANIFEST_DEBUG_ENABLED("MANIFEST_DEBUG_ENABLED"),
	MANIFEST_UNPROTECTED_SERVICE("MANIFEST_UNPROTECTED_SERVICE"),
	MANIFEST_UNPROTECTED_PROVIDER("MANIFEST_UNPROTECTED_PROVIDER"),
	MANIFEST_HIDDEN_DIALER_CODE("MANIFEST_HIDDEN_DIALER_CODE"),
	MANIFEST_BINARY_SMS_RECEIVER("MANIFEST_BINARY_SMS_RECEIVER"),
	MANIFEST_HIGH_PRIORITY("MANIFEST_HIGH_PRIORITY");

	private String tag;

	private AndroidManifestTags(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return tag;
	}

	public String description() {
		switch(this){
			case MANIFEST_SERVICE:
				return "Gets all services declared in the manifest";
			case MANIFEST_PROVIDER:
				return "Gets all the content providers declared in the manifest";
			case MANIFEST_ACTIVITY:
				return "Gets all activities declared in the manifest";
			case MANIFEST_RECEIVER:
				return "Gets all receivers declared in the manifest";
//			case MANIFEST_PERMISSION:
//				return "Get all developer declared permissions in the manifest";
			case MANIFEST_SMS_RECEIVER:
				return "Gets all SMS receivers declared in the manifest";
			case MANIFEST_USES_PERMISSION:
				return "Gets all the permission mapped-api declared via a permission in the  manifest";
			case MANIFEST_DEBUG_ENABLED:
				return "Debugging was enabled on the app which makes it easier for reverse engineers to hook a"
						+ " debugger to it. This allows dumping a stack trace and accessing debugging helper classes.";
			case MANIFEST_UNPROTECTED_SERVICE:
				return "A service was found to be shared with other apps on the device without an intent filter or a"
						+ " permission requirement therefore leaving it accessible to any other application on the device.";
			case MANIFEST_UNPROTECTED_PROVIDER:
				return "A content provider permission was set to allows access from any other app on the device."
						+ " Content providers may contain sensitive information about an app and therefore should not be shared.";
			case MANIFEST_HIDDEN_DIALER_CODE:
				return "A secret code was found in the manifest. These codes, when entered into the dialer"
						+ " grant access to hidden content that may contain sensitive information.";
			case MANIFEST_BINARY_SMS_RECEIVER:
				return "A binary SMS receiver is configured to listen on a port. Binary SMS messages sent"
						+ " to a device are processed by the application in whichever way the developer"
						+ " chooses. The data in this SMS should be properly validated by the application."
						+ " Furthermore, the application should assume that the SMS being received is from an untrusted source.";
			case MANIFEST_HIGH_PRIORITY:
				return "By setting an intent priority higher than another intent, the app effectively"
						+ " overrides other requests. This is commonly associated with malware.";
			default:
				return "";
		}
	}
}