package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

/**
 * Android advertisement library
 * Reference: http://arxiv.org/pdf/1303.0857.pdf
 * @author Ben Holland
 */
public class InnerActiveSubsystem extends AndroidAdvertisementSubsystem {

	public static final String TAG = "INNER_ACTIVE_SUBSYSTEM";

	@Override
	public String getName() {
		return "InnerActive";
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
		return getNestedPackages("com.inneractive");
	}
	
}