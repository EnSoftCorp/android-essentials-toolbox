package com.ensoftcorp.open.android.essentials.preferences;

import java.util.HashMap;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.ensoftcorp.open.android.essentials.Activator;
import com.ensoftcorp.open.android.essentials.log.Log;
import com.ensoftcorp.open.android.essentials.permissions.mappings.PermissionMapping;

public class AndroidEssentialsPreferences extends AbstractPreferenceInitializer {

	private static boolean initialized = false;
	
	/**
	 * Enable/disable mapping for individual Android api versions
	 */
	public static final String API_X_MAPPING_ENABLED = "API_X_MAPPING_ENABLED";
	public static final Boolean API_X_MAPPING_ENABLED_DEFAULT = false;
	public static final HashMap<Integer,Boolean> apiMappingEnabled = new HashMap<Integer,Boolean>();
	
	public static boolean isMappingEnabled(int apiVersion){
		if(!initialized){
			loadPreferences();
		}
		Boolean result = apiMappingEnabled.get(apiVersion);
		return result == null ? false : result;
	}
	
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
		for(Integer apiMapping : PermissionMapping.getAvailableMappings()){
			if(apiMapping == PermissionMapping.HIGHEST_AVAILABLE_MAPPING){
				preferences.setDefault(API_X_MAPPING_ENABLED.replace("X", apiMapping.toString()), true);
			} else {
				preferences.setDefault(API_X_MAPPING_ENABLED.replace("X", apiMapping.toString()), API_X_MAPPING_ENABLED_DEFAULT);
			}
		}
	}
	
	/**
	 * Loads or refreshes current preference values
	 */
	public static void loadPreferences() {
		try {
			IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
			apiMappingEnabled.clear();
			for(Integer apiMapping : PermissionMapping.getAvailableMappings()){
				apiMappingEnabled.put(apiMapping, preferences.getBoolean(API_X_MAPPING_ENABLED.replace("X", apiMapping.toString())));
			}
		} catch (Exception e){
			Log.warning("Error accessing android essentials preferences, using defaults...", e);
		}
		initialized = true;
	}

}
