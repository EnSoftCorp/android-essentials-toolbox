package com.ensoftcorp.open.android.essentials.resources;
/**
 * Tracks all tags used by the toolbox to make resource indirection (through R)
 * explicit.
 * 
 * @author Tom Deering
 */
public enum ResourceLinkingTags {
	RESOURCE("RESOURCE"),
	RESOURCE_ROOT("RESOURCE_ROOT"),
	RESOURCE_TYPE("RESOURCE_TYPE"),
	RESOURCE_VALUE("RESOURCE_VALUE"),
	RESOURCE_ANIMATION_PROPERTY("RESOURCE_ANIMATION_PROPERTY"),
	RESOURCE_ANIMATION_VIEW("RESOURCE_ANIMATION_VIEW"),
	RESOURCE_BOOL("RESOURCE_BOOL"),
	RESOURCE_COLOR("RESOURCE_COLOR"),
	RESOURCE_COLOR_STATELIST("RESOURCE_COLOR_STATELIST"),
	RESOURCE_DIMENSION("RESOURCE_DIMENSION"),
	RESOURCE_DRAWABLE("RESOURCE_DRAWABLE"),
	RESOURCE_ID("RESOURCE_ID"),
	RESOURCE_INTEGER("RESOURCE_INTEGER"),
	RESOURCE_INTEGER_ARRAY("RESOURCE_INTEGER_ARRAY"),
	RESOURCE_LAYOUT("RESOURCE_LAYOUT"),
	RESOURCE_MENU("RESOURCE_MENU"),
	RESOURCE_PLURALS("RESOURCE_PLURALS"),
	RESOURCE_RAW("RESOURCE_RAW"),
	RESOURCE_STRING("RESOURCE_STRING"),
	RESOURCE_STRING_ARRAY("RESOURCE_STRING_ARRAY"),
	RESOURCE_STYLE("RESOURCE_STYLE"),
	RESOURCE_TYPED_ARRAY("RESOURCE_TYPED_ARRAY"),
	RESOURCE_XML("RESOURCE_XML"),
	MANIFEST("MANIFEST");

	private final String tag;

	private ResourceLinkingTags(final String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return tag;
	}

	public String description() {
		switch (this) {
			case RESOURCE:
				return "Any node or edge inserted in order to link project resources";
			case RESOURCE_ROOT:
				return "The root resource type node";
			case RESOURCE_TYPE:
				return "A node inserted to represent a resource type";
			case RESOURCE_VALUE:
				return "A node inserted to represent a resource value";
			case RESOURCE_ANIMATION_PROPERTY:
				return "Any node or edge inserted to represent animation property resources";
			case RESOURCE_ANIMATION_VIEW:
				return "Any node or edge inserted to represent animation view resources";
			case RESOURCE_BOOL:
				return "Any node or edge inserted to represent boolean constant resources";
			case RESOURCE_COLOR:
				return "Any node or edge inserted to represent color resources";
			case RESOURCE_COLOR_STATELIST:
				return "Any node or edge inserted to represent color statelist resources";
			case RESOURCE_DIMENSION:
				return "Any node or edge inserted to represent dimension resources";
			case RESOURCE_DRAWABLE:
				return "Any node or edge inserted to represent drawable resources";
			case RESOURCE_ID: 
				return "Any node or edge inserted to represent ID resources";
			case RESOURCE_INTEGER:
				return "Any node or edge inserted to represent integer constant resources";
			case RESOURCE_INTEGER_ARRAY:
				return "Any node or edge inserted to represent integer array resources";
			case RESOURCE_LAYOUT:
				return "Any node or edge inserted to represent layout resources";
			case RESOURCE_MENU:
				return "Any node or edge inserted to represent menu resources";
			case RESOURCE_PLURALS:
				return "Any node or edge inserted to represent plural strings";
			case RESOURCE_RAW:
				return "Any node or edge inserted to represent raw (arbitrary) resources";
			case RESOURCE_STRING:
				return "Any node or edge inserted to represent String constants";
			case RESOURCE_STRING_ARRAY:
				return "Any node or edge inserted to represent String array constants";
			case RESOURCE_STYLE:
				return "Any node or edge inserted to represent the UI style";
			case RESOURCE_TYPED_ARRAY:
				return "Any node or edge inserted to represent typed array  resources";
			case RESOURCE_XML:
				return "Any node or edge inserted to represent  other XML resources";
			case MANIFEST:
				return "Any node or edge inserted to represent other MANIFEST XML resources";
			default:
				return "";
		}
	}
}