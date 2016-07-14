package com.ensoftcorp.open;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class ConvertPScout2ToXML {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, IOException, TransformerException {
//		String permissionName = "";
//		Element permission = null;
//		Element call = null;

		// create a file object that points at the file to parse
		File file = new File(args[0]);
		String tag = args[1]; // android api name

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

			
			String[] columns = line.split(",");
			String callerClass = columns[0];
			String callerMethod = columns[1];
			String methodDescription = columns[2];
			String permissionName = columns[3];
			
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
				if(methodDescription.endsWith(")V")){
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
				String[] params = methodDescription.substring(methodDescription.indexOf("(")+1, methodDescription.indexOf(")")).split(";");
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
							if(descriptor.startsWith("I")){
								descriptor = "int";
								param = param.substring(1);
							} else if(descriptor.startsWith("J")){
								descriptor = "long";
								param = param.substring(1);
							} else if(descriptor.startsWith("S")){
								descriptor = "short";
								param = param.substring(1);
							} else if(descriptor.startsWith("F")){
								descriptor = "float";
								param = param.substring(1);
							} else if(descriptor.startsWith("D")){
								descriptor = "double";
								param = param.substring(1);
							} else if(descriptor.startsWith("C")){
								descriptor = "char";
								param = param.substring(1);
							} else if(descriptor.startsWith("B")){
								descriptor = "byte";
								param = param.substring(1);
							} else if(descriptor.startsWith("Z")){
								descriptor = "boolean";
								param = param.substring(1);
							} else if(descriptor.startsWith("L")){
								// any non-primitive Object
								descriptor = descriptor.substring(1);
								param = "";
							}
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
			for (String paramName : parameterArray) {
				Element parameter = document.createElement("parameter");
				parameter.appendChild(document.createTextNode(paramName));
				parameters.appendChild(parameter);
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
		if(descriptor.equals("I")){
			descriptor = "int";
		} else if(descriptor.equals("J")){
			descriptor = "long";
		} else if(descriptor.equals("S")){
			descriptor = "short";
		} else if(descriptor.equals("F")){
			descriptor = "float";
		} else if(descriptor.equals("D")){
			descriptor = "double";
		} else if(descriptor.equals("C")){
			descriptor = "char";
		} else if(descriptor.equals("B")){
			descriptor = "byte";
		} else if(descriptor.equals("Z")){
			descriptor = "boolean";
		} else if(descriptor.startsWith("L")){
			// any non-primitive Object
			descriptor = descriptor.substring(1);
		}
		descriptor += suffix;
		return descriptor.replace(";", "");
	}
}
