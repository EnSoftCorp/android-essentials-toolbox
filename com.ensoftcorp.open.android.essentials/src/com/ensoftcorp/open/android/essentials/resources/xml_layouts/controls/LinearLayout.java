package com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.ensoftcorp.open.android.essentials.resources.xml_layouts.AttributeResponder;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;

public class LinearLayout extends ViewGroup {

	public LinearLayout(UniverseManipulator um, Q activities, Element element, File xmlLayoutFile) {
		super(um, activities, element, xmlLayoutFile);
		responders.putAll(LinearLayout.getAttributeResponders());
	}

	public static Map<String, AttributeResponder> getAttributeResponders() {
		Map<String, AttributeResponder> responders = new HashMap<String, AttributeResponder>();
		return responders;
	}

}