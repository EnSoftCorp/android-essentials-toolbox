package com.ensoftcorp.open.android.essentials.resources.xml_layouts;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;

public abstract class Control {

	protected Q activities;
	protected Element element;
	protected Map<String, AttributeResponder> responders;
	protected LinkedList<NestedControl> nestedControls;
	protected UniverseManipulator um;
	protected File xmlLayoutFile;

	protected Control(UniverseManipulator um, Q activities, Element element, File xmlLayoutFile) {
		this.um = um;
		this.activities = activities;
		this.element = element;
		this.xmlLayoutFile = xmlLayoutFile;
		this.responders = new HashMap<String, AttributeResponder>();
		this.nestedControls = new LinkedList<NestedControl>();
	}

	public Map<String, AttributeResponder> getAllAttributeResponders() {
		return responders;
	}

	public void respondToAttributes(UniverseManipulator.Manipulation node) {
		NamedNodeMap attributes = element.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			String attributeName = attributes.item(i).getNodeName();
			String attributeValue = attributes.item(i).getNodeValue();
			if (responders.containsKey(attributeName)) {
				responders.get(attributeName).respond(um, xmlLayoutFile, element, activities, node, attributeValue);
			}
		}

		// respond to all nested controls and initialize if needed
		for (NestedControl nestedControl : nestedControls) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String attributeName = attributes.item(i).getNodeName();
				String attributeValue = attributes.item(i).getNodeValue();
				if (nestedControl.responders.containsKey(attributeName)) {
					nestedControl.responders.get(attributeName).respond(um, xmlLayoutFile, element, activities, node, attributeValue);
				}
			}
		}
	}
}
