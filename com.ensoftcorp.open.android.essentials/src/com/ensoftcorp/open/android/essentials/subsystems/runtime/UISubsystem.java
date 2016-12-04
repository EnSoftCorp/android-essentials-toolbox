package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android user interface runtime libraries
 * 
 * @author Ben Holland
 */
public class UISubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_UI_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android User Interface";
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
		return new String[] { "android.accessibilityservice", "android.animation", "android.appwidget",
				"android.gesture", "android.graphics", "android.graphics.drawable", "android.graphics.drawable.shapes",
				"android.inputmethodservice", "android.opengl", "android.renderscript", "android.text",
				"android.text.format", "android.text.method", "android.text.style", "android.view",
				"android.view.accessibility", "android.view.animation", "android.view.inputmethod",
				"android.view.textservice", "android.widget", "android.service.wallpaper" };
	}

}
