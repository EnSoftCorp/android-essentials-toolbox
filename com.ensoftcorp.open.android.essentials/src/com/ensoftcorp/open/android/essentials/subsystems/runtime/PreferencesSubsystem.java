package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android preference runtime libraries
 * 
 * @author Ben Holland
 */
public class PreferencesSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_PREFERENCE_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Preference";
	}

	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidRuntimeSubsystem.TAG };
	}

	@Override
	public String[] getNamespaces() {
		return new String[] { "android.preference" };
	}

}
