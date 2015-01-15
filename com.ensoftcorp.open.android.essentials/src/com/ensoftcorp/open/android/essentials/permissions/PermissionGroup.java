package com.ensoftcorp.open.android.essentials.permissions;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * An convenience object that encodes Android Permission Group property values
 * @author Ben Holland, Vani Bojja
 */
public class PermissionGroup {

	public static int REFERENCE_API_VERSION = 21;
	public static String[] REFERENCE_SOURCES = { "https://developer.android.com/reference/android/Manifest.permission_group.html" };
	public static Date REFERENCE_DATE = new Date(1421257800); // 1-14-2015
	
	private String qualifiedName;
	private int addedInLevel;
	private String description;
	private String reference;
	private HashSet<Permission> permissions = new HashSet<Permission>();

	private PermissionGroup(String qualifiedName, int addedInLevel, String description, String reference) {
		this.qualifiedName = qualifiedName;
		this.addedInLevel = addedInLevel;
		this.description = description;
		this.reference = reference;
	}

	/**
	 * Returns the API version the permission group was added in
	 * @return
	 */
	public int addedInAPILevel() {
		return addedInLevel;
	}

	/**
	 * Returns the qualified permission group name
	 * @return
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * Returns an unqualified permission group name
	 * @return
	 */
	public String getSimpleName() {
		int pos = qualifiedName.lastIndexOf('.');
		return qualifiedName.substring(pos + 1);
	}
	
	/**
	 * Returns the reference information for the source of this permission group or an empty string if there is no available reference
	 * @return
	 */
	public String getReference(){
		return reference;
	}
	
	/**
	 * Returns a list of permissions associated with the permission group
	 * @return
	 */
	public HashSet<Permission> getPermissions(){
		return permissions;
	}

	/**
	 * Returns a description of the permission group
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the permission group object matching the qualified permission name or null
	 * if the permission group is not found in the known permissions list
	 * @param qualifiedName
	 * @return
	 */
	public static PermissionGroup getPermissionGroupByQualifiedName(String qualifiedName){
		for(PermissionGroup permissionGroup : allPermissionGroups){
			if(permissionGroup.getQualifiedName().equals(qualifiedName)){
				return permissionGroup;
			}
		}
		return null; // permission not found
	}
	
	/**
	 * Returns the permission group object matching the simple permission name or null
	 * if the permission group is not found in the known permissions list
	 * @param simpleName
	 * @return
	 */
	public static PermissionGroup getPermissionGroupBySimpleName(String simpleName){
		for(PermissionGroup permissionGroup : allPermissionGroups){
			if(permissionGroup.getSimpleName().equals(simpleName)){
				return permissionGroup;
			}
		}
		return null; // permission not found
	}

	// documented Android permission group objects generated from https://developer.android.com/reference/android/Manifest.permission_group.html on Wed Jan 14 15:24:59 CST 2015
	public static final PermissionGroup ACCESSIBILITY_FEATURES = new PermissionGroup("android.permission-group.ACCESSIBILITY_FEATURES", 18, "Used for permissions that allow requesting certain accessibility features.", "https://developer.android.com/reference/android/Manifest.permission_group.html#ACCESSIBILITY_FEATURES");
	public static final PermissionGroup ACCOUNTS = new PermissionGroup("android.permission-group.ACCOUNTS", 1, "Permissions for direct access to the accounts managed by the Account Manager.", "https://developer.android.com/reference/android/Manifest.permission_group.html#ACCOUNTS");
	public static final PermissionGroup AFFECTS_BATTERY = new PermissionGroup("android.permission-group.AFFECTS_BATTERY", 17, "Used for permissions that provide direct access to the hardware on the device that has an effect on battery life. This includes vibrator, flashlight, etc.", "https://developer.android.com/reference/android/Manifest.permission_group.html#AFFECTS_BATTERY");
	public static final PermissionGroup APP_INFO = new PermissionGroup("android.permission-group.APP_INFO", 17, "Group of permissions that are related to the other applications installed on the system. Examples include such as listing running apps, or killing background processes.", "https://developer.android.com/reference/android/Manifest.permission_group.html#APP_INFO");
	public static final PermissionGroup AUDIO_SETTINGS = new PermissionGroup("android.permission-group.AUDIO_SETTINGS", 17, "Used for permissions that provide direct access to speaker settings the device.", "https://developer.android.com/reference/android/Manifest.permission_group.html#AUDIO_SETTINGS");
	public static final PermissionGroup BLUETOOTH_NETWORK = new PermissionGroup("android.permission-group.BLUETOOTH_NETWORK", 17, "Used for permissions that provide access to other devices through Bluetooth.", "https://developer.android.com/reference/android/Manifest.permission_group.html#BLUETOOTH_NETWORK");
	public static final PermissionGroup BOOKMARKS = new PermissionGroup("android.permission-group.BOOKMARKS", 17, "Used for permissions that provide access to the user bookmarks and browser history.", "https://developer.android.com/reference/android/Manifest.permission_group.html#BOOKMARKS");
	public static final PermissionGroup CALENDAR = new PermissionGroup("android.permission-group.CALENDAR", 17, "Used for permissions that provide access to the device calendar to create / view events.", "https://developer.android.com/reference/android/Manifest.permission_group.html#CALENDAR");
	public static final PermissionGroup CAMERA = new PermissionGroup("android.permission-group.CAMERA", 17, "Used for permissions that are associated with accessing camera or capturing images/video from the device.", "https://developer.android.com/reference/android/Manifest.permission_group.html#CAMERA");
	public static final PermissionGroup COST_MONEY = new PermissionGroup("android.permission-group.COST_MONEY", 1, "Used for permissions that can be used to make the user spend money without their direct involvement.", "https://developer.android.com/reference/android/Manifest.permission_group.html#COST_MONEY");
	public static final PermissionGroup DEVELOPMENT_TOOLS = new PermissionGroup("android.permission-group.DEVELOPMENT_TOOLS", 1, "Group of permissions that are related to development features. These are not permissions that should appear in third-party applications; they protect APIs that are intended only to be used for development purposes.", "https://developer.android.com/reference/android/Manifest.permission_group.html#DEVELOPMENT_TOOLS");
	public static final PermissionGroup DEVICE_ALARMS = new PermissionGroup("android.permission-group.DEVICE_ALARMS", 17, "Used for permissions that provide access to device alarms.", "https://developer.android.com/reference/android/Manifest.permission_group.html#DEVICE_ALARMS");
	public static final PermissionGroup DISPLAY = new PermissionGroup("android.permission-group.DISPLAY", 17, "Group of permissions that allow manipulation of how another application displays UI to the user.", "https://developer.android.com/reference/android/Manifest.permission_group.html#DISPLAY");
	public static final PermissionGroup HARDWARE_CONTROLS = new PermissionGroup("android.permission-group.HARDWARE_CONTROLS", 1, "Used for permissions that provide direct access to the hardware on the device. This includes audio, the camera, vibrator, etc.", "https://developer.android.com/reference/android/Manifest.permission_group.html#HARDWARE_CONTROLS");
	public static final PermissionGroup LOCATION = new PermissionGroup("android.permission-group.LOCATION", 1, "Used for permissions that allow access to the user's current location.", "https://developer.android.com/reference/android/Manifest.permission_group.html#LOCATION");
	public static final PermissionGroup MESSAGES = new PermissionGroup("android.permission-group.MESSAGES", 1, "Used for permissions that allow an application to send messages on behalf of the user or intercept messages being received by the user. This is primarily intended for SMS/MMS messaging, such as receiving or reading an MMS.", "https://developer.android.com/reference/android/Manifest.permission_group.html#MESSAGES");
	public static final PermissionGroup MICROPHONE = new PermissionGroup("android.permission-group.MICROPHONE", 17, "Used for permissions that are associated with accessing microphone audio from the device. Note that phone calls also capture audio but are in a separate (more visible) permission group.", "https://developer.android.com/reference/android/Manifest.permission_group.html#MICROPHONE");
	public static final PermissionGroup NETWORK = new PermissionGroup("android.permission-group.NETWORK", 1, "Used for permissions that provide access to networking services. The main permission here is internet access, but this is also an appropriate group for accessing or modifying any network configuration or other related network operations.", "https://developer.android.com/reference/android/Manifest.permission_group.html#NETWORK");
	public static final PermissionGroup PERSONAL_INFO = new PermissionGroup("android.permission-group.PERSONAL_INFO", 1, "Used for permissions that provide access to information about the device user such as profile information. This includes both reading and writing of this data (which should generally be expressed as two distinct permissions).", "https://developer.android.com/reference/android/Manifest.permission_group.html#PERSONAL_INFO");
	public static final PermissionGroup PHONE_CALLS = new PermissionGroup("android.permission-group.PHONE_CALLS", 1, "Used for permissions that are associated with accessing and modifying telephony state: placing calls, intercepting outgoing calls, reading and modifying the phone state.", "https://developer.android.com/reference/android/Manifest.permission_group.html#PHONE_CALLS");
	public static final PermissionGroup SCREENLOCK = new PermissionGroup("android.permission-group.SCREENLOCK", 17, "Group of permissions that are related to the screenlock.", "https://developer.android.com/reference/android/Manifest.permission_group.html#SCREENLOCK");
	public static final PermissionGroup SOCIAL_INFO = new PermissionGroup("android.permission-group.SOCIAL_INFO", 17, "Used for permissions that provide access to the user's social connections, such as contacts, call logs, social stream, etc. This includes both reading and writing of this data (which should generally be expressed as two distinct permissions).", "https://developer.android.com/reference/android/Manifest.permission_group.html#SOCIAL_INFO");
	public static final PermissionGroup STATUS_BAR = new PermissionGroup("android.permission-group.STATUS_BAR", 17, "Used for permissions that change the status bar.", "https://developer.android.com/reference/android/Manifest.permission_group.html#STATUS_BAR");
	public static final PermissionGroup STORAGE = new PermissionGroup("android.permission-group.STORAGE", 4, "Group of permissions that are related to SD card access.", "https://developer.android.com/reference/android/Manifest.permission_group.html#STORAGE");
	public static final PermissionGroup SYNC_SETTINGS = new PermissionGroup("android.permission-group.SYNC_SETTINGS", 17, "Used for permissions that access the sync settings or sync related information.", "https://developer.android.com/reference/android/Manifest.permission_group.html#SYNC_SETTINGS");
	public static final PermissionGroup SYSTEM_CLOCK = new PermissionGroup("android.permission-group.SYSTEM_CLOCK", 17, "Group of permissions that are related to system clock.", "https://developer.android.com/reference/android/Manifest.permission_group.html#SYSTEM_CLOCK");
	public static final PermissionGroup SYSTEM_TOOLS = new PermissionGroup("android.permission-group.SYSTEM_TOOLS", 1, "Group of permissions that are related to system APIs. Many of these are not permissions the user will be expected to understand, and such permissions should generally be marked as \"normal\" protection level so they don't get displayed. This can also, however, be used for miscellaneous features that provide access to the operating system, such as writing the global system settings.", "https://developer.android.com/reference/android/Manifest.permission_group.html#SYSTEM_TOOLS");
	public static final PermissionGroup USER_DICTIONARY = new PermissionGroup("android.permission-group.USER_DICTIONARY", 17, "Used for permissions that provide access to the user calendar to create / view events.", "https://developer.android.com/reference/android/Manifest.permission_group.html#USER_DICTIONARY");
	public static final PermissionGroup VOICEMAIL = new PermissionGroup("android.permission-group.VOICEMAIL", 17, "Used for permissions that provide access to the user voicemail box.", "https://developer.android.com/reference/android/Manifest.permission_group.html#VOICEMAIL");
	public static final PermissionGroup WALLPAPER = new PermissionGroup("android.permission-group.WALLPAPER", 17, "Group of permissions that allow manipulation of how another application displays UI to the user.", "https://developer.android.com/reference/android/Manifest.permission_group.html#WALLPAPER");
	public static final PermissionGroup WRITE_USER_DICTIONARY = new PermissionGroup("android.permission-group.WRITE_USER_DICTIONARY", 17, "Used for permissions that provide access to the user calendar to create / view events.", "https://developer.android.com/reference/android/Manifest.permission_group.html#WRITE_USER_DICTIONARY");

	// END GENERATED CODE -----------------------
	public static final PermissionGroup UNASSIGNED = new PermissionGroup("UNASSIGNED", -1, "This permission group does not officially exist.  It contains permissions that do not belong to any permission groups.", "No reference available.");
	
	// add Permission objects to the PermissionGroup objects
	static {
		// UNASSIGNED permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		UNASSIGNED.permissions.add(Permission.PROVIDE_TRUST_AGENT);
		UNASSIGNED.permissions.add(Permission.CLEAR_APP_USER_DATA);
		UNASSIGNED.permissions.add(Permission.MANAGE_MEDIA_PROJECTION);
		UNASSIGNED.permissions.add(Permission.SHUTDOWN);
		UNASSIGNED.permissions.add(Permission.MEDIA_CONTENT_CONTROL);
		UNASSIGNED.permissions.add(Permission.BIND_INPUT_METHOD);
		UNASSIGNED.permissions.add(Permission.BIND_ACCESSIBILITY_SERVICE);
		UNASSIGNED.permissions.add(Permission.MODIFY_PARENTAL_CONTROLS);
		UNASSIGNED.permissions.add(Permission.INTERNAL_SYSTEM_WINDOW);
		UNASSIGNED.permissions.add(Permission.CAPTURE_TV_INPUT);
		UNASSIGNED.permissions.add(Permission.SERIAL_PORT);
		UNASSIGNED.permissions.add(Permission.MOVE_PACKAGE);
		UNASSIGNED.permissions.add(Permission.CONFIGURE_WIFI_DISPLAY);
		UNASSIGNED.permissions.add(Permission.LAUNCH_TRUST_AGENT_SETTINGS);
		UNASSIGNED.permissions.add(Permission.CAPTURE_AUDIO_OUTPUT);
		UNASSIGNED.permissions.add(Permission.ACCESS_CHECKIN_PROPERTIES);
		UNASSIGNED.permissions.add(Permission.CONTROL_WIFI_DISPLAY);
		UNASSIGNED.permissions.add(Permission.CRYPT_KEEPER);
		UNASSIGNED.permissions.add(Permission.READ_INPUT_STATE);
		UNASSIGNED.permissions.add(Permission.FRAME_STATS);
		UNASSIGNED.permissions.add(Permission.GET_TOP_ACTIVITY_INFO);
		UNASSIGNED.permissions.add(Permission.BIND_VOICE_INTERACTION);
		UNASSIGNED.permissions.add(Permission.DEVICE_POWER);
		UNASSIGNED.permissions.add(Permission.DELETE_PACKAGES);
		UNASSIGNED.permissions.add(Permission.ACCESS_CACHE_FILESYSTEM);
		UNASSIGNED.permissions.add(Permission.REBOOT);
		UNASSIGNED.permissions.add(Permission.FILTER_EVENTS);
		UNASSIGNED.permissions.add(Permission.STATUS_BAR);
		UNASSIGNED.permissions.add(Permission.BIND_DREAM_SERVICE);
		UNASSIGNED.permissions.add(Permission.BIND_NFC_SERVICE);
		UNASSIGNED.permissions.add(Permission.STOP_APP_SWITCHES);
		UNASSIGNED.permissions.add(Permission.BIND_VPN_SERVICE);
		UNASSIGNED.permissions.add(Permission.CONTROL_LOCATION_UPDATES);
		UNASSIGNED.permissions.add(Permission.SET_KEYBOARD_LAYOUT);
		UNASSIGNED.permissions.add(Permission.MANAGE_APP_TOKENS);
		UNASSIGNED.permissions.add(Permission.ACCESS_INPUT_FLINGER);
		UNASSIGNED.permissions.add(Permission.BIND_PACKAGE_VERIFIER);
		UNASSIGNED.permissions.add(Permission.DELETE_CACHE_FILES);
		UNASSIGNED.permissions.add(Permission.CAPTURE_VIDEO_OUTPUT);
		UNASSIGNED.permissions.add(Permission.ACCESS_NETWORK_CONDITIONS);
		UNASSIGNED.permissions.add(Permission.ACCESS_KEYGUARD_SECURE_STORAGE);
		UNASSIGNED.permissions.add(Permission.INVOKE_CARRIER_SETUP);
		UNASSIGNED.permissions.add(Permission.COPY_PROTECTED_DATA);
		UNASSIGNED.permissions.add(Permission.ACCESS_NOTIFICATIONS);
		UNASSIGNED.permissions.add(Permission.RETRIEVE_WINDOW_TOKEN);
		UNASSIGNED.permissions.add(Permission.MASTER_CLEAR);
		UNASSIGNED.permissions.add(Permission.SET_ACTIVITY_WATCHER);
		UNASSIGNED.permissions.add(Permission.BRICK);
		UNASSIGNED.permissions.add(Permission.MODIFY_NETWORK_ACCOUNTING);
		UNASSIGNED.permissions.add(Permission.READ_NETWORK_USAGE_HISTORY);
		UNASSIGNED.permissions.add(Permission.BACKUP);
		UNASSIGNED.permissions.add(Permission.REMOVE_DRM_CERTIFICATES);
		UNASSIGNED.permissions.add(Permission.SET_TIME);
		UNASSIGNED.permissions.add(Permission.STATUS_BAR_SERVICE);
		UNASSIGNED.permissions.add(Permission.NFC_HANDOVER_STATUS);
		UNASSIGNED.permissions.add(Permission.UPDATE_APP_OPS_STATS);
		UNASSIGNED.permissions.add(Permission.MODIFY_AUDIO_ROUTING);
		UNASSIGNED.permissions.add(Permission.MANAGE_DEVICE_ADMINS);
		UNASSIGNED.permissions.add(Permission.INSTALL_PACKAGES);
		UNASSIGNED.permissions.add(Permission.CAPTURE_AUDIO_HOTWORD);
		UNASSIGNED.permissions.add(Permission.PERFORM_CDMA_PROVISIONING);
		UNASSIGNED.permissions.add(Permission.BIND_NOTIFICATION_LISTENER_SERVICE);
		UNASSIGNED.permissions.add(Permission.INJECT_EVENTS);
		UNASSIGNED.permissions.add(Permission.SET_POINTER_SPEED);
		UNASSIGNED.permissions.add(Permission.READ_INSTALL_SESSIONS);
		UNASSIGNED.permissions.add(Permission.HDMI_CEC);
		UNASSIGNED.permissions.add(Permission.INSTALL_LOCATION_PROVIDER);
		UNASSIGNED.permissions.add(Permission.UPDATE_LOCK);
		UNASSIGNED.permissions.add(Permission.BROADCAST_SCORE_NETWORKS);
		UNASSIGNED.permissions.add(Permission.CONFIRM_FULL_BACKUP);
		UNASSIGNED.permissions.add(Permission.PACKAGE_USAGE_STATS);
		UNASSIGNED.permissions.add(Permission.ACCESS_SURFACE_FLINGER);
		UNASSIGNED.permissions.add(Permission.BIND_CONDITION_PROVIDER_SERVICE);
		UNASSIGNED.permissions.add(Permission.CONTROL_KEYGUARD);
		UNASSIGNED.permissions.add(Permission.CALL_PRIVILEGED);
		UNASSIGNED.permissions.add(Permission.TRUST_LISTENER);
		UNASSIGNED.permissions.add(Permission.PACKAGE_VERIFICATION_AGENT);
		UNASSIGNED.permissions.add(Permission.CHANGE_COMPONENT_ENABLED_STATE);
		UNASSIGNED.permissions.add(Permission.C2D_MESSAGE);
		UNASSIGNED.permissions.add(Permission.BIND_PRINT_SPOOLER_SERVICE);
		UNASSIGNED.permissions.add(Permission.USER_ACTIVITY);
		UNASSIGNED.permissions.add(Permission.BIND_PRINT_SERVICE);
		UNASSIGNED.permissions.add(Permission.MANAGE_CA_CERTIFICATES);
		UNASSIGNED.permissions.add(Permission.BIND_REMOTE_DISPLAY);
		UNASSIGNED.permissions.add(Permission.WRITE_GSERVICES);
		UNASSIGNED.permissions.add(Permission.MANAGE_NETWORK_POLICY);
		UNASSIGNED.permissions.add(Permission.ALLOW_ANY_CODEC_FOR_PLAYBACK);
		UNASSIGNED.permissions.add(Permission.BIND_TEXT_SERVICE);
		UNASSIGNED.permissions.add(Permission.BIND_TV_INPUT);
		UNASSIGNED.permissions.add(Permission.BIND_JOB_SERVICE);
		UNASSIGNED.permissions.add(Permission.GRANT_REVOKE_PERMISSIONS);
		UNASSIGNED.permissions.add(Permission.READ_FRAME_BUFFER);
		UNASSIGNED.permissions.add(Permission.FORCE_BACK);
		UNASSIGNED.permissions.add(Permission.UPDATE_DEVICE_STATS);
		UNASSIGNED.permissions.add(Permission.ACCESS_DRM_CERTIFICATES);
		UNASSIGNED.permissions.add(Permission.FREEZE_SCREEN);
		UNASSIGNED.permissions.add(Permission.BIND_WALLPAPER);
		UNASSIGNED.permissions.add(Permission.CAPTURE_SECURE_VIDEO_OUTPUT);
		UNASSIGNED.permissions.add(Permission.BIND_REMOTEVIEWS);
		UNASSIGNED.permissions.add(Permission.SET_ORIENTATION);
		UNASSIGNED.permissions.add(Permission.SET_INPUT_CALIBRATION);
		UNASSIGNED.permissions.add(Permission.TV_INPUT_HARDWARE);
		UNASSIGNED.permissions.add(Permission.FACTORY_TEST);
		UNASSIGNED.permissions.add(Permission.TEMPORARY_ENABLE_ACCESSIBILITY);
		UNASSIGNED.permissions.add(Permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY);
		UNASSIGNED.permissions.add(Permission.BIND_DEVICE_ADMIN);
		UNASSIGNED.permissions.add(Permission.BIND_TRUST_AGENT);
		UNASSIGNED.permissions.add(Permission.MANAGE_VOICE_KEYPHRASES);

		// android.permission-group.DEVELOPMENT_TOOLS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		DEVELOPMENT_TOOLS.permissions.add(Permission.SIGNAL_PERSISTENT_PROCESSES);
		DEVELOPMENT_TOOLS.permissions.add(Permission.DUMP);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SET_PROCESS_LIMIT);
		DEVELOPMENT_TOOLS.permissions.add(Permission.READ_LOGS);
		DEVELOPMENT_TOOLS.permissions.add(Permission.ACCESS_ALL_EXTERNAL_STORAGE);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SET_ALWAYS_FINISH);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SET_DEBUG_APP);
		DEVELOPMENT_TOOLS.permissions.add(Permission.WRITE_SECURE_SETTINGS);
		DEVELOPMENT_TOOLS.permissions.add(Permission.CHANGE_CONFIGURATION);

		// android.permission-group.AFFECTS_BATTERY permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		AFFECTS_BATTERY.permissions.add(Permission.CHANGE_WIFI_MULTICAST_STATE);
		AFFECTS_BATTERY.permissions.add(Permission.TRANSMIT_IR);
		AFFECTS_BATTERY.permissions.add(Permission.VIBRATE);
		AFFECTS_BATTERY.permissions.add(Permission.WAKE_LOCK);
		AFFECTS_BATTERY.permissions.add(Permission.FLASHLIGHT);

		// android.permission-group.STATUS_BAR permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		STATUS_BAR.permissions.add(Permission.EXPAND_STATUS_BAR);

		// android.permission-group.DISPLAY permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		DISPLAY.permissions.add(Permission.SYSTEM_ALERT_WINDOW);

		// android.permission-group.CAMERA permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		CAMERA.permissions.add(Permission.CAMERA);
		CAMERA.permissions.add(Permission.CAMERA_DISABLE_TRANSMIT_LED);

		// android.permission-group.NETWORK permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		NETWORK.permissions.add(Permission.CHANGE_WIMAX_STATE);
		NETWORK.permissions.add(Permission.NFC);
		NETWORK.permissions.add(Permission.READ_WIFI_CREDENTIAL);
		NETWORK.permissions.add(Permission.ACCESS_WIFI_STATE);
		NETWORK.permissions.add(Permission.ACCESS_WIMAX_STATE);
		NETWORK.permissions.add(Permission.INTERNET);
		NETWORK.permissions.add(Permission.CHANGE_WIFI_STATE);
		NETWORK.permissions.add(Permission.SCORE_NETWORKS);
		NETWORK.permissions.add(Permission.LOOP_RADIO);
		NETWORK.permissions.add(Permission.CONNECTIVITY_INTERNAL);
		NETWORK.permissions.add(Permission.CHANGE_NETWORK_STATE);
		NETWORK.permissions.add(Permission.RECEIVE_DATA_ACTIVITY_CHANGE);
		NETWORK.permissions.add(Permission.ACCESS_NETWORK_STATE);

		// android.permission-group.MICROPHONE permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		MICROPHONE.permissions.add(Permission.RECORD_AUDIO);

		// android.permission-group.SCREENLOCK permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		SCREENLOCK.permissions.add(Permission.DISABLE_KEYGUARD);

		// android.permission-group.AUDIO_SETTINGS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		AUDIO_SETTINGS.permissions.add(Permission.MODIFY_AUDIO_SETTINGS);

		// android.permission-group.VOICEMAIL permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		VOICEMAIL.permissions.add(Permission.ADD_VOICEMAIL);
		VOICEMAIL.permissions.add(Permission.READ_VOICEMAIL);
		VOICEMAIL.permissions.add(Permission.WRITE_VOICEMAIL);

		// android.permission-group.SYNC_SETTINGS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		SYNC_SETTINGS.permissions.add(Permission.READ_SYNC_STATS);
		SYNC_SETTINGS.permissions.add(Permission.WRITE_SYNC_SETTINGS);
		SYNC_SETTINGS.permissions.add(Permission.READ_SYNC_SETTINGS);

		// android.permission-group.SOCIAL_INFO permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		SOCIAL_INFO.permissions.add(Permission.WRITE_CALL_LOG);
		SOCIAL_INFO.permissions.add(Permission.WRITE_SOCIAL_STREAM);
		SOCIAL_INFO.permissions.add(Permission.READ_SOCIAL_STREAM);
		SOCIAL_INFO.permissions.add(Permission.READ_CALL_LOG);
		SOCIAL_INFO.permissions.add(Permission.READ_CONTACTS);
		SOCIAL_INFO.permissions.add(Permission.WRITE_CONTACTS);

		// android.permission-group.APP_INFO permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		APP_INFO.permissions.add(Permission.REORDER_TASKS);
		APP_INFO.permissions.add(Permission.RECEIVE_BOOT_COMPLETED);
		APP_INFO.permissions.add(Permission.GET_TASKS);
		APP_INFO.permissions.add(Permission.MANAGE_ACTIVITY_STACKS);
		APP_INFO.permissions.add(Permission.REMOVE_TASKS);
		APP_INFO.permissions.add(Permission.REAL_GET_TASKS);
		APP_INFO.permissions.add(Permission.PERSISTENT_ACTIVITY);
		APP_INFO.permissions.add(Permission.RESTART_PACKAGES);
		APP_INFO.permissions.add(Permission.KILL_BACKGROUND_PROCESSES);

		// android.permission-group.LOCATION permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		LOCATION.permissions.add(Permission.LOCATION_HARDWARE);
		LOCATION.permissions.add(Permission.ACCESS_FINE_LOCATION);
		LOCATION.permissions.add(Permission.ACCESS_COARSE_LOCATION);

		// android.permission-group.DEVICE_ALARMS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		DEVICE_ALARMS.permissions.add(Permission.SET_ALARM);

		// android.permission-group.PHONE_CALLS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		PHONE_CALLS.permissions.add(Permission.READ_PRECISE_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.READ_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.MODIFY_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.BIND_INCALL_SERVICE);
		PHONE_CALLS.permissions.add(Permission.PROCESS_OUTGOING_CALLS);
		PHONE_CALLS.permissions.add(Permission.CONTROL_INCALL_EXPERIENCE);
		PHONE_CALLS.permissions.add(Permission.BIND_CONNECTION_SERVICE);
		PHONE_CALLS.permissions.add(Permission.CALL_PHONE);
		PHONE_CALLS.permissions.add(Permission.USE_SIP);
		PHONE_CALLS.permissions.add(Permission.READ_PRIVILEGED_PHONE_STATE);

		// android.permission-group.SYSTEM_TOOLS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_RENAME);
		SYSTEM_TOOLS.permissions.add(Permission.START_ANY_ACTIVITY);
		SYSTEM_TOOLS.permissions.add(Permission.MOUNT_FORMAT_FILESYSTEMS);
		SYSTEM_TOOLS.permissions.add(Permission.REMOTE_AUDIO_PLAYBACK);
		SYSTEM_TOOLS.permissions.add(Permission.OEM_UNLOCK_STATE);
		SYSTEM_TOOLS.permissions.add(Permission.CLEAR_APP_CACHE);
		SYSTEM_TOOLS.permissions.add(Permission.MOUNT_UNMOUNT_FILESYSTEMS);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_ACCESS);
		SYSTEM_TOOLS.permissions.add(Permission.GLOBAL_SEARCH);
		SYSTEM_TOOLS.permissions.add(Permission.MANAGE_USERS);
		SYSTEM_TOOLS.permissions.add(Permission.START_TASKS_FROM_RECENTS);
		SYSTEM_TOOLS.permissions.add(Permission.SET_PREFERRED_APPLICATIONS);
		SYSTEM_TOOLS.permissions.add(Permission.SET_SCREEN_COMPATIBILITY);
		SYSTEM_TOOLS.permissions.add(Permission.SUBSCRIBED_FEEDS_READ);
		SYSTEM_TOOLS.permissions.add(Permission.BROADCAST_STICKY);
		SYSTEM_TOOLS.permissions.add(Permission.GET_PACKAGE_SIZE);
		SYSTEM_TOOLS.permissions.add(Permission.ACCESS_MOCK_LOCATION);
		SYSTEM_TOOLS.permissions.add(Permission.NET_ADMIN);
		SYSTEM_TOOLS.permissions.add(Permission.INSTALL_SHORTCUT);
		SYSTEM_TOOLS.permissions.add(Permission.READ_SEARCH_INDEXABLES);
		SYSTEM_TOOLS.permissions.add(Permission.CHANGE_BACKGROUND_DATA_SETTING);
		SYSTEM_TOOLS.permissions.add(Permission.BLUETOOTH_STACK);
		SYSTEM_TOOLS.permissions.add(Permission.GET_DETAILED_TASKS);
		SYSTEM_TOOLS.permissions.add(Permission.SET_WALLPAPER_COMPONENT);
		SYSTEM_TOOLS.permissions.add(Permission.INTERACT_ACROSS_USERS_FULL);
		SYSTEM_TOOLS.permissions.add(Permission.WRITE_APN_SETTINGS);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_CREATE);
		SYSTEM_TOOLS.permissions.add(Permission.BROADCAST_PACKAGE_REMOVED);
		SYSTEM_TOOLS.permissions.add(Permission.SET_ANIMATION_SCALE);
		SYSTEM_TOOLS.permissions.add(Permission.RECOVERY);
		SYSTEM_TOOLS.permissions.add(Permission.BATTERY_STATS);
		SYSTEM_TOOLS.permissions.add(Permission.UNINSTALL_SHORTCUT);
		SYSTEM_TOOLS.permissions.add(Permission.FORCE_STOP_PACKAGES);
		SYSTEM_TOOLS.permissions.add(Permission.GLOBAL_SEARCH_CONTROL);
		SYSTEM_TOOLS.permissions.add(Permission.GET_APP_OPS_STATS);
		SYSTEM_TOOLS.permissions.add(Permission.MODIFY_APPWIDGET_BIND_PERMISSIONS);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_MOUNT_UNMOUNT);
		SYSTEM_TOOLS.permissions.add(Permission.WRITE_SETTINGS);
		SYSTEM_TOOLS.permissions.add(Permission.WRITE_DREAM_STATE);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_DESTROY);
		SYSTEM_TOOLS.permissions.add(Permission.INTERACT_ACROSS_USERS);
		SYSTEM_TOOLS.permissions.add(Permission.ACCESS_LOCATION_EXTRA_COMMANDS);
		SYSTEM_TOOLS.permissions.add(Permission.NET_TUNNELING);
		SYSTEM_TOOLS.permissions.add(Permission.DIAGNOSTIC);
		SYSTEM_TOOLS.permissions.add(Permission.SUBSCRIBED_FEEDS_WRITE);
		SYSTEM_TOOLS.permissions.add(Permission.READ_DREAM_STATE);

		// android.permission-group.USER_DICTIONARY permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		USER_DICTIONARY.permissions.add(Permission.READ_USER_DICTIONARY);

		// android.permission-group.BLUETOOTH_NETWORK permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH_PRIVILEGED);
		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH);
		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH_MAP);
		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH_ADMIN);

		// android.permission-group.SYSTEM_CLOCK permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		SYSTEM_CLOCK.permissions.add(Permission.SET_TIME_ZONE);

		// android.permission-group.MESSAGES permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		MESSAGES.permissions.add(Permission.RECEIVE_WAP_PUSH);
		MESSAGES.permissions.add(Permission.READ_SMS);
		MESSAGES.permissions.add(Permission.SEND_SMS);
		MESSAGES.permissions.add(Permission.SEND_RESPOND_VIA_MESSAGE);
		MESSAGES.permissions.add(Permission.BROADCAST_WAP_PUSH);
		MESSAGES.permissions.add(Permission.RECEIVE_EMERGENCY_BROADCAST);
		MESSAGES.permissions.add(Permission.BROADCAST_SMS);
		MESSAGES.permissions.add(Permission.WRITE_SMS);
		MESSAGES.permissions.add(Permission.RECEIVE_BLUETOOTH_MAP);
		MESSAGES.permissions.add(Permission.RECEIVE_MMS);
		MESSAGES.permissions.add(Permission.RECEIVE_SMS);
		MESSAGES.permissions.add(Permission.READ_CELL_BROADCASTS);

		// android.permission-group.STORAGE permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		STORAGE.permissions.add(Permission.READ_EXTERNAL_STORAGE);
		STORAGE.permissions.add(Permission.WRITE_MEDIA_STORAGE);
		STORAGE.permissions.add(Permission.WRITE_EXTERNAL_STORAGE);
		STORAGE.permissions.add(Permission.MANAGE_DOCUMENTS);

		// android.permission-group.WRITE_USER_DICTIONARY permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		WRITE_USER_DICTIONARY.permissions.add(Permission.WRITE_USER_DICTIONARY);

		// android.permission-group.PERSONAL_INFO permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		PERSONAL_INFO.permissions.add(Permission.WRITE_CALENDAR);
		PERSONAL_INFO.permissions.add(Permission.READ_PROFILE);
		PERSONAL_INFO.permissions.add(Permission.BIND_KEYGUARD_APPWIDGET);
		PERSONAL_INFO.permissions.add(Permission.READ_CALENDAR);
		PERSONAL_INFO.permissions.add(Permission.BODY_SENSORS);
		PERSONAL_INFO.permissions.add(Permission.BIND_DIRECTORY_SEARCH);
		PERSONAL_INFO.permissions.add(Permission.BIND_APPWIDGET);
		PERSONAL_INFO.permissions.add(Permission.WRITE_PROFILE);
		PERSONAL_INFO.permissions.add(Permission.RETRIEVE_WINDOW_CONTENT);

		// android.permission-group.WALLPAPER permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		WALLPAPER.permissions.add(Permission.SET_WALLPAPER_HINTS);
		WALLPAPER.permissions.add(Permission.SET_WALLPAPER);

		// android.permission-group.BOOKMARKS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		BOOKMARKS.permissions.add(Permission.WRITE_HISTORY_BOOKMARKS);
		BOOKMARKS.permissions.add(Permission.READ_HISTORY_BOOKMARKS);

		// android.permission-group.ACCOUNTS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		ACCOUNTS.permissions.add(Permission.USE_CREDENTIALS);
		ACCOUNTS.permissions.add(Permission.ACCOUNT_MANAGER);
		ACCOUNTS.permissions.add(Permission.MANAGE_ACCOUNTS);
		ACCOUNTS.permissions.add(Permission.GET_ACCOUNTS);
		ACCOUNTS.permissions.add(Permission.AUTHENTICATE_ACCOUNTS);

		// android.permission-group.HARDWARE_CONTROLS permission group mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 18:44:33 CST 2015
		HARDWARE_CONTROLS.permissions.add(Permission.ACCESS_MTP);
		HARDWARE_CONTROLS.permissions.add(Permission.HARDWARE_TEST);
		HARDWARE_CONTROLS.permissions.add(Permission.MANAGE_USB);
	}
	
	/**
	 * A collection of all known permission groups
	 */
	public static final Collection<PermissionGroup> allPermissionGroups = allPermissionGroups();

	private static Collection<PermissionGroup> allPermissionGroups() {
		Collection<PermissionGroup> allPermissionGroups = new HashSet<PermissionGroup>();
		allPermissionGroups.add(UNASSIGNED);
		allPermissionGroups.add(DEVELOPMENT_TOOLS);
		allPermissionGroups.add(AFFECTS_BATTERY);
		allPermissionGroups.add(STATUS_BAR);
		allPermissionGroups.add(DISPLAY);
		allPermissionGroups.add(CAMERA);
		allPermissionGroups.add(NETWORK);
		allPermissionGroups.add(MICROPHONE);
		allPermissionGroups.add(SCREENLOCK);
		allPermissionGroups.add(AUDIO_SETTINGS);
		allPermissionGroups.add(VOICEMAIL);
		allPermissionGroups.add(SYNC_SETTINGS);
		allPermissionGroups.add(SOCIAL_INFO);
		allPermissionGroups.add(APP_INFO);
		allPermissionGroups.add(LOCATION);
		allPermissionGroups.add(DEVICE_ALARMS);
		allPermissionGroups.add(PHONE_CALLS);
		allPermissionGroups.add(SYSTEM_TOOLS);
		allPermissionGroups.add(USER_DICTIONARY);
		allPermissionGroups.add(BLUETOOTH_NETWORK);
		allPermissionGroups.add(SYSTEM_CLOCK);
		allPermissionGroups.add(MESSAGES);
		allPermissionGroups.add(STORAGE);
		allPermissionGroups.add(WRITE_USER_DICTIONARY);
		allPermissionGroups.add(PERSONAL_INFO);
		allPermissionGroups.add(WALLPAPER);
		allPermissionGroups.add(BOOKMARKS);
		allPermissionGroups.add(ACCOUNTS);
		allPermissionGroups.add(HARDWARE_CONTROLS);
		return allPermissionGroups;
	}

}
