package com.ensoftcorp.open;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class ConvertPScoutToXML {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, IOException, TransformerException {
		String permissionName = "";
		Element permission = null;
		Element call = null;

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
		while (scanner.hasNextLine()) {
			// read one line of the input file
			String line = scanner.nextLine();

			if (line.charAt(0) == 'P') {
				// get the permission name if this is a permission line
				permissionName = line.substring(11);

				// define permission elements in xml
				permission = document.createElement("permission");
				permissions.appendChild(permission);
				Attr attribute = document.createAttribute("name");
				attribute.setValue(permissionName);
				permission.setAttributeNode(attribute);

			} else if (line.charAt(0) != 'P' && line.charAt(0) != '<') {
				// get the number of methods to read if this is callers line
//				int spaceindex = line.indexOf(" ");
//				int numberOfMethods = Integer.parseInt(line.substring(0, spaceindex));
			} else {
				// read the method if this is method line
				String qualifiedClass = line.substring(1, line.indexOf(":"));

				// parse the package
				String packageName = qualifiedClass.substring(0, (qualifiedClass.lastIndexOf(".")));

				// parse the class
				String className = qualifiedClass.substring((qualifiedClass.lastIndexOf(".") + 1), qualifiedClass.length());

				// parse the return type
				String line2 = line.substring(line.indexOf(":") + 1, line.indexOf("("));
				String qualifiedMethodTrimmed = line2.trim();
				String methodNameWithReturnType[] = qualifiedMethodTrimmed.split(" ");
				String qualifiedMethodReturnType = methodNameWithReturnType[0];
				String unqualifiedMethodReturnType = qualifiedMethodReturnType;
				if (qualifiedMethodReturnType.contains(".")) {
					unqualifiedMethodReturnType = qualifiedMethodReturnType.substring(qualifiedMethodReturnType.lastIndexOf(".") + 1);
				}

				// parse the method name
				String methodName = methodNameWithReturnType[1];

				// parse the parameters
				String line3 = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
				String parameterArry[] = line3.split(",");

				// create call elements under permission elements
				call = document.createElement("call");
				permission.appendChild(call);

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
				method.appendChild(document.createTextNode(methodName));
				call.appendChild(method);

				// create returnType elements under call elements
				Element returnType = document.createElement("returnType");
				returnType.appendChild(document.createTextNode(unqualifiedMethodReturnType));
				call.appendChild(returnType);

				// create parameters elements under call elements
				Element parameters = document.createElement("parameters");
				call.appendChild(parameters);
				// create parameter elements under parameters elements
				for (String paramName : parameterArry) {
					if (paramName == null || paramName.equals(""))
						break;
					Element parameter = document.createElement("parameter");
					parameter.appendChild(document.createTextNode(paramName));
					parameters.appendChild(parameter);
				}
			} // end read method line
		} // end read file

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
}
