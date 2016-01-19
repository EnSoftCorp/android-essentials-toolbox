package com.ensoftcorp.open.android.essentials.resources;

/**
 * Tags to define the manifest value types. Valid types gathered from
 * http://developer.android.com/guide/topics/manifest/meta-data-element.html
 * From Android Source:
 * "// <meta-data> only supports string, integer, float, color, boolean, and resource reference types"
 * 
 * Note: Added MANIFEST_ prefix to tag to differentiate from other tag resource
 * types
 * 
 * @author Ben Holland
 */
public enum AndroidManifestMetaDataTypeTags {
	MANIFEST_STRING("MANIFEST_STRING"), 
	MANIFEST_INTEGER("MANIFEST_INTEGER"), 
	MANIFEST_FLOAT("MANIFEST_FLOAT"), 
	MANIFEST_COLOR("MANIFEST_COLOR"), 
	MANIFEST_BOOLEAN("MANIFEST_BOOLEAN"), 
	MANIFEST_RESOURCE_REFERENCE("MANIFEST_RESOURCE_REFERENCE");
	

	private final String tag;

	private AndroidManifestMetaDataTypeTags(final String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return tag;
	}

	// descriptions ripped off from: https://developer.android.com/guide/topics/manifest/meta-data-element.html
	public String description() {
		switch (this) {
		case MANIFEST_STRING:
			return "String value, using double backslashes (\\) to escape characters - such as \"\\n\" and \"\\uxxxxx\" for a Unicode character.";
		case MANIFEST_INTEGER:
			return "Integer value, such as \"100\".";
		case MANIFEST_FLOAT:
			return "Float value, such as \"1.23\".";
		case MANIFEST_COLOR:
			return "Color value, in the form \"#rgb\", \"#argb\", \"#rrggbb\", or \"#aarrggbb\".";
		case MANIFEST_BOOLEAN:
			return "Boolean value, either \"true\" or \"false\".";
		case MANIFEST_RESOURCE_REFERENCE:
			return "A reference to a resource. The ID of the resource is the value assigned to the item. "
					+ "The ID can be retrieved from the meta-data Bundle by the Bundle.getInt() method.";		
		default:
			return "";
		}
	}
}