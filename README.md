Android Essentials Toolbox
==========================

The Android Essentials Toolbox is an Atlas toolbox containing logic for Android Permissions, Manifest, and other Android domain specific logic useful in program analysis.

## Overview
This feature contains encoded mappings recovered from the Android source code (as of API version 19) to map [Protection Levels](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel) and [Permission Groups](https://developer.android.com/guide/topics/manifest/permission-element.html#pgroup) to the corresponding [manifest permissions](https://developer.android.com/guide/topics/manifest/permission-element.html).  Permission details (simple name, quaified name, description, etc.) are encoded from information found in the [Google documentation](https://developer.android.com/reference/android/Manifest.permission.html). Using the [University of Toronto's PScout utility](http://pscout.csl.toronto.edu/) this feature also includes mappings from permissions to Android permission restricted API methods.

## Setup
The `toolbox.android.essentials` project is an Eclipse plugin that can be installed into the Eclipse environment.  To install the Eclipse plugin from the workspace right click on the project and navigate to `Export`->`Plug-in Development`->`Deployable plug-ins and fragments`.  Select `Next` and make sure only the `toolbox.android.essentials` project is selected.  Then select the `Install into host.` radio and click `Finish`.  You will need to restart Eclipse.

Alternatively if you have a toolbox project that depends on the Android Essentials Toolbox keeping both projects in the workspace is enough, but you should note that confusion may occur if there is an installed version and a version in the workspace.  The Atlas interpreter tends to give priority to the version in the workspace.

## Example Usage

    int apiVersion = 19;
    PermissionMapping.applyTags(apiVersion);
    Q internetRestrictedAPIs = PermissionMapping.getMethods(Permission.INTERNET, apiVersion);
    long numMethods = CommonQueries.nodeSize(internetRestrictedAPIs);
    System.out.println("There are " + numMethods + " restricted methods for " + Permission.INTERNET.getQualifiedName());
