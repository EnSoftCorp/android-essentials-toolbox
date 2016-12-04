package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

/**
 * Android advertisement library
 * Reference: http://arxiv.org/pdf/1303.0857.pdf
 * @author Ben Holland
 */
public class WooBooSubsystem extends AndroidAdvertisementSubsystem {

	public static final String TAG = "WOO_BOO_SUBSYSTEM";

	@Override
	public String getName() {
		return "WooBoo";
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
		return getNestedPackages("com.wooboo");
	}
	
}