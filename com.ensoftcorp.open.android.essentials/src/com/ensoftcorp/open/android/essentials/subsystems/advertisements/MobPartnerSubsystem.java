package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

/**
 * Android advertisement library
 * Reference: http://arxiv.org/pdf/1303.0857.pdf
 * @author Ben Holland
 */
public class MobPartnerSubsystem extends AndroidAdvertisementSubsystem {

	public static final String TAG = "MOB_PARTNER_SUBSYSTEM";

	@Override
	public String getName() {
		return "MobPartner";
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
		return getNestedPackages("com.mobpartner");
	}
	
}