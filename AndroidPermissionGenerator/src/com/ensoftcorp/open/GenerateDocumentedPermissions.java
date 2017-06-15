package com.ensoftcorp.open;

import java.util.Date;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A helper utility for parsing online Android documentation and generating documented Android permission objects
 * @author Ben Holland
 */
public class GenerateDocumentedPermissions {

	public static String DOCUMENTED_PERMISSIONS_REFERENCE = "https://developer.android.com/reference/android/Manifest.permission.html";
	
	public static void main(String[] args) throws Exception {
		
		HashSet<String> permissions = new HashSet<String>();
		
		System.out.println("// documented Android permission objects generated from " + DOCUMENTED_PERMISSIONS_REFERENCE + " on " + new Date());
		
		// parse documented permission
		Document doc = Jsoup.connect(DOCUMENTED_PERMISSIONS_REFERENCE).get();
		Elements documentedPermissionsTable = doc.select("#constants"); // webpage has a table element with id "constants"
		
		int counted = 0;
		
		for(Element row : documentedPermissionsTable.select("tr.api")){ // select table rows with class of "api" for each entry
			
			counted++;
			
			// get the api level
			int addedInAPILevel = -1;
			for(String className : row.classNames()){
				if(className.startsWith("apilevel-")){
					String value = className.substring("apilevel-".length());
					if(value.equalsIgnoreCase("O")){
						// O is 0?? antiscraper code?
						value = "0";
					}
					addedInAPILevel = Integer.parseInt(value);
				}
			}
			
			// get the constant's name and reference link
			Element link = row.children().select("td").last().child(0);
			String simpleName = link.text();
			String reference = "https://developer.android.com/reference/android/Manifest.permission.html#" + simpleName;
			
			// get the constant's qualified name and full description
			String description = "";
			String qualifiedName = "";
			for(Element detailDiv : doc.select("div.api")){ // expanded details are in a div with classes "api"
				// note use of ownText method excludes text in inner span
				if(detailDiv.children().select("h3.api-name").text().trim().equals(simpleName)){
					for(Element p : detailDiv.select("p")){
						if(p.hasClass("api-signature")){
							continue;
						} else if(p.text().contains("Constant Value:")){
							qualifiedName = p.text().substring(p.text().indexOf("\"")+1, p.text().lastIndexOf("\"")).trim();
						} else {
							description += p.text().trim() + "\n";
						}
					}
					
					description = description.trim();

					// some descriptions are missing a period, adding one to be consistent
					if(!description.isEmpty() && (!description.endsWith(".") && !description.endsWith("!"))){
						description += ".";
					}
				}
			}
			// SUBSCRIBED_FEEDS_WRITE has no documentation it seems (as of 1/14/2015)
			if(description.equals("")){
				description = "No description available.";
			}

			// get the deprecated in version if it exists
			// TODO: find a better way to do this, currently this is a sloppy way, but it works
			int deprecatedInAPILevel = -1;
			if(description.contains("deprecated in API level ")){
				String deprecatedInString = description.substring(description.indexOf("deprecated in API level ") + "deprecated in API level ".length());
				deprecatedInString = deprecatedInString.substring(0, deprecatedInString.indexOf("."));
				deprecatedInAPILevel = Integer.parseInt(deprecatedInString);
			}
			
			// sanity checks
			if(addedInAPILevel == -1){
				throw new Exception("Invalid API level!\nRow:\n" + row);
			}
			if(simpleName == null || simpleName.equals("")){
				throw new Exception("Empty constant simple name!\nRow:\n" + row);
			}
			if(qualifiedName == null || qualifiedName.equals("")){
				throw new Exception("Empty constant qualified name!\nRow:\n" + row);
			}
			if(reference == null || reference.equals("")){
				throw new Exception("Empty reference link!\nRow:\n" + row);
			}
			if(description.equals("")){
				throw new Exception("Empty description!\nRow:\n" + row);
			}
			
			// format as Permission(String qualifiedName, int addedInLevel, int deprecatedInApi, String description, String reference)
			System.out.println("public static final Permission " + simpleName + " = new Permission(\"" + qualifiedName + "\"" 
					+ ", " + addedInAPILevel
					+ ", " + deprecatedInAPILevel
					+ ", \"" + description.replace("\n", "\\n").replace("\"", "\\\"") + "\""
					+ ", \"" + reference + "\");");
			
			// keep track of the permission we added to generate the code to add the static field to the allPermissions set
			if(permissions.contains(simpleName)){
				throw new Exception("Duplicate permission! - " + simpleName);
			}
			permissions.add(simpleName);
		}
		
		System.out.println("\n-------------------------\n");
		
		for(String permission : permissions){
			System.out.println("allDocumentedPermissions.add(" + permission + ");");
		}
		
		System.out.println("\n-------------------------\n");
		System.out.println("Counted " + counted + " permissions");
	}

}
