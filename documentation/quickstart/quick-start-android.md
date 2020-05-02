[<img width="90" align="right" src="https://user-images.githubusercontent.com/12527390/80737506-4ae3a700-8b1c-11ea-92b7-a137982595ea.png"/>](#)

# Quick start 

1. **Add the library dependency**

    TBA

<br />

2. **Add a configuration file**

    Currently, you can set up your app with dev tools directly from the code or using a [YAML](https://yaml.org/) or [JSON Schema Draft-07](https://json-schema.org/draft-07/json-schema-validation.html) configuration file.
    The example will be based on YML; however, using other sources is not hard. 
    
    You can check the [supported dev tools](../documentation.md#supported-dev-tools) and [configuration sources](../documentation.md#configuration-sources) for more information.
    
    So, create a `dev-tools.yml` configuration file inside your app's `assets` directory.
    
    ```yaml
    toggle-tool: !toggle {
      title: "Toggle tool",
      description: "A boolean configuration value tool",
      canBeDisabled: true,
      defaultEnabledValue: false,
      isCritical: true,
      default: true
    }
    
    enum-tool: !enum {
      title: "Enum tool",
      description: "An enum configuration value tool",
      canBeDisabled: true,
      defaultEnabledValue: false,
      isCritical: false,
      allowCustom: true,
      defaultValueKey: first-option,
      options: {
        first-option: "First Option Value",
        second-option: "Second Option Value",
        third-option: "Third Option Value",
      }
    }
    ```
   
<br />

3. **Create a tools source**

    Before we get to creating the [DevTools](../../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/DevTools.kt) object, the object which you'll use to interact with the library we need to learn how to create a source for the `dev-tools.yml` configuration file we added in the previous step.
    
    You can check all [configuration sources](../documentation.md#configuration-sources) the library supports at the moment. We need to use the YML one, though.
    
    ```kotlin
    val source = DevToolsSources.yaml(assets, "dev-tools.yml")
    ```

<br />

4. **Create a dev tools instance**
    
    [DevTools](../../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/DevTools.kt) is the object you'll use to interact with the library from yours app code because it represents the library interface.
    You'll be able to read, update, and perform many other useful actions with your config using it.
    
    Check out the whole [public api](../documentation.md#public-api) for more information.
    
    Most probably, you'll want to reuse this instance inside the whole app, that's why I suggest you wrap it in a custom class just to decouple the library from your project.
    
    I'll place it directly inside the Application class just for the sake of example. This is not the best design, though.
    
    ```Kotlin
    class SampleApplication : Application() {
        lateinit var devtools: DevTools private set
    
        companion object {
            lateinit var application: SampleApplication
                private set
        }
    
        override fun onCreate() {
            super.onCreate()
            application = this
    
            val source = DevToolsSources.yaml(assets, "dev-tools.yml")
            devtools = DevTools.create("DEV_TOOLS", source)
        }
    }
    ```

<br />

5. **Add a new configuration activity**

    One of the easiest ways to update a configuration value is a good user interface. 
    The library can generate one for you using just the `dev-tools.yml` configuration file you've provided.
    
    You just need to create a separate activity for the configuration screen and open it whenever you need to update your app config.
    
    ```Kotlin
    class DevToolsActivity: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
    
            val contentView = findViewById<ViewGroup>(android.R.id.content)
            DevToolsConfigurationScreen.attachToView(contentView, SampleApplication.application.devtools)
        }
    }
    ```
    You can attach this config screen view to any other view from your app.
    You can even adjust its theme if you don't like the default one, check out how to do this [here](../configscreen/android-config-screen.md).

<br />

6. **Integrate the startup arguments**

    You might need to run your app preconfigured. The most common use case would be an automation test, for example. 
    This is also possible, and to make use of this feature, you must integrate it first.
    
    Go to the very first activity to be opened from your app, in my sample, it is called `MainActivity` and add the following call.
    ```Kotlin
    SampleApplication.application.devtools.updateFromBundle(intent.extras)
    ```
   
   Now you can go and check how to use the [startup arguments](../startuparguments/android-startup-arguments.md) and start using them.

# Congratulations 🎉 
<img width="300" alt="dev-tools" align="right" src="https://user-images.githubusercontent.com/12527390/80519661-feb82b80-8990-11ea-8a6a-07e0d62a1aac.png"/>

You've just finished the integration process Now you should be able to update and consume your app config way more comfortable than before.

#### Configuration screen

Just go and open the Configuration activity you've defined, and you should see your configuration screen.

#### Startup arguments

You can also try to update your app config from startup arguments.

```shell script
adb shell am start --ez toggle-tool false com.maximbircu.devtools/com.maximbircu.devtools.MainActivity
```

#### Accessing config values

You can use the [DevTools](../../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/DevTools.kt) instance to access the configuration values of your dev tools.
```kotlin
val value: Boolean = devtools.getValue("toggle-tool")
```

Or to check if your dev tool is enabled

```kotlin
val isEnabled = devtools.isEnabled("toggle-tool")
```

And react on its configuration changes

```kotlin
devtools.onConfigUpdated = { isCriticalUpdate -> 
    /* React on configuration changes*/ 
}
```
