---
layout: page
title: Install
permalink: /install/
---

Installing the Android Essentials Toolbox Eclipse plugin is easy.  It is recommended to install the plugin from the provided update site, but it is also possible to install from source.
        
### Installing Dependencies
- If you plan to analyze an Android source application you will need the ability to compile an Android application in the Eclipse workspace. To do so you will need to install the Android ADT plugin. Google has discontinued support of Android development for Eclipse, but the eclipse update site is still active: `https://dl-ssl.google.com/android/eclipse/`. Once installed you should use the SDK Manager to download the appropriate Android API versions for your analysis task. If you plan to audit an Android APK (compiled binary) then you do not need this dependency.

### Installing from Update Site (recommended)
1. Start Eclipse, then select `Help` &gt; `Install New Software`.
2. Click `Add`, in the top-right corner.
3. In the `Add Repository` dialog that appears, enter &quot;Atlas Toolboxes&quot; for the `Name` and &quot;[https://ensoftcorp.github.io/toolbox-repository/](https://ensoftcorp.github.io/toolbox-repository/)&quot; for the `Location`.
4. In the `Available Software` dialog, select the checkbox next to "Android Essentials Toolbox" and click `Next` followed by `OK`.
5. In the next window, you'll see a list of the tools to be downloaded. Click `Next`.
6. Read and accept the license agreements, then click `Finish`. If you get a security warning saying that the authenticity or validity of the software can't be established, click `OK`.
7. When the installation completes, restart Eclipse.

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