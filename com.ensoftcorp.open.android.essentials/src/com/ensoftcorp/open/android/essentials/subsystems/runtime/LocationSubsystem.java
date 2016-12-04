package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android location runtime libraries
 * 
 * @author Ben Holland
 */
public class LocationSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_LOCATION_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Location";
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
		return new String[] { "android.hardware.location", "android.location" };
	}

}
