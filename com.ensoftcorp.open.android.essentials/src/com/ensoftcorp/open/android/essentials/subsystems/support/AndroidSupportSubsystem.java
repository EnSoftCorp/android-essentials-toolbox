package com.ensoftcorp.open.android.essentials.subsystems.support;

import java.util.ArrayList;

import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.ensoftcorp.open.android.essentials.subsystems.AndroidSubsystem;
import com.ensoftcorp.open.java.commons.analysis.PackageAnalysis;

/**
 * Android compatibility support libraries
 * 
 * @author Ben Holland
 */
public class AndroidSupportSubsystem extends AndroidSubsystem {

public static final String TAG = "ANDROID_SUPPORT_SUBSYSTEM";
	
	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidSubsystem.TAG };
	}
	
	/**
	 * Returns all packages starting with the given package prefixes
	 * @param rootPackages
	 * @return
	 */
	protected String[] getNestedPackages(String... rootPackages){
		ArrayList<String> packages = new ArrayList<String>();
		for(String rootPackage : rootPackages){
			for(Node pkg : PackageAnalysis.getPackageWithSubpackages(rootPackage).eval().nodes()){
				packages.add(pkg.getAttr(XCSG.name).toString());
			}
		}
		String[] result = new String[packages.size()];
		return packages.toArray(result); 
	}
	
}
