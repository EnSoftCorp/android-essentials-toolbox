package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android database runtime libraries
 * 
 * @author Ben Holland
 */
public class HardwareSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_HARDWARE_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Hardware";
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
		return new String[] { "android.hardware", "android.hardware.display", "android.hardware.input",
				"android.hardware.usb" };
	}

}
