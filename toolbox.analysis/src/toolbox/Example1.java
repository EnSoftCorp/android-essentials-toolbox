package toolbox;

import toolbox.android.essentials.permissions.Permission;
import toolbox.android.essentials.permissions.mappings.PermissionMapping;
import toolbox.support.SetDefinitions;

import com.ensoftcorp.atlas.core.query.Attr.Edge;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;

public class Example1 {

	/**
	 * Given a permission and API version, this method returns true if there are methods in the Android app
	 * that call the given permission protected methods
	 * @param permission The Permission to query against
	 * @param apiVersion The Android API level to query against
	 * @return returns true if the permission is used by the application, false otherwise
	 */
	public static boolean isPermissionUsed(Permission permission, int apiVersion) {
		// create a subgraph of the complete program graph containing only call edges and the nodes attached to those edges
		Q callEdges = Common.universe().edgesTaggedWithAny(Edge.CALL).retainEdges();
		// get the permission protected API methods for the permission and version of Android
		Q permissionProtectedMethods = PermissionMapping.getMethods(permission, apiVersion);
		// get the calling methods of the permission protected API methods
		Q permissionProtectedMethodCallers = callEdges.predecessors(permissionProtectedMethods);
		// get the calling methods of the permission protected API methods that also exist in the Android app
		Q permissionProtectedMethodAppCallers = permissionProtectedMethodCallers.intersection(SetDefinitions.app());
		// return true if there are callers of the permission protected APIs in the Android app
		return permissionProtectedMethodAppCallers.eval().nodes().size() != 0;
	}

}
