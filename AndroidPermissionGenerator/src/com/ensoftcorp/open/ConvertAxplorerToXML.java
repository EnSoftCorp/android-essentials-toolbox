package com.ensoftcorp.open;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConvertAxplorerToXML {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, IOException, TransformerException {
//		String permissionName = "";
//		Element permission = null;
//		Element call = null;

		// create a file object that points at the file to parse
//		File file = new File(args[0]);
		// concantated http://axplorer.org/files/pmaps/framework/framework-map-23.txt with
		// http://axplorer.org/files/pmaps/sdk/sdk-map-23.txt to form 23-all.txt
		File file = new File("/Users/benjholla/Desktop/23/23-all.txt");
//		String tag = args[1]; // android api name
		String tag = "23";

		// initialization to create xml file
		// to store all info of methods in the mapping
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element permissions = document.createElement("permissions");
		document.appendChild(permissions);

		// read to read the file
		Scanner scanner = new Scanner(file);
		scanner.nextLine(); // skip the first line which is just a header
		
		HashMap<String, Element> permissionElements = new HashMap<String,Element>();
		
		while (scanner.hasNextLine()) {
			// read one line of the input file
			String line = scanner.nextLine();

			String[] columns = line.split("  ::  ");
			String caller = columns[0];
			String permissionNames = columns[1];
			HashSet<String> mappedPermissions = new HashSet<String>();
			for(String permissionName : permissionNames.replaceAll("\\s","").split(",")){
				mappedPermissions.add(permissionName);
			}
			
			String callerClass = caller.substring(0, caller.indexOf("("));
			String callerMethod = callerClass.substring(callerClass.lastIndexOf(".")+1, callerClass.length());
			callerClass = callerClass.substring(0, callerClass.lastIndexOf("."));
			String methodDescription = caller.substring(caller.indexOf("("));
			
			for(String permissionName : mappedPermissions){
				Element permission = null;
				if(permissionElements.containsKey(permissionName)){
					permission = permissionElements.get(permissionName);
				} else {
					// define permission elements in xml
					permission = document.createElement("permission");
					permissions.appendChild(permission);
					Attr attribute = document.createAttribute("name");
					attribute.setValue(permissionName);
					permission.setAttributeNode(attribute);
					permissionElements.put(permissionName, permission);
				}
				
				// create call elements under permission elements
				Element call = document.createElement("call");
				permission.appendChild(call);

				String qualifiedClass = callerClass.replace("/", ".").trim();
						
				// parse the package
				String packageName = qualifiedClass.substring(0, (qualifiedClass.lastIndexOf(".")));

				// parse the class
				String className = qualifiedClass.substring((qualifiedClass.lastIndexOf(".") + 1), qualifiedClass.length());
				
				// create package elements under call elements
				Element pkg = document.createElement("package");
				pkg.appendChild(document.createTextNode(packageName));
				call.appendChild(pkg);

				// create class elements under call elements
				Element clazz = document.createElement("class");
				clazz.appendChild(document.createTextNode(className));
				call.appendChild(clazz);

				// create method elements under call elements
				Element method = document.createElement("method");
				String methodName = callerMethod;
				method.appendChild(document.createTextNode(methodName));
				call.appendChild(method);

				// create returnType elements under call elements
				
				String qualifiedMethodReturnType;
				try {
					if(methodDescription.endsWith(")V") || methodDescription.endsWith(")void")){
						qualifiedMethodReturnType = "void";
					} else if(methodDescription.contains("<init>") || (methodDescription.contains("<clinit>"))){
						qualifiedMethodReturnType = "void";
					} else {
						qualifiedMethodReturnType = methodDescription.substring(methodDescription.indexOf(")")+1);
						qualifiedMethodReturnType = qualifiedMethodReturnType.replace("/", ".");
						qualifiedMethodReturnType = parseJVMDescriptor(qualifiedMethodReturnType);
					}
				} catch (Exception e) {
					System.err.println("Invalid entry: " + methodDescription);
					e.printStackTrace();
					continue;
				}
				
				String unqualifiedMethodReturnType = qualifiedMethodReturnType;
				if (qualifiedMethodReturnType.contains(".")) {
					unqualifiedMethodReturnType = qualifiedMethodReturnType.substring(qualifiedMethodReturnType.lastIndexOf(".") + 1);
				}
				Element returnType = document.createElement("returnType");
				returnType.appendChild(document.createTextNode(unqualifiedMethodReturnType));
				call.appendChild(returnType);

				ArrayList<String> parameterArray = new ArrayList<String>();
				try {
					String[] params = methodDescription.substring(methodDescription.indexOf("(")+1, methodDescription.indexOf(")")).split(",");
					for(String param : params){
						if(!param.equals("")){
							param = param.replace("/", ".");
							while(param.length() > 0){
								String descriptor = param;
								String suffix = "";
								while(descriptor.startsWith("[")){
									param = param.substring(1);
									descriptor = descriptor.substring(1);
									suffix+="[]";
								}
								param = "";
								descriptor += suffix;
								parameterArray.add(descriptor);
							}
						}
					}
				} catch (Exception e){
					System.err.println("Invalid entry: " + methodDescription);
					e.printStackTrace();
					continue;
				}
				
				// create parameters elements under call elements
				Element parameters = document.createElement("parameters");
				call.appendChild(parameters);
				// create parameter elements under parameters elements
				int index = 0;
				for (String paramName : parameterArray) {
					Element parameter = document.createElement("parameter");
					parameter.setAttribute("index", ("" + index++));
					parameter.appendChild(document.createTextNode(paramName));
					parameters.appendChild(parameter);
				}
			}
		}

		// creating and writing to xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource domSource = new DOMSource(document);
		String filename = tag + "PermissionMapping.xml";
		File outputFile = new File(filename);
		StreamResult streamResult = new StreamResult(outputFile);
		transformer.transform(domSource, streamResult);
		scanner.close();

		System.out.println("Wrote: " + outputFile.getAbsolutePath());
	}
	
	private static String parseJVMDescriptor(String descriptor){
		String suffix = "";
		while(descriptor.startsWith("[")){
			descriptor = descriptor.substring(1);
			suffix+="[]";
		}
		if(descriptor.equals("I") || descriptor.equals("int")){
			descriptor = "int";
		} else if(descriptor.equals("J") || descriptor.equals("long")){
			descriptor = "long";
		} else if(descriptor.equals("S") || descriptor.equals("short")){
			descriptor = "short";
		} else if(descriptor.equals("F") || descriptor.equals("float")){
			descriptor = "float";
		} else if(descriptor.equals("D") || descriptor.equals("double")){
			descriptor = "double";
		} else if(descriptor.equals("C") || descriptor.equals("char")){
			descriptor = "char";
		} else if(descriptor.equals("B") || descriptor.equals("byte")){
			descriptor = "byte";
		} else if(descriptor.equals("Z") || descriptor.equals("boolean")){
			descriptor = "boolean";
		} else if(descriptor.startsWith("L")){
			// any non-primitive Object
			descriptor = descriptor.substring(1);
		}
		descriptor += suffix;
		return descriptor.replace(";", "");
	}
}
