package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

/**
 * Android advertisement library
 * Reference: http://arxiv.org/pdf/1303.0857.pdf
 * @author Ben Holland
 */
public class HuntMobileAdsSubsystem extends AndroidAdvertisementSubsystem {

	public static final String TAG = "HUNT_MOBILE_ADS_SUBSYSTEM";

	@Override
	public String getName() {
		return "Hunt Mobile Ads";
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
		return getNestedPackages("com.huntmads");
	}
	
}