package com.ensoftcorp.open.android.essentials.subsystems.runtime;

import com.ensoftcorp.open.android.essentials.subsystems.AndroidSubsystem;

/**
 * Android runtime libraries
 * 
 * @author Ben Holland
 */
public class AndroidRuntimeSubsystem extends AndroidSubsystem {

	public static final String TAG = "ANDROID_RUNTIME_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Core";
	}

	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidSubsystem.TAG };
	}

	@Override
	public String[] getNamespaces() {
		return new String[] { "android.app", "android.app.backup", "android.content", "android.content.pm",
				"android.os", "android.os.storage", "android.security", "android.service.dreams",
				"android.service.notification", "android.service.textservice" };
	}
	
}
