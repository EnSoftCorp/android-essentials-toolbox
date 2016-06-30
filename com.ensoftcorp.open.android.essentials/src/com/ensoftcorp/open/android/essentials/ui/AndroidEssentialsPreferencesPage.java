package com.ensoftcorp.open.android.essentials.ui;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ensoftcorp.open.android.essentials.Activator;
import com.ensoftcorp.open.android.essentials.permissions.mappings.PermissionMapping;
import com.ensoftcorp.open.android.essentials.preferences.AndroidEssentialsPreferences;

/**
 * UI for setting android essentials preferences
 * 
 * @author Ben Holland
 */
public class AndroidEssentialsPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private static final String API_X_MAPPING_DESCRIPTION = "Enable Android API X Permission Mapping";
	
	private static boolean changeListenerAdded = false;
	
	public AndroidEssentialsPreferencesPage() {
		super(GRID);
	}

	@Override
	public void init(IWorkbench workbench) {
		IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
		setPreferenceStore(preferences);
		setDescription("Configure preferences for the Android Essentials Toolbox plugin.");
		
		// use to update cached values if user edits a preference
		if(!changeListenerAdded){
			getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
				@Override
				public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
					AndroidEssentialsPreferences.loadPreferences();
				}
			});
			changeListenerAdded = true;
		}
	}

	@Override
	protected void createFieldEditors() {
		for(Integer apiMapping : PermissionMapping.getAvailableMappings()){
			String key = AndroidEssentialsPreferences.API_X_MAPPING_ENABLED.replace("X", apiMapping.toString());
			String description = API_X_MAPPING_DESCRIPTION.replace("X", apiMapping.toString());
			addField(new BooleanFieldEditor(key, "&" + description, getFieldEditorParent()));
		}
	}

}
