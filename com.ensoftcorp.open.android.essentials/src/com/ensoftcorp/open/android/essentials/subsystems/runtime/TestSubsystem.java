package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android test and mock runtime libraries
 * 
 * @author Ben Holland
 */
public class TestSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_TEST_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Test";
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
		return new String[] { "android.test", "android.test.mock", "android.test.suitebuilder", "junit.framework",
				"junit.runner" };
	}

}
