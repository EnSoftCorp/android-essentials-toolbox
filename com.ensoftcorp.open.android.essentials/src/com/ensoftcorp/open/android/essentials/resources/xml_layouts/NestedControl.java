package com.ensoftcorp.open.android.essentials.resources.xml_layouts;

import java.io.File;

import org.w3c.dom.Element;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;

public abstract class NestedControl extends Control {

	public NestedControl(UniverseManipulator um, Q activities, Element element, File xmlLayoutFile) {
		super(um, activities, element, xmlLayoutFile);
	}

}
