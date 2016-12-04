package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android accounts runtime libraries
 * 
 * @author Ben Holland
 */
public class AccountsSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_ACCOUNTS_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Accounts";
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
		return new String[] { "android.accounts" };
	}

}
