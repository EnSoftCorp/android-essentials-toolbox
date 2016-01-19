package com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.ensoftcorp.open.android.essentials.resources.xml_layouts.AttributeResponder;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.Control;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;
import com.ensoftcorp.atlas.core.script.UniverseManipulator.Manipulation;

public class View extends Control {

	public View(UniverseManipulator um, Q activities, Element element, File xmlLayoutFile) {
		super(um, activities, element, xmlLayoutFile);
		responders.putAll(View.getAttributeResponders());
	}

	public static Map<String, AttributeResponder> getAttributeResponders() {
		Map<String, AttributeResponder> responders = new HashMap<String, AttributeResponder>();

		// Name of the method in this View's context to invoke when the view is
		// clicked.
		// Attribute Documentation:
		// http://developer.android.com/reference/android/view/View.html#attr_android:onClick
		// Method Documentation: N/A
		responders.put("android:onClick", new AttributeResponder("android:onClick") {
			@Override
			public void respond(UniverseManipulator um, File xmlLayoutFile, Element element, Q activities, UniverseManipulator.Manipulation parent, String attributeValue) {
				// add the declared xml attribute
				UniverseManipulator.Manipulation attributeNode = addXMLAttributeToParent(um, xmlLayoutFile, element, parent, attributeValue);
				// add a call edge from the xml attribute node to the activity method defined in the attribute value
				addCallEdgeToActivityMethod(um, activities, attributeValue, attributeNode);
			}

		});
		
		responders.put("android:id", new AttributeResponder("android:id") {

			@Override
			public void respond(UniverseManipulator um, File xmlLayoutFile, Element element, Q activity, Manipulation parent, String attributeValue) {
				// TODO Auto-generated method stub
				addXMLAttributeToParent(um, xmlLayoutFile, element, parent, attributeValue);	
			}	
		
	});

		return responders;
	}

}