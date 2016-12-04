package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android provider runtime libraries
 * 
 * @author Ben Holland
 */
public class ProviderSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_PROVIDER_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Provider";
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
		return new String[] { "android.provider" };
	}

}
