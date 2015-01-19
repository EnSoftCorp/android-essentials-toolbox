package com.ensoftcorp.open.android.essentials.permissions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * An convenience object that encodes Android Protection Level property values
 * @author Ben Holland, Vani Bojja
 */
public class ProtectionLevel {
	
	public static String[] REFERENCE_SOURCES = { "https://developer.android.com/guide/topics/manifest/permission-element.html", "https://developer.android.com/reference/android/content/pm/PermissionInfo.html" };
	public static Date REFERENCE_DATE = new Date(1421257800); // 1-14-2015
	
	private String name;
	private int level;
	private String description;
	private HashSet<Permission> permissions = new HashSet<Permission>();
	
	private ProtectionLevel(String name, int level, String description) {
		this.name = name;
		this.level = level;
		this.description = description;
	}

	/**
	 * Returns the protection level name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the protection level integer as defined in the Android source
	 * @return
	 */
	public int getLevel(){
		return level;
	}

	/**
	 * Returns a description of the protection level
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns a list of permissions associated with the protection level
	 * @return
	 */
	public HashSet<Permission> getPermissions(){
		return permissions;
	}

	@Override
	public String toString() {
		return name;
	}
	
	// official permission levels
	public static final ProtectionLevel NORMAL = new ProtectionLevel("Normal", 0, "The default value. A lower-risk permission that gives requesting applications access to isolated application-level features, with minimal risk to other applications, the system, or the user. The system automatically grants this type of permission to a requesting application at installation, without asking for the user's explicit approval (though the user always has the option to review these permissions before installing).");
	public static final ProtectionLevel DANGEROUS = new ProtectionLevel("Dangerous", 1, "A higher-risk permission that would give a requesting application access to private user data or control over the device that can negatively impact the user. Because this type of permission introduces potential risk, the system may not automatically grant it to the requesting application. For example, any dangerous permissions requested by an application may be displayed to the user and require confirmation before proceeding, or some other approach may be taken to avoid the user automatically allowing the use of such facilities.");
	public static final ProtectionLevel SIGNATURE = new ProtectionLevel("Signature", 2, "A permission that the system grants only if the requesting application is signed with the same certificate as the application that declared the permission. If the certificates match, the system automatically grants the permission without notifying the user or asking for the user's explicit approval.");
	public static final ProtectionLevel SIGNATURE_OR_SYSTEM = new ProtectionLevel("SignatureOrSystem", 3, "A permission that the system grants only to applications that are in the Android system image or that are signed with the same certificate as the application that declared the permission. Please avoid using this option, as the signature protection level should be sufficient for most needs and works regardless of exactly where applications are installed. The \"signatureOrSystem\" permission is used for certain special situations where multiple vendors have applications built into a system image and need to share specific features explicitly because they are being built together.");
	
	// more or less undocumented permission levels (but do officially exist in the Android source manifest file)
	public static final ProtectionLevel SYSTEM = new ProtectionLevel("System", 16, "A system level permission.");
	public static final ProtectionLevel DEVELOPMENT = new ProtectionLevel("Development", 32, "A development level permission.");
	public static final ProtectionLevel APPOP = new ProtectionLevel("AppOp", 64, "A permission that can access or modify operational statistics");
	
	// unassigned/unclassified (protection level is an empty string)
	// these may be upcoming permissions that have not been officially assigned a protection level in an official Android release
	// or they are hidden permissions which are intended for system level apps but for some reason were not assigned a protection level
	// this protection level does not officially exist!
	public static final ProtectionLevel UNASSIGNED = new ProtectionLevel("Unassigned", -1, "NOTE: This protection level does not officially exist!  It was created to hold permissions that for some reason have not been assigned a protection level.");
	
	// add the permissions for each protection level
	static {
		// UNASSIGNED protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		UNASSIGNED.permissions.add(Permission.BODY_SENSORS);
		UNASSIGNED.permissions.add(Permission.READ_INSTALL_SESSIONS);

		// system protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		SYSTEM.permissions.add(Permission.SHUTDOWN);
		SYSTEM.permissions.add(Permission.MEDIA_CONTENT_CONTROL);
		SYSTEM.permissions.add(Permission.MODIFY_PARENTAL_CONTROLS);
		SYSTEM.permissions.add(Permission.BIND_INCALL_SERVICE);
		SYSTEM.permissions.add(Permission.CAMERA_DISABLE_TRANSMIT_LED);
		SYSTEM.permissions.add(Permission.READ_SEARCH_INDEXABLES);
		SYSTEM.permissions.add(Permission.SERIAL_PORT);
		SYSTEM.permissions.add(Permission.BIND_DIRECTORY_SEARCH);
		SYSTEM.permissions.add(Permission.WRITE_MEDIA_STORAGE);
		SYSTEM.permissions.add(Permission.MOVE_PACKAGE);
		SYSTEM.permissions.add(Permission.CONTROL_INCALL_EXPERIENCE);
		SYSTEM.permissions.add(Permission.RECOVERY);
		SYSTEM.permissions.add(Permission.CAPTURE_AUDIO_OUTPUT);
		SYSTEM.permissions.add(Permission.ACCESS_CHECKIN_PROPERTIES);
		SYSTEM.permissions.add(Permission.FORCE_STOP_PACKAGES);
		SYSTEM.permissions.add(Permission.READ_PRECISE_PHONE_STATE);
		SYSTEM.permissions.add(Permission.CRYPT_KEEPER);
		SYSTEM.permissions.add(Permission.SCORE_NETWORKS);
		SYSTEM.permissions.add(Permission.BIND_APPWIDGET);
		SYSTEM.permissions.add(Permission.WRITE_DREAM_STATE);
		SYSTEM.permissions.add(Permission.MANAGE_ACTIVITY_STACKS);
		SYSTEM.permissions.add(Permission.CHANGE_CONFIGURATION);
		SYSTEM.permissions.add(Permission.DELETE_PACKAGES);
		SYSTEM.permissions.add(Permission.ACCESS_CACHE_FILESYSTEM);
		SYSTEM.permissions.add(Permission.REBOOT);
		SYSTEM.permissions.add(Permission.READ_VOICEMAIL);
		SYSTEM.permissions.add(Permission.STATUS_BAR);
		SYSTEM.permissions.add(Permission.MOUNT_UNMOUNT_FILESYSTEMS);
		SYSTEM.permissions.add(Permission.GLOBAL_SEARCH);
		SYSTEM.permissions.add(Permission.START_TASKS_FROM_RECENTS);
		SYSTEM.permissions.add(Permission.STOP_APP_SWITCHES);
		SYSTEM.permissions.add(Permission.CONTROL_LOCATION_UPDATES);
		SYSTEM.permissions.add(Permission.MANAGE_USB);
		SYSTEM.permissions.add(Permission.SET_WALLPAPER_COMPONENT);
		SYSTEM.permissions.add(Permission.RECEIVE_EMERGENCY_BROADCAST);
		SYSTEM.permissions.add(Permission.DELETE_CACHE_FILES);
		SYSTEM.permissions.add(Permission.CAPTURE_VIDEO_OUTPUT);
		SYSTEM.permissions.add(Permission.BATTERY_STATS);
		SYSTEM.permissions.add(Permission.ACCESS_NETWORK_CONDITIONS);
		SYSTEM.permissions.add(Permission.READ_PRIVILEGED_PHONE_STATE);
		SYSTEM.permissions.add(Permission.SEND_RESPOND_VIA_MESSAGE);
		SYSTEM.permissions.add(Permission.INTERACT_ACROSS_USERS);
		SYSTEM.permissions.add(Permission.INVOKE_CARRIER_SETUP);
		SYSTEM.permissions.add(Permission.RECEIVE_DATA_ACTIVITY_CHANGE);
		SYSTEM.permissions.add(Permission.RETRIEVE_WINDOW_CONTENT);
		SYSTEM.permissions.add(Permission.ACCESS_NOTIFICATIONS);
		SYSTEM.permissions.add(Permission.MOUNT_FORMAT_FILESYSTEMS);
		SYSTEM.permissions.add(Permission.SIGNAL_PERSISTENT_PROCESSES);
		SYSTEM.permissions.add(Permission.LOCATION_HARDWARE);
		SYSTEM.permissions.add(Permission.MASTER_CLEAR);
		SYSTEM.permissions.add(Permission.READ_LOGS);
		SYSTEM.permissions.add(Permission.MODIFY_NETWORK_ACCOUNTING);
		SYSTEM.permissions.add(Permission.READ_NETWORK_USAGE_HISTORY);
		SYSTEM.permissions.add(Permission.BACKUP);
		SYSTEM.permissions.add(Permission.REMOVE_DRM_CERTIFICATES);
		SYSTEM.permissions.add(Permission.MANAGE_USERS);
		SYSTEM.permissions.add(Permission.SET_TIME);
		SYSTEM.permissions.add(Permission.NFC_HANDOVER_STATUS);
		SYSTEM.permissions.add(Permission.UPDATE_APP_OPS_STATS);
		SYSTEM.permissions.add(Permission.MODIFY_AUDIO_ROUTING);
		SYSTEM.permissions.add(Permission.CONNECTIVITY_INTERNAL);
		SYSTEM.permissions.add(Permission.MANAGE_DEVICE_ADMINS);
		SYSTEM.permissions.add(Permission.INSTALL_PACKAGES);
		SYSTEM.permissions.add(Permission.CAPTURE_AUDIO_HOTWORD);
		SYSTEM.permissions.add(Permission.PERFORM_CDMA_PROVISIONING);
		SYSTEM.permissions.add(Permission.SET_ALWAYS_FINISH);
		SYSTEM.permissions.add(Permission.WRITE_SECURE_SETTINGS);
		SYSTEM.permissions.add(Permission.ACCESS_MTP);
		SYSTEM.permissions.add(Permission.INSTALL_LOCATION_PROVIDER);
		SYSTEM.permissions.add(Permission.MODIFY_PHONE_STATE);
		SYSTEM.permissions.add(Permission.BROADCAST_SCORE_NETWORKS);
		SYSTEM.permissions.add(Permission.BIND_CONNECTION_SERVICE);
		SYSTEM.permissions.add(Permission.CALL_PRIVILEGED);
		SYSTEM.permissions.add(Permission.READ_DREAM_STATE);
		SYSTEM.permissions.add(Permission.PACKAGE_VERIFICATION_AGENT);
		SYSTEM.permissions.add(Permission.CHANGE_COMPONENT_ENABLED_STATE);
		SYSTEM.permissions.add(Permission.DUMP);
		SYSTEM.permissions.add(Permission.RECEIVE_BLUETOOTH_MAP);
		SYSTEM.permissions.add(Permission.USER_ACTIVITY);
		SYSTEM.permissions.add(Permission.MANAGE_CA_CERTIFICATES);
		SYSTEM.permissions.add(Permission.WRITE_GSERVICES);
		SYSTEM.permissions.add(Permission.ALLOW_ANY_CODEC_FOR_PLAYBACK);
		SYSTEM.permissions.add(Permission.BLUETOOTH_PRIVILEGED);
		SYSTEM.permissions.add(Permission.BIND_TV_INPUT);
		SYSTEM.permissions.add(Permission.READ_FRAME_BUFFER);
		SYSTEM.permissions.add(Permission.UPDATE_DEVICE_STATS);
		SYSTEM.permissions.add(Permission.ACCESS_DRM_CERTIFICATES);
		SYSTEM.permissions.add(Permission.READ_WIFI_CREDENTIAL);
		SYSTEM.permissions.add(Permission.WRITE_APN_SETTINGS);
		SYSTEM.permissions.add(Permission.BIND_WALLPAPER);
		SYSTEM.permissions.add(Permission.CAPTURE_SECURE_VIDEO_OUTPUT);
		SYSTEM.permissions.add(Permission.SET_ANIMATION_SCALE);
		SYSTEM.permissions.add(Permission.REAL_GET_TASKS);
		SYSTEM.permissions.add(Permission.BIND_REMOTEVIEWS);
		SYSTEM.permissions.add(Permission.SET_DEBUG_APP);
		SYSTEM.permissions.add(Permission.WRITE_VOICEMAIL);
		SYSTEM.permissions.add(Permission.GET_APP_OPS_STATS);
		SYSTEM.permissions.add(Permission.BIND_KEYGUARD_APPWIDGET);
		SYSTEM.permissions.add(Permission.SET_PROCESS_LIMIT);
		SYSTEM.permissions.add(Permission.MODIFY_APPWIDGET_BIND_PERMISSIONS);
		SYSTEM.permissions.add(Permission.LOOP_RADIO);
		SYSTEM.permissions.add(Permission.MANAGE_VOICE_KEYPHRASES);

		// development protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		DEVELOPMENT.permissions.add(Permission.GET_APP_OPS_STATS);
		DEVELOPMENT.permissions.add(Permission.SIGNAL_PERSISTENT_PROCESSES);
		DEVELOPMENT.permissions.add(Permission.DUMP);
		DEVELOPMENT.permissions.add(Permission.SET_PROCESS_LIMIT);
		DEVELOPMENT.permissions.add(Permission.READ_LOGS);
		DEVELOPMENT.permissions.add(Permission.INTERACT_ACROSS_USERS);
		DEVELOPMENT.permissions.add(Permission.SET_ANIMATION_SCALE);
		DEVELOPMENT.permissions.add(Permission.SET_ALWAYS_FINISH);
		DEVELOPMENT.permissions.add(Permission.PACKAGE_USAGE_STATS);
		DEVELOPMENT.permissions.add(Permission.SET_DEBUG_APP);
		DEVELOPMENT.permissions.add(Permission.BATTERY_STATS);
		DEVELOPMENT.permissions.add(Permission.WRITE_SECURE_SETTINGS);
		DEVELOPMENT.permissions.add(Permission.CHANGE_CONFIGURATION);

		// appop protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		APPOP.permissions.add(Permission.PACKAGE_USAGE_STATS);

		// normal protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		NORMAL.permissions.add(Permission.READ_EXTERNAL_STORAGE);
		NORMAL.permissions.add(Permission.SET_ALARM);
		NORMAL.permissions.add(Permission.SET_WALLPAPER);
		NORMAL.permissions.add(Permission.GET_TASKS);
		NORMAL.permissions.add(Permission.ACCESS_WIFI_STATE);
		NORMAL.permissions.add(Permission.GET_ACCOUNTS);
		NORMAL.permissions.add(Permission.RESTART_PACKAGES);
		NORMAL.permissions.add(Permission.ACCESS_WIMAX_STATE);
		NORMAL.permissions.add(Permission.SET_WALLPAPER_HINTS);
		NORMAL.permissions.add(Permission.RECEIVE_BOOT_COMPLETED);
		NORMAL.permissions.add(Permission.SUBSCRIBED_FEEDS_READ);
		NORMAL.permissions.add(Permission.BROADCAST_STICKY);
		NORMAL.permissions.add(Permission.PERSISTENT_ACTIVITY);
		NORMAL.permissions.add(Permission.WRITE_USER_DICTIONARY);
		NORMAL.permissions.add(Permission.GET_PACKAGE_SIZE);
		NORMAL.permissions.add(Permission.WAKE_LOCK);
		NORMAL.permissions.add(Permission.FLASHLIGHT);
		NORMAL.permissions.add(Permission.ACCESS_NETWORK_STATE);
		NORMAL.permissions.add(Permission.TRANSMIT_IR);
		NORMAL.permissions.add(Permission.MODIFY_AUDIO_SETTINGS);
		NORMAL.permissions.add(Permission.KILL_BACKGROUND_PROCESSES);
		NORMAL.permissions.add(Permission.REORDER_TASKS);
		NORMAL.permissions.add(Permission.READ_SYNC_STATS);
		NORMAL.permissions.add(Permission.WRITE_SYNC_SETTINGS);
		NORMAL.permissions.add(Permission.EXPAND_STATUS_BAR);
		NORMAL.permissions.add(Permission.WRITE_SETTINGS);
		NORMAL.permissions.add(Permission.ACCESS_LOCATION_EXTRA_COMMANDS);
		NORMAL.permissions.add(Permission.READ_SYNC_SETTINGS);
		NORMAL.permissions.add(Permission.VIBRATE);
		NORMAL.permissions.add(Permission.SET_TIME_ZONE);
		NORMAL.permissions.add(Permission.CHANGE_NETWORK_STATE);

		// dangerous protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		DANGEROUS.permissions.add(Permission.CHANGE_WIFI_MULTICAST_STATE);
		DANGEROUS.permissions.add(Permission.WRITE_EXTERNAL_STORAGE);
		DANGEROUS.permissions.add(Permission.PROCESS_OUTGOING_CALLS);
		DANGEROUS.permissions.add(Permission.WRITE_CALL_LOG);
		DANGEROUS.permissions.add(Permission.READ_SOCIAL_STREAM);
		DANGEROUS.permissions.add(Permission.WRITE_SMS);
		DANGEROUS.permissions.add(Permission.RECEIVE_SMS);
		DANGEROUS.permissions.add(Permission.ACCESS_COARSE_LOCATION);
		DANGEROUS.permissions.add(Permission.CALL_PHONE);
		DANGEROUS.permissions.add(Permission.READ_CONTACTS);
		DANGEROUS.permissions.add(Permission.READ_USER_DICTIONARY);
		DANGEROUS.permissions.add(Permission.CLEAR_APP_CACHE);
		DANGEROUS.permissions.add(Permission.WRITE_CONTACTS);
		DANGEROUS.permissions.add(Permission.AUTHENTICATE_ACCOUNTS);
		DANGEROUS.permissions.add(Permission.READ_PHONE_STATE);
		DANGEROUS.permissions.add(Permission.READ_CALENDAR);
		DANGEROUS.permissions.add(Permission.READ_SMS);
		DANGEROUS.permissions.add(Permission.CAMERA);
		DANGEROUS.permissions.add(Permission.WRITE_HISTORY_BOOKMARKS);
		DANGEROUS.permissions.add(Permission.ACCESS_FINE_LOCATION);
		DANGEROUS.permissions.add(Permission.MANAGE_ACCOUNTS);
		DANGEROUS.permissions.add(Permission.USE_SIP);
		DANGEROUS.permissions.add(Permission.RECORD_AUDIO);
		DANGEROUS.permissions.add(Permission.ACCESS_MOCK_LOCATION);
		DANGEROUS.permissions.add(Permission.INSTALL_SHORTCUT);
		DANGEROUS.permissions.add(Permission.NFC);
		DANGEROUS.permissions.add(Permission.CHANGE_WIMAX_STATE);
		DANGEROUS.permissions.add(Permission.USE_CREDENTIALS);
		DANGEROUS.permissions.add(Permission.RECEIVE_WAP_PUSH);
		DANGEROUS.permissions.add(Permission.ADD_VOICEMAIL);
		DANGEROUS.permissions.add(Permission.SEND_SMS);
		DANGEROUS.permissions.add(Permission.WRITE_PROFILE);
		DANGEROUS.permissions.add(Permission.RECEIVE_MMS);
		DANGEROUS.permissions.add(Permission.UNINSTALL_SHORTCUT);
		DANGEROUS.permissions.add(Permission.DISABLE_KEYGUARD);
		DANGEROUS.permissions.add(Permission.READ_PROFILE);
		DANGEROUS.permissions.add(Permission.WRITE_CALENDAR);
		DANGEROUS.permissions.add(Permission.SYSTEM_ALERT_WINDOW);
		DANGEROUS.permissions.add(Permission.BLUETOOTH);
		DANGEROUS.permissions.add(Permission.INTERNET);
		DANGEROUS.permissions.add(Permission.CHANGE_WIFI_STATE);
		DANGEROUS.permissions.add(Permission.WRITE_SOCIAL_STREAM);
		DANGEROUS.permissions.add(Permission.READ_CALL_LOG);
		DANGEROUS.permissions.add(Permission.SUBSCRIBED_FEEDS_WRITE);
		DANGEROUS.permissions.add(Permission.BLUETOOTH_ADMIN);
		DANGEROUS.permissions.add(Permission.READ_CELL_BROADCASTS);
		DANGEROUS.permissions.add(Permission.READ_HISTORY_BOOKMARKS);

		// signature protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		SIGNATURE.permissions.add(Permission.CLEAR_APP_USER_DATA);
		SIGNATURE.permissions.add(Permission.MANAGE_MEDIA_PROJECTION);
		SIGNATURE.permissions.add(Permission.OEM_UNLOCK_STATE);
		SIGNATURE.permissions.add(Permission.SHUTDOWN);
		SIGNATURE.permissions.add(Permission.MEDIA_CONTENT_CONTROL);
		SIGNATURE.permissions.add(Permission.BIND_INPUT_METHOD);
		SIGNATURE.permissions.add(Permission.BIND_ACCESSIBILITY_SERVICE);
		SIGNATURE.permissions.add(Permission.MODIFY_PARENTAL_CONTROLS);
		SIGNATURE.permissions.add(Permission.ASEC_ACCESS);
		SIGNATURE.permissions.add(Permission.SET_PREFERRED_APPLICATIONS);
		SIGNATURE.permissions.add(Permission.SET_SCREEN_COMPATIBILITY);
		SIGNATURE.permissions.add(Permission.BIND_INCALL_SERVICE);
		SIGNATURE.permissions.add(Permission.INTERNAL_SYSTEM_WINDOW);
		SIGNATURE.permissions.add(Permission.ACCESS_ALL_EXTERNAL_STORAGE);
		SIGNATURE.permissions.add(Permission.CAMERA_DISABLE_TRANSMIT_LED);
		SIGNATURE.permissions.add(Permission.READ_SEARCH_INDEXABLES);
		SIGNATURE.permissions.add(Permission.CHANGE_BACKGROUND_DATA_SETTING);
		SIGNATURE.permissions.add(Permission.SERIAL_PORT);
		SIGNATURE.permissions.add(Permission.BIND_DIRECTORY_SEARCH);
		SIGNATURE.permissions.add(Permission.GET_DETAILED_TASKS);
		SIGNATURE.permissions.add(Permission.WRITE_MEDIA_STORAGE);
		SIGNATURE.permissions.add(Permission.CONFIGURE_WIFI_DISPLAY);
		SIGNATURE.permissions.add(Permission.MOVE_PACKAGE);
		SIGNATURE.permissions.add(Permission.CONTROL_INCALL_EXPERIENCE);
		SIGNATURE.permissions.add(Permission.RECOVERY);
		SIGNATURE.permissions.add(Permission.HARDWARE_TEST);
		SIGNATURE.permissions.add(Permission.CAPTURE_AUDIO_OUTPUT);
		SIGNATURE.permissions.add(Permission.ACCESS_CHECKIN_PROPERTIES);
		SIGNATURE.permissions.add(Permission.FORCE_STOP_PACKAGES);
		SIGNATURE.permissions.add(Permission.CRYPT_KEEPER);
		SIGNATURE.permissions.add(Permission.GLOBAL_SEARCH_CONTROL);
		SIGNATURE.permissions.add(Permission.CONTROL_WIFI_DISPLAY);
		SIGNATURE.permissions.add(Permission.READ_PRECISE_PHONE_STATE);
		SIGNATURE.permissions.add(Permission.FRAME_STATS);
		SIGNATURE.permissions.add(Permission.READ_INPUT_STATE);
		SIGNATURE.permissions.add(Permission.WRITE_DREAM_STATE);
		SIGNATURE.permissions.add(Permission.BIND_APPWIDGET);
		SIGNATURE.permissions.add(Permission.SCORE_NETWORKS);
		SIGNATURE.permissions.add(Permission.BROADCAST_SMS);
		SIGNATURE.permissions.add(Permission.MANAGE_ACTIVITY_STACKS);
		SIGNATURE.permissions.add(Permission.GET_TOP_ACTIVITY_INFO);
		SIGNATURE.permissions.add(Permission.NET_TUNNELING);
		SIGNATURE.permissions.add(Permission.BIND_VOICE_INTERACTION);
		SIGNATURE.permissions.add(Permission.DIAGNOSTIC);
		SIGNATURE.permissions.add(Permission.DEVICE_POWER);
		SIGNATURE.permissions.add(Permission.CHANGE_CONFIGURATION);
		SIGNATURE.permissions.add(Permission.START_ANY_ACTIVITY);
		SIGNATURE.permissions.add(Permission.DELETE_PACKAGES);
		SIGNATURE.permissions.add(Permission.ACCESS_CACHE_FILESYSTEM);
		SIGNATURE.permissions.add(Permission.BROADCAST_WAP_PUSH);
		SIGNATURE.permissions.add(Permission.REBOOT);
		SIGNATURE.permissions.add(Permission.READ_VOICEMAIL);
		SIGNATURE.permissions.add(Permission.FILTER_EVENTS);
		SIGNATURE.permissions.add(Permission.STATUS_BAR);
		SIGNATURE.permissions.add(Permission.BIND_DREAM_SERVICE);
		SIGNATURE.permissions.add(Permission.MOUNT_UNMOUNT_FILESYSTEMS);
		SIGNATURE.permissions.add(Permission.GLOBAL_SEARCH);
		SIGNATURE.permissions.add(Permission.START_TASKS_FROM_RECENTS);
		SIGNATURE.permissions.add(Permission.BIND_NFC_SERVICE);
		SIGNATURE.permissions.add(Permission.STOP_APP_SWITCHES);
		SIGNATURE.permissions.add(Permission.BIND_VPN_SERVICE);
		SIGNATURE.permissions.add(Permission.BLUETOOTH_MAP);
		SIGNATURE.permissions.add(Permission.CONTROL_LOCATION_UPDATES);
		SIGNATURE.permissions.add(Permission.SET_KEYBOARD_LAYOUT);
		SIGNATURE.permissions.add(Permission.MANAGE_APP_TOKENS);
		SIGNATURE.permissions.add(Permission.MANAGE_USB);
		SIGNATURE.permissions.add(Permission.BLUETOOTH_STACK);
		SIGNATURE.permissions.add(Permission.SET_WALLPAPER_COMPONENT);
		SIGNATURE.permissions.add(Permission.ASEC_CREATE);
		SIGNATURE.permissions.add(Permission.RECEIVE_EMERGENCY_BROADCAST);
		SIGNATURE.permissions.add(Permission.ACCESS_INPUT_FLINGER);
		SIGNATURE.permissions.add(Permission.BIND_PACKAGE_VERIFIER);
		SIGNATURE.permissions.add(Permission.DELETE_CACHE_FILES);
		SIGNATURE.permissions.add(Permission.BATTERY_STATS);
		SIGNATURE.permissions.add(Permission.CAPTURE_VIDEO_OUTPUT);
		SIGNATURE.permissions.add(Permission.ACCESS_NETWORK_CONDITIONS);
		SIGNATURE.permissions.add(Permission.READ_PRIVILEGED_PHONE_STATE);
		SIGNATURE.permissions.add(Permission.ACCESS_KEYGUARD_SECURE_STORAGE);
		SIGNATURE.permissions.add(Permission.SEND_RESPOND_VIA_MESSAGE);
		SIGNATURE.permissions.add(Permission.INTERACT_ACROSS_USERS);
		SIGNATURE.permissions.add(Permission.ASEC_DESTROY);
		SIGNATURE.permissions.add(Permission.INVOKE_CARRIER_SETUP);
		SIGNATURE.permissions.add(Permission.MANAGE_DOCUMENTS);
		SIGNATURE.permissions.add(Permission.RECEIVE_DATA_ACTIVITY_CHANGE);
		SIGNATURE.permissions.add(Permission.COPY_PROTECTED_DATA);
		SIGNATURE.permissions.add(Permission.RETRIEVE_WINDOW_CONTENT);
		SIGNATURE.permissions.add(Permission.ASEC_RENAME);
		SIGNATURE.permissions.add(Permission.ACCESS_NOTIFICATIONS);
		SIGNATURE.permissions.add(Permission.MOUNT_FORMAT_FILESYSTEMS);
		SIGNATURE.permissions.add(Permission.SIGNAL_PERSISTENT_PROCESSES);
		SIGNATURE.permissions.add(Permission.LOCATION_HARDWARE);
		SIGNATURE.permissions.add(Permission.MASTER_CLEAR);
		SIGNATURE.permissions.add(Permission.RETRIEVE_WINDOW_TOKEN);
		SIGNATURE.permissions.add(Permission.REMOTE_AUDIO_PLAYBACK);
		SIGNATURE.permissions.add(Permission.READ_LOGS);
		SIGNATURE.permissions.add(Permission.BRICK);
		SIGNATURE.permissions.add(Permission.SET_ACTIVITY_WATCHER);
		SIGNATURE.permissions.add(Permission.MODIFY_NETWORK_ACCOUNTING);
		SIGNATURE.permissions.add(Permission.READ_NETWORK_USAGE_HISTORY);
		SIGNATURE.permissions.add(Permission.BACKUP);
		SIGNATURE.permissions.add(Permission.REMOVE_DRM_CERTIFICATES);
		SIGNATURE.permissions.add(Permission.MANAGE_USERS);
		SIGNATURE.permissions.add(Permission.SET_TIME);
		SIGNATURE.permissions.add(Permission.STATUS_BAR_SERVICE);
		SIGNATURE.permissions.add(Permission.NFC_HANDOVER_STATUS);
		SIGNATURE.permissions.add(Permission.MODIFY_AUDIO_ROUTING);
		SIGNATURE.permissions.add(Permission.UPDATE_APP_OPS_STATS);
		SIGNATURE.permissions.add(Permission.CONNECTIVITY_INTERNAL);
		SIGNATURE.permissions.add(Permission.MANAGE_DEVICE_ADMINS);
		SIGNATURE.permissions.add(Permission.PERFORM_CDMA_PROVISIONING);
		SIGNATURE.permissions.add(Permission.CAPTURE_AUDIO_HOTWORD);
		SIGNATURE.permissions.add(Permission.INSTALL_PACKAGES);
		SIGNATURE.permissions.add(Permission.BIND_NOTIFICATION_LISTENER_SERVICE);
		SIGNATURE.permissions.add(Permission.INJECT_EVENTS);
		SIGNATURE.permissions.add(Permission.SET_POINTER_SPEED);
		SIGNATURE.permissions.add(Permission.ACCOUNT_MANAGER);
		SIGNATURE.permissions.add(Permission.INTERACT_ACROSS_USERS_FULL);
		SIGNATURE.permissions.add(Permission.REMOVE_TASKS);
		SIGNATURE.permissions.add(Permission.SET_ALWAYS_FINISH);
		SIGNATURE.permissions.add(Permission.WRITE_SECURE_SETTINGS);
		SIGNATURE.permissions.add(Permission.ACCESS_MTP);
		SIGNATURE.permissions.add(Permission.INSTALL_LOCATION_PROVIDER);
		SIGNATURE.permissions.add(Permission.MODIFY_PHONE_STATE);
		SIGNATURE.permissions.add(Permission.BROADCAST_SCORE_NETWORKS);
		SIGNATURE.permissions.add(Permission.CONFIRM_FULL_BACKUP);
		SIGNATURE.permissions.add(Permission.PACKAGE_USAGE_STATS);
		SIGNATURE.permissions.add(Permission.BIND_CONNECTION_SERVICE);
		SIGNATURE.permissions.add(Permission.ACCESS_SURFACE_FLINGER);
		SIGNATURE.permissions.add(Permission.BIND_CONDITION_PROVIDER_SERVICE);
		SIGNATURE.permissions.add(Permission.CONTROL_KEYGUARD);
		SIGNATURE.permissions.add(Permission.TRUST_LISTENER);
		SIGNATURE.permissions.add(Permission.CALL_PRIVILEGED);
		SIGNATURE.permissions.add(Permission.PACKAGE_VERIFICATION_AGENT);
		SIGNATURE.permissions.add(Permission.READ_DREAM_STATE);
		SIGNATURE.permissions.add(Permission.CHANGE_COMPONENT_ENABLED_STATE);
		SIGNATURE.permissions.add(Permission.DUMP);
		SIGNATURE.permissions.add(Permission.C2D_MESSAGE);
		SIGNATURE.permissions.add(Permission.RECEIVE_BLUETOOTH_MAP);
		SIGNATURE.permissions.add(Permission.USER_ACTIVITY);
		SIGNATURE.permissions.add(Permission.BIND_PRINT_SPOOLER_SERVICE);
		SIGNATURE.permissions.add(Permission.MANAGE_CA_CERTIFICATES);
		SIGNATURE.permissions.add(Permission.BIND_PRINT_SERVICE);
		SIGNATURE.permissions.add(Permission.BIND_REMOTE_DISPLAY);
		SIGNATURE.permissions.add(Permission.MANAGE_NETWORK_POLICY);
		SIGNATURE.permissions.add(Permission.WRITE_GSERVICES);
		SIGNATURE.permissions.add(Permission.BLUETOOTH_PRIVILEGED);
		SIGNATURE.permissions.add(Permission.ALLOW_ANY_CODEC_FOR_PLAYBACK);
		SIGNATURE.permissions.add(Permission.BIND_TEXT_SERVICE);
		SIGNATURE.permissions.add(Permission.BIND_TV_INPUT);
		SIGNATURE.permissions.add(Permission.BIND_JOB_SERVICE);
		SIGNATURE.permissions.add(Permission.GRANT_REVOKE_PERMISSIONS);
		SIGNATURE.permissions.add(Permission.READ_FRAME_BUFFER);
		SIGNATURE.permissions.add(Permission.FORCE_BACK);
		SIGNATURE.permissions.add(Permission.UPDATE_DEVICE_STATS);
		SIGNATURE.permissions.add(Permission.NET_ADMIN);
		SIGNATURE.permissions.add(Permission.ACCESS_DRM_CERTIFICATES);
		SIGNATURE.permissions.add(Permission.READ_WIFI_CREDENTIAL);
		SIGNATURE.permissions.add(Permission.FREEZE_SCREEN);
		SIGNATURE.permissions.add(Permission.WRITE_APN_SETTINGS);
		SIGNATURE.permissions.add(Permission.CAPTURE_SECURE_VIDEO_OUTPUT);
		SIGNATURE.permissions.add(Permission.BIND_WALLPAPER);
		SIGNATURE.permissions.add(Permission.BIND_REMOTEVIEWS);
		SIGNATURE.permissions.add(Permission.BROADCAST_PACKAGE_REMOVED);
		SIGNATURE.permissions.add(Permission.REAL_GET_TASKS);
		SIGNATURE.permissions.add(Permission.SET_ANIMATION_SCALE);
		SIGNATURE.permissions.add(Permission.SET_ORIENTATION);
		SIGNATURE.permissions.add(Permission.SET_DEBUG_APP);
		SIGNATURE.permissions.add(Permission.SET_INPUT_CALIBRATION);
		SIGNATURE.permissions.add(Permission.FACTORY_TEST);
		SIGNATURE.permissions.add(Permission.TEMPORARY_ENABLE_ACCESSIBILITY);
		SIGNATURE.permissions.add(Permission.WRITE_VOICEMAIL);
		SIGNATURE.permissions.add(Permission.GET_APP_OPS_STATS);
		SIGNATURE.permissions.add(Permission.BIND_KEYGUARD_APPWIDGET);
		SIGNATURE.permissions.add(Permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY);
		SIGNATURE.permissions.add(Permission.MODIFY_APPWIDGET_BIND_PERMISSIONS);
		SIGNATURE.permissions.add(Permission.ASEC_MOUNT_UNMOUNT);
		SIGNATURE.permissions.add(Permission.SET_PROCESS_LIMIT);
		SIGNATURE.permissions.add(Permission.LOOP_RADIO);
		SIGNATURE.permissions.add(Permission.BIND_DEVICE_ADMIN);
		SIGNATURE.permissions.add(Permission.BIND_TRUST_AGENT);
		SIGNATURE.permissions.add(Permission.MANAGE_VOICE_KEYPHRASES);
		
		// signatureOrSystem protection level mappings generated from https://raw.githubusercontent.com/android/platform_frameworks_base/master/core/res/AndroidManifest.xml on Wed Jan 14 16:54:55 CST 2015
		SIGNATURE_OR_SYSTEM.permissions.add(Permission.PROVIDE_TRUST_AGENT);
		SIGNATURE_OR_SYSTEM.permissions.add(Permission.CAPTURE_TV_INPUT);
		SIGNATURE_OR_SYSTEM.permissions.add(Permission.HDMI_CEC);
		SIGNATURE_OR_SYSTEM.permissions.add(Permission.UPDATE_LOCK);
		SIGNATURE_OR_SYSTEM.permissions.add(Permission.LAUNCH_TRUST_AGENT_SETTINGS);
		SIGNATURE_OR_SYSTEM.permissions.add(Permission.TV_INPUT_HARDWARE);
		
		// ------ END GENERATE CODE --------------------------------------------------------------------------------------------
		// SIGNATURE_OR_SYSTEM is the union of SIGNATURE and SYSTEM plus anything specifically called out as SIGNATURE_OR_SYSTEM
		SIGNATURE_OR_SYSTEM.permissions.addAll(SIGNATURE.getPermissions()); // add SIGNATURE only permissions
		SIGNATURE_OR_SYSTEM.permissions.addAll(SYSTEM.getPermissions()); // add SYSTEM only permissions
	}

	/**
	 * Returns a collection of all known protection levels
	 */
	public static Collection<ProtectionLevel> getAllProtectionLevels() {
		Collection<ProtectionLevel> allProtectionLevels = new ArrayList<ProtectionLevel>();
		allProtectionLevels.add(UNASSIGNED);
		allProtectionLevels.add(SIGNATURE_OR_SYSTEM);
		allProtectionLevels.add(SYSTEM);
		allProtectionLevels.add(DEVELOPMENT);
		allProtectionLevels.add(APPOP);
		allProtectionLevels.add(NORMAL);
		allProtectionLevels.add(DANGEROUS);
		allProtectionLevels.add(SIGNATURE);
		return allProtectionLevels;
	}
	
}
