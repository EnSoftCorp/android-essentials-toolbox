package com.ensoftcorp.open.android.essentials.permissions;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * An convenience object that encodes Android Permission property values
 * @author Ben Holland, Vani Bojja
 */
public class Permission {
	
	public static int REFERENCE_API_VERSION = 21;
	public static String[] REFERENCE_SOURCES = { "https://developer.android.com/reference/android/Manifest.permission.html" };
	public static Date REFERENCE_DATE = new Date(1421257800); // 1-14-2015
	
	private String qualifiedName;
	private int addedInAPILevel;
	private int deprecatedInAPILevel;
	private String description;
	private String reference;

	private Permission(String qualifiedName, int addedInLevel, int deprecatedInAPILevel, String description, String reference){
		this.qualifiedName = qualifiedName;
		this.addedInAPILevel = addedInLevel;
		this.deprecatedInAPILevel = deprecatedInAPILevel;
		this.description = description;
		this.reference = reference;
	}

	/**
	 * Returns the API version the permission was added in
	 * @return
	 */
	public int addedInAPILevel() {
		return addedInAPILevel;
	}

	/**
	 * Returns true if the permission has been deprecated in the latest API version
	 * @return
	 */
	public boolean isDeprecated() {
		return deprecatedInAPILevel != -1;
	}
	
	/**
	 * Returns the API level this permission was deprecated in or -1 if not deprecated
	 * @return
	 */
	public int getDeprecatedInAPILevel(){
		return deprecatedInAPILevel;
	}
	
	/**
	 * Returns the reference information for the source of this permission or an empty string if there is no available reference
	 * @return
	 */
	public String getReference(){
		return reference;
	}

	/**
	 * Returns the qualified permission name
	 * @return
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * Returns an unqualified permission name
	 * @return
	 */
	public String getSimpleName() {
		int pos = qualifiedName.lastIndexOf('.');
		return qualifiedName.substring(pos + 1);
	}

	/**
	 * Returns a description of the permission
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return getSimpleName();
	}
	
	/**
	 * Returns the permission object matching the qualified permission name or null
	 * if the permission is not found in the known permissions list
	 * @param qualifiedName
	 * @return
	 */
	public static Permission getPermissionByQualifiedName(String qualifiedName){
		for(Permission permission : allPermissions){
			if(permission.getQualifiedName().equals(qualifiedName)){
				return permission;
			}
		}
		return null; // permission not found
	}
	
	/**
	 * Returns the permission object matching the simple permission name or null
	 * if the permission is not found in the known permissions list
	 * @param simpleName
	 * @return
	 */
	public static Permission getPermissionBySimpleName(String simpleName){
		for(Permission permission : allPermissions){
			if(permission.getSimpleName().equals(simpleName)){
				return permission;
			}
		}
		return null; // permission not found
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Permission))
			return false;
		Permission other = (Permission) obj;
		if (this.qualifiedName == null)
			return other.qualifiedName == null;
		return this.qualifiedName.equals(other.qualifiedName);
	}

	@Override
	public int hashCode() {
		if (this.qualifiedName == null)
			return 0;
		return this.qualifiedName.hashCode();
	}

	// documented Android permission objects generated from https://developer.android.com/reference/android/Manifest.permission.html on Wed Jan 14 14:57:37 CST 2015
	public static final Permission ACCESS_CHECKIN_PROPERTIES = new Permission("android.permission.ACCESS_CHECKIN_PROPERTIES", 1, -1, "Allows read/write access to the \"properties\" table in the checkin database, to change values that get uploaded.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_CHECKIN_PROPERTIES");
	public static final Permission ACCESS_COARSE_LOCATION = new Permission("android.permission.ACCESS_COARSE_LOCATION", 1, -1, "Allows an app to access approximate location derived from network location sources such as cell towers and Wi-Fi.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_COARSE_LOCATION");
	public static final Permission ACCESS_FINE_LOCATION = new Permission("android.permission.ACCESS_FINE_LOCATION", 1, -1, "Allows an app to access precise location from location sources such as GPS, cell towers, and Wi-Fi.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_FINE_LOCATION");
	public static final Permission ACCESS_LOCATION_EXTRA_COMMANDS = new Permission("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", 1, -1, "Allows an application to access extra location provider commands.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_LOCATION_EXTRA_COMMANDS");
	public static final Permission ACCESS_MOCK_LOCATION = new Permission("android.permission.ACCESS_MOCK_LOCATION", 1, -1, "Allows an application to create mock location providers for testing.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_MOCK_LOCATION");
	public static final Permission ACCESS_NETWORK_STATE = new Permission("android.permission.ACCESS_NETWORK_STATE", 1, -1, "Allows applications to access information about networks.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_NETWORK_STATE");
	public static final Permission ACCESS_SURFACE_FLINGER = new Permission("android.permission.ACCESS_SURFACE_FLINGER", 1, -1, "Allows an application to use SurfaceFlinger's low level features.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_SURFACE_FLINGER");
	public static final Permission ACCESS_WIFI_STATE = new Permission("android.permission.ACCESS_WIFI_STATE", 1, -1, "Allows applications to access information about Wi-Fi networks.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_WIFI_STATE");
	public static final Permission ACCOUNT_MANAGER = new Permission("android.permission.ACCOUNT_MANAGER", 5, -1, "Allows applications to call into AccountAuthenticators.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#ACCOUNT_MANAGER");
	public static final Permission ADD_VOICEMAIL = new Permission("com.android.voicemail.permission.ADD_VOICEMAIL", 14, -1, "Allows an application to add voicemails into the system.", "https://developer.android.com/reference/android/Manifest.permission.html#ADD_VOICEMAIL");
	public static final Permission AUTHENTICATE_ACCOUNTS = new Permission("android.permission.AUTHENTICATE_ACCOUNTS", 5, -1, "Allows an application to act as an AccountAuthenticator for the AccountManager.", "https://developer.android.com/reference/android/Manifest.permission.html#AUTHENTICATE_ACCOUNTS");
	public static final Permission BATTERY_STATS = new Permission("android.permission.BATTERY_STATS", 1, -1, "Allows an application to collect battery statistics.", "https://developer.android.com/reference/android/Manifest.permission.html#BATTERY_STATS");
	public static final Permission BIND_ACCESSIBILITY_SERVICE = new Permission("android.permission.BIND_ACCESSIBILITY_SERVICE", 16, -1, "Must be required by an AccessibilityService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_ACCESSIBILITY_SERVICE");
	public static final Permission BIND_APPWIDGET = new Permission("android.permission.BIND_APPWIDGET", 3, -1, "Allows an application to tell the AppWidget service which application can access AppWidget's data. The normal user flow is that a user picks an AppWidget to go into a particular host, thereby giving that host application access to the private data from the AppWidget app. An application that has this permission should honor that contract.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_APPWIDGET");
	public static final Permission BIND_DEVICE_ADMIN = new Permission("android.permission.BIND_DEVICE_ADMIN", 8, -1, "Must be required by device administration receiver, to ensure that only the system can interact with it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_DEVICE_ADMIN");
	public static final Permission BIND_DREAM_SERVICE = new Permission("android.permission.BIND_DREAM_SERVICE", 21, -1, "Must be required by an DreamService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_DREAM_SERVICE");
	public static final Permission BIND_INPUT_METHOD = new Permission("android.permission.BIND_INPUT_METHOD", 3, -1, "Must be required by an InputMethodService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_INPUT_METHOD");
	public static final Permission BIND_NFC_SERVICE = new Permission("android.permission.BIND_NFC_SERVICE", 19, -1, "Must be required by a HostApduService or OffHostApduService to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_NFC_SERVICE");
	public static final Permission BIND_NOTIFICATION_LISTENER_SERVICE = new Permission("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", 18, -1, "Must be required by an NotificationListenerService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_NOTIFICATION_LISTENER_SERVICE");
	public static final Permission BIND_PRINT_SERVICE = new Permission("android.permission.BIND_PRINT_SERVICE", 19, -1, "Must be required by a PrintService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_PRINT_SERVICE");
	public static final Permission BIND_REMOTEVIEWS = new Permission("android.permission.BIND_REMOTEVIEWS", 11, -1, "Must be required by a RemoteViewsService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_REMOTEVIEWS");
	public static final Permission BIND_TEXT_SERVICE = new Permission("android.permission.BIND_TEXT_SERVICE", 14, -1, "Must be required by a TextService (e.g. SpellCheckerService) to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_TEXT_SERVICE");
	public static final Permission BIND_TV_INPUT = new Permission("android.permission.BIND_TV_INPUT", 21, -1, "Must be required by a TvInputService to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_TV_INPUT");
	public static final Permission BIND_VOICE_INTERACTION = new Permission("android.permission.BIND_VOICE_INTERACTION", 21, -1, "Must be required by a VoiceInteractionService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_VOICE_INTERACTION");
	public static final Permission BIND_VPN_SERVICE = new Permission("android.permission.BIND_VPN_SERVICE", 14, -1, "Must be required by a VpnService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_VPN_SERVICE");
	public static final Permission BIND_WALLPAPER = new Permission("android.permission.BIND_WALLPAPER", 8, -1, "Must be required by a WallpaperService, to ensure that only the system can bind to it.", "https://developer.android.com/reference/android/Manifest.permission.html#BIND_WALLPAPER");
	public static final Permission BLUETOOTH = new Permission("android.permission.BLUETOOTH", 1, -1, "Allows applications to connect to paired bluetooth devices.", "https://developer.android.com/reference/android/Manifest.permission.html#BLUETOOTH");
	public static final Permission BLUETOOTH_ADMIN = new Permission("android.permission.BLUETOOTH_ADMIN", 1, -1, "Allows applications to discover and pair bluetooth devices.", "https://developer.android.com/reference/android/Manifest.permission.html#BLUETOOTH_ADMIN");
	public static final Permission BLUETOOTH_PRIVILEGED = new Permission("android.permission.BLUETOOTH_PRIVILEGED", 19, -1, "Allows applications to pair bluetooth devices without user interaction, and to allow or disallow phonebook access or message access. This is not available to third party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#BLUETOOTH_PRIVILEGED");
	public static final Permission BODY_SENSORS = new Permission("android.permission.BODY_SENSORS", 20, -1, "Allows an application to access data from sensors that the user uses to measure what is happening inside his/her body, such as heart rate.", "https://developer.android.com/reference/android/Manifest.permission.html#BODY_SENSORS");
	public static final Permission BRICK = new Permission("android.permission.BRICK", 1, -1, "Required to be able to disable the device (very dangerous!).\nNot for use by third-party applications..", "https://developer.android.com/reference/android/Manifest.permission.html#BRICK");
	public static final Permission BROADCAST_PACKAGE_REMOVED = new Permission("android.permission.BROADCAST_PACKAGE_REMOVED", 1, -1, "Allows an application to broadcast a notification that an application package has been removed.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#BROADCAST_PACKAGE_REMOVED");
	public static final Permission BROADCAST_SMS = new Permission("android.permission.BROADCAST_SMS", 2, -1, "Allows an application to broadcast an SMS receipt notification.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#BROADCAST_SMS");
	public static final Permission BROADCAST_STICKY = new Permission("android.permission.BROADCAST_STICKY", 1, -1, "Allows an application to broadcast sticky intents. These are broadcasts whose data is held by the system after being finished, so that clients can quickly retrieve that data without having to wait for the next broadcast.", "https://developer.android.com/reference/android/Manifest.permission.html#BROADCAST_STICKY");
	public static final Permission BROADCAST_WAP_PUSH = new Permission("android.permission.BROADCAST_WAP_PUSH", 2, -1, "Allows an application to broadcast a WAP PUSH receipt notification.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#BROADCAST_WAP_PUSH");
	public static final Permission CALL_PHONE = new Permission("android.permission.CALL_PHONE", 1, -1, "Allows an application to initiate a phone call without going through the Dialer user interface for the user to confirm the call being placed.", "https://developer.android.com/reference/android/Manifest.permission.html#CALL_PHONE");
	public static final Permission CALL_PRIVILEGED = new Permission("android.permission.CALL_PRIVILEGED", 1, -1, "Allows an application to call any phone number, including emergency numbers, without going through the Dialer user interface for the user to confirm the call being placed.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CALL_PRIVILEGED");
	public static final Permission CAMERA = new Permission("android.permission.CAMERA", 1, -1, "Required to be able to access the camera device.\nThis will automatically enforce the <uses-feature> manifest element for all camera features. If you do not require all camera features or can properly operate if a camera is not available, then you must modify your manifest as appropriate in order to install on devices that don't support all camera features.", "https://developer.android.com/reference/android/Manifest.permission.html#CAMERA");
	public static final Permission CAPTURE_AUDIO_OUTPUT = new Permission("android.permission.CAPTURE_AUDIO_OUTPUT", 19, -1, "Allows an application to capture audio output.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CAPTURE_AUDIO_OUTPUT");
	public static final Permission CAPTURE_SECURE_VIDEO_OUTPUT = new Permission("android.permission.CAPTURE_SECURE_VIDEO_OUTPUT", 19, -1, "Allows an application to capture secure video output.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CAPTURE_SECURE_VIDEO_OUTPUT");
	public static final Permission CAPTURE_VIDEO_OUTPUT = new Permission("android.permission.CAPTURE_VIDEO_OUTPUT", 19, -1, "Allows an application to capture video output.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CAPTURE_VIDEO_OUTPUT");
	public static final Permission CHANGE_COMPONENT_ENABLED_STATE = new Permission("android.permission.CHANGE_COMPONENT_ENABLED_STATE", 1, -1, "Allows an application to change whether an application component (other than its own) is enabled or not.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CHANGE_COMPONENT_ENABLED_STATE");
	public static final Permission CHANGE_CONFIGURATION = new Permission("android.permission.CHANGE_CONFIGURATION", 1, -1, "Allows an application to modify the current configuration, such as locale.", "https://developer.android.com/reference/android/Manifest.permission.html#CHANGE_CONFIGURATION");
	public static final Permission CHANGE_NETWORK_STATE = new Permission("android.permission.CHANGE_NETWORK_STATE", 1, -1, "Allows applications to change network connectivity state.", "https://developer.android.com/reference/android/Manifest.permission.html#CHANGE_NETWORK_STATE");
	public static final Permission CHANGE_WIFI_MULTICAST_STATE = new Permission("android.permission.CHANGE_WIFI_MULTICAST_STATE", 4, -1, "Allows applications to enter Wi-Fi Multicast mode.", "https://developer.android.com/reference/android/Manifest.permission.html#CHANGE_WIFI_MULTICAST_STATE");
	public static final Permission CHANGE_WIFI_STATE = new Permission("android.permission.CHANGE_WIFI_STATE", 1, -1, "Allows applications to change Wi-Fi connectivity state.", "https://developer.android.com/reference/android/Manifest.permission.html#CHANGE_WIFI_STATE");
	public static final Permission CLEAR_APP_CACHE = new Permission("android.permission.CLEAR_APP_CACHE", 1, -1, "Allows an application to clear the caches of all installed applications on the device.", "https://developer.android.com/reference/android/Manifest.permission.html#CLEAR_APP_CACHE");
	public static final Permission CLEAR_APP_USER_DATA = new Permission("android.permission.CLEAR_APP_USER_DATA", 1, -1, "Allows an application to clear user data.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CLEAR_APP_USER_DATA");
	public static final Permission CONTROL_LOCATION_UPDATES = new Permission("android.permission.CONTROL_LOCATION_UPDATES", 1, -1, "Allows enabling/disabling location update notifications from the radio.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#CONTROL_LOCATION_UPDATES");
	public static final Permission DELETE_CACHE_FILES = new Permission("android.permission.DELETE_CACHE_FILES", 1, -1, "Allows an application to delete cache files.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#DELETE_CACHE_FILES");
	public static final Permission DELETE_PACKAGES = new Permission("android.permission.DELETE_PACKAGES", 1, -1, "Allows an application to delete packages.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#DELETE_PACKAGES");
	public static final Permission DEVICE_POWER = new Permission("android.permission.DEVICE_POWER", 1, -1, "Allows low-level access to power management.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#DEVICE_POWER");
	public static final Permission DIAGNOSTIC = new Permission("android.permission.DIAGNOSTIC", 1, -1, "Allows applications to RW to diagnostic resources.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#DIAGNOSTIC");
	public static final Permission DISABLE_KEYGUARD = new Permission("android.permission.DISABLE_KEYGUARD", 1, -1, "Allows applications to disable the keyguard.", "https://developer.android.com/reference/android/Manifest.permission.html#DISABLE_KEYGUARD");
	public static final Permission DUMP = new Permission("android.permission.DUMP", 1, -1, "Allows an application to retrieve state dump information from system services.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#DUMP");
	public static final Permission EXPAND_STATUS_BAR = new Permission("android.permission.EXPAND_STATUS_BAR", 1, -1, "Allows an application to expand or collapse the status bar.", "https://developer.android.com/reference/android/Manifest.permission.html#EXPAND_STATUS_BAR");
	public static final Permission FACTORY_TEST = new Permission("android.permission.FACTORY_TEST", 1, -1, "Run as a manufacturer test application, running as the root user. Only available when the device is running in manufacturer test mode.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#FACTORY_TEST");
	public static final Permission FLASHLIGHT = new Permission("android.permission.FLASHLIGHT", 1, -1, "Allows access to the flashlight.", "https://developer.android.com/reference/android/Manifest.permission.html#FLASHLIGHT");
	public static final Permission FORCE_BACK = new Permission("android.permission.FORCE_BACK", 1, -1, "Allows an application to force a BACK operation on whatever is the top activity.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#FORCE_BACK");
	public static final Permission GET_ACCOUNTS = new Permission("android.permission.GET_ACCOUNTS", 1, -1, "Allows access to the list of accounts in the Accounts Service.", "https://developer.android.com/reference/android/Manifest.permission.html#GET_ACCOUNTS");
	public static final Permission GET_PACKAGE_SIZE = new Permission("android.permission.GET_PACKAGE_SIZE", 1, -1, "Allows an application to find out the space used by any package.", "https://developer.android.com/reference/android/Manifest.permission.html#GET_PACKAGE_SIZE");
	public static final Permission GET_TASKS = new Permission("android.permission.GET_TASKS", 1, 21, "This constant was deprecated in API level 21. No longer enforced.", "https://developer.android.com/reference/android/Manifest.permission.html#GET_TASKS");
	public static final Permission GET_TOP_ACTIVITY_INFO = new Permission("android.permission.GET_TOP_ACTIVITY_INFO", 18, -1, "Allows an application to retrieve private information about the current top activity, such as any assist context it can provide.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#GET_TOP_ACTIVITY_INFO");
	public static final Permission GLOBAL_SEARCH = new Permission("android.permission.GLOBAL_SEARCH", 4, -1, "This permission can be used on content providers to allow the global search system to access their data. Typically it used when the provider has some permissions protecting it (which global search would not be expected to hold), and added as a read-only permission to the path in the provider where global search queries are performed. This permission can not be held by regular applications; it is used by applications to protect themselves from everyone else besides global search.", "https://developer.android.com/reference/android/Manifest.permission.html#GLOBAL_SEARCH");
	public static final Permission HARDWARE_TEST = new Permission("android.permission.HARDWARE_TEST", 1, -1, "Allows access to hardware peripherals. Intended only for hardware testing.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#HARDWARE_TEST");
	public static final Permission INJECT_EVENTS = new Permission("android.permission.INJECT_EVENTS", 1, -1, "Allows an application to inject user events (keys, touch, trackball) into the event stream and deliver them to ANY window. Without this permission, you can only deliver events to windows in your own process.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#INJECT_EVENTS");
	public static final Permission INSTALL_LOCATION_PROVIDER = new Permission("android.permission.INSTALL_LOCATION_PROVIDER", 4, -1, "Allows an application to install a location provider into the Location Manager.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#INSTALL_LOCATION_PROVIDER");
	public static final Permission INSTALL_PACKAGES = new Permission("android.permission.INSTALL_PACKAGES", 1, -1, "Allows an application to install packages.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#INSTALL_PACKAGES");
	public static final Permission INSTALL_SHORTCUT = new Permission("com.android.launcher.permission.INSTALL_SHORTCUT", 19, -1, "Allows an application to install a shortcut in Launcher.", "https://developer.android.com/reference/android/Manifest.permission.html#INSTALL_SHORTCUT");
	public static final Permission INTERNAL_SYSTEM_WINDOW = new Permission("android.permission.INTERNAL_SYSTEM_WINDOW", 1, -1, "Allows an application to open windows that are for use by parts of the system user interface.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#INTERNAL_SYSTEM_WINDOW");
	public static final Permission INTERNET = new Permission("android.permission.INTERNET", 1, -1, "Allows applications to open network sockets.", "https://developer.android.com/reference/android/Manifest.permission.html#INTERNET");
	public static final Permission KILL_BACKGROUND_PROCESSES = new Permission("android.permission.KILL_BACKGROUND_PROCESSES", 8, -1, "Allows an application to call killBackgroundProcesses(String).", "https://developer.android.com/reference/android/Manifest.permission.html#KILL_BACKGROUND_PROCESSES");
	public static final Permission LOCATION_HARDWARE = new Permission("android.permission.LOCATION_HARDWARE", 18, -1, "Allows an application to use location features in hardware, such as the geofencing api.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#LOCATION_HARDWARE");
	public static final Permission MANAGE_ACCOUNTS = new Permission("android.permission.MANAGE_ACCOUNTS", 5, -1, "Allows an application to manage the list of accounts in the AccountManager.", "https://developer.android.com/reference/android/Manifest.permission.html#MANAGE_ACCOUNTS");
	public static final Permission MANAGE_APP_TOKENS = new Permission("android.permission.MANAGE_APP_TOKENS", 1, -1, "Allows an application to manage (create, destroy, Z-order) application tokens in the window manager.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#MANAGE_APP_TOKENS");
	public static final Permission MANAGE_DOCUMENTS = new Permission("android.permission.MANAGE_DOCUMENTS", 19, -1, "Allows an application to manage access to documents, usually as part of a document picker.", "https://developer.android.com/reference/android/Manifest.permission.html#MANAGE_DOCUMENTS");
	public static final Permission MASTER_CLEAR = new Permission("android.permission.MASTER_CLEAR", 1, -1, "Not for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#MASTER_CLEAR");
	public static final Permission MEDIA_CONTENT_CONTROL = new Permission("android.permission.MEDIA_CONTENT_CONTROL", 19, -1, "Allows an application to know what content is playing and control its playback.\nNot for use by third-party applications due to privacy of media consumption.", "https://developer.android.com/reference/android/Manifest.permission.html#MEDIA_CONTENT_CONTROL");
	public static final Permission MODIFY_AUDIO_SETTINGS = new Permission("android.permission.MODIFY_AUDIO_SETTINGS", 1, -1, "Allows an application to modify global audio settings.", "https://developer.android.com/reference/android/Manifest.permission.html#MODIFY_AUDIO_SETTINGS");
	public static final Permission MODIFY_PHONE_STATE = new Permission("android.permission.MODIFY_PHONE_STATE", 1, -1, "Allows modification of the telephony state - power on, mmi, etc. Does not include placing calls.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#MODIFY_PHONE_STATE");
	public static final Permission MOUNT_FORMAT_FILESYSTEMS = new Permission("android.permission.MOUNT_FORMAT_FILESYSTEMS", 3, -1, "Allows formatting file systems for removable storage.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#MOUNT_FORMAT_FILESYSTEMS");
	public static final Permission MOUNT_UNMOUNT_FILESYSTEMS = new Permission("android.permission.MOUNT_UNMOUNT_FILESYSTEMS", 1, -1, "Allows mounting and unmounting file systems for removable storage.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#MOUNT_UNMOUNT_FILESYSTEMS");
	public static final Permission NFC = new Permission("android.permission.NFC", 9, -1, "Allows applications to perform I/O operations over NFC.", "https://developer.android.com/reference/android/Manifest.permission.html#NFC");
	public static final Permission PERSISTENT_ACTIVITY = new Permission("android.permission.PERSISTENT_ACTIVITY", 1, 9, "This constant was deprecated in API level 9. This functionality will be removed in the future; please do not use. Allow an application to make its activities persistent.", "https://developer.android.com/reference/android/Manifest.permission.html#PERSISTENT_ACTIVITY");
	public static final Permission PROCESS_OUTGOING_CALLS = new Permission("android.permission.PROCESS_OUTGOING_CALLS", 1, -1, "Allows an application to see the number being dialed during an outgoing call with the option to redirect the call to a different number or abort the call altogether.", "https://developer.android.com/reference/android/Manifest.permission.html#PROCESS_OUTGOING_CALLS");
	public static final Permission READ_CALENDAR = new Permission("android.permission.READ_CALENDAR", 1, -1, "Allows an application to read the user's calendar data.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_CALENDAR");
	public static final Permission READ_CALL_LOG = new Permission("android.permission.READ_CALL_LOG", 16, -1, "Allows an application to read the user's call log.\nNote: If your app uses the READ_CONTACTS permission and both your minSdkVersion and targetSdkVersion values are set to 15 or lower, the system implicitly grants your app this permission. If you don't need this permission, be sure your targetSdkVersion is 16 or higher.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_CALL_LOG");
	public static final Permission READ_CONTACTS = new Permission("android.permission.READ_CONTACTS", 1, -1, "Allows an application to read the user's contacts data.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_CONTACTS");
	public static final Permission READ_EXTERNAL_STORAGE = new Permission("android.permission.READ_EXTERNAL_STORAGE", 16, -1, "Allows an application to read from external storage.\nAny app that declares the WRITE_EXTERNAL_STORAGE permission is implicitly granted this permission.\nThis permission is enforced starting in API level 19. Before API level 19, this permission is not enforced and all apps still have access to read from external storage. You can test your app with the permission enforced by enabling Protect USB storage under Developer options in the Settings app on a device running Android 4.1 or higher.\nAlso starting in API level 19, this permission is not required to read/write files in your application-specific directories returned by getExternalFilesDir(String) and getExternalCacheDir().\nNote: If both your minSdkVersion and targetSdkVersion values are set to 3 or lower, the system implicitly grants your app this permission. If you don't need this permission, be sure your targetSdkVersion is 4 or higher.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_EXTERNAL_STORAGE");
	public static final Permission READ_FRAME_BUFFER = new Permission("android.permission.READ_FRAME_BUFFER", 1, -1, "Allows an application to take screen shots and more generally get access to the frame buffer data.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_FRAME_BUFFER");
	public static final Permission READ_HISTORY_BOOKMARKS = new Permission("com.android.browser.permission.READ_HISTORY_BOOKMARKS", 4, -1, "Allows an application to read (but not write) the user's browsing history and bookmarks.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_HISTORY_BOOKMARKS");
	public static final Permission READ_INPUT_STATE = new Permission("android.permission.READ_INPUT_STATE", 1, 16, "Allows an application to retrieve the current state of keys and switches.\nNot for use by third-party applications.\nThis constant was deprecated in API level 16. The API that used this permission has been removed.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_INPUT_STATE");
	public static final Permission READ_LOGS = new Permission("android.permission.READ_LOGS", 1, -1, "Allows an application to read the low-level system log files.\nNot for use by third-party applications, because Log entries can contain the user's private information.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_LOGS");
	public static final Permission READ_PHONE_STATE = new Permission("android.permission.READ_PHONE_STATE", 1, -1, "Allows read only access to phone state.\nNote: If both your minSdkVersion and targetSdkVersion values are set to 3 or lower, the system implicitly grants your app this permission. If you don't need this permission, be sure your targetSdkVersion is 4 or higher.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_PHONE_STATE");
	public static final Permission READ_PROFILE = new Permission("android.permission.READ_PROFILE", 14, -1, "Allows an application to read the user's personal profile data.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_PROFILE");
	public static final Permission READ_SMS = new Permission("android.permission.READ_SMS", 1, -1, "Allows an application to read SMS messages.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_SMS");
	public static final Permission READ_SOCIAL_STREAM = new Permission("android.permission.READ_SOCIAL_STREAM", 15, 21, "Allows an application to read from the user's social stream.\nThis constant was deprecated in API level 21. This functionality will be unsupported in the future; cursors returned will be empty. Please do not use.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_SOCIAL_STREAM");
	public static final Permission READ_SYNC_SETTINGS = new Permission("android.permission.READ_SYNC_SETTINGS", 1, -1, "Allows applications to read the sync settings.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_SYNC_SETTINGS");
	public static final Permission READ_SYNC_STATS = new Permission("android.permission.READ_SYNC_STATS", 1, -1, "Allows applications to read the sync stats.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_SYNC_STATS");
	public static final Permission READ_USER_DICTIONARY = new Permission("android.permission.READ_USER_DICTIONARY", 16, -1, "Allows an application to read the user dictionary. This should really only be required by an IME, or a dictionary editor like the Settings app.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_USER_DICTIONARY");
	public static final Permission READ_VOICEMAIL = new Permission("com.android.voicemail.permission.READ_VOICEMAIL", 21, -1, "Allows an application to read voicemails in the system.", "https://developer.android.com/reference/android/Manifest.permission.html#READ_VOICEMAIL");
	public static final Permission REBOOT = new Permission("android.permission.REBOOT", 1, -1, "Required to be able to reboot the device.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#REBOOT");
	public static final Permission RECEIVE_BOOT_COMPLETED = new Permission("android.permission.RECEIVE_BOOT_COMPLETED", 1, -1, "Allows an application to receive the ACTION_BOOT_COMPLETED that is broadcast after the system finishes booting. If you don't request this permission, you will not receive the broadcast at that time. Though holding this permission does not have any security implications, it can have a negative impact on the user experience by increasing the amount of time it takes the system to start and allowing applications to have themselves running without the user being aware of them. As such, you must explicitly declare your use of this facility to make that visible to the user.", "https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_BOOT_COMPLETED");
	public static final Permission RECEIVE_MMS = new Permission("android.permission.RECEIVE_MMS", 1, -1, "Allows an application to monitor incoming MMS messages, to record or perform processing on them.", "https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_MMS");
	public static final Permission RECEIVE_SMS = new Permission("android.permission.RECEIVE_SMS", 1, -1, "Allows an application to monitor incoming SMS messages, to record or perform processing on them.", "https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_SMS");
	public static final Permission RECEIVE_WAP_PUSH = new Permission("android.permission.RECEIVE_WAP_PUSH", 1, -1, "Allows an application to monitor incoming WAP push messages.", "https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_WAP_PUSH");
	public static final Permission RECORD_AUDIO = new Permission("android.permission.RECORD_AUDIO", 1, -1, "Allows an application to record audio.", "https://developer.android.com/reference/android/Manifest.permission.html#RECORD_AUDIO");
	public static final Permission REORDER_TASKS = new Permission("android.permission.REORDER_TASKS", 1, -1, "Allows an application to change the Z-order of tasks.", "https://developer.android.com/reference/android/Manifest.permission.html#REORDER_TASKS");
	public static final Permission RESTART_PACKAGES = new Permission("android.permission.RESTART_PACKAGES", 1, 8, "This constant was deprecated in API level 8. The restartPackage(String) API is no longer supported.", "https://developer.android.com/reference/android/Manifest.permission.html#RESTART_PACKAGES");
	public static final Permission SEND_RESPOND_VIA_MESSAGE = new Permission("android.permission.SEND_RESPOND_VIA_MESSAGE", 18, -1, "Allows an application (Phone) to send a request to other applications to handle the respond-via-message action during incoming calls.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SEND_RESPOND_VIA_MESSAGE");
	public static final Permission SEND_SMS = new Permission("android.permission.SEND_SMS", 1, -1, "Allows an application to send SMS messages.", "https://developer.android.com/reference/android/Manifest.permission.html#SEND_SMS");
	public static final Permission SET_ACTIVITY_WATCHER = new Permission("android.permission.SET_ACTIVITY_WATCHER", 1, -1, "Allows an application to watch and control how activities are started globally in the system. Only for is in debugging (usually the monkey command).\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_ACTIVITY_WATCHER");
	public static final Permission SET_ALARM = new Permission("com.android.alarm.permission.SET_ALARM", 9, -1, "Allows an application to broadcast an Intent to set an alarm for the user.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_ALARM");
	public static final Permission SET_ALWAYS_FINISH = new Permission("android.permission.SET_ALWAYS_FINISH", 1, -1, "Allows an application to control whether activities are immediately finished when put in the background.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_ALWAYS_FINISH");
	public static final Permission SET_ANIMATION_SCALE = new Permission("android.permission.SET_ANIMATION_SCALE", 1, -1, "Modify the global animation scaling factor.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_ANIMATION_SCALE");
	public static final Permission SET_DEBUG_APP = new Permission("android.permission.SET_DEBUG_APP", 1, -1, "Configure an application for debugging.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_DEBUG_APP");
	public static final Permission SET_ORIENTATION = new Permission("android.permission.SET_ORIENTATION", 1, -1, "Allows low-level access to setting the orientation (actually rotation) of the screen.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_ORIENTATION");
	public static final Permission SET_POINTER_SPEED = new Permission("android.permission.SET_POINTER_SPEED", 13, -1, "Allows low-level access to setting the pointer speed.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_POINTER_SPEED");
	public static final Permission SET_PREFERRED_APPLICATIONS = new Permission("android.permission.SET_PREFERRED_APPLICATIONS", 1, 7, "This constant was deprecated in API level 7. No longer useful, see addPackageToPreferred(String) for details.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_PREFERRED_APPLICATIONS");
	public static final Permission SET_PROCESS_LIMIT = new Permission("android.permission.SET_PROCESS_LIMIT", 1, -1, "Allows an application to set the maximum number of (not needed) application processes that can be running.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_PROCESS_LIMIT");
	public static final Permission SET_TIME = new Permission("android.permission.SET_TIME", 8, -1, "Allows applications to set the system time.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_TIME");
	public static final Permission SET_TIME_ZONE = new Permission("android.permission.SET_TIME_ZONE", 1, -1, "Allows applications to set the system time zone.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_TIME_ZONE");
	public static final Permission SET_WALLPAPER = new Permission("android.permission.SET_WALLPAPER", 1, -1, "Allows applications to set the wallpaper.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_WALLPAPER");
	public static final Permission SET_WALLPAPER_HINTS = new Permission("android.permission.SET_WALLPAPER_HINTS", 1, -1, "Allows applications to set the wallpaper hints.", "https://developer.android.com/reference/android/Manifest.permission.html#SET_WALLPAPER_HINTS");
	public static final Permission SIGNAL_PERSISTENT_PROCESSES = new Permission("android.permission.SIGNAL_PERSISTENT_PROCESSES", 1, -1, "Allow an application to request that a signal be sent to all persistent processes.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#SIGNAL_PERSISTENT_PROCESSES");
	public static final Permission STATUS_BAR = new Permission("android.permission.STATUS_BAR", 1, -1, "Allows an application to open, close, or disable the status bar and its icons.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#STATUS_BAR");
	public static final Permission SUBSCRIBED_FEEDS_READ = new Permission("android.permission.SUBSCRIBED_FEEDS_READ", 1, -1, "Allows an application to allow access the subscribed feeds ContentProvider.", "https://developer.android.com/reference/android/Manifest.permission.html#SUBSCRIBED_FEEDS_READ");
	public static final Permission SUBSCRIBED_FEEDS_WRITE = new Permission("android.permission.SUBSCRIBED_FEEDS_WRITE", 1, -1, "No description available.", "https://developer.android.com/reference/android/Manifest.permission.html#SUBSCRIBED_FEEDS_WRITE");
	public static final Permission SYSTEM_ALERT_WINDOW = new Permission("android.permission.SYSTEM_ALERT_WINDOW", 1, -1, "Allows an application to open windows using the type TYPE_SYSTEM_ALERT, shown on top of all other applications. Very few applications should use this permission; these windows are intended for system-level interaction with the user.", "https://developer.android.com/reference/android/Manifest.permission.html#SYSTEM_ALERT_WINDOW");
	public static final Permission TRANSMIT_IR = new Permission("android.permission.TRANSMIT_IR", 19, -1, "Allows using the device's IR transmitter, if available.", "https://developer.android.com/reference/android/Manifest.permission.html#TRANSMIT_IR");
	public static final Permission UNINSTALL_SHORTCUT = new Permission("com.android.launcher.permission.UNINSTALL_SHORTCUT", 19, -1, "Allows an application to uninstall a shortcut in Launcher.", "https://developer.android.com/reference/android/Manifest.permission.html#UNINSTALL_SHORTCUT");
	public static final Permission UPDATE_DEVICE_STATS = new Permission("android.permission.UPDATE_DEVICE_STATS", 3, -1, "Allows an application to update device statistics.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#UPDATE_DEVICE_STATS");
	public static final Permission USE_CREDENTIALS = new Permission("android.permission.USE_CREDENTIALS", 5, -1, "Allows an application to request authtokens from the AccountManager.", "https://developer.android.com/reference/android/Manifest.permission.html#USE_CREDENTIALS");
	public static final Permission USE_SIP = new Permission("android.permission.USE_SIP", 9, -1, "Allows an application to use SIP service.", "https://developer.android.com/reference/android/Manifest.permission.html#USE_SIP");
	public static final Permission VIBRATE = new Permission("android.permission.VIBRATE", 1, -1, "Allows access to the vibrator.", "https://developer.android.com/reference/android/Manifest.permission.html#VIBRATE");
	public static final Permission WAKE_LOCK = new Permission("android.permission.WAKE_LOCK", 1, -1, "Allows using PowerManager WakeLocks to keep processor from sleeping or screen from dimming.", "https://developer.android.com/reference/android/Manifest.permission.html#WAKE_LOCK");
	public static final Permission WRITE_APN_SETTINGS = new Permission("android.permission.WRITE_APN_SETTINGS", 1, -1, "Allows applications to write the apn settings.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_APN_SETTINGS");
	public static final Permission WRITE_CALENDAR = new Permission("android.permission.WRITE_CALENDAR", 1, -1, "Allows an application to write (but not read) the user's calendar data.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_CALENDAR");
	public static final Permission WRITE_CALL_LOG = new Permission("android.permission.WRITE_CALL_LOG", 16, -1, "Allows an application to write (but not read) the user's contacts data.\nNote: If your app uses the WRITE_CONTACTS permission and both your minSdkVersion and targetSdkVersion values are set to 15 or lower, the system implicitly grants your app this permission. If you don't need this permission, be sure your targetSdkVersion is 16 or higher.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_CALL_LOG");
	public static final Permission WRITE_CONTACTS = new Permission("android.permission.WRITE_CONTACTS", 1, -1, "Allows an application to write (but not read) the user's contacts data.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_CONTACTS");
	public static final Permission WRITE_EXTERNAL_STORAGE = new Permission("android.permission.WRITE_EXTERNAL_STORAGE", 4, -1, "Allows an application to write to external storage.\nNote: If both your minSdkVersion and targetSdkVersion values are set to 3 or lower, the system implicitly grants your app this permission. If you don't need this permission, be sure your targetSdkVersion is 4 or higher.\nStarting in API level 19, this permission is not required to read/write files in your application-specific directories returned by getExternalFilesDir(String) and getExternalCacheDir().", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_EXTERNAL_STORAGE");
	public static final Permission WRITE_GSERVICES = new Permission("android.permission.WRITE_GSERVICES", 1, -1, "Allows an application to modify the Google service map.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_GSERVICES");
	public static final Permission WRITE_HISTORY_BOOKMARKS = new Permission("com.android.browser.permission.WRITE_HISTORY_BOOKMARKS", 4, -1, "Allows an application to write (but not read) the user's browsing history and bookmarks.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_HISTORY_BOOKMARKS");
	public static final Permission WRITE_PROFILE = new Permission("android.permission.WRITE_PROFILE", 14, -1, "Allows an application to write (but not read) the user's personal profile data.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_PROFILE");
	public static final Permission WRITE_SECURE_SETTINGS = new Permission("android.permission.WRITE_SECURE_SETTINGS", 3, -1, "Allows an application to read or write the secure system settings.\nNot for use by third-party applications.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_SECURE_SETTINGS");
	public static final Permission WRITE_SETTINGS = new Permission("android.permission.WRITE_SETTINGS", 1, -1, "Allows an application to read or write the system settings.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_SETTINGS");
	public static final Permission WRITE_SMS = new Permission("android.permission.WRITE_SMS", 1, -1, "Allows an application to write SMS messages.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_SMS");
	public static final Permission WRITE_SOCIAL_STREAM = new Permission("android.permission.WRITE_SOCIAL_STREAM", 15, 21, "Allows an application to write (but not read) the user's social stream data.\nThis constant was deprecated in API level 21. This functionality will be unsupported in the future; cursors returned will be empty. Please do not use.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_SOCIAL_STREAM");
	public static final Permission WRITE_SYNC_SETTINGS = new Permission("android.permission.WRITE_SYNC_SETTINGS", 1, -1, "Allows applications to write the sync settings.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_SYNC_SETTINGS");
	public static final Permission WRITE_USER_DICTIONARY = new Permission("android.permission.WRITE_USER_DICTIONARY", 16, -1, "Allows an application to write to the user dictionary.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_USER_DICTIONARY");
	public static final Permission WRITE_VOICEMAIL = new Permission("com.android.voicemail.permission.WRITE_VOICEMAIL", 21, -1, "Allows an application to modify and remove existing voicemails in the system.", "https://developer.android.com/reference/android/Manifest.permission.html#WRITE_VOICEMAIL");

	// special permissions
	public static final Permission ACCESS_WIMAX_STATE = new Permission("android.permission.ACCESS_WIMAX_STATE", 0, -1, "Unknown origin, required to control 4G state", "");
	public static final Permission ASEC_ACCESS = new Permission("andorid.permission.ASEC_ACCESS", 0, -1, "Unknown origin, likely from an internal method missidentified by Berkley", "");
	public static final Permission PACKAGE_USAGE_STATS = new Permission("andorid.permission.PACKAGE_USAGE_STATS", 0, -1, "Unknown origin, likely from an internal method missidentified by Berkley", "");
	public static final Permission SET_WALLPAPER_COMPONENT = new Permission("andorid.permission.SET_WALLPAPER_COMPONENT", 0, -1, "Unknown origin, likely from an internal method missidentified by Berkley", "");
	public static final Permission SHUTDOWN = new Permission("android.permission.SHUTDOWN", 0, -1, "Unknown origin, likely from an internal method missidentified by Berkley", "");
	public static final Permission ACCESS_DOWNLOAD_MANAGER = new Permission("android.permission.ACCESS_DOWNLOAD_MANAGER", 1, -1, "System permission to allow access to downloads.", "");

	// undocumented permissions
	public static final Permission FORCE_STOP_PACKAGES = new Permission("android.permission.FORCE_STOP_PACKAGES", 1, -1, "Have the system perform a force stop of everything associated with the given application package.", "Seems to have been completely removed from online Android documentation.");
	public static final Permission BACKUP = new Permission("android.permission.BACKUP", 8, -1, "Required to programmically backup other applications.", "Seems to have been completely removed from online Android documentation.");
	public static final Permission ACCESS_ALL_EXTERNAL_STORAGE = new Permission("android.permission.ACCESS_ALL_EXTERNAL_STORAGE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CAMERA_DISABLE_TRANSMIT_LED = new Permission("android.permission.CAMERA_DISABLE_TRANSMIT_LED", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CHANGE_WIMAX_STATE = new Permission("android.permission.CHANGE_WIMAX_STATE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CONNECTIVITY_INTERNAL = new Permission("android.permission.CONNECTIVITY_INTERNAL", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission RECEIVE_DATA_ACTIVITY_CHANGE = new Permission("android.permission.RECEIVE_DATA_ACTIVITY_CHANGE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission LOOP_RADIO = new Permission("android.permission.LOOP_RADIO", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission REMOVE_TASKS = new Permission("android.permission.REMOVE_TASKS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MANAGE_ACTIVITY_STACKS = new Permission("android.permission.MANAGE_ACTIVITY_STACKS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission READ_PRECISE_PHONE_STATE = new Permission("android.permission.READ_PRECISE_PHONE_STATE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission READ_PRIVILEGED_PHONE_STATE = new Permission("android.permission.READ_PRIVILEGED_PHONE_STATE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BIND_CALL_SERVICE = new Permission("android.permission.BIND_CALL_SERVICE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BLUETOOTH_STACK = new Permission("android.permission.BLUETOOTH_STACK", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission NET_ADMIN = new Permission("android.permission.NET_ADMIN", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission REMOTE_AUDIO_PLAYBACK = new Permission("android.permission.REMOTE_AUDIO_PLAYBACK", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission INTERACT_ACROSS_USERS = new Permission("android.permission.INTERACT_ACROSS_USERS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission INTERACT_ACROSS_USERS_FULL = new Permission("android.permission.INTERACT_ACROSS_USERS_FULL", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MANAGE_USERS = new Permission("android.permission.MANAGE_USERS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission GET_DETAILED_TASKS = new Permission("android.permission.GET_DETAILED_TASKS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission START_ANY_ACTIVITY = new Permission("android.permission.START_ANY_ACTIVITY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission SET_SCREEN_COMPATIBILITY = new Permission("android.permission.SET_SCREEN_COMPATIBILITY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ASEC_CREATE = new Permission("android.permission.ASEC_CREATE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ASEC_DESTROY = new Permission("android.permission.ASEC_DESTROY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ASEC_MOUNT_UNMOUNT = new Permission("android.permission.ASEC_MOUNT_UNMOUNT", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ASEC_RENAME = new Permission("android.permission.ASEC_RENAME", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission GET_APP_OPS_STATS = new Permission("android.permission.GET_APP_OPS_STATS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission NET_TUNNELING = new Permission("android.permission.NET_TUNNELING", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MODIFY_APPWIDGET_BIND_PERMISSIONS = new Permission("android.permission.MODIFY_APPWIDGET_BIND_PERMISSIONS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CHANGE_BACKGROUND_DATA_SETTING = new Permission("android.permission.CHANGE_BACKGROUND_DATA_SETTING", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission GLOBAL_SEARCH_CONTROL = new Permission("android.permission.GLOBAL_SEARCH_CONTROL", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission READ_DREAM_STATE = new Permission("android.permission.READ_DREAM_STATE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission WRITE_DREAM_STATE = new Permission("android.permission.WRITE_DREAM_STATE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission RECEIVE_EMERGENCY_BROADCAST = new Permission("android.permission.RECEIVE_EMERGENCY_BROADCAST", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission READ_CELL_BROADCASTS = new Permission("android.permission.READ_CELL_BROADCASTS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission WRITE_MEDIA_STORAGE = new Permission("android.permission.WRITE_MEDIA_STORAGE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BIND_DIRECTORY_SEARCH = new Permission("android.permission.BIND_DIRECTORY_SEARCH", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission RETRIEVE_WINDOW_CONTENT = new Permission("android.permission.RETRIEVE_WINDOW_CONTENT", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BIND_KEYGUARD_APPWIDGET = new Permission("android.permission.BIND_KEYGUARD_APPWIDGET", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MANAGE_USB = new Permission("android.permission.MANAGE_USB", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ACCESS_MTP = new Permission("android.permission.ACCESS_MTP", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission SERIAL_PORT = new Permission("android.permission.SERIAL_PORT", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CONFIGURE_WIFI_DISPLAY = new Permission("android.permission.CONFIGURE_WIFI_DISPLAY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MOVE_PACKAGE = new Permission("android.permission.MOVE_PACKAGE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CRYPT_KEEPER = new Permission("android.permission.CRYPT_KEEPER", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CONTROL_WIFI_DISPLAY = new Permission("android.permission.CONTROL_WIFI_DISPLAY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ACCESS_CACHE_FILESYSTEM = new Permission("android.permission.ACCESS_CACHE_FILESYSTEM", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission FILTER_EVENTS = new Permission("android.permission.FILTER_EVENTS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission STOP_APP_SWITCHES = new Permission("android.permission.STOP_APP_SWITCHES", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission SET_KEYBOARD_LAYOUT = new Permission("android.permission.SET_KEYBOARD_LAYOUT", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission RETRIEVE_WINDOW_INFO = new Permission("android.permission.RETRIEVE_WINDOW_INFO", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BIND_PACKAGE_VERIFIER = new Permission("android.permission.BIND_PACKAGE_VERIFIER", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ACCESS_NETWORK_CONDITIONS = new Permission("android.permission.ACCESS_NETWORK_CONDITIONS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ACCESS_KEYGUARD_SECURE_STORAGE = new Permission("android.permission.ACCESS_KEYGUARD_SECURE_STORAGE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission INVOKE_CARRIER_SETUP = new Permission("android.permission.INVOKE_CARRIER_SETUP", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission COPY_PROTECTED_DATA = new Permission("android.permission.COPY_PROTECTED_DATA", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ACCESS_NOTIFICATIONS = new Permission("android.permission.ACCESS_NOTIFICATIONS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MODIFY_NETWORK_ACCOUNTING = new Permission("android.permission.MODIFY_NETWORK_ACCOUNTING", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission READ_NETWORK_USAGE_HISTORY = new Permission("android.permission.READ_NETWORK_USAGE_HISTORY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission STATUS_BAR_SERVICE = new Permission("android.permission.STATUS_BAR_SERVICE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission UPDATE_APP_OPS_STATS = new Permission("android.permission.UPDATE_APP_OPS_STATS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MANAGE_DEVICE_ADMINS = new Permission("android.permission.MANAGE_DEVICE_ADMINS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission PERFORM_CDMA_PROVISIONING = new Permission("android.permission.PERFORM_CDMA_PROVISIONING", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CAPTURE_AUDIO_HOTWORD = new Permission("android.permission.CAPTURE_AUDIO_HOTWORD", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CONFIRM_FULL_BACKUP = new Permission("android.permission.CONFIRM_FULL_BACKUP", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission CONTROL_KEYGUARD = new Permission("android.permission.CONTROL_KEYGUARD", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission PACKAGE_VERIFICATION_AGENT = new Permission("android.permission.PACKAGE_VERIFICATION_AGENT", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission C2D_MESSAGE = new Permission("android.permission.C2D_MESSAGE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BIND_PRINT_SPOOLER_SERVICE = new Permission("android.permission.BIND_PRINT_SPOOLER_SERVICE", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MANAGE_CA_CERTIFICATES = new Permission("android.permission.MANAGE_CA_CERTIFICATES", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission BIND_REMOTE_DISPLAY = new Permission("android.permission.BIND_REMOTE_DISPLAY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MANAGE_NETWORK_POLICY = new Permission("android.permission.MANAGE_NETWORK_POLICY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ALLOW_ANY_CODEC_FOR_PLAYBACK = new Permission("android.permission.ALLOW_ANY_CODEC_FOR_PLAYBACK", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MARK_NETWORK_SOCKET = new Permission("android.permission.MARK_NETWORK_SOCKET", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission GRANT_REVOKE_PERMISSIONS = new Permission("android.permission.GRANT_REVOKE_PERMISSIONS", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission FREEZE_SCREEN = new Permission("android.permission.FREEZE_SCREEN", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission TEMPORARY_ENABLE_ACCESSIBILITY = new Permission("android.permission.TEMPORARY_ENABLE_ACCESSIBILITY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission ACCESS_CONTENT_PROVIDERS_EXTERNALLY = new Permission("android.permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission MAGNIFY_DISPLAY = new Permission("android.permission.MAGNIFY_DISPLAY", -1, -1, "This permission is undocumented", "PScout mapping results");
	public static final Permission UPDATE_LOCK = new Permission("android.permission.UPDATE_LOCK", -1, -1, "This permission is undocumented", "PScout mapping results");

	/**
	 * A collection of all known permissions (the union of all known documented and undocumented permissions)
	 */
	public static final Collection<Permission> allPermissions = allPermissions();
	
	/**
	 * A collection of all known documented permissions
	 */
	public static final Collection<Permission> allDocumentedPermissions = allDocumentedPermissions();
	
	/**
	 * A collection of all known undocumented permissions
	 */
	public static final Collection<Permission> allUndocumentedPermissions = allUndocumentedPermissions();
	
	/**
	 * Helper method for creating a static field with all known permissions (the union of all known documented and undocumented permissions)
	 * @return
	 */
	private static Collection<Permission> allPermissions() {
		Collection<Permission> allPermissions = new HashSet<Permission>();
		allPermissions.addAll(allDocumentedPermissions());
		allPermissions.addAll(allUndocumentedPermissions());
		return allPermissions;
	}
		
	/**
	 * Helper method for creating a static field with all known documented permissions
	 * @return
	 */
	private static Collection<Permission> allDocumentedPermissions() {
		Collection<Permission> allDocumentedPermissions = new HashSet<Permission>();
		allDocumentedPermissions.add(ADD_VOICEMAIL);
		allDocumentedPermissions.add(INSTALL_SHORTCUT);
		allDocumentedPermissions.add(DELETE_PACKAGES);
		allDocumentedPermissions.add(UPDATE_DEVICE_STATS);
		allDocumentedPermissions.add(ACCESS_NETWORK_STATE);
		allDocumentedPermissions.add(REBOOT);
		allDocumentedPermissions.add(ACCESS_WIFI_STATE);
		allDocumentedPermissions.add(SET_WALLPAPER_HINTS);
		allDocumentedPermissions.add(SET_TIME_ZONE);
		allDocumentedPermissions.add(HARDWARE_TEST);
		allDocumentedPermissions.add(INTERNAL_SYSTEM_WINDOW);
		allDocumentedPermissions.add(WRITE_PROFILE);
		allDocumentedPermissions.add(USE_CREDENTIALS);
		allDocumentedPermissions.add(CLEAR_APP_CACHE);
		allDocumentedPermissions.add(WRITE_SETTINGS);
		allDocumentedPermissions.add(ACCOUNT_MANAGER);
		allDocumentedPermissions.add(GET_PACKAGE_SIZE);
		allDocumentedPermissions.add(CHANGE_WIFI_STATE);
		allDocumentedPermissions.add(GET_TOP_ACTIVITY_INFO);
		allDocumentedPermissions.add(PERSISTENT_ACTIVITY);
		allDocumentedPermissions.add(NFC);
		allDocumentedPermissions.add(READ_SMS);
		allDocumentedPermissions.add(TRANSMIT_IR);
		allDocumentedPermissions.add(BIND_APPWIDGET);
		allDocumentedPermissions.add(MODIFY_AUDIO_SETTINGS);
		allDocumentedPermissions.add(READ_CALENDAR);
		allDocumentedPermissions.add(CAMERA);
		allDocumentedPermissions.add(GLOBAL_SEARCH);
		allDocumentedPermissions.add(KILL_BACKGROUND_PROCESSES);
		allDocumentedPermissions.add(CLEAR_APP_USER_DATA);
		allDocumentedPermissions.add(BIND_NFC_SERVICE);
		allDocumentedPermissions.add(CAPTURE_VIDEO_OUTPUT);
		allDocumentedPermissions.add(WRITE_SOCIAL_STREAM);
		allDocumentedPermissions.add(CHANGE_WIFI_MULTICAST_STATE);
		allDocumentedPermissions.add(ACCESS_COARSE_LOCATION);
		allDocumentedPermissions.add(RESTART_PACKAGES);
		allDocumentedPermissions.add(CALL_PRIVILEGED);
		allDocumentedPermissions.add(SUBSCRIBED_FEEDS_READ);
		allDocumentedPermissions.add(UNINSTALL_SHORTCUT);
		allDocumentedPermissions.add(BLUETOOTH_ADMIN);
		allDocumentedPermissions.add(FLASHLIGHT);
		allDocumentedPermissions.add(READ_PHONE_STATE);
		allDocumentedPermissions.add(CALL_PHONE);
		allDocumentedPermissions.add(BIND_WALLPAPER);
		allDocumentedPermissions.add(INSTALL_PACKAGES);
		allDocumentedPermissions.add(READ_FRAME_BUFFER);
		allDocumentedPermissions.add(RECEIVE_WAP_PUSH);
		allDocumentedPermissions.add(BIND_TV_INPUT);
		allDocumentedPermissions.add(SET_ANIMATION_SCALE);
		allDocumentedPermissions.add(AUTHENTICATE_ACCOUNTS);
		allDocumentedPermissions.add(BIND_DEVICE_ADMIN);
		allDocumentedPermissions.add(MANAGE_APP_TOKENS);
		allDocumentedPermissions.add(MODIFY_PHONE_STATE);
		allDocumentedPermissions.add(RECEIVE_BOOT_COMPLETED);
		allDocumentedPermissions.add(WRITE_SECURE_SETTINGS);
		allDocumentedPermissions.add(SET_TIME);
		allDocumentedPermissions.add(BIND_ACCESSIBILITY_SERVICE);
		allDocumentedPermissions.add(INJECT_EVENTS);
		allDocumentedPermissions.add(WRITE_CALENDAR);
		allDocumentedPermissions.add(DIAGNOSTIC);
		allDocumentedPermissions.add(ACCESS_LOCATION_EXTRA_COMMANDS);
		allDocumentedPermissions.add(WRITE_SMS);
		allDocumentedPermissions.add(DEVICE_POWER);
		allDocumentedPermissions.add(ACCESS_MOCK_LOCATION);
		allDocumentedPermissions.add(READ_PROFILE);
		allDocumentedPermissions.add(SYSTEM_ALERT_WINDOW);
		allDocumentedPermissions.add(WRITE_CONTACTS);
		allDocumentedPermissions.add(RECORD_AUDIO);
		allDocumentedPermissions.add(VIBRATE);
		allDocumentedPermissions.add(SUBSCRIBED_FEEDS_WRITE);
		allDocumentedPermissions.add(RECEIVE_SMS);
		allDocumentedPermissions.add(READ_EXTERNAL_STORAGE);
		allDocumentedPermissions.add(BIND_REMOTEVIEWS);
		allDocumentedPermissions.add(BIND_NOTIFICATION_LISTENER_SERVICE);
		allDocumentedPermissions.add(SET_DEBUG_APP);
		allDocumentedPermissions.add(CHANGE_COMPONENT_ENABLED_STATE);
		allDocumentedPermissions.add(BIND_VOICE_INTERACTION);
		allDocumentedPermissions.add(DELETE_CACHE_FILES);
		allDocumentedPermissions.add(MEDIA_CONTENT_CONTROL);
		allDocumentedPermissions.add(READ_HISTORY_BOOKMARKS);
		allDocumentedPermissions.add(BATTERY_STATS);
		allDocumentedPermissions.add(READ_LOGS);
		allDocumentedPermissions.add(ACCESS_CHECKIN_PROPERTIES);
		allDocumentedPermissions.add(WRITE_GSERVICES);
		allDocumentedPermissions.add(READ_USER_DICTIONARY);
		allDocumentedPermissions.add(BROADCAST_STICKY);
		allDocumentedPermissions.add(SET_PROCESS_LIMIT);
		allDocumentedPermissions.add(CHANGE_CONFIGURATION);
		allDocumentedPermissions.add(GET_ACCOUNTS);
		allDocumentedPermissions.add(MOUNT_FORMAT_FILESYSTEMS);
		allDocumentedPermissions.add(BROADCAST_PACKAGE_REMOVED);
		allDocumentedPermissions.add(PROCESS_OUTGOING_CALLS);
		allDocumentedPermissions.add(SET_ALWAYS_FINISH);
		allDocumentedPermissions.add(SET_ALARM);
		allDocumentedPermissions.add(BODY_SENSORS);
		allDocumentedPermissions.add(BIND_VPN_SERVICE);
		allDocumentedPermissions.add(RECEIVE_MMS);
		allDocumentedPermissions.add(BLUETOOTH);
		allDocumentedPermissions.add(WRITE_SYNC_SETTINGS);
		allDocumentedPermissions.add(WRITE_EXTERNAL_STORAGE);
		allDocumentedPermissions.add(MANAGE_DOCUMENTS);
		allDocumentedPermissions.add(STATUS_BAR);
		allDocumentedPermissions.add(REORDER_TASKS);
		allDocumentedPermissions.add(BIND_INPUT_METHOD);
		allDocumentedPermissions.add(DUMP);
		allDocumentedPermissions.add(SET_WALLPAPER);
		allDocumentedPermissions.add(SET_ORIENTATION);
		allDocumentedPermissions.add(DISABLE_KEYGUARD);
		allDocumentedPermissions.add(READ_SYNC_STATS);
		allDocumentedPermissions.add(EXPAND_STATUS_BAR);
		allDocumentedPermissions.add(SEND_SMS);
		allDocumentedPermissions.add(WAKE_LOCK);
		allDocumentedPermissions.add(MANAGE_ACCOUNTS);
		allDocumentedPermissions.add(SET_ACTIVITY_WATCHER);
		allDocumentedPermissions.add(CHANGE_NETWORK_STATE);
		allDocumentedPermissions.add(WRITE_APN_SETTINGS);
		allDocumentedPermissions.add(READ_CALL_LOG);
		allDocumentedPermissions.add(CONTROL_LOCATION_UPDATES);
		allDocumentedPermissions.add(READ_VOICEMAIL);
		allDocumentedPermissions.add(BRICK);
		allDocumentedPermissions.add(SEND_RESPOND_VIA_MESSAGE);
		allDocumentedPermissions.add(MASTER_CLEAR);
		allDocumentedPermissions.add(WRITE_USER_DICTIONARY);
		allDocumentedPermissions.add(BLUETOOTH_PRIVILEGED);
		allDocumentedPermissions.add(WRITE_VOICEMAIL);
		allDocumentedPermissions.add(SIGNAL_PERSISTENT_PROCESSES);
		allDocumentedPermissions.add(SET_POINTER_SPEED);
		allDocumentedPermissions.add(READ_SYNC_SETTINGS);
		allDocumentedPermissions.add(SET_PREFERRED_APPLICATIONS);
		allDocumentedPermissions.add(WRITE_HISTORY_BOOKMARKS);
		allDocumentedPermissions.add(INTERNET);
		allDocumentedPermissions.add(GET_TASKS);
		allDocumentedPermissions.add(CAPTURE_AUDIO_OUTPUT);
		allDocumentedPermissions.add(BIND_TEXT_SERVICE);
		allDocumentedPermissions.add(MOUNT_UNMOUNT_FILESYSTEMS);
		allDocumentedPermissions.add(BROADCAST_WAP_PUSH);
		allDocumentedPermissions.add(BROADCAST_SMS);
		allDocumentedPermissions.add(USE_SIP);
		allDocumentedPermissions.add(FACTORY_TEST);
		allDocumentedPermissions.add(ACCESS_FINE_LOCATION);
		allDocumentedPermissions.add(READ_SOCIAL_STREAM);
		allDocumentedPermissions.add(WRITE_CALL_LOG);
		allDocumentedPermissions.add(BIND_PRINT_SERVICE);
		allDocumentedPermissions.add(READ_INPUT_STATE);
		allDocumentedPermissions.add(ACCESS_SURFACE_FLINGER);
		allDocumentedPermissions.add(CAPTURE_SECURE_VIDEO_OUTPUT);
		allDocumentedPermissions.add(INSTALL_LOCATION_PROVIDER);
		allDocumentedPermissions.add(FORCE_BACK);
		allDocumentedPermissions.add(READ_CONTACTS);
		allDocumentedPermissions.add(BIND_DREAM_SERVICE);
		allDocumentedPermissions.add(LOCATION_HARDWARE);
		return allDocumentedPermissions;
	}
	
	/**
	 * Helper method for creating a static field with all known undocumented permissions
	 * @return
	 */
	private static Collection<Permission> allUndocumentedPermissions() {
		Collection<Permission> allUndocumentedPermissions = new HashSet<Permission>();
		
		// special permissions
		allUndocumentedPermissions.add(ACCESS_WIMAX_STATE);
		allUndocumentedPermissions.add(ASEC_ACCESS);
		allUndocumentedPermissions.add(PACKAGE_USAGE_STATS);
		allUndocumentedPermissions.add(SET_WALLPAPER_COMPONENT);
		allUndocumentedPermissions.add(SHUTDOWN);
		allUndocumentedPermissions.add(ACCESS_DOWNLOAD_MANAGER);
		
		// other undocumented permissions
		allUndocumentedPermissions.add(ACCESS_ALL_EXTERNAL_STORAGE);
		allUndocumentedPermissions.add(CAMERA_DISABLE_TRANSMIT_LED);
		allUndocumentedPermissions.add(CHANGE_WIMAX_STATE);
		allUndocumentedPermissions.add(CONNECTIVITY_INTERNAL);
		allUndocumentedPermissions.add(RECEIVE_DATA_ACTIVITY_CHANGE);
		allUndocumentedPermissions.add(LOOP_RADIO);
		allUndocumentedPermissions.add(REMOVE_TASKS);
		allUndocumentedPermissions.add(MANAGE_ACTIVITY_STACKS);
		allUndocumentedPermissions.add(READ_PRECISE_PHONE_STATE);
		allUndocumentedPermissions.add(READ_PRIVILEGED_PHONE_STATE);
		allUndocumentedPermissions.add(BIND_CALL_SERVICE);
		allUndocumentedPermissions.add(BLUETOOTH_STACK);
		allUndocumentedPermissions.add(NET_ADMIN);
		allUndocumentedPermissions.add(REMOTE_AUDIO_PLAYBACK);
		allUndocumentedPermissions.add(INTERACT_ACROSS_USERS);
		allUndocumentedPermissions.add(INTERACT_ACROSS_USERS_FULL);
		allUndocumentedPermissions.add(MANAGE_USERS);
		allUndocumentedPermissions.add(GET_DETAILED_TASKS);
		allUndocumentedPermissions.add(START_ANY_ACTIVITY);
		allUndocumentedPermissions.add(SET_SCREEN_COMPATIBILITY);
		allUndocumentedPermissions.add(ASEC_CREATE);
		allUndocumentedPermissions.add(ASEC_DESTROY);
		allUndocumentedPermissions.add(ASEC_MOUNT_UNMOUNT);
		allUndocumentedPermissions.add(ASEC_RENAME);
		allUndocumentedPermissions.add(GET_APP_OPS_STATS);
		allUndocumentedPermissions.add(NET_TUNNELING);
		allUndocumentedPermissions.add(MODIFY_APPWIDGET_BIND_PERMISSIONS);
		allUndocumentedPermissions.add(CHANGE_BACKGROUND_DATA_SETTING);
		allUndocumentedPermissions.add(GLOBAL_SEARCH_CONTROL);
		allUndocumentedPermissions.add(READ_DREAM_STATE);
		allUndocumentedPermissions.add(WRITE_DREAM_STATE);
		allUndocumentedPermissions.add(RECEIVE_EMERGENCY_BROADCAST);
		allUndocumentedPermissions.add(READ_CELL_BROADCASTS);
		allUndocumentedPermissions.add(WRITE_MEDIA_STORAGE);
		allUndocumentedPermissions.add(BIND_DIRECTORY_SEARCH);
		allUndocumentedPermissions.add(RETRIEVE_WINDOW_CONTENT);
		allUndocumentedPermissions.add(BIND_KEYGUARD_APPWIDGET);
		allUndocumentedPermissions.add(MANAGE_USB);
		allUndocumentedPermissions.add(ACCESS_MTP);
		allUndocumentedPermissions.add(SERIAL_PORT);
		allUndocumentedPermissions.add(CONFIGURE_WIFI_DISPLAY);
		allUndocumentedPermissions.add(MOVE_PACKAGE);
		allUndocumentedPermissions.add(CRYPT_KEEPER);
		allUndocumentedPermissions.add(CONTROL_WIFI_DISPLAY);
		allUndocumentedPermissions.add(ACCESS_CACHE_FILESYSTEM);
		allUndocumentedPermissions.add(FILTER_EVENTS);
		allUndocumentedPermissions.add(STOP_APP_SWITCHES);
		allUndocumentedPermissions.add(SET_KEYBOARD_LAYOUT);
		allUndocumentedPermissions.add(RETRIEVE_WINDOW_INFO);
		allUndocumentedPermissions.add(BIND_PACKAGE_VERIFIER);
		allUndocumentedPermissions.add(ACCESS_NETWORK_CONDITIONS);
		allUndocumentedPermissions.add(ACCESS_KEYGUARD_SECURE_STORAGE);
		allUndocumentedPermissions.add(INVOKE_CARRIER_SETUP);
		allUndocumentedPermissions.add(COPY_PROTECTED_DATA);
		allUndocumentedPermissions.add(ACCESS_NOTIFICATIONS);
		allUndocumentedPermissions.add(MODIFY_NETWORK_ACCOUNTING);
		allUndocumentedPermissions.add(READ_NETWORK_USAGE_HISTORY);
		allUndocumentedPermissions.add(STATUS_BAR_SERVICE);
		allUndocumentedPermissions.add(UPDATE_APP_OPS_STATS);
		allUndocumentedPermissions.add(MANAGE_DEVICE_ADMINS);
		allUndocumentedPermissions.add(PERFORM_CDMA_PROVISIONING);
		allUndocumentedPermissions.add(CAPTURE_AUDIO_HOTWORD);
		allUndocumentedPermissions.add(CONFIRM_FULL_BACKUP);
		allUndocumentedPermissions.add(CONTROL_KEYGUARD);
		allUndocumentedPermissions.add(PACKAGE_VERIFICATION_AGENT);
		allUndocumentedPermissions.add(C2D_MESSAGE);
		allUndocumentedPermissions.add(BIND_PRINT_SPOOLER_SERVICE);
		allUndocumentedPermissions.add(MANAGE_CA_CERTIFICATES);
		allUndocumentedPermissions.add(BIND_REMOTE_DISPLAY);
		allUndocumentedPermissions.add(MANAGE_NETWORK_POLICY);
		allUndocumentedPermissions.add(ALLOW_ANY_CODEC_FOR_PLAYBACK);
		allUndocumentedPermissions.add(MARK_NETWORK_SOCKET);
		allUndocumentedPermissions.add(GRANT_REVOKE_PERMISSIONS);
		allUndocumentedPermissions.add(FREEZE_SCREEN);
		allUndocumentedPermissions.add(TEMPORARY_ENABLE_ACCESSIBILITY);
		allUndocumentedPermissions.add(ACCESS_CONTENT_PROVIDERS_EXTERNALLY);
		allUndocumentedPermissions.add(MAGNIFY_DISPLAY);
		allUndocumentedPermissions.add(UPDATE_LOCK);
		return allUndocumentedPermissions;
	}
	
}

