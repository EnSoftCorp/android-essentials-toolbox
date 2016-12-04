package com.ensoftcorp.open.android.essentials.subsystems;

import com.ensoftcorp.open.commons.subsystems.Subsystem;

public class AndroidSubsystem extends Subsystem {

	public static final String TAG = "ANDROID_SUBSYSTEM";
	
	@Override
	public String getCategory() {
		return "ANDROID_SUBSYSTEM";
	}
	
	@Override
	public String getCategoryDescription(){
		return "Android Subsystems";
	}

	@Override
	public String getName() {
		return "Android";
	}

	@Override
	public String getDescription() {
		return "Android libraries";
	}

	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { Subsystem.ROOT_SUBSYSTEM_TAG };
	}

	@Override
	public String[] getNamespaces() {
		return new String[] {};
	}
	
}
