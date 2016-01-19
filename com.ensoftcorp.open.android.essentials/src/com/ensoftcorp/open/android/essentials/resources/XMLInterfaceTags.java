package com.ensoftcorp.open.android.essentials.resources;

public enum XMLInterfaceTags {
	XML_INTERFACE_INDEX("XML_INTERFACE_INDEX"), 
	INPUT_EVENT("INPUT_EVENT"),
	XML_CALL("XML_CALL"),
	LAYOUT_DEFAULT("LAYOUT_DEFAULT"),
	LAYOUT_OTHERS("LAYOUT_OTHERS"),
	XML_ELEMENT("XML_ELEMENT"),
	XML_ATTRIBUTE("XML_ATTRIBUTE");

	private String tag;

	private XMLInterfaceTags(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return tag;
	}

	public String description() {
		switch (this) {
		case XML_INTERFACE_INDEX:
			return "A tag to represent the view for nodes and edges inserted as part of indexing the Android XML Layouts";
		case INPUT_EVENT:
			return "A tag to represent a method that is invoked following an XML component input event (example: Button onClick)";
		case XML_CALL:
			return "An a tag for an inserted edge similiar to what would be a CALL edge from an XML element onClick event to the corresponding method";
		case LAYOUT_DEFAULT:
			return "An added tag to identify whether from default layout in res folder";
		case LAYOUT_OTHERS:
			return "An added tag to identify whether from non-default layout in res folder";
		case XML_ELEMENT:
			return "A tag that identifies an inserted XML Element node.";
		case XML_ATTRIBUTE:
			return "A tag that identifies an inserted XML Attribute node.";
		default:
			return "";
		}
	}

}
