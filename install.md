---
layout: page
title: Install
permalink: /install/
---

Installing the Toolbox Commons Eclipse plugin is easy.  It is recommended to install the plugin from the provided update site, but it is also possible to install from source.
        
### Installing Dependencies

1. First make sure you have [Atlas](http://www.ensoftcorp.com/atlas/download/) Standard or Pro installed.
2. When installing Atlas make sure to also include Atlas Experimental features (to include Atlas for Jimple support).
3. Finally, while it is not technically required for this plugin to function you will likely need the ability to compile an Android application in the Eclipse workspace. To do so you will need to install the Android ADT plugin. Google has discontinued support of Android development for Eclipse, but the eclipse update site is still active: `https://dl-ssl.google.com/android/eclipse/`. Once installed you should use the SDK Manager to download the appropriate Android API versions for your analysis task.
        
### Installing from Update Site
Follow the steps below to install the Toolbox Commons plugin from the Eclipse update site.

1. Start Eclipse, then select `Help` &gt; `Install New Software`.
2. Click `Add`, in the top-right corner.
3. In the `Add Repository` dialog that appears, enter &quot;Android Essentials Toolbox&quot; for the `Name` and &quot;[https://ensoftcorp.github.io/android-essentials-toolbox/updates/](https://ensoftcorp.github.io/android-essentials-toolbox/updates/)&quot; for the `Location`.
4. In the `Available Software` dialog, select the checkbox next to "Toolbox Commons" and click `Next` followed by `OK`.
5. In the next window, you'll see a list of the tools to be downloaded. Click `Next`.
6. Read and accept the license agreements, then click `Finish`. If you get a security warning saying that the authenticity or validity of the software can't be established, click `OK`.
7. When the installation completes, restart Eclipse.

**Note:** For legacy Atlas 2.x updates use the &quot;[https://ensoftcorp.github.io/android-essentials-toolbox/atlas2-updates/](https://ensoftcorp.github.io/android-essentials-toolbox/atlas2-updates/)&quot; update site.

## Installing from Source
If you want to install from source for bleeding edge changes, first grab a copy of the [source](https://github.com/EnSoftCorp/android-essentials-toolbox) repository. In the Eclipse workspace, import the `com.ensoftcorp.open.android.essentials` Eclipse project located in the source repository.  Right click on the project and select `Export`.  Select `Plug-in Development` &gt; `Deployable plug-ins and fragments`.  Select the `Install into host. Repository:` radio box and click `Finish`.  Press `OK` for the notice about unsigned software.  Once Eclipse restarts the plugin will be installed and it is advisable to close or remove the `com.ensoftcorp.open.android.essentials` project from the workspace.

## Changelog
Note that version numbers are based off [Atlas](http://www.ensoftcorp.com/atlas/download/) version numbers.

## 3.1.0
- Updates to dependencies

### 3.0.8
- Version bump, icon updates

### 3.0.7
- Atlas 3.0 release, added permission mapping preferences and menu handlers, general refactoring

### 2.5.2
- Atlas updates

### 2.4.3
- Atlas updates

### 2.2.6
- Updates to multiple permission mappings, guard logic for safely applying permissions

### 2.1.2
- Updates for Eclipse Luna

### 2.0.6
- Version bump, bug fixes for formatted source correspondents

### 2.0.5
- Version bump, bug fixes

### 2.0.2
- Intial release