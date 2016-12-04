package com.ensoftcorp.open.android.essentials.subsystems.advertisements;

import java.util.ArrayList;

import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.ensoftcorp.open.android.essentials.subsystems.AndroidSubsystem;
import com.ensoftcorp.open.java.commons.analysis.PackageAnalysis;

/**
 * A collection of known Android advertisement libraries
 * Note: Primary reference http://arxiv.org/pdf/1303.0857.pdf
 * 
 * Ported from the QuestionableCode.org project (by Ben Holland)
 * 
 * @author Ben Holland
 */
public class AndroidAdvertisementSubsystem extends AndroidSubsystem {
	
	public static final String TAG = "ANDROID_ADVERTISEMENT_SUBSYSTEM";
	
	@Override
	public String getTag() {
		return TAG;
	}

	@Override
	public String[] getParentTags() {
		return new String[] { AndroidSubsystem.TAG };
	}
	
	@Override
	public String getDescription() {
		return getName() + " advertisement libraries";
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
