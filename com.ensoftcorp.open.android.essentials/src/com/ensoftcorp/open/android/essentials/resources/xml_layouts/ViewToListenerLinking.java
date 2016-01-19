package com.ensoftcorp.open.android.essentials.resources.xml_layouts;

import static com.ensoftcorp.atlas.core.script.Common.toQ;
import static com.ensoftcorp.atlas.core.script.CommonQueries.data;
import static com.ensoftcorp.atlas.core.script.CommonQueries.declarations;
import static com.ensoftcorp.atlas.core.script.CommonQueries.isEmpty;
import static com.ensoftcorp.atlas.core.script.CommonQueries.nodesContaining;
import static com.ensoftcorp.atlas.java.core.script.CommonQueries.methodParameter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.graph.GraphElement.EdgeDirection;
import com.ensoftcorp.atlas.core.db.graph.operation.InducedGraph;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.query.Attr.Edge;
import com.ensoftcorp.atlas.core.query.Attr.Node;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.CommonQueries.TraversalDirection;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;
import com.ensoftcorp.atlas.core.script.UniverseManipulator.Manipulation;
import com.ensoftcorp.atlas.java.core.script.Common;
import com.ensoftcorp.open.android.essentials.resources.XMLInterfaceTags;
import com.ensoftcorp.open.toolbox.commons.SetDefinitions;

public class ViewToListenerLinking {

	public static HashMap<GraphElement, String> thisCallSiteIdMap = new HashMap<GraphElement, String>();
	public static HashMap<String, GraphElement> callSiteParamMap = new HashMap<String, GraphElement>();

	public static HashMap<GraphElement, String> fVByIDReturnCallSiteMap = new HashMap<GraphElement, String>();
	public static HashMap<String, GraphElement> fVByIDParamCallSiteMap = new HashMap<String, GraphElement>();
	public static Q result = Common.empty();
	private static final String XML_ONCLICK = "XML_ONCLICK";

	public static Q testEnvelope() {
		//result = Common.empty();
		linkViewsInSourceToOnClickMethods();
		return result;
	}

	public static HashMap<GraphElement, String> getThisCallSiteIdMap() {
		return thisCallSiteIdMap;
	}

	public static HashMap<String, GraphElement> getCallSiteParamMap() {
		return callSiteParamMap;
	}

	public static HashMap<GraphElement, String> getfVByIDReturnCallSiteMap() {
		return fVByIDReturnCallSiteMap;
	}

	public static HashMap<String, GraphElement> getfVByIDParamCallSiteMap() {
		return fVByIDParamCallSiteMap;
	}

	/**
	 * @author Sandeep Krishnan
	 */
	public static void linkViewsInSourceToOnClickMethods() {

		Set<Manipulation> edgeAdditions = new HashSet<Manipulation>();
		UniverseManipulator um = new UniverseManipulator();

		Map<String, Object> attr;
		Set<String> tags = new HashSet<String>();
		
		AtlasSet<GraphElement> resNodes = new AtlasHashSet<GraphElement>();
		AtlasSet<GraphElement> resEdges = new AtlasHashSet<GraphElement>();

		Q listeners = Common.methodSelect("android.view", "View", "setOnClickListener");
		listeners = listeners.union(Common.methodSelect("android.view", "View", "setOnLongClickListener"));
		listeners = Common.edges(Edge.OVERRIDES).reverse(listeners).difference(SetDefinitions.app());

		// There may be multiple clickListeners, so iterating over them
		// Iterator<GraphElement> listenerIterator =
		// listeners.eval().nodes().iterator();

		// mapping THIS. nodes in app to the call site id of corresponding edge
		Q listenerThisNodes = mapListenerThisNodesToCallSiteID(listeners);

		// Mapping params to call site ids.
		mapListenerParamsToCallSiteID(listeners);

		Q onClickMethodsInJar = Common.methodSelect("android.view", "View", "onClick");
		onClickMethodsInJar = onClickMethodsInJar.union(Common.methodSelect("android.view", "View", "onLongClick"));
		onClickMethodsInJar = onClickMethodsInJar.union(Common.edges(Edge.OVERRIDES).reverse(onClickMethodsInJar)).difference(SetDefinitions.app());
		Q onClickMethodsInApp = Common.stepFrom(Common.edges(Edge.OVERRIDES), onClickMethodsInJar).intersection(SetDefinitions.app());

		// Find views referenced using findViewById() and which have
		// setOnClickListener() called on them.
		Q findViewByIdMethodInJar = Common.methodSelect("android.app", "Activity", "findViewById");
		Q findViewByIdParam = methodParameter(findViewByIdMethodInJar, 0);
		Q findViewByIdReturn = declarations(findViewByIdMethodInJar, TraversalDirection.FORWARD).nodesTaggedWithAny(Node.IS_MASTER_RETURN);
		Q forwardFromReturn = data(findViewByIdReturn, TraversalDirection.FORWARD);
		Q listenerThisNodesInApp = Common.stepFrom(Common.edges(Edge.DF_INTERPROCEDURAL), listenerThisNodes);
		Q fVBIdAndSetOnClickListener = forwardFromReturn.intersection(listenerThisNodesInApp);
		Q betweenReturnAndFVBId = Common.edges(Edge.DATA_FLOW).between(findViewByIdReturn, fVBIdAndSetOnClickListener);

		// Map return edges from findViewById with call site ids.
		mapFindViewByIdReturnToCallSiteID(findViewByIdReturn, betweenReturnAndFVBId);

		// Map params of findViewById to their call site ids
		mapFindViewByIDParamToCallSiteID(findViewByIdParam);

		Q declarationsInR = declarations(Common.types("R"), TraversalDirection.FORWARD);
		Q allInputEventNodes = Common.universe().nodesTaggedWithAny(XMLInterfaceTags.INPUT_EVENT.toString());

		Iterator<Entry<GraphElement, String>> iterator = fVByIDReturnCallSiteMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<GraphElement, String> next = iterator.next();
			GraphElement fVBIdArg = fVByIDParamCallSiteMap.get(next.getValue());

			Q fVBIdArgQ = Common.toQ(Common.toGraph(fVBIdArg));
			Q variableNodeInR = data(fVBIdArgQ, TraversalDirection.REVERSE).intersection(declarationsInR).nodesTaggedWithAll(Node.VARIABLE, Node.IS_STATIC, Node.IS_FINAL);
			String variableNodeName = variableNodeInR.eval().nodes().getFirst().attr().get(Node.NAME).toString();

			// This is the node in xml with the interested id. Get its parent
			// (View or Button, etc.)
			Q idNodesInXmlWithVarName = nodesContaining(allInputEventNodes, variableNodeName);
			result = result.union(idNodesInXmlWithVarName);
			Q parentsOfIdNodes = Common.stepFrom(Common.universe().edgesTaggedWithAny(Edge.DECLARES),idNodesInXmlWithVarName);
			result = result.union(parentsOfIdNodes);
			// Get the corresponding onClick method of the setOnClickListener to
			// which connection is to be made
			GraphElement cur = next.getKey();
			Q curQ = Common.toQ(Common.toGraph(cur));
			GraphElement listenerThisNode = betweenReturnAndFVBId.forward(curQ).intersection(listenerThisNodesInApp).eval().nodes().getFirst();
			String listenerThisCallSiteId = thisCallSiteIdMap.get(listenerThisNode);
			GraphElement paramNodeInApp = callSiteParamMap.get(listenerThisCallSiteId);
			Q paramNodeInAppQ = Common.toQ(Common.toGraph(paramNodeInApp));
			Q parentActivityType = Common.stepTo(Common.edges(Edge.TYPEOF), paramNodeInAppQ);
			Q onClickMethod = declarations(parentActivityType, TraversalDirection.FORWARD).intersection(onClickMethodsInApp);
			result = result.union(onClickMethod);
			if (!isEmpty(parentsOfIdNodes) && !isEmpty(onClickMethod)) {
				AtlasSet<GraphElement> sources = parentsOfIdNodes.eval().nodes();
				AtlasSet<GraphElement> dest = onClickMethod.eval().nodes();
				resNodes.addAll(sources);
				resNodes.addAll(dest);
				attr = new HashMap<String, Object>();
				tags.add(XML_ONCLICK);
				attr.put(Edge.NAME, XML_ONCLICK);
				
				edgeAdditions.add(um.createEdge(tags, attr, sources, dest));
				// sources.add(idNodesInXmlWithVarName.eval().nodes());
				// destinations.add(onClickMethod.eval().nodes());
			}
		}
		um.perform();
		for(Manipulation m : edgeAdditions) resEdges.addAll(m.getResult());
		result=result.union(toQ(new InducedGraph(resNodes, resEdges)));
		
	}

	public static void mapFindViewByIDParamToCallSiteID(Q findViewByIdParam) {
		Q fVByIDParamReverseStep = Common.edges(Edge.DF_INTERPROCEDURAL).reverseStep(findViewByIdParam);
		Iterator<GraphElement> fVByIDParamEdgeIterator = fVByIDParamReverseStep.eval().edges().iterator();
		while (fVByIDParamEdgeIterator.hasNext()) {
			GraphElement curfVByIDEdge = fVByIDParamEdgeIterator.next();
			GraphElement curfVByIDArgNode = curfVByIDEdge.getNode(EdgeDirection.FROM);
			String curfVByIDEdgeCallSiteID = curfVByIDEdge.attr().get(Edge.CALL_SITE_ID).toString();
			fVByIDParamCallSiteMap.put(curfVByIDEdgeCallSiteID, curfVByIDArgNode);
		}
	}

	public static void mapFindViewByIdReturnToCallSiteID(Q findViewByIdReturn, Q betweenReturnAndFVBId) {
		Q forwardStepEdgesFromReturn = betweenReturnAndFVBId.forwardStep(findViewByIdReturn);
		Iterator<GraphElement> forwardStepEdgesFromReturnIterator = forwardStepEdgesFromReturn.eval().edges().iterator();
		while (forwardStepEdgesFromReturnIterator.hasNext()) {
			GraphElement curForwardStepEdge = forwardStepEdgesFromReturnIterator.next();
			String curCallsiteID = curForwardStepEdge.attr().get(Edge.CALL_SITE_ID).toString();
			GraphElement curForwardStepNode = curForwardStepEdge.getNode(EdgeDirection.TO);
			fVByIDReturnCallSiteMap.put(curForwardStepNode, curCallsiteID);
		}
	}

	public static void mapListenerParamsToCallSiteID(Q listeners) {
		Q listenerParams = methodParameter(listeners, 0);
		Q listenerParamConnections = Common.edges(Edge.DF_INTERPROCEDURAL).reverseStep(listenerParams);
		Iterator<GraphElement> paramEdgeIterator = listenerParamConnections.eval().edges().iterator();
		while (paramEdgeIterator.hasNext()) {
			GraphElement curParamEdge = paramEdgeIterator.next();
			String curParamEdgeCallSiteId = curParamEdge.attr().get(Edge.CALL_SITE_ID).toString();
			GraphElement curEdgeParamNodeInApp = curParamEdge.getNode(EdgeDirection.FROM);
			if (!callSiteParamMap.containsKey(curParamEdgeCallSiteId))
				callSiteParamMap.put(curParamEdgeCallSiteId, curEdgeParamNodeInApp);
		}
	}

	public static Q mapListenerThisNodesToCallSiteID(Q listeners) {
		Q listenerThisNodes = Common.universe().forwardStep(listeners).nodesTaggedWithAny(Node.IS_THIS);
		Q listenerThisConnections = Common.edges(Edge.DF_INTERPROCEDURAL).reverseStep(listenerThisNodes);
		Iterator<GraphElement> thisEdgeIterator = listenerThisConnections.eval().edges().iterator();
		while (thisEdgeIterator.hasNext()) {
			GraphElement curThisEdge = thisEdgeIterator.next();
			String curThisEdgeCallSiteId = curThisEdge.attr().get(Edge.CALL_SITE_ID).toString();
			GraphElement curEdgeThisNodeInApp = curThisEdge.getNode(EdgeDirection.FROM);
			if (!thisCallSiteIdMap.containsValue(curThisEdgeCallSiteId))
				thisCallSiteIdMap.put(curEdgeThisNodeInApp, curThisEdgeCallSiteId);
		}
		return listenerThisNodes;
	}
}
