package com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.ensoftcorp.open.android.essentials.resources.xml_layouts.AttributeResponder;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;
import com.ensoftcorp.atlas.core.script.UniverseManipulator.Manipulation;

public class TextView extends View {

	public TextView(UniverseManipulator um, Q activities, Element element, File xmlLayoutFile) {
		super(um, activities, element, xmlLayoutFile);
		responders.putAll(TextView.getAttributeResponders());
	}

	public static Map<String, AttributeResponder> getAttributeResponders() {
		Map<String, AttributeResponder> responders = new HashMap<String, AttributeResponder>();
		
		responders.put("android:text", new AttributeResponder("android:text") {

			@Override
			public void respond(UniverseManipulator um, File xmlLayoutFile, Element element, Q activity, Manipulation parent, String attributeValue) {
				// TODO implement
//				UniverseManipulator.Manipulation attributeNode = addXMLAttributeToParent(um, xmlLayoutFile, element, parent, attributeValue);	
			}	
		
	});
		
		return responders;
	}

}
