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
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidSubsystem.TAG };
	}
	
}
