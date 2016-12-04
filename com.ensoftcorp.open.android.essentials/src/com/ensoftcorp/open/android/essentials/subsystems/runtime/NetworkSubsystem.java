package com.ensoftcorp.open.android.essentials.subsystems.runtime;

/**
 * Android network runtime libraries
 * 
 * @author Ben Holland
 */
public class NetworkSubsystem extends AndroidRuntimeSubsystem {

	public static final String TAG = "ANDROID_NETWORK_SUBSYSTEM";

	@Override
	public String getName() {
		return "Android Network";
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
		return new String[] { "android.bluetooth", "android.net", "android.net.http", "android.net.nsd",
				"android.net.rtp", "android.net.sip", "android.net.wifi", "android.net.wifi.p2p",
				"android.net.wifi.p2p.nsd", "android.nfc", "android.nfc.tech", "android.telephony",
				"android.telephony.cdma", "android.telephony.gsm", "android.webkit", "org.apache.http",
				"org.apache.http.auth", "org.apache.http.auth.params", "org.apache.http.client",
				"org.apache.http.client.entity", "org.apache.http.client.methods", "org.apache.http.client.params",
				"org.apache.http.client.protocol", "org.apache.http.client.utils", "org.apache.http.conn",
				"org.apache.http.conn.params", "org.apache.http.conn.routing", "org.apache.http.conn.scheme",
				"org.apache.http.conn.ssl", "org.apache.http.conn.util", "org.apache.http.cookie",
				"org.apache.http.cookie.params", "org.apache.http.entity", "org.apache.http.impl",
				"org.apache.http.impl.auth", "org.apache.http.impl.client", "org.apache.http.impl.conn",
				"org.apache.http.impl.conn.tsccm", "org.apache.http.impl.cookie", "org.apache.http.impl.entity",
				"org.apache.http.impl.io", "org.apache.http.io", "org.apache.http.message", "org.apache.http.params",
				"org.apache.http.protocol", "org.apache.http.util" };
	}

}
