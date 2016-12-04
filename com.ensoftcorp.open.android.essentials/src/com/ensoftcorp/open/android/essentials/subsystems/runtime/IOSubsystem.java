package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android input/output runtime libraries
 * 
 * @author Ben Holland
 */
public class IOSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_IO_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android I/O";
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
		return new String[] { "android.sax", "android.speech", "android.speech.tts", "org.json", "org.w3c.dom",
				"org.w3c.dom.ls", "org.xml.sax", "org.xml.sax.ext", "org.xml.sax.helpers", "org.xmlpull.v1",
				"org.xmlpull.v1.sax2" };
	}

}
