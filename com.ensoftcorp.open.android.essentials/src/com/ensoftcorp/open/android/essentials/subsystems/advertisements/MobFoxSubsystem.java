package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

/**
 * Android advertisement library
 * Reference: http://arxiv.org/pdf/1303.0857.pdf
 * @author Ben Holland
 */
public class MobFoxSubsystem extends AndroidAdvertisementSubsystem {

	public static final String TAG = "MOB_FOX_SUBSYSTEM";

	@Override
	public String getName() {
		return "MobFox";
	}

	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidAdvertisementSubsystem.TAG };
	}

	@Override
	public String[] getNamespaces() {
		return getNestedPackages("com.mobfox");
	}
	
}