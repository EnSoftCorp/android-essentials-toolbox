package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android media runtime libraries
 * 
 * @author Ben Holland
 */
public class MediaSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_MEDIA_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Media";
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
		return new String[] { "android.drm", "android.media", "android.media.audiofx", "android.media.effect",
				"android.mtp" };
	}

}
