package toolbox.android.essentials.permissions;

import java.util.Collection;
import java.util.HashSet;

public class PermissionGroup {

	private String qualifiedName;
	private int addedInLevel;
	private String description;
	private HashSet<Permission> permissions = new HashSet<Permission>();

	private PermissionGroup(String qualifiedName, int addedInLevel, String description) {
		this.qualifiedName = qualifiedName;
		this.addedInLevel = addedInLevel;
		this.description = description;
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

	// define the PermissionGroup static objects
	public static final PermissionGroup ACCESSIBILITY_FEATURES = new PermissionGroup("android.permission-group.ACCESSIBILITY_FEATURES", 18, "Used for permissions that allow requesting certain accessibility features.");
	public static final PermissionGroup ACCOUNTS = new PermissionGroup("android.permission-group.ACCOUNTS", 1, "Permissions for direct access to the accounts managed by the Account Manager.");
	public static final PermissionGroup AFFECTS_BATTERY = new PermissionGroup("android.permission-group.AFFECTS_BATTERY", 17, "Used for permissions that provide direct access to the hardware on the device that has an effect on battery life. This includes vibrator, flashlight, etc.");
	public static final PermissionGroup APP_INFO = new PermissionGroup("android.permission-group.APP_INFO", 17, "Group of permissions that are related to the other applications installed on the system. Examples include such as listing running apps, or killing background processes.");
	public static final PermissionGroup AUDIO_SETTINGS = new PermissionGroup("android.permission-group.AUDIO_SETTINGS", 17, "Used for permissions that provide direct access to speaker settings the device.");
	public static final PermissionGroup BLUETOOTH_NETWORK = new PermissionGroup("android.permission-group.BLUETOOTH_NETWORK", 17, "Used for permissions that provide access to other devices through Bluetooth.");
	public static final PermissionGroup BOOKMARKS = new PermissionGroup("android.permission-group.BOOKMARKS", 17, "Used for permissions that provide access to the user bookmarks and browser history.");
	public static final PermissionGroup CALENDAR = new PermissionGroup("android.permission-group.CALENDAR", 17, "Used for permissions that provide access to the device calendar to create / view events.");
	public static final PermissionGroup CAMERA = new PermissionGroup("android.permission-group.CAMERA", 17, "Used for permissions that are associated with accessing camera or capturing images/video from the device.");
	public static final PermissionGroup COST_MONEY = new PermissionGroup("android.permission-group.COST_MONEY", 1, "Used for permissions that can be used to make the user spend money without their direct involvement.");
	public static final PermissionGroup DEVELOPMENT_TOOLS = new PermissionGroup("android.permission-group.DEVELOPMENT_TOOLS", 1, "Group of permissions that are related to development features. These are not permissions that should appear in third-party applications; they protect APIs that are intended only to be used for development purposes.");
	public static final PermissionGroup DEVICE_ALARMS = new PermissionGroup("android.permission-group.DEVICE_ALARMS", 17, "Used for permissions that provide access to the user voicemail box.");
	public static final PermissionGroup DISPLAY = new PermissionGroup("android.permission-group.DISPLAY", 17, "Group of permissions that allow manipulation of how another application displays UI to the user.");
	public static final PermissionGroup HARDWARE_CONTROLS = new PermissionGroup("android.permission-group.HARDWARE_CONTROLS", 1, "Used for permissions that provide direct access to the hardware on the device. This includes audio, the camera, vibrator, etc.");
	public static final PermissionGroup LOCATION = new PermissionGroup("android.permission-group.LOCATION", 1, "Used for permissions that allow access to the user's current location.");
	public static final PermissionGroup MESSAGES = new PermissionGroup("android.permission-group.MESSAGES", 1, "Used for permissions that allow an application to send messages on behalf of the user or intercept messages being received by the user. This is primarily intended for SMS/MMS messaging, such as receiving or reading an MMS.");
	public static final PermissionGroup MICROPHONE = new PermissionGroup("android.permission-group.MICROPHONE", 17, "Used for permissions that are associated with accessing microphone audio from the device. Note that phone calls also capture audio but are in a separate (more visible) permission group.");
	public static final PermissionGroup NETWORK = new PermissionGroup("android.permission-group.NETWORK", 1, "Used for permissions that provide access to networking services. The main permission here is internet access, but this is also an appropriate group for accessing or modifying any network configuration or other related network operations.");
	public static final PermissionGroup PERSONAL_INFO = new PermissionGroup("android.permission-group.PERSONAL_INFO", 1, "Used for permissions that provide access to information about the device user such as profile information. This includes both reading and writing of this data (which should generally be expressed as two distinct permissions).");
	public static final PermissionGroup PHONE_CALLS = new PermissionGroup("android.permission-group.PHONE_CALLS", 1, "Used for permissions that are associated with accessing and modifyign telephony state: intercepting outgoing calls, reading and modifying the phone state.");
	public static final PermissionGroup SCREENLOCK = new PermissionGroup("android.permission-group.SCREENLOCK", 17, "Group of permissions that are related to the screenlock.");
	public static final PermissionGroup SOCIAL_INFO = new PermissionGroup("android.permission-group.SOCIAL_INFO", 17, "Used for permissions that provide access to the user's social connections, such as contacts, call logs, social stream, etc. This includes both reading and writing of this data (which should generally be expressed as two distinct permissions).");
	public static final PermissionGroup STATUS_BAR = new PermissionGroup("android.permission-group.STATUS_BAR", 17, "Used for permissions that change the status bar.");
	public static final PermissionGroup STORAGE = new PermissionGroup("android.permission-group.STORAGE", 4, "Group of permissions that are related to SD card access.");
	public static final PermissionGroup SYNC_SETTINGS = new PermissionGroup("android.permission-group.SYNC_SETTINGS", 17, "Used for permissions that access the sync settings or sync related information.");
	public static final PermissionGroup SYSTEM_CLOCK = new PermissionGroup("android.permission-group.SYSTEM_CLOCK", 17, "Group of permissions that are related to system clock.");
	public static final PermissionGroup SYSTEM_TOOLS = new PermissionGroup("android.permission-group.SYSTEM_TOOLS", 1, "Group of permissions that are related to system APIs. Many of these are not permissions the user will be expected to understand, and such permissions should generally be marked as \"normal\" protection level so they don't get displayed. This can also, however, be used for miscellaneous features that provide access to the operating system, such as writing the global system settings.");
	public static final PermissionGroup USER_DICTIONARY = new PermissionGroup("android.permission-group.USER_DICTIONARY", 17, "Used for permissions that provide access to the user calendar to create / view events.");
	public static final PermissionGroup VOICEMAIL = new PermissionGroup("android.permission-group.VOICEMAIL", 17, "Used for permissions that provide access to the user voicemail box.");
	public static final PermissionGroup WALLPAPER = new PermissionGroup("android.permission-group.WALLPAPER", 17, "Group of permissions that allow manipulation of how another application displays UI to the user.");
	public static final PermissionGroup WRITE_USER_DICTIONARY = new PermissionGroup("android.permission-group.WRITE_USER_DICTIONARY", 17, "Used for permissions that provide access to the user calendar to create / view events.");

	// add Permission objects to the PermissionGroup objects
	static {
		DEVELOPMENT_TOOLS.permissions.add(Permission.ACCESS_ALL_EXTERNAL_STORAGE);
		DEVELOPMENT_TOOLS.permissions.add(Permission.CHANGE_CONFIGURATION);
		DEVELOPMENT_TOOLS.permissions.add(Permission.WRITE_SECURE_SETTINGS);
		DEVELOPMENT_TOOLS.permissions.add(Permission.DUMP);
		DEVELOPMENT_TOOLS.permissions.add(Permission.READ_LOGS);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SET_DEBUG_APP);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SET_PROCESS_LIMIT);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SET_ALWAYS_FINISH);
		DEVELOPMENT_TOOLS.permissions.add(Permission.SIGNAL_PERSISTENT_PROCESSES);

		AFFECTS_BATTERY.permissions.add(Permission.CHANGE_WIFI_MULTICAST_STATE);
		AFFECTS_BATTERY.permissions.add(Permission.VIBRATE);
		AFFECTS_BATTERY.permissions.add(Permission.FLASHLIGHT);
		AFFECTS_BATTERY.permissions.add(Permission.WAKE_LOCK);
		AFFECTS_BATTERY.permissions.add(Permission.TRANSMIT_IR);

		STATUS_BAR.permissions.add(Permission.EXPAND_STATUS_BAR);

		DISPLAY.permissions.add(Permission.SYSTEM_ALERT_WINDOW);

		CAMERA.permissions.add(Permission.CAMERA);
		CAMERA.permissions.add(Permission.CAMERA_DISABLE_TRANSMIT_LED);

		NETWORK.permissions.add(Permission.INTERNET);
		NETWORK.permissions.add(Permission.ACCESS_NETWORK_STATE);
		NETWORK.permissions.add(Permission.ACCESS_WIFI_STATE);
		NETWORK.permissions.add(Permission.CHANGE_WIFI_STATE);
		NETWORK.permissions.add(Permission.ACCESS_WIMAX_STATE);
		NETWORK.permissions.add(Permission.CHANGE_WIMAX_STATE);
		NETWORK.permissions.add(Permission.NFC);
		NETWORK.permissions.add(Permission.CONNECTIVITY_INTERNAL);
		NETWORK.permissions.add(Permission.RECEIVE_DATA_ACTIVITY_CHANGE);
		NETWORK.permissions.add(Permission.LOOP_RADIO);
		NETWORK.permissions.add(Permission.CHANGE_NETWORK_STATE);

		MICROPHONE.permissions.add(Permission.RECORD_AUDIO);

		SCREENLOCK.permissions.add(Permission.DISABLE_KEYGUARD);

		AUDIO_SETTINGS.permissions.add(Permission.MODIFY_AUDIO_SETTINGS);

		VOICEMAIL.permissions.add(Permission.ADD_VOICEMAIL);

		SYNC_SETTINGS.permissions.add(Permission.READ_SYNC_SETTINGS);
		SYNC_SETTINGS.permissions.add(Permission.WRITE_SYNC_SETTINGS);
		SYNC_SETTINGS.permissions.add(Permission.READ_SYNC_STATS);

		SOCIAL_INFO.permissions.add(Permission.READ_CONTACTS);
		SOCIAL_INFO.permissions.add(Permission.WRITE_CONTACTS);
		SOCIAL_INFO.permissions.add(Permission.READ_CALL_LOG);
		SOCIAL_INFO.permissions.add(Permission.WRITE_CALL_LOG);
		SOCIAL_INFO.permissions.add(Permission.READ_SOCIAL_STREAM);
		SOCIAL_INFO.permissions.add(Permission.WRITE_SOCIAL_STREAM);

		APP_INFO.permissions.add(Permission.GET_TASKS);
		APP_INFO.permissions.add(Permission.REORDER_TASKS);
		APP_INFO.permissions.add(Permission.REMOVE_TASKS);
		APP_INFO.permissions.add(Permission.MANAGE_ACTIVITY_STACKS);
		APP_INFO.permissions.add(Permission.RESTART_PACKAGES);
		APP_INFO.permissions.add(Permission.KILL_BACKGROUND_PROCESSES);
		APP_INFO.permissions.add(Permission.PERSISTENT_ACTIVITY);
		APP_INFO.permissions.add(Permission.RECEIVE_BOOT_COMPLETED);

		LOCATION.permissions.add(Permission.ACCESS_FINE_LOCATION);
		LOCATION.permissions.add(Permission.ACCESS_COARSE_LOCATION);
		LOCATION.permissions.add(Permission.LOCATION_HARDWARE);

		DEVICE_ALARMS.permissions.add(Permission.SET_ALARM);

		PHONE_CALLS.permissions.add(Permission.PROCESS_OUTGOING_CALLS);
		PHONE_CALLS.permissions.add(Permission.MODIFY_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.READ_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.READ_PRECISE_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.READ_PRIVILEGED_PHONE_STATE);
		PHONE_CALLS.permissions.add(Permission.CALL_PHONE);
		PHONE_CALLS.permissions.add(Permission.USE_SIP);
		PHONE_CALLS.permissions.add(Permission.BIND_CALL_SERVICE);

		SYSTEM_TOOLS.permissions.add(Permission.ACCESS_MOCK_LOCATION);
		SYSTEM_TOOLS.permissions.add(Permission.ACCESS_LOCATION_EXTRA_COMMANDS);
		SYSTEM_TOOLS.permissions.add(Permission.BLUETOOTH_STACK);
		SYSTEM_TOOLS.permissions.add(Permission.NET_ADMIN);
		SYSTEM_TOOLS.permissions.add(Permission.REMOTE_AUDIO_PLAYBACK);
		SYSTEM_TOOLS.permissions.add(Permission.INTERACT_ACROSS_USERS);
		SYSTEM_TOOLS.permissions.add(Permission.INTERACT_ACROSS_USERS_FULL);
		SYSTEM_TOOLS.permissions.add(Permission.MANAGE_USERS);
		SYSTEM_TOOLS.permissions.add(Permission.GET_DETAILED_TASKS);
		SYSTEM_TOOLS.permissions.add(Permission.START_ANY_ACTIVITY);
		SYSTEM_TOOLS.permissions.add(Permission.INSTALL_SHORTCUT);
		SYSTEM_TOOLS.permissions.add(Permission.UNINSTALL_SHORTCUT);
		SYSTEM_TOOLS.permissions.add(Permission.SET_SCREEN_COMPATIBILITY);
		SYSTEM_TOOLS.permissions.add(Permission.WRITE_SETTINGS);
		SYSTEM_TOOLS.permissions.add(Permission.FORCE_STOP_PACKAGES);
		SYSTEM_TOOLS.permissions.add(Permission.SET_ANIMATION_SCALE);
		SYSTEM_TOOLS.permissions.add(Permission.GET_PACKAGE_SIZE);
		SYSTEM_TOOLS.permissions.add(Permission.SET_PREFERRED_APPLICATIONS);
		SYSTEM_TOOLS.permissions.add(Permission.BROADCAST_STICKY);
		SYSTEM_TOOLS.permissions.add(Permission.MOUNT_UNMOUNT_FILESYSTEMS);
		SYSTEM_TOOLS.permissions.add(Permission.MOUNT_FORMAT_FILESYSTEMS);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_ACCESS);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_CREATE);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_DESTROY);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_MOUNT_UNMOUNT);
		SYSTEM_TOOLS.permissions.add(Permission.ASEC_RENAME);
		SYSTEM_TOOLS.permissions.add(Permission.WRITE_APN_SETTINGS);
		SYSTEM_TOOLS.permissions.add(Permission.SUBSCRIBED_FEEDS_READ);
		SYSTEM_TOOLS.permissions.add(Permission.SUBSCRIBED_FEEDS_WRITE);
		SYSTEM_TOOLS.permissions.add(Permission.CLEAR_APP_CACHE);
		SYSTEM_TOOLS.permissions.add(Permission.DIAGNOSTIC);
		SYSTEM_TOOLS.permissions.add(Permission.GET_APP_OPS_STATS);
		SYSTEM_TOOLS.permissions.add(Permission.NET_TUNNELING);
		SYSTEM_TOOLS.permissions.add(Permission.BROADCAST_PACKAGE_REMOVED);
		SYSTEM_TOOLS.permissions.add(Permission.BATTERY_STATS);
		SYSTEM_TOOLS.permissions.add(Permission.MODIFY_APPWIDGET_BIND_PERMISSIONS);
		SYSTEM_TOOLS.permissions.add(Permission.CHANGE_BACKGROUND_DATA_SETTING);
		SYSTEM_TOOLS.permissions.add(Permission.GLOBAL_SEARCH);
		SYSTEM_TOOLS.permissions.add(Permission.GLOBAL_SEARCH_CONTROL);
		SYSTEM_TOOLS.permissions.add(Permission.SET_WALLPAPER_COMPONENT);
		SYSTEM_TOOLS.permissions.add(Permission.READ_DREAM_STATE);
		SYSTEM_TOOLS.permissions.add(Permission.WRITE_DREAM_STATE);

		USER_DICTIONARY.permissions.add(Permission.READ_USER_DICTIONARY);

		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH);
		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH_ADMIN);
		BLUETOOTH_NETWORK.permissions.add(Permission.BLUETOOTH_PRIVILEGED);

		SYSTEM_CLOCK.permissions.add(Permission.SET_TIME_ZONE);

		MESSAGES.permissions.add(Permission.SEND_SMS);
		MESSAGES.permissions.add(Permission.SEND_RESPOND_VIA_MESSAGE);
		MESSAGES.permissions.add(Permission.RECEIVE_SMS);
		MESSAGES.permissions.add(Permission.RECEIVE_MMS);
		MESSAGES.permissions.add(Permission.RECEIVE_EMERGENCY_BROADCAST);
		MESSAGES.permissions.add(Permission.READ_CELL_BROADCASTS);
		MESSAGES.permissions.add(Permission.READ_SMS);
		MESSAGES.permissions.add(Permission.WRITE_SMS);
		MESSAGES.permissions.add(Permission.RECEIVE_WAP_PUSH);
		MESSAGES.permissions.add(Permission.BROADCAST_SMS);
		MESSAGES.permissions.add(Permission.BROADCAST_WAP_PUSH);

		STORAGE.permissions.add(Permission.READ_EXTERNAL_STORAGE);
		STORAGE.permissions.add(Permission.WRITE_EXTERNAL_STORAGE);
		STORAGE.permissions.add(Permission.WRITE_MEDIA_STORAGE);
		STORAGE.permissions.add(Permission.MANAGE_DOCUMENTS);

		WRITE_USER_DICTIONARY.permissions.add(Permission.WRITE_USER_DICTIONARY);

		PERSONAL_INFO.permissions.add(Permission.BIND_DIRECTORY_SEARCH);
		PERSONAL_INFO.permissions.add(Permission.READ_PROFILE);
		PERSONAL_INFO.permissions.add(Permission.WRITE_PROFILE);
		PERSONAL_INFO.permissions.add(Permission.READ_CALENDAR);
		PERSONAL_INFO.permissions.add(Permission.WRITE_CALENDAR);
		PERSONAL_INFO.permissions.add(Permission.RETRIEVE_WINDOW_CONTENT);
		PERSONAL_INFO.permissions.add(Permission.BIND_APPWIDGET);
		PERSONAL_INFO.permissions.add(Permission.BIND_KEYGUARD_APPWIDGET);

		WALLPAPER.permissions.add(Permission.SET_WALLPAPER);
		WALLPAPER.permissions.add(Permission.SET_WALLPAPER_HINTS);

		BOOKMARKS.permissions.add(Permission.READ_HISTORY_BOOKMARKS);
		BOOKMARKS.permissions.add(Permission.WRITE_HISTORY_BOOKMARKS);

		ACCOUNTS.permissions.add(Permission.GET_ACCOUNTS);
		ACCOUNTS.permissions.add(Permission.AUTHENTICATE_ACCOUNTS);
		ACCOUNTS.permissions.add(Permission.USE_CREDENTIALS);
		ACCOUNTS.permissions.add(Permission.MANAGE_ACCOUNTS);
		ACCOUNTS.permissions.add(Permission.ACCOUNT_MANAGER);

		HARDWARE_CONTROLS.permissions.add(Permission.MANAGE_USB);
		HARDWARE_CONTROLS.permissions.add(Permission.ACCESS_MTP);
		HARDWARE_CONTROLS.permissions.add(Permission.HARDWARE_TEST);
	}
	
	/**
	 * A collection of all known permission groups
	 */
	public static final Collection<PermissionGroup> allPermissionGroups = allPermissionGroups();

	private static Collection<PermissionGroup> allPermissionGroups() {
		Collection<PermissionGroup> allPermissionGroups = new HashSet<PermissionGroup>();
		allPermissionGroups.add(ACCESSIBILITY_FEATURES);
		allPermissionGroups.add(ACCOUNTS);
		allPermissionGroups.add(AFFECTS_BATTERY);
		allPermissionGroups.add(APP_INFO);
		allPermissionGroups.add(AUDIO_SETTINGS);
		allPermissionGroups.add(BLUETOOTH_NETWORK);
		allPermissionGroups.add(BOOKMARKS);
		allPermissionGroups.add(CALENDAR);
		allPermissionGroups.add(CAMERA);
		allPermissionGroups.add(COST_MONEY);
		allPermissionGroups.add(DEVELOPMENT_TOOLS);
		allPermissionGroups.add(DEVICE_ALARMS);
		allPermissionGroups.add(DISPLAY);
		allPermissionGroups.add(HARDWARE_CONTROLS);
		allPermissionGroups.add(LOCATION);
		allPermissionGroups.add(MESSAGES);
		allPermissionGroups.add(MICROPHONE);
		allPermissionGroups.add(NETWORK);
		allPermissionGroups.add(PERSONAL_INFO);
		allPermissionGroups.add(PHONE_CALLS);
		allPermissionGroups.add(SCREENLOCK);
		allPermissionGroups.add(SOCIAL_INFO);
		allPermissionGroups.add(STATUS_BAR);
		allPermissionGroups.add(STORAGE);
		allPermissionGroups.add(SYNC_SETTINGS);
		allPermissionGroups.add(SYSTEM_CLOCK);
		allPermissionGroups.add(SYSTEM_TOOLS);
		allPermissionGroups.add(USER_DICTIONARY);
		allPermissionGroups.add(VOICEMAIL);
		allPermissionGroups.add(WALLPAPER);
		allPermissionGroups.add(WRITE_USER_DICTIONARY);
		return allPermissionGroups;
	}

}
