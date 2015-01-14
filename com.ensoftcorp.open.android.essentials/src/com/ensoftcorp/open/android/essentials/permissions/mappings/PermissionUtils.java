package com.ensoftcorp.open.android.essentials.permissions.mappings;

import java.util.ArrayList;
import java.util.List;

import com.ensoftcorp.atlas.core.db.graph.Graph;
import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.graph.GraphElement.EdgeDirection;
import com.ensoftcorp.atlas.core.db.graph.GraphElement.NodeDirection;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.query.Attr.Edge;
import com.ensoftcorp.atlas.core.query.Attr.Node;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.java.core.script.Common;

/**
 * A helper class for applying Permission protected API method tags
 * @author Yuqing Chen, Jon Mathews, Ben Holland, Zach Lones
 */
public class PermissionUtils {
	
	/**
	 * A method which find the methods matching the name, declarative structure, and parameters
	 * Tag the matched methods with corresponding permission tag.
	 * <p>
	 * <ul>
	 * <li>Expected format is based on PScout mappings.
	 * <li>Class names are essentially the binary names.
	 * <li>Constructors are always named &lt;init&gt;.
	 * </ul>
	 * 
	 * @param context
	 *            A context from which to start searching
	 *            
	 * @param packageName
	 *            The package containing the class(es) and method
	 *            
	 * @param className
	 *            The type name, essentially the binary name (unqualified)
	 *            
	 * @param parameters
	 *            fully qualified parameter type names of the method
	 * @param tag
	 * 			 a set of tags to apply to the method
	 *           
	 * @return the GraphElement, or null if method could not be found
	 */
	public static GraphElement tagMethod(Q context, String packageName, String className, String methodName, String[] parameters, String... tags) {
		
		Q methods;
		// Get all methods with the same name to methodName
		//if method is constructor, compare to input classNames
		if (methodName.equals("<init>")){
			methods = Common.universe().selectNode(Node.NAME, className).nodesTaggedWithAny(Node.IS_CONSTRUCTOR);
		}else{
			// FIXME: technically, this search must exclude constructors. XCSG
			// will have dedicated tags for non-constructors, which will be
			// faster than using Q.difference(). In the meantime, this corner
			// case is not affecting PScout mappings.
			methods = Common.universe().selectNode(Node.NAME, methodName).nodesTaggedWithAll(Node.METHOD);
		}
				
		//find methods nodes with matched parameter type names
		List<Q> allParamTypeNodes = new ArrayList<Q>();
		for (String parameter : parameters){
			String elementTypeName = parameter;
			Q paramTypeNode;
			//the array dimension of parameter type's array type
			int arrayDim = 0;
			if (parameter.contains("[]")){
				//get element type name for comparing
				elementTypeName = parameter.substring(0, parameter.indexOf("["));
				//count array dimension
				for (int i=0; i<parameter.length(); i++){
					if (parameter.charAt(i) == '[')
						arrayDim++;
				}
			}
			// match parameter type names
			paramTypeNode = Common.universe().selectNode(Node.BINARY_NAME,
					elementTypeName);
			
			//if array type exists, use the array type to represent type node
			if (arrayDim !=0){
				paramTypeNode = Common.universe().reverseStep(paramTypeNode).nodesTaggedWithAll(Node.ARRAY_TYPE).selectNode(Node.DIMENSION, new Integer(arrayDim));
			}
			if (paramTypeNode.eval().nodes().size()!=0)		
				allParamTypeNodes.add(paramTypeNode);
		}
		
		//use public methods from Common to get matched methods
		methods = Common.signature(methods, allParamTypeNodes);
		
		//Get nodes representing selected methods
		AtlasSet<GraphElement> nodes = methods.eval().nodes();
		
		for (GraphElement node : nodes){
			//get the parent of the method node
			GraphElement parent = null;
			AtlasSet<GraphElement> es = Graph.U.edges(node, NodeDirection.IN);
			for (GraphElement e : es) {
				if (e.tags().contains(Edge.DECLARES)) {
					parent = e.getNode(EdgeDirection.FROM);
					break;
				}
			}
			
			// compare parent binary name to input qualified class name
			// if binary name not available, skip it
			if (parent == null){
				continue;
			} else if (parent.attr().get(Node.BINARY_NAME) == null){
				continue;
			} else if (parent.attr().get(Node.BINARY_NAME).equals(packageName + "." + className)){
				for(String tag : tags){
					node.tags().add(tag);
				}
				return node;
			}
		}
		return null;
	}
	
	/**
	 * A method to return the method given the universe as a context
	 * 
	 * @param packageName
	 *            The package containing the class(es) and method
	 * @param className
	 *            A String containing the classes going from outermost class to
	 *            innermost class delimited by $ Assume anonymous inner classes
	 *            start with a number and non-anonymous classes do not.
	 * @param methodName
	 *            name of the method
	 * @param parameters
	 *            array containing fully qualified name of parameter types for the method
	 * @param tags
	 * 			  a set of tags to apply to the method
	 * @return
	 */
	public static GraphElement tagMethod(String packageName, String className, String methodName, String[] parameters, String... tags) {
		return tagMethod(Common.universe(), packageName, className, methodName, parameters, tags);
	}

}
