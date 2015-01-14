package com.ensoftcorp.open.android.essentials.permissions;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * An convenience object that encodes Android Permission property values
 * @author Ben Holland, Vani Bojja
 */
public class Permission {
	
	public static int LAST_SUPPORTED_API_VERSION = 19;
	public static String[] REFERENCE_SOURCES = {};
	public static Date REFERENCE_DATE = new Date();
	
	private String qualifiedName;
	private int addedInAPILevel;
	private boolean isDeprecated;
	private String description;

	private Permission(String qualifiedName, int addedInLevel, boolean isDeprecated, String description) {
		this.qualifiedName = qualifiedName;
		this.addedInAPILevel = addedInLevel;
		this.isDeprecated = isDeprecated;
		this.description = description;
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
		return isDeprecated;
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

	// documented permissions
	public static final Permission ACCESS_CHECKIN_PROPERTIES = new Permission("android.permission.ACCESS_CHECKIN_PROPERTIES", 1, false, "Allows read/write access to the \"properties\" table in the checkin database, to change values that get uploaded.");
	public static final Permission ACCESS_COARSE_LOCATION = new Permission("android.permission.ACCESS_COARSE_LOCATION", 1, false, "Allows an application to access coarse (e.g., Cell-ID, WiFi) location");
	public static final Permission ACCESS_DRM = new Permission("android.permission.ACCESS_DRM", 0, false, "Allows an application to access DRM content");
	public static final Permission ACCESS_FINE_LOCATION = new Permission("android.permission.ACCESS_FINE_LOCATION", 1, false, "Allows an application to access fine (e.g., GPS) location");
	public static final Permission ACCESS_LOCATION_EXTRA_COMMANDS = new Permission("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", 1, false, "Allows an application to access extra location provider commands");
	public static final Permission ACCESS_MOCK_LOCATION = new Permission("android.permission.ACCESS_MOCK_LOCATION", 1, false, "Allows an application to create mock location providers for testing");
	public static final Permission ACCESS_NETWORK_STATE = new Permission("android.permission.ACCESS_NETWORK_STATE", 1, false, "Allows applications to access information about networks");
	public static final Permission ACCESS_SURFACE_FLINGER = new Permission("android.permission.ACCESS_SURFACE_FLINGER", 1, false, "Allows an application to use SurfaceFlinger's low level features");
	public static final Permission ACCESS_WIFI_STATE = new Permission("android.permission.ACCESS_WIFI_STATE", 1, false, "Allows applications to access information about Wi-Fi networks");
	public static final Permission ACCOUNT_MANAGER = new Permission("android.permission.ACCOUNT_MANAGER", 5, false, "Allows applications to call into AccountAuthenticators. Not for use by third-party applications.");
	public static final Permission ADD_VOICEMAIL = new Permission("com.android.voicemail.permission.ADD_VOICEMAIL", 14, false, "Allows an application to add voicemails into the system.");
	public static final Permission AUTHENTICATE_ACCOUNTS = new Permission("android.permission.AUTHENTICATE_ACCOUNTS", 5, false, "Allows an application to act as an AccountAuthenticator for the AccountManager");
	public static final Permission BACKUP = new Permission("android.permission.BACKUP", 8, false, "Required to programmically backup other applications.");
	public static final Permission BATTERY_STATS = new Permission("android.permission.BATTERY_STATS", 1, false, "Allows an application to collect battery statistics");
	public static final Permission BIND_ACCESSIBILITY_SERVICE = new Permission("android.permission.BIND_ACCESSIBILITY_SERVICE", 16, false, "Must be required by an AccessibilityService, to ensure that only the system can bind to it.");
	public static final Permission BIND_APPWIDGET = new Permission("android.permission.BIND_APPWIDGET", 3, false, "Allows an application to tell the AppWidget service which application can access AppWidget's data. The normal user flow is that a user picks an AppWidget to go into a particular host, thereby giving that host application access to the private data from the AppWidget app. An application that has this permission should honor that contract. Very few applications should need to use this permission.");
	public static final Permission BIND_DEVICE_ADMIN = new Permission("android.permission.BIND_DEVICE_ADMIN", 8, false, "Must be required by device administration receiver, to ensure that only the system can interact with it.");
	public static final Permission BIND_INPUT_METHOD = new Permission("android.permission.BIND_INPUT_METHOD", 3, false, "  Must be required by an InputMethodService, to ensure that only the system can bind to it.");
	public static final Permission BIND_NFC_SERVICE = new Permission("android.permission.BIND_NFC_SERVICE", 19, false, "Must be required by a HostApduService or OffHostApduService to ensure that only the system can bind to it.");
	public static final Permission BIND_NOTIFICATION_LISTENER_SERVICE = new Permission("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", 18, false, "Must be required by an NotificationListenerService, to ensure that only the system can bind to it.");
	public static final Permission BIND_PRINT_SERVICE = new Permission("android.permission.BIND_PRINT_SERVICE", 19, false, "Must be required by a PrintService, to ensure that only the system can bind to it.");
	public static final Permission BIND_REMOTEVIEWS = new Permission("android.permission.BIND_REMOTEVIEWS", 11, false, "Must be required by a RemoteViewsService, to ensure that only the system can bind to it.");
	public static final Permission BIND_TEXT_SERVICE = new Permission("android.permission.BIND_TEXT_SERVICE", 14, false, "Must be required by a TextService (e.g. SpellCheckerService) to ensure that only the system can bind to it. ");
	public static final Permission BIND_VPN_SERVICE = new Permission("android.permission.BIND_VPN_SERVICE", 14, false, "Must be required by an VpnService, to ensure that only the system can bind to it.");
	public static final Permission BIND_WALLPAPER = new Permission("android.permission.BIND_WALLPAPER", 8, false, "Must be required by a WallpaperService, to ensure that only the system can bind to it. ");
	public static final Permission BLUETOOTH = new Permission("android.permission.BLUETOOTH", 1, false, "Allows applications to connect to paired bluetooth devices");
	public static final Permission BLUETOOTH_ADMIN = new Permission("android.permission.BLUETOOTH_ADMIN", 1, false, "Allows applications to discover and pair bluetooth devices");
	public static final Permission BRICK = new Permission("android.permission.BRICK", 1, false, "Required to be able to disable the device (very dangerous!).");
	public static final Permission BROADCAST_PACKAGE_REMOVED = new Permission("android.permission.BROADCAST_PACKAGE_REMOVED", 1, false, "Allows an application to broadcast a notification that an application package has been removed.");
	public static final Permission BROADCAST_SMS = new Permission("android.permission.BROADCAST_SMS", 2, false, "Allows an application to broadcast an SMS receipt notification");
	public static final Permission BROADCAST_STICKY = new Permission("android.permission.BROADCAST_STICKY", 1, false, "Allows an application to broadcast sticky intents. These are broadcasts whose data is held by the system after being finished, so that clients can quickly retrieve that data without having to wait for the next broadcast.");
	public static final Permission BROADCAST_WAP_PUSH = new Permission("android.permission.BROADCAST_WAP_PUSH", 2, false, "Allows an application to broadcast a WAP PUSH receipt notification");
	public static final Permission CALL_PHONE = new Permission("android.permission.CALL_PHONE", 1, false, "Allows an application to initiate a phone call without going through the Dialer user interface for the user to confirm the call being placed.");
	public static final Permission CALL_PRIVILEGED = new Permission("android.permission.CALL_PRIVILEGED", 1, false, "Allows an application to call any phone number, including emergency numbers, without going through the Dialer user interface for the user to confirm the call being placed.");
	public static final Permission CAMERA = new Permission("android.permission.CAMERA", 1, false, "Required to be able to access the camera device. This will automatically enforce the <uses-feature> manifest element for all camera features. If you do not require all camera features or can properly operate if a camera is not available, then you must modify your manifest as appropriate in order to install on devices that don't support all camera features.");
	public static final Permission CAPTURE_AUDIO_OUTPUT = new Permission("android.permission.CAPTURE_AUDIO_OUTPUT", 19, false, "Allows an application to capture audio output. Not for use by third-party applications");
	public static final Permission CAPTURE_SECURE_VIDEO_OUTPUT = new Permission("android.permission.CAPTURE_SECURE_VIDEO_OUTPUT", 19, false, "Allows an application to capture secure video output. Not for use by third-party applications.");
	public static final Permission CHANGE_COMPONENT_ENABLED_STATE = new Permission("android.permission.CHANGE_COMPONENT_ENABLED_STATE", 1, false, "Allows an application to change whether an application component (other than its own) is enabled or not.");
	public static final Permission CHANGE_CONFIGURATION = new Permission("android.permission.CHANGE_CONFIGURATION", 1, false, "Allows an application to modify the current configuration, such as locale.");
	public static final Permission CHANGE_NETWORK_STATE = new Permission("android.permission.CHANGE_NETWORK_STATE", 1, false, "Allows applications to change network connectivity state");
	public static final Permission CHANGE_WIFI_MULTICAST_STATE = new Permission("android.permission.CHANGE_WIFI_MULTICAST_STATE", 4, false, "Allows applications to enter Wi-Fi Multicast mode");
	public static final Permission CHANGE_WIFI_STATE = new Permission("android.permission.CHANGE_WIFI_STATE", 1, false, "Allows applications to change Wi-Fi connectivity state");
	public static final Permission CLEAR_APP_CACHE = new Permission("android.permission.CLEAR_APP_CACHE", 1, false, "Allows an application to clear the caches of all installed applications on the device.");
	public static final Permission CLEAR_APP_USER_DATA = new Permission("android.permission.CLEAR_APP_USER_DATA", 1, false, "Allows an application to clear user data");
	public static final Permission CONTROL_LOCATION_UPDATES = new Permission("android.permission.CONTROL_LOCATION_UPDATES", 1, false, "Allows enabling/disabling location update notifications from the radio. Not for use by normal applications.");
	public static final Permission DELETE_CACHE_FILES = new Permission("android.permission.DELETE_CACHE_FILES", 1, false, "Allows an application to delete cache files.");
	public static final Permission DELETE_PACKAGES = new Permission("android.permission.DELETE_PACKAGES", 1, false, "Allows an application to delete packages.");
	public static final Permission DEVICE_POWER = new Permission("android.permission.DEVICE_POWER", 1, false, "Allows low-level access to power management");
	public static final Permission DIAGNOSTIC = new Permission("android.permission.DIAGNOSTIC", 1, false, "Allows applications to RW to diagnostic resources.");
	public static final Permission DISABLE_KEYGUARD = new Permission("android.permission.DISABLE_KEYGUARD", 1, false, "Allows applications to disable the keyguard");
	public static final Permission DUMP = new Permission("android.permission.DUMP", 1, false, "Allows an application to retrieve state dump information from system services.");
	public static final Permission EXPAND_STATUS_BAR = new Permission("android.permission.EXPAND_STATUS_BAR", 1, false, "Allows an application to expand or collapse the status bar.");
	public static final Permission FACTORY_TEST = new Permission("android.permission.FACTORY_TEST", 1, false, "Run as a manufacturer test application, running as the root user. Only available when the device is running in manufacturer test mode.");
	public static final Permission FLASHLIGHT = new Permission("android.permission.FLASHLIGHT", 1, false, "Allows access to the flashlight");
	public static final Permission FORCE_BACK = new Permission("android.permission.FORCE_BACK", 1, false, "Allows an application to force a BACK operation on whatever is the top activity.");
	public static final Permission FORCE_STOP_PACKAGES = new Permission("android.permission.FORCE_STOP_PACKAGES", 1, false, "Have the system perform a force stop of everything associated with the given application package.");
	public static final Permission GET_ACCOUNTS = new Permission("android.permission.GET_ACCOUNTS", 1, false, "Allows access to the list of accounts in the Accounts Service");
	public static final Permission GET_PACKAGE_SIZE = new Permission("android.permission.GET_PACKAGE_SIZE", 1, false, "Allows an application to find out the space used by any package.");
	public static final Permission GET_TASKS = new Permission("android.permission.GET_TASKS", 1, false, "Allows an application to get information about the currently or recently running tasks.");
	public static final Permission GET_TOP_ACTIVITY_INFO = new Permission("android.permission.GET_TOP_ACTIVITY_INFO", 18, false, "Allows an application to retrieve private information about the current top activity, such as any assist context it can provide. Not for use by third-party applications.");
	public static final Permission GLOBAL_SEARCH = new Permission("android.permission.GLOBAL_SEARCH", 4, false, "This permission can be used on content providers to allow the global search system to access their data. Typically it used when the provider has some permissions protecting it (which global search would not be expected to hold); and added as a read-only permission to the path in the provider where global search queries are performed. This permission can not be held by regular applications, it is used by applications to protect themselves from everyone else besides global search.");
	public static final Permission HARDWARE_TEST = new Permission("android.permission.HARDWARE_TEST", 1, false, "Allows access to hardware peripherals. Intended only for hardware testing.");
	public static final Permission INJECT_EVENTS = new Permission("android.permission.INJECT_EVENTS", 1, false, "Allows an application to inject user events (keys, touch, trackball) into the event stream and deliver them to ANY window. Without this permission, you can only deliver events to windows in your own process. Very few applications should need to use this permission.");
	public static final Permission INSTALL_LOCATION_PROVIDER = new Permission("android.permission.INSTALL_LOCATION_PROVIDER", 4, false, "Allows an application to install a location provider into the Location Manager");
	public static final Permission INSTALL_PACKAGES = new Permission("android.permission.INSTALL_PACKAGES", 1, false, "Allows an application to install packages.");
	public static final Permission INSTALL_SHORTCUT = new Permission("android.permission.INSTALL_SHORTCUT", 19, false, "Allows an application to install a shortcut in Launcher.");
	public static final Permission INSTALL_INTERNAL_SYSTEM_WINDOW = new Permission("android.permission.INSTALL_INTERNAL_SYSTEM_WINDOW", 1, false, "Allows an application to open windows that are for use by parts of the system user interface. Not for use by third-party applications.");
	public static final Permission INTERNET = new Permission("android.permission.INTERNET", 1, false, "Allows applications to open network sockets.");
	public static final Permission KILL_BACKGROUND_PROCESSES = new Permission("android.permission.KILL_BACKGROUND_PROCESSES", 8, false, "Allows an application to call public static final Permission killBackgroundProcesses = new Permission(String).");
	public static final Permission LOCATION_HARDWARE = new Permission("android.permission.LOCATION_HARDWARE", 18, false, "Allows an application to use location features in hardware, such as the geofencing api. Not for use by third-party applications.");
	public static final Permission MANAGE_ACCOUNTS = new Permission("android.permission.MANAGE_ACCOUNTS", 5, false, "Allows an application to manage the list of accounts in the AccountManager");
	public static final Permission MANAGE_APP_TOKENS = new Permission("android.permission.MANAGE_APP_TOKENS", 1, false, "Allows an application to manage (create, destroy, Z-order) application tokens in the window manager. Not for use by third-party applications.");
	public static final Permission MANAGE_DOCUMENTS = new Permission("android.permission.MANAGE_DOCUMENTS", 19, false, "Allows an application to manage access to documents, usually as part of a document picker.");
	public static final Permission MASTER_CLEAR = new Permission("android.permission.MASTER_CLEAR", 1, false, "Not for use by third-party applications.");
	public static final Permission MEDIA_CONTENT_CONTROL = new Permission("android.permission.MEDIA_CONTENT_CONTROL", 19, false, "Allows an application to know what content is playing and control its playback. Not for use by third-party applications due to privacy of media consumption)");
	public static final Permission MODIFY_AUDIO_SETTINGS = new Permission("android.permission.MODIFY_AUDIO_SETTINGS", 1, false, "Allows an application to modify global audio settings");
	public static final Permission MODIFY_PHONE_STATE = new Permission("android.permission.MODIFY_PHONE_STATE", 1, false, "Allows modification of the telephony state - power on, mmi, etc. Does not include placing calls.");
	public static final Permission MOUNT_FORMAT_FILESYSTEMS = new Permission("android.permission.MOUNT_FORMAT_FILESYSTEMS", 3, false, "Allows formatting file systems for removable storage.");
	public static final Permission MOUNT_UNMOUNT_FILESYSTEMS = new Permission("android.permission.MOUNT_UNMOUNT_FILESYSTEMS", 1, false, "Allows mounting and unmounting file systems for removable storage.");
	public static final Permission NFC = new Permission("android.permission.NFC", 9, false, "Allows applications to perform I/O operations over NFC");
	public static final Permission NONE = new Permission("android.permission.NONE", 0, false, "Does not require a special permission");
	public static final Permission PERSISTENT_ACTIVITY = new Permission("android.permission.PERSISTENT_ACTIVITY", 1, true, "This constant was deprecated in API level 9. This functionality will be removed in the future, please do not use. Allow an application to make its activities persistent. ");
	public static final Permission PROCESS_OUTGOING_CALLS = new Permission("android.permission.PROCESS_OUTGOING_CALLS", 1, false, "Allows an application to monitor, modify, or abort outgoing calls.");
	public static final Permission READ_CALENDAR = new Permission("android.permission.READ_CALENDAR", 1, false, "Allows an application to read the user's calendar data.");
	public static final Permission READ_CALL_LOG = new Permission("android.permission.READ_CALL_LOG", 16, false, "Allows an application to read the user's call log. Note: If your app uses the READ_CONTACTS permission and both your minSdkVersion and targetSdkVersion values are set to 15 or lower, the system implicitly grants your app this permission. If you don't need this permission, be sure your targetSdkVersion is 16 or higher.");
	public static final Permission READ_CONTACTS = new Permission("android.permission.READ_CONTACTS", 1, false, "Allows an application to read the user's contacts data.");
	public static final Permission READ_EXTERNAL_STORAGE = new Permission("android.permission.READ_EXTERNAL_STORAGE", 16, false, "Allows an application to read from external storage. Any app that declares the WRITE_EXTERNAL_STORAGE permission is implicitly granted this permission. Currently, this permission is not enforced and all apps still have access to read from external storage without this permission. That will change in a future release");
	public static final Permission READ_FRAME_BUFFER = new Permission("android.permission.READ_FRAME_BUFFER", 1, false, "Allows an application to take screen shots and more generally get access to the frame buffer data");
	public static final Permission READ_HISTORY_BOOKMARKS = new Permission("com.android.browser.permission.READ_HISTORY_BOOKMARKS", 4, false, "Allows an application to read (but not write) the user's browsing history and bookmarks.");
	public static final Permission READ_INPUT_STATE = new Permission("android.permission.READ_INPUT_STATE", 1, false, "This constant was deprecated in API level 16. The API that used this permission has been removed. Allows an application to retrieve the current state of keys and switches. This is only for use by the system.");
	public static final Permission READ_LOGS = new Permission("android.permission.READ_LOGS", 1, false, "Allows an application to read the low-level system log files. Log entries can contain the user's private information, which is why this permission is not available to normal apps.");
	public static final Permission READ_PHONE_STATE = new Permission("android.permission.READ_PHONE_STATE", 1, false, "Allows read only access to phone state.");
	public static final Permission READ_PROFILE = new Permission("android.permission.READ_PROFILE", 14, false, "Allows an application to read the user's personal profile data.");
	public static final Permission READ_SOCIAL_STREAM = new Permission("android.permission.READ_SOCIAL_STREAM", 15, false, "Allows an application to read from the user's social stream.");
	public static final Permission READ_SMS = new Permission("android.permission.READ_SMS", 1, false, "Allows an application to read SMS messages.");
	public static final Permission READ_SYNC_SETTINGS = new Permission("android.permission.READ_SYNC_SETTINGS", 1, false, "Allows applications to read the sync settings");
	public static final Permission READ_SYNC_STATS = new Permission("android.permission.READ_SYNC_STATS", 1, false, "Allows applications to read the sync stats");
	public static final Permission READ_USER_DICTIONARY = new Permission("android.permission.READ_USER_DICTIONARY", 16, false, "Allows an application to read the user dictionary. This should really only be required by an IME, or a dictionary editor like the Settings app. ");
	public static final Permission REBOOT = new Permission("android.permission.REBOOT", 1, false, "Required to be able to reboot the device.");
	public static final Permission RECEIVE_BOOT_COMPLETED = new Permission("android.permission.RECEIVE_BOOT_COMPLETED", 1, false, "Allows an application to receive the ACTION_BOOT_COMPLETED that is broadcast after the system finishes booting. If you don't request this permission, you will not receive the broadcast at that time. Though holding this permission does not have any security implications, it can have a negative impact on the user experience by increasing the amount of time it takes the system to start and allowing applications to have themselves running without the user being aware of them. As such, you must explicitly declare your use of this facility to make that visible to the user.");
	public static final Permission RECEIVE_MMS = new Permission("android.permission.RECEIVE_MMS", 1, false, "Allows an application to monitor incoming MMS messages, to record or perform processing on them.");
	public static final Permission RECEIVE_SMS = new Permission("android.permission.RECEIVE_SMS", 1, false, "Allows an application to monitor incoming SMS messages, to record or perform processing on them.");
	public static final Permission RECEIVE_WAP_PUSH = new Permission("android.permission.RECEIVE_WAP_PUSH", 1, false, "Allows an application to monitor incoming WAP push messages.");
	public static final Permission RECORD_AUDIO = new Permission("android.permission.RECORD_AUDIO", 1, false, "Allows an application to record audio");
	public static final Permission REORDER_TASKS = new Permission("android.permission.REORDER_TASKS", 1, false, "Allows an application to change the Z-order of tasks");
	public static final Permission RESTART_PACKAGES = new Permission("android.permission.RESTART_PACKAGES", 1, false, "This constant was deprecated in API level 8. The public static final Permission restartPackage = new Permission(String) API is no longer supported. ");
	public static final Permission SEND_RESPOND_VIA_MESSAGE = new Permission("android.permission.SEND_RESPOND_VIA_MESSAGE", 18, false, "Allows an application (Phone) to send a request to other applications to handle the respond-via-message action during incoming calls. Not for use by third-party applications. ");
	public static final Permission SEND_SMS = new Permission("android.permission.SEND_SMS", 1, false, "Allows an application to send SMS messages.");
	public static final Permission SET_ACTIVITY_WATCHER = new Permission("android.permission.SET_ACTIVITY_WATCHER", 1, false, "Allows an application to watch and control how activities are started globally in the system. Only use is for debugging (usually the monkey command).");
	public static final Permission SET_ALARM = new Permission("com.android.alarm.permission.SET_ALARM", 9, false, "Allows an application to broadcast an Intent to set an alarm for the user.");
	public static final Permission SET_ALWAYS_FINISH = new Permission("android.permission.SET_ALWAYS_FINISH", 1, false, "Allows an application to control whether activities are immediately finished when put in the background.");
	public static final Permission SET_ANIMATION_SCALE = new Permission("android.permission.SET_ANIMATION_SCALE", 1, false, "Modify the global animation scaling factor.");
	public static final Permission SET_DEBUG_APP = new Permission("android.permission.SET_DEBUG_APP", 1, false, "Configure an application for debugging.");
	public static final Permission SET_ORIENTATION = new Permission("android.permission.SET_ORIENTATION", 1, false, "Allows low-level access to setting the orientation (actually rotation) of the screen. Not for use by normal applications.");
	public static final Permission SET_POINTER_SPEED = new Permission("android.permission.SET_POINTER_SPEED", 13, false, "Allows low-level access to setting the pointer speed. Not for use by normal applications.");
	public static final Permission SET_PREFERRED_APPLICATIONS = new Permission("android.permission.SET_PREFERRED_APPLICATIONS", 1, true, "This constant was deprecated in API level 7. No longer useful, see public static final Permission addPackageToPreferred = new Permission(String) for details. ");
	public static final Permission SET_PROCESS_LIMIT = new Permission("android.permission.SET_PROCESS_LIMIT", 1, false, "Allows an application to set the maximum number of (not needed) application processes that can be running.");
	public static final Permission SET_TIME = new Permission("android.permission.SET_TIME", 8, false, "Allows applications to set the system time.");
	public static final Permission SET_TIME_ZONE = new Permission("android.permission.SET_TIME_ZONE", 1, false, "Allows applications to set the system time zone.");
	public static final Permission SET_WALLPAPER = new Permission("android.permission.SET_WALLPAPER", 1, false, "Allows applications to set the wallpaper.");
	public static final Permission SET_WALLPAPER_HINTS = new Permission("android.permission.SET_WALLPAPER_HINTS", 1, false, "Allows applications to set the wallpaper hints");
	public static final Permission SIGNAL_PERSISTENT_PROCESSES = new Permission("android.permission.SIGNAL_PERSISTENT_PROCESSES", 1, false, "Allow an application to request that a signal be sent to all persistent processes");
	public static final Permission STATUS_BAR = new Permission("android.permission.STATUS_BAR", 1, false, "Allows an application to open, close, or disable the status bar and its icons.");
	public static final Permission SUBSCRIBED_FEEDS_READ = new Permission("android.permission.SUBSCRIBED_FEEDS_READ", 1, false, "Allows an application to allow access the subscribed feeds ContentProvider.");
	public static final Permission SUBSCRIBED_FEEDS_WRITE = new Permission("android.permission.SUBSCRIBED_FEEDS_WRITE", 1, false, "(description not provided by Google)");
	public static final Permission SYSTEM_ALERT_WINDOW = new Permission("android.permission.SYSTEM_ALERT_WINDOW", 1, false, "Allows an application to open windows using the type TYPE_SYSTEM_ALERT, shown on top of all other applications. Very few applications should use this permission, these windows are intended for system-level interaction with the user.");
	public static final Permission TRANSMIT_IR = new Permission("android.permission.TRANSMIT_IR", 19, false, "Allows using the device's IR transmitter, if available.");
	public static final Permission UNINSTALL_SHORTCUT = new Permission("android.permission.UNINSTALL_SHORTCUT", 1, false, "Allows an application to uninstall a shortcut in Launcher.");
	public static final Permission UPDATE_DEVICE_STATS = new Permission("android.permission.UPDATE_DEVICE_STATS", 3, false, "Allows an application to update device statistics. Not for use by third party apps.");
	public static final Permission USE_CREDENTIALS = new Permission("android.permission.USE_CREDENTIALS", 5, false, "Allows an application to request authtokens from the AccountManager.");
	public static final Permission USE_SIP = new Permission("android.permission.USE_SIP", 9, false, "Allows an application to use SIP service.");
	public static final Permission VIBRATE = new Permission("android.permission.VIBRATE", 1, false, "Allows access to the vibrator.");
	public static final Permission WAKE_LOCK = new Permission("android.permission.WAKE_LOCK", 1, false, "Allows using PowerManager WakeLocks to keep processor from sleeping or screen from dimming.");
	public static final Permission WRITE_APN_SETTINGS = new Permission("android.permission.WRITE_APN_SETTINGS", 1, false, "Allows applications to write the apn settings");
	public static final Permission WRITE_CALENDAR = new Permission("android.permission.WRITE_CALENDAR", 1, false, "Allows an application to write (but not read) the user's calendar data.");
	public static final Permission WRITE_CALL_LOG = new Permission("android.permission.WRITE_CALL_LOG", 16, false, "Allows an application to write (but not read) the user's contacts data. Note: If your app uses the WRITE_CONTACTS permission and both your minSdkVersion and targetSdkVersion values are set to 15 or lower, the system implicitly grants your app this permission.");
	public static final Permission WRITE_CONTACTS = new Permission("android.permission.WRITE_CONTACTS", 1, false, "Allows an application to write (but not read) the user's contacts data.");
	public static final Permission WRITE_EXTERNAL_STORAGE = new Permission("android.permission.WRITE_EXTERNAL_STORAGE", 4, false, "Allows an application to write to external storage.");
	public static final Permission WRITE_GSERVICES = new Permission("android.permission.WRITE_GSERVICES", 1, false, "Allows an application to modify the Google service map.");
	public static final Permission WRITE_HISTORY_BOOKMARKS = new Permission("com.android.browser.permission.WRITE_HISTORY_BOOKMARKS", 4, false, "Allows an application to write (but not read) the user's browsing history and bookmarks.");
	public static final Permission WRITE_PROFILE = new Permission("android.permission.WRITE_PROFILE", 14, false, "Allows an application to write (but not read) the user's personal profile data.");
	public static final Permission WRITE_SECURE_SETTINGS = new Permission("android.permission.WRITE_SECURE_SETTINGS", 3, false, "Allows an application to read or write the secure system settings.");
	public static final Permission WRITE_SETTINGS = new Permission("android.permission.WRITE_SETTINGS", 1, false, "Allows an application to read or write the system settings.");
	public static final Permission WRITE_SMS = new Permission("android.permission.WRITE_SMS", 1, false, "Allows an application to write SMS messages.");
	public static final Permission WRITE_SOCIAL_STREAM = new Permission("android.permission.WRITE_SOCIAL_STREAM", 16, false, "Allows an application to write (but not read) the user's social stream data. ");
	public static final Permission WRITE_SYNC_SETTINGS = new Permission("android.permission.WRITE_SYNC_SETTINGS", 1, false, "Allows applications to write the sync settings");
	public static final Permission WRITE_USER_DICTIONARY = new Permission("android.permission.WRITE_USER_DICTIONARY", 16, false, "Allows an application to write to the user dictionary. ");

	// special permissions
	public static final Permission ACCESS_WIMAX_STATE = new Permission("android.permission.ACCESS_WIMAX_STATE", 0, false, "Unknown origin, required to control 4G state");
	public static final Permission ASEC_ACCESS = new Permission("andorid.permission.ASEC_ACCESS", 0, false, "Unknown origin, likely from an internal method missidentified by Berkley");
	public static final Permission PACKAGE_USAGE_STATS = new Permission("andorid.permission.PACKAGE_USAGE_STATS", 0, false, "Unknown origin, likely from an internal method missidentified by Berkley");
	public static final Permission SET_WALLPAPER_COMPONENT = new Permission("andorid.permission.SET_WALLPAPER_COMPONENT", 0, false, "Unknown origin, likely from an internal method missidentified by Berkley");
	public static final Permission SHUTDOWN = new Permission("android.permission.SHUTDOWN", 0, false, "Unknown origin, likely from an internal method missidentified by Berkley");
	public static final Permission ACCESS_DOWNLOAD_MANAGER = new Permission("android.permission.ACCESS_DOWNLOAD_MANAGER", 1, false, "System permission to allow access to downloads.");
	public static final Permission INTERNAL_SYSTEM_WINDOW = new Permission("android.permission.INTERNAL_SYSTEM_WINDOW", 1, false, "Allows an application to open windows that are for use by parts of the system user interface. Not for use by third party apps.");
	
	// undocumented permissions
	public static final Permission ACCESS_ALL_EXTERNAL_STORAGE = new Permission("android.permission.ACCESS_ALL_EXTERNAL_STORAGE", -1, false, "This permission is undocumented");
	public static final Permission CAMERA_DISABLE_TRANSMIT_LED = new Permission("android.permission.CAMERA_DISABLE_TRANSMIT_LED", -1, false, "This permission is undocumented");
	public static final Permission CHANGE_WIMAX_STATE = new Permission("android.permission.CHANGE_WIMAX_STATE", -1, false, "This permission is undocumented");
	public static final Permission CONNECTIVITY_INTERNAL = new Permission("android.permission.CONNECTIVITY_INTERNAL", -1, false, "This permission is undocumented");
	public static final Permission RECEIVE_DATA_ACTIVITY_CHANGE = new Permission("android.permission.RECEIVE_DATA_ACTIVITY_CHANGE", -1, false, "This permission is undocumented");
	public static final Permission LOOP_RADIO = new Permission("android.permission.LOOP_RADIO", -1, false, "This permission is undocumented");
	public static final Permission REMOVE_TASKS = new Permission("android.permission.REMOVE_TASKS", -1, false, "This permission is undocumented");
	public static final Permission MANAGE_ACTIVITY_STACKS = new Permission("android.permission.MANAGE_ACTIVITY_STACKS", -1, false, "This permission is undocumented");
	public static final Permission READ_PRECISE_PHONE_STATE = new Permission("android.permission.READ_PRECISE_PHONE_STATE", -1, false, "This permission is undocumented");
	public static final Permission READ_PRIVILEGED_PHONE_STATE = new Permission("android.permission.READ_PRIVILEGED_PHONE_STATE", -1, false, "This permission is undocumented");
	public static final Permission BIND_CALL_SERVICE = new Permission("android.permission.BIND_CALL_SERVICE", -1, false, "This permission is undocumented");
	public static final Permission BLUETOOTH_STACK = new Permission("android.permission.BLUETOOTH_STACK", -1, false, "This permission is undocumented");
	public static final Permission NET_ADMIN = new Permission("android.permission.NET_ADMIN", -1, false, "This permission is undocumented");
	public static final Permission REMOTE_AUDIO_PLAYBACK = new Permission("android.permission.REMOTE_AUDIO_PLAYBACK", -1, false, "This permission is undocumented");
	public static final Permission INTERACT_ACROSS_USERS = new Permission("android.permission.INTERACT_ACROSS_USERS", -1, false, "This permission is undocumented");
	public static final Permission INTERACT_ACROSS_USERS_FULL = new Permission("android.permission.INTERACT_ACROSS_USERS_FULL", -1, false, "This permission is undocumented");
	public static final Permission MANAGE_USERS = new Permission("android.permission.MANAGE_USERS", -1, false, "This permission is undocumented");
	public static final Permission GET_DETAILED_TASKS = new Permission("android.permission.GET_DETAILED_TASKS", -1, false, "This permission is undocumented");
	public static final Permission START_ANY_ACTIVITY = new Permission("android.permission.START_ANY_ACTIVITY", -1, false, "This permission is undocumented");
	public static final Permission SET_SCREEN_COMPATIBILITY = new Permission("android.permission.SET_SCREEN_COMPATIBILITY", -1, false, "This permission is undocumented");
	public static final Permission ASEC_CREATE = new Permission("android.permission.ASEC_CREATE", -1, false, "This permission is undocumented");
	public static final Permission ASEC_DESTROY = new Permission("android.permission.ASEC_DESTROY", -1, false, "This permission is undocumented");
	public static final Permission ASEC_MOUNT_UNMOUNT = new Permission("android.permission.ASEC_MOUNT_UNMOUNT", -1, false, "This permission is undocumented");
	public static final Permission ASEC_RENAME = new Permission("android.permission.ASEC_RENAME", -1, false, "This permission is undocumented");
	public static final Permission GET_APP_OPS_STATS = new Permission("android.permission.GET_APP_OPS_STATS", -1, false, "This permission is undocumented");
	public static final Permission NET_TUNNELING = new Permission("android.permission.NET_TUNNELING", -1, false, "This permission is undocumented");
	public static final Permission MODIFY_APPWIDGET_BIND_PERMISSIONS = new Permission("android.permission.MODIFY_APPWIDGET_BIND_PERMISSIONS", -1, false, "This permission is undocumented");
	public static final Permission CHANGE_BACKGROUND_DATA_SETTING = new Permission("android.permission.CHANGE_BACKGROUND_DATA_SETTING", -1, false, "This permission is undocumented");
	public static final Permission GLOBAL_SEARCH_CONTROL = new Permission("android.permission.GLOBAL_SEARCH_CONTROL", -1, false, "This permission is undocumented");
	public static final Permission READ_DREAM_STATE = new Permission("android.permission.READ_DREAM_STATE", -1, false, "This permission is undocumented");
	public static final Permission WRITE_DREAM_STATE = new Permission("android.permission.WRITE_DREAM_STATE", -1, false, "This permission is undocumented");
	public static final Permission BLUETOOTH_PRIVILEGED = new Permission("android.permission.BLUETOOTH_PRIVILEGED", -1, false, "This permission is undocumented");
	public static final Permission RECEIVE_EMERGENCY_BROADCAST = new Permission("android.permission.RECEIVE_EMERGENCY_BROADCAST", -1, false, "This permission is undocumented");
	public static final Permission READ_CELL_BROADCASTS = new Permission("android.permission.READ_CELL_BROADCASTS", -1, false, "This permission is undocumented");
	public static final Permission WRITE_MEDIA_STORAGE = new Permission("android.permission.WRITE_MEDIA_STORAGE", -1, false, "This permission is undocumented");
	public static final Permission BIND_DIRECTORY_SEARCH = new Permission("android.permission.BIND_DIRECTORY_SEARCH", -1, false, "This permission is undocumented");
	public static final Permission RETRIEVE_WINDOW_CONTENT = new Permission("android.permission.RETRIEVE_WINDOW_CONTENT", -1, false, "This permission is undocumented");
	public static final Permission BIND_KEYGUARD_APPWIDGET = new Permission("android.permission.BIND_KEYGUARD_APPWIDGET", -1, false, "This permission is undocumented");
	public static final Permission MANAGE_USB = new Permission("android.permission.MANAGE_USB", -1, false, "This permission is undocumented");
	public static final Permission ACCESS_MTP = new Permission("android.permission.ACCESS_MTP", -1, false, "This permission is undocumented");
	public static final Permission SERIAL_PORT = new Permission("android.permission.SERIAL_PORT", -1, false, "This permission is undocumented");
	public static final Permission CONFIGURE_WIFI_DISPLAY = new Permission("android.permission.CONFIGURE_WIFI_DISPLAY", -1, false, "This permission is undocumented");
	public static final Permission MOVE_PACKAGE = new Permission("android.permission.MOVE_PACKAGE", -1, false, "This permission is undocumented");
	public static final Permission CRYPT_KEEPER = new Permission("android.permission.CRYPT_KEEPER", -1, false, "This permission is undocumented");
	public static final Permission CONTROL_WIFI_DISPLAY = new Permission("android.permission.CONTROL_WIFI_DISPLAY", -1, false, "This permission is undocumented");
	public static final Permission ACCESS_CACHE_FILESYSTEM = new Permission("android.permission.ACCESS_CACHE_FILESYSTEM", -1, false, "This permission is undocumented");
	public static final Permission FILTER_EVENTS = new Permission("android.permission.FILTER_EVENTS", -1, false, "This permission is undocumented");
	public static final Permission STOP_APP_SWITCHES = new Permission("android.permission.STOP_APP_SWITCHES", -1, false, "This permission is undocumented");
	public static final Permission SET_KEYBOARD_LAYOUT = new Permission("android.permission.SET_KEYBOARD_LAYOUT", -1, false, "This permission is undocumented");
	public static final Permission RETRIEVE_WINDOW_INFO = new Permission("android.permission.RETRIEVE_WINDOW_INFO", -1, false, "This permission is undocumented");
	public static final Permission BIND_PACKAGE_VERIFIER = new Permission("android.permission.BIND_PACKAGE_VERIFIER", -1, false, "This permission is undocumented");
	public static final Permission CAPTURE_VIDEO_OUTPUT = new Permission("android.permission.CAPTURE_VIDEO_OUTPUT", -1, false, "This permission is undocumented");
	public static final Permission ACCESS_NETWORK_CONDITIONS = new Permission("android.permission.ACCESS_NETWORK_CONDITIONS", -1, false, "This permission is undocumented");
	public static final Permission ACCESS_KEYGUARD_SECURE_STORAGE = new Permission("android.permission.ACCESS_KEYGUARD_SECURE_STORAGE", -1, false, "This permission is undocumented");
	public static final Permission INVOKE_CARRIER_SETUP = new Permission("android.permission.INVOKE_CARRIER_SETUP", -1, false, "This permission is undocumented");
	public static final Permission COPY_PROTECTED_DATA = new Permission("android.permission.COPY_PROTECTED_DATA", -1, false, "This permission is undocumented");
	public static final Permission ACCESS_NOTIFICATIONS = new Permission("android.permission.ACCESS_NOTIFICATIONS", -1, false, "This permission is undocumented");
	public static final Permission MODIFY_NETWORK_ACCOUNTING = new Permission("android.permission.MODIFY_NETWORK_ACCOUNTING", -1, false, "This permission is undocumented");
	public static final Permission READ_NETWORK_USAGE_HISTORY = new Permission("android.permission.READ_NETWORK_USAGE_HISTORY", -1, false, "This permission is undocumented");
	public static final Permission STATUS_BAR_SERVICE = new Permission("android.permission.STATUS_BAR_SERVICE", -1, false, "This permission is undocumented");
	public static final Permission UPDATE_APP_OPS_STATS = new Permission("android.permission.UPDATE_APP_OPS_STATS", -1, false, "This permission is undocumented");
	public static final Permission MANAGE_DEVICE_ADMINS = new Permission("android.permission.MANAGE_DEVICE_ADMINS", -1, false, "This permission is undocumented");
	public static final Permission PERFORM_CDMA_PROVISIONING = new Permission("android.permission.PERFORM_CDMA_PROVISIONING", -1, false, "This permission is undocumented");
	public static final Permission CAPTURE_AUDIO_HOTWORD = new Permission("android.permission.CAPTURE_AUDIO_HOTWORD", -1, false, "This permission is undocumented");
	public static final Permission CONFIRM_FULL_BACKUP = new Permission("android.permission.CONFIRM_FULL_BACKUP", -1, false, "This permission is undocumented");
	public static final Permission CONTROL_KEYGUARD = new Permission("android.permission.CONTROL_KEYGUARD", -1, false, "This permission is undocumented");
	public static final Permission PACKAGE_VERIFICATION_AGENT = new Permission("android.permission.PACKAGE_VERIFICATION_AGENT", -1, false, "This permission is undocumented");
	public static final Permission C2D_MESSAGE = new Permission("android.permission.C2D_MESSAGE", -1, false, "This permission is undocumented");
	public static final Permission BIND_PRINT_SPOOLER_SERVICE = new Permission("android.permission.BIND_PRINT_SPOOLER_SERVICE", -1, false, "This permission is undocumented");
	public static final Permission MANAGE_CA_CERTIFICATES = new Permission("android.permission.MANAGE_CA_CERTIFICATES", -1, false, "This permission is undocumented");
	public static final Permission BIND_REMOTE_DISPLAY = new Permission("android.permission.BIND_REMOTE_DISPLAY", -1, false, "This permission is undocumented");
	public static final Permission MANAGE_NETWORK_POLICY = new Permission("android.permission.MANAGE_NETWORK_POLICY", -1, false, "This permission is undocumented");
	public static final Permission ALLOW_ANY_CODEC_FOR_PLAYBACK = new Permission("android.permission.ALLOW_ANY_CODEC_FOR_PLAYBACK", -1, false, "This permission is undocumented");
	public static final Permission MARK_NETWORK_SOCKET = new Permission("android.permission.MARK_NETWORK_SOCKET", -1, false, "This permission is undocumented");
	public static final Permission GRANT_REVOKE_PERMISSIONS = new Permission("android.permission.GRANT_REVOKE_PERMISSIONS", -1, false, "This permission is undocumented");
	public static final Permission FREEZE_SCREEN = new Permission("android.permission.FREEZE_SCREEN", -1, false, "This permission is undocumented");
	public static final Permission TEMPORARY_ENABLE_ACCESSIBILITY = new Permission("android.permission.TEMPORARY_ENABLE_ACCESSIBILITY", -1, false, "This permission is undocumented");
	public static final Permission ACCESS_CONTENT_PROVIDERS_EXTERNALLY = new Permission("android.permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY", -1, false, "This permission is undocumented");
	public static final Permission MAGNIFY_DISPLAY = new Permission("android.permission.MAGNIFY_DISPLAY", -1, false, "This permission is undocumented");
	public static final Permission UPDATE_LOCK = new Permission("android.permission.UPDATE_LOCK", -1, false, "This permission is undocumented");
	
	/**
	 * A collection of all known permissions
	 */
	public static final Collection<Permission> allPermissions = allPermissions();

	private static Collection<Permission> allPermissions() {
		Collection<Permission> allPermissions = new HashSet<Permission>();
		allPermissions.add(ACCESS_CHECKIN_PROPERTIES);
		allPermissions.add(ACCESS_COARSE_LOCATION);
		allPermissions.add(ACCESS_DRM);
		allPermissions.add(ACCESS_FINE_LOCATION);
		allPermissions.add(ACCESS_LOCATION_EXTRA_COMMANDS);
		allPermissions.add(ACCESS_MOCK_LOCATION);
		allPermissions.add(ACCESS_NETWORK_STATE);
		allPermissions.add(ACCESS_SURFACE_FLINGER);
		allPermissions.add(ACCESS_WIFI_STATE);
		allPermissions.add(ACCOUNT_MANAGER);
		allPermissions.add(ADD_VOICEMAIL);
		allPermissions.add(AUTHENTICATE_ACCOUNTS);
		allPermissions.add(BACKUP);
		allPermissions.add(BATTERY_STATS);
		allPermissions.add(BIND_ACCESSIBILITY_SERVICE);
		allPermissions.add(BIND_APPWIDGET);
		allPermissions.add(BIND_DEVICE_ADMIN);
		allPermissions.add(BIND_INPUT_METHOD);
		allPermissions.add(BIND_NFC_SERVICE);
		allPermissions.add(BIND_NOTIFICATION_LISTENER_SERVICE);
		allPermissions.add(BIND_PRINT_SERVICE);
		allPermissions.add(BIND_REMOTEVIEWS);
		allPermissions.add(BIND_TEXT_SERVICE);
		allPermissions.add(BIND_VPN_SERVICE);
		allPermissions.add(BIND_WALLPAPER);
		allPermissions.add(BLUETOOTH);
		allPermissions.add(BLUETOOTH_ADMIN);
		allPermissions.add(BRICK);
		allPermissions.add(BROADCAST_PACKAGE_REMOVED);
		allPermissions.add(BROADCAST_SMS);
		allPermissions.add(BROADCAST_STICKY);
		allPermissions.add(BROADCAST_WAP_PUSH);
		allPermissions.add(CALL_PHONE);
		allPermissions.add(CALL_PRIVILEGED);
		allPermissions.add(CAMERA);
		allPermissions.add(CAPTURE_AUDIO_OUTPUT);
		allPermissions.add(CAPTURE_SECURE_VIDEO_OUTPUT);
		allPermissions.add(CHANGE_COMPONENT_ENABLED_STATE);
		allPermissions.add(CHANGE_CONFIGURATION);
		allPermissions.add(CHANGE_NETWORK_STATE);
		allPermissions.add(CHANGE_WIFI_MULTICAST_STATE);
		allPermissions.add(CHANGE_WIFI_STATE);
		allPermissions.add(CLEAR_APP_CACHE);
		allPermissions.add(CLEAR_APP_USER_DATA);
		allPermissions.add(CONTROL_LOCATION_UPDATES);
		allPermissions.add(DELETE_CACHE_FILES);
		allPermissions.add(DELETE_PACKAGES);
		allPermissions.add(DEVICE_POWER);
		allPermissions.add(DIAGNOSTIC);
		allPermissions.add(DISABLE_KEYGUARD);
		allPermissions.add(DUMP);
		allPermissions.add(EXPAND_STATUS_BAR);
		allPermissions.add(FACTORY_TEST);
		allPermissions.add(FLASHLIGHT);
		allPermissions.add(FORCE_BACK);
		allPermissions.add(FORCE_STOP_PACKAGES);
		allPermissions.add(GET_ACCOUNTS);
		allPermissions.add(GET_PACKAGE_SIZE);
		allPermissions.add(GET_TASKS);
		allPermissions.add(GET_TOP_ACTIVITY_INFO);
		allPermissions.add(GLOBAL_SEARCH);
		allPermissions.add(HARDWARE_TEST);
		allPermissions.add(INJECT_EVENTS);
		allPermissions.add(INSTALL_LOCATION_PROVIDER);
		allPermissions.add(INSTALL_PACKAGES);
		allPermissions.add(INSTALL_SHORTCUT);
		allPermissions.add(INSTALL_INTERNAL_SYSTEM_WINDOW);
		allPermissions.add(INTERNET);
		allPermissions.add(KILL_BACKGROUND_PROCESSES);
		allPermissions.add(LOCATION_HARDWARE);
		allPermissions.add(MANAGE_ACCOUNTS);
		allPermissions.add(MANAGE_APP_TOKENS);
		allPermissions.add(MANAGE_DOCUMENTS);
		allPermissions.add(MASTER_CLEAR);
		allPermissions.add(MEDIA_CONTENT_CONTROL);
		allPermissions.add(MODIFY_AUDIO_SETTINGS);
		allPermissions.add(MODIFY_PHONE_STATE);
		allPermissions.add(MOUNT_FORMAT_FILESYSTEMS);
		allPermissions.add(MOUNT_UNMOUNT_FILESYSTEMS);
		allPermissions.add(NFC);
		allPermissions.add(NONE);
		allPermissions.add(PERSISTENT_ACTIVITY);
		allPermissions.add(PROCESS_OUTGOING_CALLS);
		allPermissions.add(READ_CALENDAR);
		allPermissions.add(READ_CALL_LOG);
		allPermissions.add(READ_CONTACTS);
		allPermissions.add(READ_EXTERNAL_STORAGE);
		allPermissions.add(READ_FRAME_BUFFER);
		allPermissions.add(READ_HISTORY_BOOKMARKS);
		allPermissions.add(READ_INPUT_STATE);
		allPermissions.add(READ_LOGS);
		allPermissions.add(READ_PHONE_STATE);
		allPermissions.add(READ_PROFILE);
		allPermissions.add(READ_SOCIAL_STREAM);
		allPermissions.add(READ_SMS);
		allPermissions.add(READ_SYNC_SETTINGS);
		allPermissions.add(READ_SYNC_STATS);
		allPermissions.add(READ_USER_DICTIONARY);
		allPermissions.add(REBOOT);
		allPermissions.add(RECEIVE_BOOT_COMPLETED);
		allPermissions.add(RECEIVE_MMS);
		allPermissions.add(RECEIVE_SMS);
		allPermissions.add(RECEIVE_WAP_PUSH);
		allPermissions.add(RECORD_AUDIO);
		allPermissions.add(REORDER_TASKS);
		allPermissions.add(RESTART_PACKAGES);
		allPermissions.add(SEND_RESPOND_VIA_MESSAGE);
		allPermissions.add(SEND_SMS);
		allPermissions.add(SET_ACTIVITY_WATCHER);
		allPermissions.add(SET_ALARM);
		allPermissions.add(SET_ALWAYS_FINISH);
		allPermissions.add(SET_ANIMATION_SCALE);
		allPermissions.add(SET_DEBUG_APP);
		allPermissions.add(SET_ORIENTATION);
		allPermissions.add(SET_POINTER_SPEED);
		allPermissions.add(SET_PREFERRED_APPLICATIONS);
		allPermissions.add(SET_PROCESS_LIMIT);
		allPermissions.add(SET_TIME);
		allPermissions.add(SET_TIME_ZONE);
		allPermissions.add(SET_WALLPAPER);
		allPermissions.add(SET_WALLPAPER_HINTS);
		allPermissions.add(SIGNAL_PERSISTENT_PROCESSES);
		allPermissions.add(STATUS_BAR);
		allPermissions.add(SUBSCRIBED_FEEDS_READ);
		allPermissions.add(SUBSCRIBED_FEEDS_WRITE);
		allPermissions.add(SYSTEM_ALERT_WINDOW);
		allPermissions.add(TRANSMIT_IR);
		allPermissions.add(UNINSTALL_SHORTCUT);
		allPermissions.add(UPDATE_DEVICE_STATS);
		allPermissions.add(USE_CREDENTIALS);
		allPermissions.add(USE_SIP);
		allPermissions.add(VIBRATE);
		allPermissions.add(WAKE_LOCK);
		allPermissions.add(WRITE_APN_SETTINGS);
		allPermissions.add(WRITE_CALENDAR);
		allPermissions.add(WRITE_CALL_LOG);
		allPermissions.add(WRITE_CONTACTS);
		allPermissions.add(WRITE_EXTERNAL_STORAGE);
		allPermissions.add(WRITE_GSERVICES);
		allPermissions.add(WRITE_HISTORY_BOOKMARKS);
		allPermissions.add(WRITE_PROFILE);
		allPermissions.add(WRITE_SECURE_SETTINGS);
		allPermissions.add(WRITE_SETTINGS);
		allPermissions.add(WRITE_SMS);
		allPermissions.add(WRITE_SOCIAL_STREAM);
		allPermissions.add(WRITE_SYNC_SETTINGS);
		allPermissions.add(WRITE_USER_DICTIONARY);
		allPermissions.add(ACCESS_WIMAX_STATE);
		allPermissions.add(ASEC_ACCESS);
		allPermissions.add(PACKAGE_USAGE_STATS);
		allPermissions.add(SET_WALLPAPER_COMPONENT);
		allPermissions.add(SHUTDOWN);
		allPermissions.add(ACCESS_DOWNLOAD_MANAGER);
		allPermissions.add(INTERNAL_SYSTEM_WINDOW);
		allPermissions.add(ACCESS_ALL_EXTERNAL_STORAGE);
		allPermissions.add(CAMERA_DISABLE_TRANSMIT_LED);
		allPermissions.add(CHANGE_WIMAX_STATE);
		allPermissions.add(CONNECTIVITY_INTERNAL);
		allPermissions.add(RECEIVE_DATA_ACTIVITY_CHANGE);
		allPermissions.add(LOOP_RADIO);
		allPermissions.add(REMOVE_TASKS);
		allPermissions.add(MANAGE_ACTIVITY_STACKS);
		allPermissions.add(READ_PRECISE_PHONE_STATE);
		allPermissions.add(READ_PRIVILEGED_PHONE_STATE);
		allPermissions.add(BIND_CALL_SERVICE);
		allPermissions.add(BLUETOOTH_STACK);
		allPermissions.add(NET_ADMIN);
		allPermissions.add(REMOTE_AUDIO_PLAYBACK);
		allPermissions.add(INTERACT_ACROSS_USERS);
		allPermissions.add(INTERACT_ACROSS_USERS_FULL);
		allPermissions.add(MANAGE_USERS);
		allPermissions.add(GET_DETAILED_TASKS);
		allPermissions.add(START_ANY_ACTIVITY);
		allPermissions.add(SET_SCREEN_COMPATIBILITY);
		allPermissions.add(ASEC_CREATE);
		allPermissions.add(ASEC_DESTROY);
		allPermissions.add(ASEC_MOUNT_UNMOUNT);
		allPermissions.add(ASEC_RENAME);
		allPermissions.add(GET_APP_OPS_STATS);
		allPermissions.add(NET_TUNNELING);
		allPermissions.add(MODIFY_APPWIDGET_BIND_PERMISSIONS);
		allPermissions.add(CHANGE_BACKGROUND_DATA_SETTING);
		allPermissions.add(GLOBAL_SEARCH_CONTROL);
		allPermissions.add(READ_DREAM_STATE);
		allPermissions.add(WRITE_DREAM_STATE);
		allPermissions.add(BLUETOOTH_PRIVILEGED);
		allPermissions.add(RECEIVE_EMERGENCY_BROADCAST);
		allPermissions.add(READ_CELL_BROADCASTS);
		allPermissions.add(WRITE_MEDIA_STORAGE);
		allPermissions.add(BIND_DIRECTORY_SEARCH);
		allPermissions.add(RETRIEVE_WINDOW_CONTENT);
		allPermissions.add(BIND_KEYGUARD_APPWIDGET);
		allPermissions.add(MANAGE_USB);
		allPermissions.add(ACCESS_MTP);
		allPermissions.add(SERIAL_PORT);
		allPermissions.add(CONFIGURE_WIFI_DISPLAY);
		allPermissions.add(MOVE_PACKAGE);
		allPermissions.add(CRYPT_KEEPER);
		allPermissions.add(CONTROL_WIFI_DISPLAY);
		allPermissions.add(ACCESS_CACHE_FILESYSTEM);
		allPermissions.add(FILTER_EVENTS);
		allPermissions.add(STOP_APP_SWITCHES);
		allPermissions.add(SET_KEYBOARD_LAYOUT);
		allPermissions.add(RETRIEVE_WINDOW_INFO);
		allPermissions.add(BIND_PACKAGE_VERIFIER);
		allPermissions.add(CAPTURE_VIDEO_OUTPUT);
		allPermissions.add(ACCESS_NETWORK_CONDITIONS);
		allPermissions.add(ACCESS_KEYGUARD_SECURE_STORAGE);
		allPermissions.add(INVOKE_CARRIER_SETUP);
		allPermissions.add(COPY_PROTECTED_DATA);
		allPermissions.add(ACCESS_NOTIFICATIONS);
		allPermissions.add(MODIFY_NETWORK_ACCOUNTING);
		allPermissions.add(READ_NETWORK_USAGE_HISTORY);
		allPermissions.add(STATUS_BAR_SERVICE);
		allPermissions.add(UPDATE_APP_OPS_STATS);
		allPermissions.add(MANAGE_DEVICE_ADMINS);
		allPermissions.add(PERFORM_CDMA_PROVISIONING);
		allPermissions.add(CAPTURE_AUDIO_HOTWORD);
		allPermissions.add(CONFIRM_FULL_BACKUP);
		allPermissions.add(CONTROL_KEYGUARD);
		allPermissions.add(PACKAGE_VERIFICATION_AGENT);
		allPermissions.add(C2D_MESSAGE);
		allPermissions.add(BIND_PRINT_SPOOLER_SERVICE);
		allPermissions.add(MANAGE_CA_CERTIFICATES);
		allPermissions.add(BIND_REMOTE_DISPLAY);
		allPermissions.add(MANAGE_NETWORK_POLICY);
		allPermissions.add(ALLOW_ANY_CODEC_FOR_PLAYBACK);
		allPermissions.add(MARK_NETWORK_SOCKET);
		allPermissions.add(GRANT_REVOKE_PERMISSIONS);
		allPermissions.add(FREEZE_SCREEN);
		allPermissions.add(TEMPORARY_ENABLE_ACCESSIBILITY);
		allPermissions.add(ACCESS_CONTENT_PROVIDERS_EXTERNALLY);
		allPermissions.add(MAGNIFY_DISPLAY);
		allPermissions.add(UPDATE_LOCK);
		return allPermissions;
	}
}

