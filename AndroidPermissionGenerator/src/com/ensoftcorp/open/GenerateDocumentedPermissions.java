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
		for(Element row : documentedPermissionsTable.select("tr.api")){ // select table rows with class of "api" for each entry
			
			// get the api level
			int addedInAPILevel = -1;
			for(String className : row.classNames()){
				if(className.startsWith("apilevel-")){
					addedInAPILevel = Integer.parseInt(className.substring("apilevel-".length()));
				}
			}
			
			// get the constant's name and reference link
			String simpleName = "";
			String reference = "";
			Element link = row.children().select("td.jd-linkcol").first().child(0);
			simpleName = link.text().trim();
			reference = "https://developer.android.com" + link.attr("href");
			
			// get the constant's qualified name and full description
			String description = "";
			String qualifiedName = "";
			for(Element detailDiv : doc.select("div.jd-details.api")){ // expanded details are in a div with classes "jd-details" and "api"
				// note use of ownText method excludes text in inner span
				if(detailDiv.children().select("h4.jd-details-title").first().ownText().trim().equals(simpleName)){
					// qualified name is in a span without a class in a div with the class "jd-tagdata" inside a div with the class "jd-details-descr"
					for(Element jdTagDataDiv : detailDiv.children().select("div.jd-details-descr").first().children().select("div.jd-tagdata")){						
						// we want the div that only has the jd-tagdata class
						if(jdTagDataDiv.className().equals("jd-tagdata")){
							for(Element span : jdTagDataDiv.children().select("span")){
								if(span.className().equals("")){
									qualifiedName = span.text().replaceAll("\"", "").trim();
								}
							}
						}
					}
					
					// description is in multiple paragraph elements nested inside a div with classes "jd-tagdata" and "jd-tagdescr" 
					// within a div with the class "jd-details-descr"
					for(Element paragraph : detailDiv.children().select("div.jd-details-descr").first().children()
							.select("div.jd-tagdata.jd-tagdescr").first().children()
							.select("p")){
						description += "\n" + paragraph.text().trim();
						description = description.trim();
						// some descriptions are missing a period, adding one to be consistent
						if(!description.isEmpty() && (!description.endsWith(".") && !description.endsWith("!"))){
							description += ".";
						}
					}
					// description may be for a deprecated method, in which case some info is in a special paragraph with a "caution" class
					for(Element paragraph : detailDiv.children().select("div.jd-details-descr").first().children()
							.select("p.caution")){
						description += "\n" + paragraph.text().trim();
						description = description.trim();
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
	}

}
