package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android utility runtime libraries
 * 
 * @author Ben Holland
 */
public class UtilSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_UTIL_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Util";
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
		return new String[] { "android.text.util", "android.util" };
	}

}
