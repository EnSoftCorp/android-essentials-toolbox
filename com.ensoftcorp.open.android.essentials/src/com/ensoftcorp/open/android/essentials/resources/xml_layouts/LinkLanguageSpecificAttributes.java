package com.ensoftcorp.open.android.essentials.resources.xml_layouts;


public class LinkLanguageSpecificAttributes {

	// Dead code, uncomment to use again
	
//	/**
//	 * First have to run the Interface Indexer and the code in ViewToListenerLinking.java file
//	 * 
//	 * This code is to link the attributes of an xml element (e.g., android:text) to the corresponding language
//	 * specific value.  
//	 */
//	
//	public void linkAttributes(){
//		Q xmlAttributes = getXMLAttributes();
//		Iterator<GraphElement> attrItr = xmlAttributes.eval().nodes().iterator();
//		String str = "=\"@string/";
//		String header = "android:text=\"";
//		while(attrItr.hasNext()){
//			GraphElement curXMLAttr = attrItr.next();
//			String curAttrName = curXMLAttr.attr().get(Node.NAME).toString();
//			if(curAttrName.contains(header)){
//				if(curAttrName.contains(str)){
//					String curAttrActualName = curAttrName.substring(curAttrName.indexOf(str)+str.length()+1, curAttrName.length()-1);
//				}
//				else{
//					String curAttrActualName = curAttrName.substring(curAttrName.indexOf(header)+header.length()+1, curAttrName.length()-1);
//				}
//			}
//		}
//	}
//	
//	private Q getXMLAttributes(){
//		Q xmlAttributes = Common.universe().nodesTaggedWithAny(XMLInterfaceTags.XML_ATTRIBUTE.toString());
//		return xmlAttributes;
//	}
}
