package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

/**
 * Android advertisement library
 * Reference: http://arxiv.org/pdf/1303.0857.pdf
 * @author Ben Holland
 */
public class MoboSquareSubsystem extends AndroidAdvertisementSubsystem {

	public static final String TAG = "MOBO_SQUARE_SUBSYSTEM";

	@Override
	public String getName() {
		return "MoboSquare";
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
		return getNestedPackages("com.mobosquare");
	}
	
}