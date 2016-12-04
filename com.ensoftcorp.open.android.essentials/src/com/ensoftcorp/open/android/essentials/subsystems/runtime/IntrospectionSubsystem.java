package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android introspection runtime libraries
 * 
 * @author Ben Holland
 */
public class IntrospectionSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_INTROSPECTION_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Introspection";
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
		return new String[] { "dalvik.bytecode", "dalvik.system" };
	}

}
