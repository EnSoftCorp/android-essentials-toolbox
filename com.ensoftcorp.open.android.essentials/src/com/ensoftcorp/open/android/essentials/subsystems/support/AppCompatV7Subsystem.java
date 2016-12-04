package com.ensoftcorp.open.android.essentials.subsystems.support;

/**
 * Android compatibility support library
 * Reference: https://developer.android.com/tools/support-library/index.html
 * 
 * @author Ben Holland
 */
public class AppCompatV7Subsystem extends AndroidSupportSubsystem {

	public static final String TAG = "ANDROID_APPCOMPATV7_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Support Library v7";
	}

	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidSupportSubsystem.TAG };
	}

	@Override
	public String[] getNamespaces() {
		return getNestedPackages("android.support.v7");
	}

	@Override
	public String getDescription() {
		return "Android compatibility library version 7.\nReference: https://developer.android.com/tools/support-library/index.html";
	}
	
}
