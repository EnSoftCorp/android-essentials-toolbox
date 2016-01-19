package com.ensoftcorp.open.android.essentials.resources.xml_layouts;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.index.common.SourceCorrespondence;
import com.ensoftcorp.atlas.core.query.Attr.Edge;
import com.ensoftcorp.atlas.core.query.Attr.Node;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;
import com.ensoftcorp.atlas.java.core.index.summary.SummaryGraph;
import com.ensoftcorp.atlas.java.core.script.Common;
import com.ensoftcorp.atlas.java.core.script.CommonQueries;
import com.ensoftcorp.open.android.essentials.resources.XMLInterfaceTags;
import com.ensoftcorp.open.toolbox.commons.SetDefinitions;
import com.ensoftcorp.open.toolbox.commons.utils.XMLUtils;

public abstract class AttributeResponder {

	protected String attributeName;
	public static AttrAndTags attrAndTags = new AttrAndTags();
	public AttributeResponder(String attributeName) {
		
		this.attributeName = attributeName;
	}

	public abstract void respond(UniverseManipulator um, File xmlLayoutFile, Element element, Q activity, UniverseManipulator.Manipulation parent, String attributeValue);

	protected UniverseManipulator.Manipulation addXMLAttributeToParent(UniverseManipulator um, File xmlLayoutFile, Element element, UniverseManipulator.Manipulation elementNode, String attributeValue) {
		SourceCorrespondence sc = XMLUtils.xmlElementToSourceCorrespondence(xmlLayoutFile, element);

		// insert the attribute node
		Set<String> attributeTags = new HashSet<String>(attrAndTags.tags);
		attributeTags.add(XMLInterfaceTags.XML_ATTRIBUTE.toString());
		Map<String, Object> attributeAttributes = new HashMap<String, Object>(attrAndTags.attributes);
		
		// TODO: Consider breaking the attribute/value node up into multiple nodes
		attributeAttributes.put(Node.NAME, attributeName + "=\"" + attributeValue + "\"");
		attributeAttributes.put(Node.SC, sc);
		UniverseManipulator.Manipulation attributeNode = um.createNode(attributeTags, attributeAttributes);

		// TODO: implement
//		// add the declares edge from the element to the attribute
//		AtlasMetaSchema xmlDeclaresEdgeSchema = AtlasMetaSchema.getForEdgeTag(Edge.DECLARES);
//		Set<String> xmlDeclaresEdgeTags = new HashSet<String>(attrAndTags.tags);
//		xmlDeclaresEdgeTags.addAll(xmlDeclaresEdgeSchema.getTags(SchemaRole.REQUIRED));
//		Map<String, Object> xmlDeclaresEdgeAttributes = new HashMap<String, Object>(attrAndTags.attributes);
//		xmlDeclaresEdgeAttributes.putAll(xmlDeclaresEdgeSchema.getAttributes(SchemaRole.REQUIRED));
//		xmlDeclaresEdgeAttributes.put(Node.SC, sc);
//		um.createEdge(xmlDeclaresEdgeTags, xmlDeclaresEdgeAttributes, elementNode, attributeNode);

		return attributeNode;
	}
	
	protected void addCallEdgeToActivityMethod(UniverseManipulator um, Q activities, String attributeValue, UniverseManipulator.Manipulation attributeNode) {

		Iterator<GraphElement> iter = activities.eval().nodes().iterator();
		while(iter.hasNext()){
			GraphElement activity = iter.next();
			// just get the immediate methods
			Q activityMethods = Common.edges(Edge.DECLARES).forwardStep(Common.toQ(Common.toGraph(activity))).intersection(SetDefinitions.app()).nodesTaggedWithAny(Node.METHOD);

			// filter declared activity methods by methods that have a single parameter of android.view.View
			LinkedList<Q> params = new LinkedList<Q>();
			params.add(Common.typeSelect("android.view", "View"));
			Q signatureMethods = Common.signature(activityMethods, params);

			// filter the signatureMethods by methods containing the name of the attribute value
			Q signatureMethodsWithCorrectMethodName = CommonQueries.nodesMatchingRegex(attributeValue).intersection(signatureMethods);

			// we "should" just have one method at this point
			GraphElement method = signatureMethodsWithCorrectMethodName.eval().nodes().getFirst();

			// a null case at this point is either a bug in our code
			// OR a potential runtime exception if the method declared in
			// the XML is not present in the source
			if (method != null) {
				// add call edge from xml attribute node to method
//				AtlasMetaSchema callEdgeSchema = AtlasMetaSchema.getForEdgeTag(Edge.CALL); // uncomment this line for CALL edge clone
				Set<String> xmlCallEdgeTags = new HashSet<String>(attrAndTags.tags);
				xmlCallEdgeTags.add(XMLInterfaceTags.XML_CALL.toString()); // remove this line for CALL edge clone
//				xmlCallEdgeTags.addAll(callEdgeSchema.getTags(SchemaRole.REQUIRED)); // uncomment this line for CALL edge clone
				Map<String, Object> xmlCallAttributes = new HashMap<String, Object>(attrAndTags.attributes);
//				xmlCallAttributes.putAll(callEdgeSchema.getAttributes(SchemaRole.REQUIRED)); // uncomment this line for CALL edge clone
				LinkedList<SourceCorrespondence> sc = new LinkedList<SourceCorrespondence>();
				sc.add((SourceCorrespondence) method.attr().get(Node.SC));
				xmlCallAttributes.put(SummaryGraph.SummaryEdge.LIST_SC_RANGE, sc);
				um.createEdge(xmlCallEdgeTags, xmlCallAttributes, attributeNode, method);
			}
		}
	}

}
