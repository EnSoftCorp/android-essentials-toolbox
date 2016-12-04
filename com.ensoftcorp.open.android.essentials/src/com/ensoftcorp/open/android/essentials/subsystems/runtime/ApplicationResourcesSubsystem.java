package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android application resource runtime libraries
 * 
 * @author Ben Holland
 */
public class ApplicationResourcesSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_APPLICATION_RESOURCSE_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Application Resources";
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
		return new String[] { "android", "android.content.res" };
	}

}
