[<img width="90" align="right" src="https://user-images.githubusercontent.com/12527390/80737506-4ae3a700-8b1c-11ea-92b7-a137982595ea.png"/>](#)

# Startup arguments

Well, the integration is pretty simple, you just need to find the activity that runs very first in your application and add the line below to its `onCreate` method.

```Kotlin
SampleApplication.application.devtools.updateFromBundle(intent.extras)
```
Now, whenever you start your app, the library will receive the very first intent bundle and will try to extract the startup arguments from it and update configuration values.

Thus, after you redeploy the app to your device, you'll be able to use the command below.

```shell script
adb shell am start <argument-flag> <tool-key> <config-value> -n <app-package-name>/<fully-qualified-activity-class-name>
```
Where:
* **argument-flag:** indicates the type of the configuration value extra. You can read more about this [here](https://developer.android.com/studio/command-line/adb#IntentSpec);
   * `--es`: for string configuration values;
   * `--ez`: for boolean configuration values;
   * `--ei`: for int configuration values;
   * `--el`: for long configuration values;
   * `--ef`: for floating-point configuration values.
* **tool-key:** is the unique key of the dev tool you want to update. You might take a look at the configuration file to find it, or you can use the tool to help dialog from the configuration screen;
* **config-value:** is the configuration value you want this tool to take after the app start;
* **app-package-name:** os the package name of your app;
* **fully-qualified-activity-class-name:** is the qualified activity name which contains the LUNCH intent filter.

For example: 
```shell script
adb shell am start --ez yml-toggle-tool false com.maximbircu.devtools/com.maximbircu.devtools.MainActivity
```

## ℹ️ Build the right command

<img width="230" alt="help-dialog" align="right" src="https://user-images.githubusercontent.com/12527390/80735942-1d95f980-8b1a-11ea-9ea1-50bee8bd8907.png" />

To build easier the shell command which will start your app preconfigured you can:

1. Open the app and go to the configuration screen
1. Find the tool you intend to update
1. Press on the context menu button (three dots button from the right upper corner)
1. Select the `Help` dialog menu
1. You'll notice the correct startup argument at the bottom of the dialog.

Now just run: 
````shell script
adb shell am start --ez toggle-tool false com.maximbircu.devtools/com.maximbircu.devtools.MainActivity
````
