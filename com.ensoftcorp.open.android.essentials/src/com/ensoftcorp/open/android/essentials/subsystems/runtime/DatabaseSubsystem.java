package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android database runtime libraries
 * 
 * @author Ben Holland
 */
public class DatabaseSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_DATABASE_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Database";
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
		return new String[] { "android.database", "android.database.sqlite" };
	}

}
