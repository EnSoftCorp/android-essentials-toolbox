package com.ensoftcorp.open.android.essentials.resources;

import static com.ensoftcorp.atlas.core.script.Common.edges;
import static com.ensoftcorp.atlas.core.script.Common.methodSelect;
import static com.ensoftcorp.atlas.core.script.Common.toQ;
import static com.ensoftcorp.atlas.core.script.Common.typeSelect;
import static com.ensoftcorp.atlas.core.script.Common.types;
import static com.ensoftcorp.atlas.core.script.Common.universe;
import static com.ensoftcorp.atlas.core.script.CommonQueries.TraversalDirection.FORWARD;
import static com.ensoftcorp.atlas.java.core.script.Common.stepFrom;
import static com.ensoftcorp.atlas.java.core.script.Common.stepTo;
import static com.ensoftcorp.atlas.java.core.script.CommonQueries.methodParameter;
import static com.ensoftcorp.atlas.java.core.script.CommonQueries.methodReturn;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.ensoftcorp.atlas.core.db.graph.Address;
import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.graph.NodeGraph;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.query.Attr;
import com.ensoftcorp.atlas.core.query.Attr.Edge;
import com.ensoftcorp.atlas.core.query.Attr.Node;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator.EmptyManipulation;
import com.ensoftcorp.atlas.core.script.UniverseManipulator.Manipulation;
import com.ensoftcorp.atlas.java.core.script.CommonQueries;
import com.ensoftcorp.open.android.essentials.AndroidManifest;
import com.ensoftcorp.open.toolbox.commons.utils.XMLUtils;

/**
 * A class to make the project resource files (XML, icons, etc) have explicit
 * index representation.
 * 
 * (1) Creates a master ProjectResources type (2) For each resource type,
 * creates a type to represent the resource, declared by the master (2a) Creates
 * a network of literal nodes to represent the resource (XML or other) (3) Adds
 * DF_LOCAL links from literal nodes to R constants as appropriate
 * 
 * All added nodes and edges are tagged with Tag.RESOURCE for easy selection
 * 
 * Usage Examples: var ri = new ResourceIndexer("connectbot") ri.index()
 * ri.clear()
 * 
 * @author Tom Deering
 * @author Ben Holland (manifest resources)
 * 
 */
public class ResourceIndexer {
//	// Subfolders of res/
//	private static final String ANIMATOR_SUBFOLDER = "animator";
//	private static final String ANIM_SUBFOLDER = "anim";
//	private static final String COLOR_SUBFOLDER = "color";
//	private static final String DRAWABLE_SUBFOLDER = "drawable";
//	private static final String LAYOUT_SUBFOLDER = "layout";
//	private static final String MENU_SUBFOLDER = "menu";
//	private static final String RAW_SUBFOLDER = "raw";
//	private static final String VALUES_SUBFOLDER = "values";
//	private static final String XML_SUBFOLDER = "xml";
//
//	private String projectName;
//	
//	/**
//	 * Constructs a new ResourceIndexer for the given project.
//	 * 
//	 * @param projectName
//	 * @throws Exception
//	 */
//	public ResourceIndexer(String projectName) throws Exception {
//		this.projectName = projectName;
//	}
//
//	/**
//	 * Given the name of a project and types of resources to make explicit,
//	 * links them.
//	 * 
//	 * @param tags
//	 */
//	protected void doIndex(IProgressMonitor monitor, String... tags) {
//		// The path to the project res/ folder.
//		String resDirPath = projectRootPath + File.separatorChar + "res";
//
//		// The structure of resource types in the index
//		createResourceTypeStructure(projectName);
//
//		// Types declared under R
//		Q rTypes = CommonQueries.declarations(types("R"), FORWARD)
//				.nodesTaggedWithAny(Attr.Node.TYPE);
//
//		// Type nodes inserted by the ResourceIndexer
//		um.perform();
//		Q insertedTypeNodes = universe().nodesTaggedWithAll(
//				Attr.Node.TYPE, ResourceLinkingTags.RESOURCE.toString(),
//				ResourceLinkingTags.RESOURCE_TYPE.toString());
//
//		// find and read the manifest file into memory
//		Document androidManifest;
//		try {
//			AndroidManifest manifest = new AndroidManifest(
//					AndroidManifest.getManifestFile(projectName));
//			if (manifest.getManifestLocation().exists()) {
//				try {
//					DocumentBuilderFactory dbFactory = DocumentBuilderFactory
//							.newInstance();
//					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//					androidManifest = dBuilder.parse(manifest
//							.getManifestLocation());
//					androidManifest.getDocumentElement().normalize();
//				} catch (Exception e) {
//					// Project manifest file is corrupt
//					androidManifest = null;
//				}
//			} else {
//				// Project manifest file is missing
//				androidManifest = null;
//			}
//		} catch (Exception e) {
//			// Could not locate manifest file for project
//			androidManifest = null;
//		}
//
//		// Link each type as appropriate
//		Q literalType, accessMethods;
//		for (String t : tags) {
//
//			if (t instanceof ResourceLinkingTags) {
//				ResourceLinkingTags prl = (ResourceLinkingTags) t;
//				switch (prl) {
//				case RESOURCE_ANIMATION_PROPERTY:
//					linkFileResourcesToR(prl, resDirPath, ANIMATOR_SUBFOLDER,
//							"animator", rTypes, insertedTypeNodes);
//				case RESOURCE_ANIMATION_VIEW:
//					linkFileResourcesToR(prl, resDirPath, ANIM_SUBFOLDER,
//							"anim", rTypes, insertedTypeNodes);
//					break;
//				case RESOURCE_BOOL:
//					literalType = types("boolean").nodesTaggedWithAny(Node.PRIMITIVE_TYPE);
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//							methodSelect("android.content","Resources","getBoolean"));
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "bool", "name",
//							"bool", rTypes, insertedTypeNodes, literalType, accessMethods);
//					break;
//				case RESOURCE_COLOR:
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//							methodSelect("android.content","Resources","getColor"));
//					
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "color", "name",
//							"color", rTypes, insertedTypeNodes, null, accessMethods);
//					break;
//				case RESOURCE_COLOR_STATELIST:
//					linkFileResourcesToR(prl, resDirPath, COLOR_SUBFOLDER,
//							"color", rTypes, insertedTypeNodes);
//					break;
//				case RESOURCE_DIMENSION:
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//							methodSelect("android.content","Resources","getDimension"));
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "dimen", "name",
//							"dimen", rTypes, insertedTypeNodes, null, accessMethods);
//					break;
//				case RESOURCE_DRAWABLE:
//					linkFileResourcesToR(prl, resDirPath, DRAWABLE_SUBFOLDER,
//							"drawable", rTypes, insertedTypeNodes);
//					break;
//				case RESOURCE_ID:
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//							methodSelect("android.content","Resources","getValue"));
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "item", "name",
//							"id", rTypes, insertedTypeNodes, null, accessMethods);
//					break;
//				case RESOURCE_INTEGER:
//					literalType = types("int").nodesTaggedWithAny(Node.PRIMITIVE_TYPE);
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//						methodSelect("android.content","Resources","getInteger"));
//					
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "integer", "name",
//							"integer", rTypes, insertedTypeNodes, literalType, accessMethods);
//					break;
//				case RESOURCE_INTEGER_ARRAY:
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//							methodSelect("android.content","Resources","getIntArray"));
//					
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "integer-array",
//							"name", "array", rTypes, insertedTypeNodes, null, accessMethods);
//					break;
//				case RESOURCE_LAYOUT:
//					linkFileResourcesToR(prl, resDirPath, LAYOUT_SUBFOLDER,
//							"layout", rTypes, insertedTypeNodes);
//					break;
//				case RESOURCE_MENU:
//					linkFileResourcesToR(prl, resDirPath, MENU_SUBFOLDER,
//							"menu", rTypes, insertedTypeNodes);
//					break;
//				case RESOURCE_PLURALS:
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "plurals", "name",
//							"plurals", rTypes, insertedTypeNodes, null, null);
//					break;
//				case RESOURCE_RAW:
//					linkFileResourcesToR(prl, resDirPath, RAW_SUBFOLDER, "raw",
//							rTypes, insertedTypeNodes);
//					break;
//				case RESOURCE_STRING:
//					literalType = typeSelect("java.lang","String");
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//						methodSelect("android.content","Resources","getString").union(
//						methodSelect("android.content","Resources","getText"),
//						methodSelect("android.content","Context","getString")));
//					
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "string", "name",
//							"string", rTypes, insertedTypeNodes, literalType, accessMethods);
//					break;
//				case RESOURCE_STRING_ARRAY:
//					accessMethods = edges(Edge.OVERRIDES).reverse(
//							methodSelect("android.content","Resources","getStringArray").union(
//							methodSelect("android.content","Resources","getTextArray")));
//					
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "string-array",
//							"name", "array", rTypes, insertedTypeNodes, null, accessMethods);
//					break;
//				case RESOURCE_STYLE:
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "style", "name",
//							"style", rTypes, insertedTypeNodes, null, null);
//					break;
//				case RESOURCE_TYPED_ARRAY:
//					linkXMLResourcesToR(prl, projectName, resDirPath,
//							VALUES_SUBFOLDER, "resources", "array", "name",
//							"array", rTypes, insertedTypeNodes, null, null);
//					break;
//				case RESOURCE_XML:
//					linkFileResourcesToR(prl, resDirPath, XML_SUBFOLDER, "xml",
//							rTypes, insertedTypeNodes);
//					break;
//				default:
//					break;
//				}
//			}
//
//			if (t instanceof AndroidManifestMetaDataTypeTags) {
//				AndroidManifestMetaDataTypeTags prl = (AndroidManifestMetaDataTypeTags) t;
//				linkManifestResourceToBundle(prl, androidManifest,
//						insertedTypeNodes);
//			}
//		}
//	}
//	
//	
//
//	/**
//	 * If the type node for resources don't exist, create them and link them up.
//	 * 
//	 * @param prl
//	 */
//	private void createResourceTypeStructure(String projectName) {
//		Q resourceTypes = universe().nodesTaggedWithAll(Attr.Node.TYPE,
//				ResourceLinkingTags.RESOURCE.toString(),
//				ResourceLinkingTags.RESOURCE_TYPE.toString());
//
//		// Create the root type
//		Manipulation xmlResourceRoot = createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_ROOT, resourceTypes);
//		Manipulation manifestResourceRoot = createTypeIfNotExists(
//				ResourceLinkingTags.MANIFEST, resourceTypes);
//
//		// Have the project declare the root type
//		Q projectRoot = universe().nodesTaggedWithAny(Attr.Node.PROJECT)
//				.selectNode(Node.NAME, projectName);
//
//		Set<String> tags = new HashSet<String>();
//		tags.add(Attr.Edge.DECLARES);
//		Map<String, Object> attr = new HashMap<String, Object>();
//		contributeTagsAndAttributes(tags, attr);
//		um.createEdge(tags, attr, projectRoot.eval().nodes(), xmlResourceRoot);
//
//		// Create the individual xml resource types
//		Set<Manipulation> resourceTypeSet = new HashSet<Manipulation>();
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_ANIMATION_PROPERTY, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_ANIMATION_VIEW, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_BOOL, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_COLOR, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_COLOR_STATELIST, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_DIMENSION, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_DRAWABLE, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(ResourceLinkingTags.RESOURCE_ID,
//				resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_INTEGER, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_INTEGER_ARRAY, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_LAYOUT, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_MENU, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_PLURALS, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(ResourceLinkingTags.RESOURCE_RAW,
//				resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_STRING, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_STRING_ARRAY, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_STYLE, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(
//				ResourceLinkingTags.RESOURCE_TYPED_ARRAY, resourceTypes));
//		resourceTypeSet.add(createTypeIfNotExists(ResourceLinkingTags.RESOURCE_XML,
//				resourceTypes));
//
//		// Have the root type declare the xml resource subtypes
//		for (Manipulation m : resourceTypeSet)
//			um.createEdge(tags, attr, xmlResourceRoot, m);
//
//		// Create the individual manifest resource types
//		Set<Manipulation> manifestTypeSet = new HashSet<Manipulation>();
//		manifestTypeSet.add(createTypeIfNotExists(
//				AndroidManifestMetaDataTypeTags.MANIFEST_STRING, resourceTypes));
//		manifestTypeSet.add(createTypeIfNotExists(
//				AndroidManifestMetaDataTypeTags.MANIFEST_INTEGER, resourceTypes));
//		manifestTypeSet.add(createTypeIfNotExists(
//				AndroidManifestMetaDataTypeTags.MANIFEST_FLOAT, resourceTypes));
//		manifestTypeSet.add(createTypeIfNotExists(
//				AndroidManifestMetaDataTypeTags.MANIFEST_COLOR, resourceTypes));
//		manifestTypeSet.add(createTypeIfNotExists(
//				AndroidManifestMetaDataTypeTags.MANIFEST_BOOLEAN, resourceTypes));
//		manifestTypeSet.add(createTypeIfNotExists(
//				AndroidManifestMetaDataTypeTags.MANIFEST_RESOURCE_REFERENCE,
//				resourceTypes));
//
//		// Have the root type declare the manifest resource subtypes
//		for (Manipulation m : manifestTypeSet)
//			um.createEdge(tags, attr, manifestResourceRoot, m);
//	}
//
//	/**
//	 * If the type node for the given resource does not exist yet, creates it.
//	 * 
//	 * @param prl
//	 */
//	private Manipulation createTypeIfNotExists(ResourceLinkingTags prl,
//			Q resourceTypes) {
//		Q typeNode = resourceTypes.nodesTaggedWithAny(prl.toString());
//
//		if (CommonQueries.isEmpty(typeNode)) {
//			Set<String> tags = new HashSet<String>();
//			tags.add(prl.toString());
//			tags.add(ResourceLinkingTags.RESOURCE_TYPE.toString());
//			tags.add(Attr.Node.TYPE);
//			Map<String, Object> attr = new HashMap<String, Object>();
//			attr.put(Node.NAME, prl.toString());
//			contributeTagsAndAttributes(tags, attr);
//			return um.createNode(tags, attr);
//		}
//
//		return new EmptyManipulation(typeNode);
//	}
//
//	/**
//	 * If the type node for the given manifest resource does not exist yet,
//	 * creates it.
//	 * 
//	 * @param prl
//	 */
//	private Manipulation createTypeIfNotExists(
//			AndroidManifestMetaDataTypeTags prl, Q resourceTypes) {
//		Q typeNode = resourceTypes.nodesTaggedWithAny(prl.toString());
//
//		if (CommonQueries.isEmpty(typeNode)) {
//			Set<String> tags = new HashSet<String>();
//			tags.add(prl.toString());
//			tags.add(ResourceLinkingTags.RESOURCE_TYPE.toString());
//			tags.add(Attr.Node.TYPE);
//			Map<String, Object> attr = new HashMap<String, Object>();
//			attr.put(Node.NAME, prl.toString());
//			contributeTagsAndAttributes(tags, attr);
//			return um.createNode(tags, attr);
//		}
//
//		return new EmptyManipulation(typeNode);
//	}
//
//	private void linkManifestResourceToBundle(AndroidManifestMetaDataTypeTags prl,
//			Document androidManifest, Q insertedTypeNodes) {
//
//		// parse out the application package name
//		NodeList manifest = androidManifest.getElementsByTagName("manifest");
//		String packageName = "";
//		try {
//			packageName = ((Element) manifest.item(0)).getAttribute("package");
//		} catch (Exception e) {
//			if (manifest.getLength() == 0) {
//				// project manifest is missing the manifest element, return
//				// empty string
//			}
//			if (manifest.getLength() > 1) {
//				// project manifest has multiple manifest elements, return empty
//				// string
//			}
//		}
//
//		NodeList metaData = androidManifest.getElementsByTagName("meta-data");
//		for (int i = 0; i < metaData.getLength(); i++) {
//			Element metaDatum = (Element) metaData.item(i);
//			String metaDatumName = metaDatum.getAttribute("android:name");
//			String metaDatumValue = metaDatum.getAttribute("android:value");
//			String metaDatumResource = metaDatum
//					.getAttribute("android:resource");
//
//			// value is supplied as a name-value pair
//			// <meta-data> only supports string, integer, float, color, boolean,
//			// and resource reference types
//			if (!metaDatumName.equals("") && !metaDatumValue.equals("")) {
//
//				// data is explicitly a string
//				if (metaDatumValue.startsWith("@string/")) {
//					metaDatumValue = metaDatumValue.substring(8);
//					if (prl == AndroidManifestMetaDataTypeTags.MANIFEST_STRING)
//						insertBundleValueEdge(packageName, metaDatum,
//								metaDatumName, metaDatumValue,
//								AndroidManifestMetaDataTypeTags.MANIFEST_STRING,
//								insertedTypeNodes);
//				} else {
//					// identify possible data representations
//					boolean isString = true;
//
//					// Color value, in the form "#rgb", "#argb", "#rrggbb", or
//					// "#aarrggbb"
//					if (metaDatumValue.startsWith("#")) {
//						String color = metaDatumValue.substring(1);
//						boolean isHex = color.matches("[0-9A-F]+");
//						if (isHex
//								&& (color.length() == "rrggbb".length() || color
//										.length() == "aarrggbb".length())) {
//							boolean isColor = true;
//							try {
//								for (int j = 0; j < color.length(); j += 2) {
//									int colorComponent = Integer.parseInt(
//											color.substring(j, j + 2), 16);
//									if (!(colorComponent >= 0 && colorComponent <= 255)) {
//										isColor = false;
//									}
//								}
//							} catch (Exception e) {
//								isColor = false;
//							}
//
//							if (isColor) {
//								if (prl == AndroidManifestMetaDataTypeTags.MANIFEST_COLOR)
//									insertBundleValueEdge(
//											packageName,
//											metaDatum,
//											metaDatumName,
//											metaDatumValue,
//											AndroidManifestMetaDataTypeTags.MANIFEST_COLOR,
//											insertedTypeNodes);
//								isString = false;
//							}
//						}
//					}
//
//					// check if integer
//					boolean isInteger = true;
//					try {
//						Integer.parseInt(metaDatumValue);
//					} catch (Exception e) {
//						isInteger = false;
//					}
//					if (isInteger) {
//						if (prl == AndroidManifestMetaDataTypeTags.MANIFEST_INTEGER)
//							insertBundleValueEdge(
//									packageName,
//									metaDatum,
//									metaDatumName,
//									metaDatumValue,
//									AndroidManifestMetaDataTypeTags.MANIFEST_INTEGER,
//									insertedTypeNodes);
//						isString = false;
//					}
//
//					// if not integer check if float
//					if (!isInteger) {
//						boolean isFloat = true;
//						try {
//							Double.parseDouble(metaDatumValue);
//						} catch (Exception e) {
//							isFloat = false;
//						}
//						if (isFloat) {
//							if (prl == AndroidManifestMetaDataTypeTags.MANIFEST_FLOAT)
//								insertBundleValueEdge(
//										packageName,
//										metaDatum,
//										metaDatumName,
//										metaDatumValue,
//										AndroidManifestMetaDataTypeTags.MANIFEST_FLOAT,
//										insertedTypeNodes);
//							isString = false;
//						}
//					}
//
//					// check if boolean
//					boolean isBoolean = metaDatumValue.equalsIgnoreCase("TRUE")
//							|| metaDatumValue.equalsIgnoreCase("FALSE");
//					if (isBoolean) {
//						if (prl == AndroidManifestMetaDataTypeTags.MANIFEST_BOOLEAN)
//							insertBundleValueEdge(
//									packageName,
//									metaDatum,
//									metaDatumName,
//									metaDatumValue,
//									AndroidManifestMetaDataTypeTags.MANIFEST_BOOLEAN,
//									insertedTypeNodes);
//						isString = false;
//					}
//
//					// if not another type default to string
//					// note any value could be asked for in code as another
//					// type, this is just a best effort
//					if (isString) {
//						if (prl == AndroidManifestMetaDataTypeTags.MANIFEST_STRING)
//							insertBundleValueEdge(
//									packageName,
//									metaDatum,
//									metaDatumName,
//									metaDatumValue,
//									AndroidManifestMetaDataTypeTags.MANIFEST_STRING,
//									insertedTypeNodes);
//					}
//				}
//			}
//
//			// value is supplied as a resource
//			if (!metaDatumName.equals("") && !metaDatumResource.equals("")) {
//				insertBundleResourceReferenceEdge(metaDatum, metaDatumName,
//						metaDatumResource);
//			}
//		}
//	}
//
//	private void insertBundleValueEdge(String packageName, Element metaDatum,
//			String metaDatumName, String metaDatumValue,
//			AndroidManifestMetaDataTypeTags type, Q insertedTypeNodes) {
//		// insert the literal node
//		// TODO: implement source correspondence
//		Set<String> tags = new HashSet<String>();
//		tags.add(Node.DATA_FLOW);
//		tags.add(Node.IS_LITERAL);
//		tags.add(type.toString());
//		Map<String, Object> attr = new HashMap<String, Object>();
//		attr.put(Node.NAME, metaDatumValue);
//		contributeTagsAndAttributes(tags, attr);
//		Manipulation dataNode = um.createNode(tags, attr);
//		Q typeNode = insertedTypeNodes.selectNode(Node.NAME, type.toString());
//
//		tags = new HashSet<String>();
//		tags.add(Edge.DATA_FLOW);
//		tags.add(Edge.TYPEOF);
//		attr = new HashMap<String,Object>();
//		contributeTagsAndAttributes(tags, attr);
//		um.createEdge(tags, attr, dataNode, typeNode.eval().nodes());
//
//		// insert the named data flow node
//		// TODO: implement source correspondence
//		tags = new HashSet<String>();
//		tags.add(Node.DATA_FLOW);
//		attr = new HashMap<String,Object>();
//		attr.put(Node.NAME, metaDatumName);
//		contributeTagsAndAttributes(tags, attr);
//		Manipulation variableNode = um.createNode(tags, attr);
//
//		tags = new HashSet<String>();
//		tags.add(Edge.DATA_FLOW);
//		tags.add(Edge.DF_LOCAL);
//		attr = new HashMap<String,Object>();
//		contributeTagsAndAttributes(tags, attr);
//		um.createEdge(tags, attr, dataNode, variableNode);
//
//		// added an edge to the getBundle method for the meta-data parent
//		// container element
//		Q bundles = stepFrom(edges(Edge.TYPEOF),
//				typeSelect("android.os", "Bundle"));
//
//		// default connect to all bundle data nodes (this is the case when
//		// meta-data is applied at the application level)
//		if (metaDatum.getParentNode().getNodeName().equals("application")) {
//			contributeTagsAndAttributes(tags, attr);
//			um.createEdge(tags, attr, variableNode, bundles.eval().nodes());
//		} else {
//			// figure out which bundle data nodes to link to based on the parent
//			// container
//			String containerName = ((Element) metaDatum.getParentNode())
//					.getAttribute("android:name");
//			try {
//				if (containerName.startsWith(".")) {
//					containerName = packageName + containerName;
//				}
//
//				String containerPackage = "";
//				if (containerName.contains(".")) {
//					containerPackage = containerName.substring(0,
//							containerName.lastIndexOf("."));
//					containerName = containerName.substring(
//							containerName.lastIndexOf(".") + 1,
//							containerName.length());
//				}
//
//				Q container = typeSelect(containerPackage, containerName);
//				if (CommonQueries.isEmpty(container)) {
//					containerName = packageName + "." + containerName;
//					if (containerName.contains(".")) {
//						containerPackage = containerName.substring(0,
//								containerName.lastIndexOf("."));
//						containerName = containerName.substring(
//								containerName.lastIndexOf(".") + 1,
//								containerName.length());
//					}
//					container = typeSelect(containerPackage,
//							containerName);
//				}
//
//				Q bundleDataNodes = bundles.intersection(CommonQueries
//						.declarations(container, FORWARD));
//
//				// insert data flow edge from manifest meta-data to bundle node
//				contributeTagsAndAttributes(tags, attr);
//				um.createEdge(tags, attr, variableNode, bundleDataNodes.eval()
//						.nodes());
//			} catch (Exception e) {
//				// invalid service entry, skipping
//			}
//		}
//	}
//
//	private void insertBundleResourceReferenceEdge(Element metaDatum,
//			String metaDatumName, String metaDatumResource) {
//		// TODO: I'm not quite sure what a manifest bundle reference resource is
//		// and what it should look like in the index ~BH
//		// Reference:
//		// http://developer.android.com/guide/topics/manifest/meta-data-element.html#rsrc
//	}
//
//	/**
//	 * Links the given file to its reference in the given internal class of R.
//	 * 
//	 * @param prl
//	 *            The tag for which we're linking resources
//	 * @param folder
//	 *            The parent folder underneath which the resources live
//	 * @param folderPrefix
//	 *            The prefix for all subfolder names that should be considered
//	 * @param rSubclass
//	 *            The inner class in R where the constants for this kind of
//	 *            resource live
//	 * @param rTypes
//	 *            A Q of all types declared under R. Passed in to avoid
//	 *            recomputation
//	 */
//	private void linkFileResourcesToR(ResourceLinkingTags prl, String parentFolder,
//			String folderPrefix, String rSubclass, Q rTypes, Q insertedTypeNodes) {
//		// Find all files in subfolders of the parent folder which start with
//		// the given prefix
//		List<String> files = filesInSubfoldersMatchingPrefix(parentFolder,
//				folderPrefix);
//
//		// Look up the type node for this type
//		Q typeNode = CommonQueries.nodesMatchingRegex(insertedTypeNodes,
//				prl.toString());
//
//		// Find the inner class of R in which the fields for this type are
//		// declared
//		Q rContainingType = CommonQueries.declarations(
//				CommonQueries.nodesMatchingRegex(rTypes, rSubclass), FORWARD);
//
//		Set<String> tags = new HashSet<String>();
//		Map<String, Object> attr = new HashMap<String, Object>();
//
//		for (String file : files) {
//			// Get the name of the file, with no path or extension
//			String fileName = new File(file).getName();
//			String extension = FileIntegrityScanner.fileExtension(fileName);
//			if (!extension.isEmpty()) {
//				fileName = fileName.substring(0,
//						fileName.length() - extension.length() - 1);
//			}
//
//			// Find the exact field that this should be linked to
//			Q rFieldNode = rContainingType.fields(fileName);
//
//			// Create a new node to represent this file resource
//			tags = new HashSet<String>();
//			tags.add(prl.toString());
//			tags.add(ResourceLinkingTags.RESOURCE_VALUE.toString());
//			tags.add(Node.DATA_FLOW);
//			attr.put(Node.SC,
//					XMLUtils.xmlFileToSourceCorrespondence(new File(file)));
//			attr.put(Node.NAME, fileName);
//			contributeTagsAndAttributes(tags, attr);
//			Manipulation fileNode = um.createNode(tags, attr);
//
//			// Create a declares edge from the type to the file node
//			tags = new HashSet<String>();
//			tags.add(Edge.DECLARES);
//			attr = new HashMap<String,Object>();
//			contributeTagsAndAttributes(tags, attr);
//			um.createEdge(tags, attr, typeNode.eval().nodes(), fileNode);
//
//			// If this file isn't referenced
//			if (!CommonQueries.isEmpty(rFieldNode)) {
//				// Add the edge
//				tags = new HashSet<String>();
//				tags.add(Edge.DATA_FLOW);
//				tags.add(Edge.DF_LOCAL);
//				attr = new HashMap<String,Object>();
//				contributeTagsAndAttributes(tags, attr);
//				um.createEdge(tags, attr, fileNode, rFieldNode.eval().nodes());
//			}
//		}
//	}
//
//	/**
//	 * Given a parent folder and a subfolder prefix, returns a list of all files
//	 * which were in subfolders which start with the given prefix.
//	 * 
//	 * @param parentFolder
//	 * @param subfolderPrefix
//	 * @return
//	 */
//	private List<String> filesInSubfoldersMatchingPrefix(String parentFolder,
//			String subfolderPrefix) {
//		List<String> allFiles = new LinkedList<String>();
//
//		File[] filesWithin = new File(parentFolder).listFiles();
//		if (filesWithin != null) {
//			for (File f : filesWithin) {
//				if (f.isDirectory() && f.getName().startsWith(subfolderPrefix)) {
//					try {
//						allFiles.addAll(FileIntegrityScanner.filesInDirectory(f
//								.getCanonicalPath()));
//					} catch (IOException e) {
//					}
//				}
//			}
//		}
//
//		return allFiles;
//	}
//	
//	/**
//	 * Starts searching at the given file by walking backwards up the File tree parent by parent
//	 * checking to see if the parent directory matches the given folder prefix.  The search is complete
//	 * at the first match or aborted and returns null at the project directory.
//	 * @param file
//	 * @param folderPefix
//	 * @param projectDirectory
//	 * @return
//	 */
//	private File folderOfFileMatchingPrefix(File file, String folderPrefix,
//			File projectDirectory) {
//		File parent = file.isDirectory() ? file : file.getParentFile();
//		while (parent != null
//				&& !file.getAbsolutePath().equals(
//						projectDirectory.getAbsolutePath())) {
//			if (parent.getName().startsWith(folderPrefix)) {
//				return parent;
//			}
//			parent = parent.getParentFile();
//		}
//		return null;
//	}
//
//	/**
//	 * Links XML resources from the given folder, with the given root element
//	 * name, to constants in R under the given subclass, with the name provided
//	 * in XML by the given key.
//	 * 
//	 * @param prl
//	 *            The tag of the resource being linked
//	 * @param projectName
//	 *            The name of the Eclipse project
//	 * @param folder
//	 *            The folder in which files should be found
//	 * @param rootElemTag
//	 *            The expected root element tag in the XML file
//	 * @param valElementTag
//	 *            The expected tag for values in this file (eg. for strings,
//	 *            'string')
//	 * @param rKeyAttribute
//	 *            The name of the attribute which R uses for the reference name
//	 * @param rSubclass
//	 *            The name of the subclass
//	 * @param rTypes
//	 *            A Q of all types declared under R. Passed in to avoid
//	 *            recomputation
//	 * @param accessMethodParams
//	 * 			  The PARAMETER nodes of methods which allow runtime access to 
//	 * 			  these resources values.
//	 */
//	@SuppressWarnings("serial")
//	private void linkXMLResourcesToR(final ResourceLinkingTags prl, String projectName,
//			String parentFolder, String subfolderPrefix, String rootElemTag,
//			String valElementTag, String rKeyAttribute, String rSubclass,
//			Q rTypes, Q insertedTypeNodes, Q literalType, Q accessMethods) {
//		// Find all files in subfolders of the parent folder which start with
//		// the given prefix
//		List<String> files = filesInSubfoldersMatchingPrefix(parentFolder,
//				subfolderPrefix);
//
//		// Look up the type node for this type
//		Q typeNode = CommonQueries.nodesMatchingRegex(insertedTypeNodes,
//				prl.toString());
//
//		// Find the inner class of R in which the fields for this type are
//		// declared
//		Q rContainingType = CommonQueries.declarations(
//				CommonQueries.nodesMatchingRegex(rTypes, rSubclass), FORWARD);
//
//		Set<String> tags = new HashSet<String>();
//		tags.add(Edge.DATA_FLOW);
//		tags.add(Edge.DF_LOCAL);
//		Map<String, Object> attr = new HashMap<String, Object>();
//		contributeTagsAndAttributes(tags, attr);
//		
//		Q accessParams = null, accessReturns = null;
//		if(accessMethods != null){
//			accessParams = methodParameter(accessMethods);
//			accessReturns = methodReturn(accessMethods);
//		}
//		
//		
//		// For each XML file
//		for (String fileName : files) {
//
//			// identify language if applicable
//			String language = null; // null means the language is n/a for the
//									// file
//			File folder = folderOfFileMatchingPrefix(new File(fileName),
//					VALUES_SUBFOLDER, new File(projectRootPath));
//			if (folder.getName().startsWith(VALUES_SUBFOLDER)) {
//				// TODO: is "values" a good tag for this?
//				language = "values"; // the "values" folder is a special case
//										// for the default
//				if (folder.getName().startsWith(VALUES_SUBFOLDER + "-")) {
//					// special language values are denoted by the folder such as
//					// "values-es" (example for Spanish)
//					// reference:
//					// https://developer.android.com/training/basics/supporting-devices/languages.html
//					language = folder.getName();
//				}
//			}
//
//			// If not an XML file, move on
//			if (!FileIntegrityScanner.fileExtension(fileName).equalsIgnoreCase(
//					"xml")) {
//				continue;
//			}
//
//			try {
//				// Parse the XML file
//				Document doc = XMLUtils.getAnnotatedDOM(new File(fileName));
//
//				// If the element's name is not what we expect, move on
//				Element rootElement = doc.getDocumentElement();
//				if (!rootElement.getTagName().equalsIgnoreCase(rootElemTag))
//					continue;
//
//				// Go through each value element
//				NodeList nl = rootElement.getElementsByTagName(valElementTag);
//				for (int i = 0; i < nl.getLength(); i++) {
//					Element valElement = (Element) nl.item(i);
//
//					// If the value element has the attribute on which R bases
//					// its constant name, represent it in the index
//					if (valElement.hasAttribute(rKeyAttribute)) {
//						// Create a literal substructure in the index to
//						// represent this XML element
//						Q rResolutionElement = literalSubstructureForElement(
//								prl, projectName, new File(fileName),
//								valElement, rKeyAttribute, typeNode, language);
//
//						// Look up the name for the value of this element
//						String valueName = valElement
//								.getAttribute(rKeyAttribute);
//
//						// Find the exact field that this should be linked to
//						Q rFieldNode = rContainingType.fields(valueName);
//
//						// Add the edge
//						AtlasSet<GraphElement> fieldNodes = rFieldNode.eval().nodes();
//						um.createEdge(tags, attr, rResolutionElement.roots().eval().nodes(), fieldNodes);
//
//						// If the literal type isn't null, then hook up a
//						// TYPEOF edge and add the IS_LITERAL tag
//						if(literalType != null){
//							AtlasSet<GraphElement> literals = stepFrom(universe().edgesTaggedWithAny(Edge.DATA_FLOW), rResolutionElement).eval().nodes();
//							um.addTag(literals, Node.IS_LITERAL);
//							
//							um.createEdge(new HashSet<String>(){{
//								add(Edge.TYPEOF);
//								add(prl.toString());
//							}}, new HashMap<String, Object>(), 
//							literals, literalType.eval().nodes());
//						}
//						
//						// If the access method isn't null, hook up the
//						// field to the return values of sites which use it.
//						if(accessMethods != null){
//							Q fieldUse = universe().edgesTaggedWithAny(Edge.DATA_FLOW).forward(rFieldNode).reverseStep(accessParams);
//							
//							Set<Address> callsiteIds = new HashSet<Address>();
//							for(GraphElement ge : fieldUse.eval().edges())
//								callsiteIds.add((Address) ge.attr().get(Edge.CALL_SITE_ID));
//							
//							if(callsiteIds.isEmpty()) continue;
//							
//							Address[] ids = new Address[callsiteIds.size()];
//							ids = callsiteIds.toArray(ids);
//							
//							Q stackReturns = stepTo(universe().edgesTaggedWithAny(Edge.DATA_FLOW).forwardStep(accessReturns).selectEdge(Edge.CALL_SITE_ID, (Object[]) ids), accessReturns);
//							
//							um.createEdge(new HashSet<String>(){{
//								add(Edge.DATA_FLOW);
//								add(Edge.DF_INTERPROCEDURAL);
//								add(prl.toString());
//							}}, new HashMap<String, Object>(), 
//							fieldNodes, stackReturns.eval().nodes());
//						}
//					}
//				}
//			} catch (Exception e) {
//			}
//		}
//	}
//
//	/**
//	 * Recursively build a substructure of DATA_FLOW nodes with literal names to
//	 * represent the given XML Element.
//	 * 
//	 * (1) Creates new node for this element (2) Adds DECLARES edge from
//	 * resource type to it (3) Creates new nodes for each attribute (3a) Adds
//	 * DECLARES edge from the resource type to the attribute node (3b) Creates
//	 * DF_LOCAL edge from attribute node to this node (4) Recursively creates
//	 * new nodes for each child node (4a) Creates DF_LOCAL edge from roots of
//	 * child node to this node
//	 * 
//	 * @param prl
//	 *            The tag of the resource being linked
//	 * @param projectName
//	 *            The name of the Eclipse project
//	 * @param elem
//	 *            The XML Element for which the substructure should be built
//	 * @param rKeyAttribute
//	 *            The name of the attribute which R uses for the reference name
//	 * @param resourceTypeNode
//	 *            The type representing the type of resource for this tag.
//	 *            Passed in to avoid recomputation.
//	 * @params language set to null if not applicable, otherwise language
//	 *         specifies the language the xml file was classified under. For
//	 *         example: a node in an xml file in values_es is "es" for Spanish.
//	 * @return
//	 */
//	private Q literalSubstructureForElement(ResourceLinkingTags prl,
//			String projectName, File file, Element elem, String rKeyAttribute,
//			Q resourceTypeNode, String language) {
//		// Detect if this element is the root element for this value
//		boolean valueRoot = elem.hasAttribute(rKeyAttribute);
//
//		Set<String> tags = new HashSet<String>();
//		Map<String, Object> attr = new HashMap<String, Object>();
//		contributeTagsAndAttributes(tags, attr);
//
//		// Create a node for this element
//		String nodeName = valueRoot ? elem.getAttribute(rKeyAttribute) : elem
//				.getTagName();
//		tags = new HashSet<String>();
//		tags.add(prl.toString());
//		tags.add(ResourceLinkingTags.RESOURCE_VALUE.toString());
//		tags.add(Node.DATA_FLOW);
//		attr = new HashMap<String, Object>();
//		attr.put(Node.SC,
//				XMLUtils.xmlElementToSourceCorrespondence(file, elem));
//		attr.put(Node.NAME, nodeName);
//		contributeTagsAndAttributes(tags, attr);
//		Manipulation thisNode = um.createNode(tags, attr);
//
//		// If this node is the value root, add a declares edge from the
//		// containing type to this node
//		if (valueRoot) {
//			tags = new HashSet<String>();
//			tags.add(Edge.DECLARES);
//			attr = new HashMap<String, Object>();
//			contributeTagsAndAttributes(tags, attr);
//			um.createEdge(tags, attr, resourceTypeNode.eval().nodes(), thisNode);
//		}
//
//		// Create a node for each non-key attribute of this element, and link it
//		// to the element
//		NamedNodeMap attributes = elem.getAttributes();
//		for (int i = 0; i < attributes.getLength(); i++) {
//			org.w3c.dom.Attr att = (org.w3c.dom.Attr) attributes.item(i);
//
//			if (!att.getName().equalsIgnoreCase(rKeyAttribute)) {
//				String attNodeName = att.getName() + "=" + att.getValue();
//				tags = new HashSet<String>();
//				tags.add(prl.toString());
//				tags.add(ResourceLinkingTags.RESOURCE_VALUE.toString());
//				tags.add(Node.DATA_FLOW);
//				attr = new HashMap<String, Object>();
//				attr.put(Node.NAME, attNodeName);
//				attr.put(Node.SC,
//						XMLUtils.xmlElementToSourceCorrespondence(file, elem));
//				contributeTagsAndAttributes(tags, attr);
//				Manipulation attNode = um.createNode(tags, attr);
//
//				tags = new HashSet<String>();
//				tags.add(Edge.DECLARES);
//				attr = new HashMap<String, Object>();
//				contributeTagsAndAttributes(tags, attr);
//				um.createEdge(tags, attr, resourceTypeNode.eval().nodes(),
//						attNode);
//
//				tags = new HashSet<String>();
//				tags.add(Edge.DATA_FLOW);
//				tags.add(Edge.DF_LOCAL);
//				attr = new HashMap<String, Object>();
//				contributeTagsAndAttributes(tags, attr);
//				um.createEdge(tags, attr, attNode, thisNode);
//			}
//		}
//
//		// Create a node for each child element
//		NodeList children = elem.getElementsByTagName("*");
//		for (int i = 0; i < children.getLength(); i++) {
//			Q childNode = literalSubstructureForElement(prl, projectName, file,
//					(Element) children.item(i), rKeyAttribute,
//					resourceTypeNode, language);
//
//			// Add a flow edge from its roots to the parent
//			tags = new HashSet<String>();
//			tags.add(Edge.DATA_FLOW);
//			tags.add(Edge.DF_LOCAL);
//			attr = new HashMap<String, Object>();
//			contributeTagsAndAttributes(tags, attr);
//			um.createEdge(tags, attr, childNode.roots().eval().nodes(),
//					thisNode);
//		}
//
//		// If there were no children && this tag has internal content, add one
//		// more node to represent that content.
//		String textContent = elem.getTextContent();
//		if (children.getLength() == 0 && !textContent.isEmpty()) {
//			tags = new HashSet<String>();
//			tags.add(prl.toString());
//			if (language != null) {
//				tags.add(language);
//			}
//			tags.add(Node.DATA_FLOW);
//			tags.add(ResourceLinkingTags.RESOURCE_VALUE.toString());
//			attr = new HashMap<String, Object>();
//			attr.put(Node.NAME, textContent);
//			attr.put(Node.SC,
//					XMLUtils.xmlElementToSourceCorrespondence(file, elem));
//			contributeTagsAndAttributes(tags, attr);
//			Manipulation contentNode = um.createNode(tags, attr);
//
//			tags = new HashSet<String>();
//			tags.add(Edge.DECLARES);
//			attr = new HashMap<String, Object>();
//			contributeTagsAndAttributes(tags, attr);
//			um.createEdge(tags, attr, resourceTypeNode.eval().nodes(),
//					contentNode);
//
//			tags = new HashSet<String>();
//			tags.add(Edge.DATA_FLOW);
//			tags.add(Edge.DF_LOCAL);
//			attr = new HashMap<String, Object>();
//			contributeTagsAndAttributes(tags, attr);
//			um.createEdge(tags, attr, contentNode, thisNode);
//		}
//
//		um.perform();
//		return toQ(new NodeGraph(thisNode.getResult()));
//	}
}