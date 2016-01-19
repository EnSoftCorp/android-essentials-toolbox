package com.ensoftcorp.open.android.essentials.resources.xml_layouts;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.index.common.SourceCorrespondence;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.UniverseManipulator;
import com.ensoftcorp.atlas.core.script.UniverseManipulator.Manipulation;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.ensoftcorp.atlas.java.core.script.Common;
import com.ensoftcorp.open.android.essentials.resources.XMLInterfaceTags;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.AbsoluteLayout;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.AdapterViewFlipper;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.AnalogClock;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.AutoCompleteTextView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Button;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.CalendarView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Checkbox;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.CheckedTextView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Chronometer;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.DatePicker;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.DialerFilter;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.DigitalClock;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.EditText;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ExpandableListView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Gallery;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.GridLayout;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.GridView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.HorizontalScrollView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ImageButton;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ImageSwitcher;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ImageView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.LinearLayout;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.MediaController;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.MultiAutoCompleteTextView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.NumberPicker;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ProgressBar;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.QuickContactBadge;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.RadioButton;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.RadioGroup;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.RatingBar;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.RelativeLayout;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ScrollView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.SearchView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.SeekBar;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.SlidingDrawer;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Space;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Spinner;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.StackView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.Switch;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TabHost;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TabWidget;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TableLayout;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TableRow;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TextClock;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TextSwitcher;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TextView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TimePicker;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ToggleButton;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.TwoLineListItem;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.VideoView;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ViewAnimator;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ViewFlipper;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ViewSwitcher;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ZoomButton;
import com.ensoftcorp.open.android.essentials.resources.xml_layouts.controls.ZoomControls;
import com.ensoftcorp.open.toolbox.commons.utils.XMLUtils;

public class IndexXmlLayouts {
	
	public static AttrAndTags attrAndTags = new AttrAndTags();

	// Android XML layout constants
	// Note: These are case-sensitive!
	private static final String TextView = "TextView";
	private static final String RelativeLayout = "RelativeLayout";
	private static final String ImageView = "ImageView";
	private static final String LinearLayout = "LinearLayout";
	private static final String TableLayout = "TableLayout";
	private static final String TableRow = "TableRow";
	private static final String Space = "Space";
	private static final String Button = "Button";
	private static final String Checkbox = "Checkbox";
	private static final String EditText = "EditText";
	private static final String AutoCompleteTextView = "AutoCompleteTextView";
	private static final String MultiAutoCompleteTextView = "MultiAutoCompleteTextView";
	private static final String GridLayout = "GridLayout";
	private static final String ExpandableListView = "ExpandableListView";
	private static final String GridView = "GridView";
	private static final String ScrollView = "ScrollView";
	private static final String HorizontalScrollView = "HorizontalScrollView";
	private static final String SearchView = "SearchView";
	private static final String SlidingDrawer = "SlidingDrawer";
	private static final String ImageButton = "ImageButton";
	private static final String Gallery = "Gallery";
	private static final String MediaController = "MediaController";
	private static final String VideoView = "VideoView";
	private static final String TimePicker = "TimePicker";
	private static final String DatePicker = "DatePicker";
	private static final String CalendarView = "CalendarView";
	private static final String Chronometer = "Chronometer";
	private static final String AnalogClock = "AnalogClock";
	private static final String DigitalClock = "DigitalClock";
	private static final String ImageSwitcher = "ImageSwitcher";
	private static final String AdapterViewFlipper = "AdapterViewFlipper";
	private static final String StackView = "StackView";
	private static final String TextSwitcher = "TextSwitcher";
	private static final String ViewAnimator = "ViewAnimator";
	private static final String ViewFlipper = "ViewFlipper";
	private static final String ViewSwitcher = "ViewSwitcher";
	private static final String NumberPicker = "NumberPicker";
	private static final String ZoomButton = "ZoomButton";
	private static final String ZoomControls = "ZoomControls";
	private static final String DialerFilter = "DialerFilter";
	private static final String TwoLineListItem = "TwoLineListItem";
	private static final String AbsoluteLayout = "AbsoluteLayout";
	private static final String TextClock = "TextClock";
	private static final String ProgressBar = "ProgressBar";
	private static final String RadioButton = "RadioButton";
	private static final String RadioGroup = "RadioGroup";
	private static final String RatingBar = "RatingBar";
	private static final String Switch = "Switch";
	private static final String SeekBar = "SeekBar";
	private static final String Spinner = "Spinner";
	private static final String TabHost = "TabHost";
	private static final String TabWidget = "TabWidget";
	private static final String ToggleButton = "ToggleButton";
	private static final String CheckedTextView = "CheckedTextView";
	private static final String QuickContactBadge = "QuickContactBadge";

	public static void indexXML(File xmlLayoutFile, Q activities, UniverseManipulator um, UniverseManipulator.Manipulation xmlLayoutsSchemaRoot) throws Exception {

		// TODO: Stop making these assumptions
		// for now just assume an XML is only used once per activity...
		// also assume all xml files are in the root directory of the layouts folder
		UniverseManipulator.Manipulation xmlLayoutNode = linkXMLLayoutFileToXMLLayoutsSchemaRoot(um, xmlLayoutFile, xmlLayoutsSchemaRoot);

		// parse the xml file and insert nodes and edges into the graph
		Document document = XMLUtils.getAnnotatedDOM(xmlLayoutFile);
		Element rootElement = document.getDocumentElement();
		indexXML(um, xmlLayoutFile, activities, rootElement, xmlLayoutNode);
	}

	private static UniverseManipulator.Manipulation linkXMLLayoutFileToXMLLayoutsSchemaRoot(UniverseManipulator um, File xmlLayout, UniverseManipulator.Manipulation xmlLayoutsSchemaRoot) {
		// insert the xml file node
		Set<String> fileTags = new HashSet<String>(attrAndTags.tags);
		Map<String, Object> fileAttributes = new HashMap<String, Object>(attrAndTags.attributes);
		fileAttributes.put(com.ensoftcorp.atlas.core.query.Attr.Node.NAME, xmlLayout.getName());
		SourceCorrespondence sc = XMLUtils.xmlFileToSourceCorrespondence(xmlLayout);
		fileAttributes.put(XCSG.sourceCorrespondence, sc);
		UniverseManipulator.Manipulation fileNode = um.createNode(fileTags, fileAttributes);

		// TODO: implement
//		// add declares edge from xml schema root to file node
//		AtlasMetaSchema xmlDeclaresEdgeSchema = AtlasMetaSchema.getForEdgeTag(Edge.DECLARES);
//		Set<String> xmlDeclaresEdgeTags = new HashSet<String>(attrAndTags.tags);
//		xmlDeclaresEdgeTags.addAll(xmlDeclaresEdgeSchema.getTags(SchemaRole.REQUIRED));
//		Map<String, Object> xmlDeclaresEdgeAttributes = new HashMap<String, Object>(attrAndTags.attributes);
//		xmlDeclaresEdgeAttributes.putAll(xmlDeclaresEdgeSchema.getAttributes(SchemaRole.REQUIRED));
//		xmlDeclaresEdgeAttributes.put(XCSG.sourceCorrespondence, sc);
//		um.createEdge(xmlDeclaresEdgeTags, xmlDeclaresEdgeAttributes, xmlLayoutsSchemaRoot, fileNode);

		return fileNode;
	}

	private static void indexXML(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, UniverseManipulator.Manipulation elementNode) {

		// child element becomes next parent
		elementNode = linkElementToParent(um, xmlLayoutFile, element, elementNode);

		// convert the element to the corresponding Java code
		if (element.getNodeName().equals(TextView)) {
			handleTextViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(RelativeLayout)) {
			handleRelativeLayoutElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ImageView)) {
			handleImageViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(LinearLayout)) {
			handleLinearLayoutElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TableLayout)) {
			handleTableLayoutElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TableRow)) {
			handleTableRowElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Space)) {
			handleSpaceElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Button)) {
			handleButtonElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Checkbox)) {
			handleCheckboxElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(EditText)) {
			handleEditTextElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(AutoCompleteTextView)) {
			handleAutoCompleteTextViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(MultiAutoCompleteTextView)) {
			handleMultiAutoCompleteTextViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(GridLayout)) {
			handleGridLayoutElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ExpandableListView)) {
			handleExpandableListViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(GridView)) {
			handleGridViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ScrollView)) {
			handleScrollViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(HorizontalScrollView)) {
			handleHorizontalScrollViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(SearchView)) {
			handleSearchViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(SlidingDrawer)) {
			handleSlidingDrawerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ImageButton)) {
			handleImageButtonElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Gallery)) {
			handleGalleryElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(MediaController)) {
			handleMediaControllerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(VideoView)) {
			handleVideoViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TimePicker)) {
			handleTimePickerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(DatePicker)) {
			handleDatePickerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(CalendarView)) {
			handleCalendarViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Chronometer)) {
			handleChronometerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(AnalogClock)) {
			handleAnalogClockElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(DigitalClock)) {
			handleDigitalClockElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ImageSwitcher)) {
			handleImageSwitcherElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(AdapterViewFlipper)) {
			handleAdapterViewFlipperElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(StackView)) {
			handleStackViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TextSwitcher)) {
			handleTextSwitcherElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ViewAnimator)) {
			handleViewAnimatorElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ViewFlipper)) {
			handleViewFlipperElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ViewSwitcher)) {
			handleViewSwitcherElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(NumberPicker)) {
			handleNumberPickerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ZoomButton)) {
			handleZoomButtonElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ZoomControls)) {
			handleZoomControlsElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(DialerFilter)) {
			handleDialerFilterElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TwoLineListItem)) {
			handleTwoLineListItemElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(AbsoluteLayout)) {
			handleAbsoluteLayoutElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TextClock)) {
			handleTextClockElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ProgressBar)) {
			handleProgressBarElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(RadioButton)) {
			handleRadioButtonElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(RadioGroup)) {
			handleRadioGroupElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(RatingBar)) {
			handleRatingBarElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Switch)) {
			handleSwitchElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(SeekBar)) {
			handleSeekBarElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(Spinner)) {
			handleSpinnerElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TabHost)) {
			handleTabHostElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(TabWidget)) {
			handleTabWidgetElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(ToggleButton)) {
			handleToggleButtonElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(CheckedTextView)) {
			handleCheckedTextViewElement(um, xmlLayoutFile, activities, element, elementNode);
		} else if (element.getNodeName().equals(QuickContactBadge)) {
			handleQuickContactBadgeElement(um, xmlLayoutFile, activities, element, elementNode);
		} else {
			// unknown xml handler
		}

		// iterate over the node's children
		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				indexXML(um, xmlLayoutFile, activities, (Element) currentNode, elementNode);
			}
		}
	}

	private static Manipulation linkElementToParent(UniverseManipulator um, File xmlLayoutFile, Element element, Manipulation parent) {
		// insert the xml file node
		Set<String> childTags = new HashSet<String>(attrAndTags.tags);
		childTags.add(XMLInterfaceTags.XML_ELEMENT.toString());
		Map<String, Object> childAttributes = new HashMap<String, Object>(attrAndTags.attributes);
		childAttributes.put(com.ensoftcorp.atlas.core.query.Attr.Node.NAME, element.getNodeName());
		SourceCorrespondence sc = XMLUtils.xmlElementToSourceCorrespondence(xmlLayoutFile, element);
		childAttributes.put(XCSG.sourceCorrespondence, sc);
		UniverseManipulator.Manipulation child = um.createNode(childTags, childAttributes);

		// TODO: implement
		// add declares edge from xml schema root to file node
//		AtlasMetaSchema xmlDeclaresEdgeSchema = AtlasMetaSchema.getForEdgeTag(Edge.DECLARES);
//		Set<String> xmlDeclaresEdgeTags = new HashSet<String>(attrAndTags.tags);
//		xmlDeclaresEdgeTags.addAll(xmlDeclaresEdgeSchema.getTags(SchemaRole.REQUIRED));
//		Map<String, Object> xmlDeclaresEdgeAttributes = new HashMap<String, Object>(attrAndTags.attributes);
//		xmlDeclaresEdgeAttributes.putAll(xmlDeclaresEdgeSchema.getAttributes(SchemaRole.REQUIRED));
//		LinkedList<SourceCorrespondence> scList = new LinkedList<SourceCorrespondence>();
//		scList.add(sc);
//		xmlDeclaresEdgeAttributes.put(SummaryGraph.SummaryEdge.LIST_SC_RANGE, scList);
//		um.createEdge(xmlDeclaresEdgeTags, xmlDeclaresEdgeAttributes, parent, child);

		return child;
	}

	private static void addSupertypeEdgeForXmlLayoutElement(UniverseManipulator um, Manipulation node, GraphElement type) {
		// TODO: implement
//		// add supertype edge from xml element to its type
//		AtlasMetaSchema xmlSupertypeEdgeSchema = AtlasMetaSchema.getForEdgeTag(Edge.SUPERTYPE);
//		Set<String> xmlSupertypeEdgeTags = new HashSet<String>(attrAndTags.tags);
//		xmlSupertypeEdgeTags.addAll(xmlSupertypeEdgeSchema.getTags(SchemaRole.REQUIRED));
//		Map<String, Object> xmlSupertypeEdgeAttributes = new HashMap<String, Object>(attrAndTags.attributes);
//		xmlSupertypeEdgeAttributes.putAll(xmlSupertypeEdgeSchema.getAttributes(SchemaRole.REQUIRED));
//		if(type != null){
//			if(type.attr() != null && type.attr().get(XCSG.sourceCorrespondence) != null){
//				LinkedList<SourceCorrespondence> sc = new LinkedList<SourceCorrespondence>();
//				sc.add((SourceCorrespondence) type.attr().get(XCSG.sourceCorrespondence));
//				xmlSupertypeEdgeAttributes.put(SummaryGraph.SummaryEdge.LIST_SC_RANGE, sc);
//			}
//			um.createEdge(xmlSupertypeEdgeTags, xmlSupertypeEdgeAttributes, node, type);
//		}
	}

	// begin known XML layout element handlers

	private static void handleQuickContactBadgeElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "QuickContactBadge").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		QuickContactBadge attributeHandlers = new QuickContactBadge(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleCheckedTextViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "CheckedTextView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		CheckedTextView attributeHandlers = new CheckedTextView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleToggleButtonElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ToggleButton").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ToggleButton attributeHandlers = new ToggleButton(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTabWidgetElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TabWidget").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TabWidget attributeHandlers = new TabWidget(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTabHostElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TabHost").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TabHost attributeHandlers = new TabHost(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleSpinnerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Spinner").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Spinner attributeHandlers = new Spinner(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleSeekBarElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "SeekBar").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		SeekBar attributeHandlers = new SeekBar(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleSwitchElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Switch").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Switch attributeHandlers = new Switch(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleRatingBarElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "RatingBar").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		RatingBar attributeHandlers = new RatingBar(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleRadioGroupElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "RadioGroup").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		RadioGroup attributeHandlers = new RadioGroup(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleRadioButtonElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "RadioButton").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		RadioButton attributeHandlers = new RadioButton(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleProgressBarElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ProgressBar").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ProgressBar attributeHandlers = new ProgressBar(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTextClockElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TextClock").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TextClock attributeHandlers = new TextClock(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleAbsoluteLayoutElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "AbsoluteLayout").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		AbsoluteLayout attributeHandlers = new AbsoluteLayout(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTwoLineListItemElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TwoLineListItem").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TwoLineListItem attributeHandlers = new TwoLineListItem(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleDialerFilterElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "DialerFilter").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		DialerFilter attributeHandlers = new DialerFilter(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleZoomControlsElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ZoomControls").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ZoomControls attributeHandlers = new ZoomControls(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleZoomButtonElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ZoomButton").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ZoomButton attributeHandlers = new ZoomButton(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleNumberPickerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "NumberPicker").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		NumberPicker attributeHandlers = new NumberPicker(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleViewSwitcherElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ViewSwitcher").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ViewSwitcher attributeHandlers = new ViewSwitcher(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleViewFlipperElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ViewFlipper").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ViewFlipper attributeHandlers = new ViewFlipper(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleViewAnimatorElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ViewAnimator").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ViewAnimator attributeHandlers = new ViewAnimator(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTextSwitcherElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TextSwitcher").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TextSwitcher attributeHandlers = new TextSwitcher(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleStackViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "StackView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		StackView attributeHandlers = new StackView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleAdapterViewFlipperElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "AdapterViewFlipper").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		AdapterViewFlipper attributeHandlers = new AdapterViewFlipper(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleImageSwitcherElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ImageSwitcher").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ImageSwitcher attributeHandlers = new ImageSwitcher(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleDigitalClockElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "DigitalClock").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		DigitalClock attributeHandlers = new DigitalClock(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleAnalogClockElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "AnalogClock").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		AnalogClock attributeHandlers = new AnalogClock(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleChronometerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Chronometer").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Chronometer attributeHandlers = new Chronometer(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleCalendarViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "CalendarView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		CalendarView attributeHandlers = new CalendarView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleDatePickerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "DatePicker").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		DatePicker attributeHandlers = new DatePicker(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTimePickerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TimePicker").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TimePicker attributeHandlers = new TimePicker(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleVideoViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "VideoView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		VideoView attributeHandlers = new VideoView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleMediaControllerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "MediaController").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		MediaController attributeHandlers = new MediaController(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleGalleryElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Gallery").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Gallery attributeHandlers = new Gallery(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleImageButtonElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ImageButton").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ImageButton attributeHandlers = new ImageButton(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleSlidingDrawerElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "SlidingDrawer").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		SlidingDrawer attributeHandlers = new SlidingDrawer(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleSearchViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "SearchView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		SearchView attributeHandlers = new SearchView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleHorizontalScrollViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "HorizontalScrollView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		HorizontalScrollView attributeHandlers = new HorizontalScrollView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleScrollViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ScrollView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ScrollView attributeHandlers = new ScrollView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleGridViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "GridView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		GridView attributeHandlers = new GridView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleExpandableListViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ExpandableListView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ExpandableListView attributeHandlers = new ExpandableListView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleGridLayoutElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "GridLayout").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		GridLayout attributeHandlers = new GridLayout(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleMultiAutoCompleteTextViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "MultiAutoCompleteTextView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		MultiAutoCompleteTextView attributeHandlers = new MultiAutoCompleteTextView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleAutoCompleteTextViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "AutoCompleteTextView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		AutoCompleteTextView attributeHandlers = new AutoCompleteTextView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleEditTextElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "EditText").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		EditText attributeHandlers = new EditText(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleCheckboxElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Checkbox").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Checkbox attributeHandlers = new Checkbox(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleButtonElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Button").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Button attributeHandlers = new Button(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleSpaceElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "Space").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		Space attributeHandlers = new Space(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTableRowElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TableRow").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TableRow attributeHandlers = new TableRow(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTableLayoutElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TableLayout").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TableLayout attributeHandlers = new TableLayout(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleLinearLayoutElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "LinearLayout").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		LinearLayout attributeHandlers = new LinearLayout(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleImageViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "ImageView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		ImageView attributeHandlers = new ImageView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleRelativeLayoutElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "RelativeLayout").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		RelativeLayout attributeHandlers = new RelativeLayout(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

	private static void handleTextViewElement(UniverseManipulator um, File xmlLayoutFile, Q activities, Element element, Manipulation node) {
		GraphElement type = Common.typeSelect("android.widget", "TextView").eval().nodes().getFirst();
		addSupertypeEdgeForXmlLayoutElement(um, node, type);
		TextView attributeHandlers = new TextView(um, activities, element, xmlLayoutFile);
		attributeHandlers.respondToAttributes(node);
	}

}
