## Build
To build AccessibilityServiceDemo sources you will need to: 

1.  Download android sdk from https://developer.android.com/sdk/installing/index.html?pkg=tools
2.  Set ANDROID_HOME to the path of Android sdk folder
3.  Open Android SDK manager and install
  - Tools/Android SDK Build-tools 23.0.3
  - Android 6.0 (API 23)
  - Extras/Android Support Repository
  - Extras/Google Repository

AccessibilityServiceDemo uses gradle as build system.
Here are commands to build and install the project from command line:

1.  Assemble only debug apk: ./gradlew assembleDebug
2.  Install debug apk on connected device: ./gradlew installDebug  

## Test
To test this you will need to do the following:

1.  Install it on a device.
2.  Go to Settings > Accessibility
3.  Find '*TextReader*' Accessibility Service and turn it on. 
4.  Now text from all TextViews should be spoken when window content is changed, start by clicking back button.
