[<img width="90" align="right" src="https://user-images.githubusercontent.com/12527390/80737506-4ae3a700-8b1c-11ea-92b7-a137982595ea.png"/>](#)

# 👷 Custom dev tool types

In case the prebuilt tools set is not sufficient, or you don't like the UX they provided, you might build your own inside your app. 
The process is straightforward, you just need to extend a couple of classes and implement your custom logic inside them. 
Let's see how to do it in a simple step by step example:

1. **Define the configuration value type**

   So, first of all, you need to understand what's the type of configuration value that your new dev tool will wrap and manipulate. Let's say it will be `Boolean` just for the sake of example.

1. **Create a domain object**

    To create a domain object you should extend the [DevTool](../../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/core/DevTool.kt) class and provide a default value and a store implementation.
    
    Creating a custom store is not difficult, check [custom store](#custom-dev-tools-store) for more information.
    
    However to make the example simpler I'll extend the [PreferencesDevTool](../../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/core/DevTool.kt#L116) which comes with a built-in `SharedPreferences` store. 

    ```kotlin
    class MyCustomTool : PreferencesDevTool<Boolean>() {
        override fun getDefaultValue(): Boolean {
            return true // Suppose that I want the default value to always be true.
        }
    }
    ```

2. **Create a UI component**

    To do this, you just need to extend the [DevToolLayout](../../devtools/android/src/main/kotlin/com/maximbircu/devtools/android/presentation/tool/DevToolLayout.kt) and provide my custom dev tool user interface implementation. 

    ```kotlin
    class MyCustomToolLayout(context: Context) : DevToolLayout<MyCustomTool>(context) {
        override val layoutRes: Int get() = layout.layout_my_custom_tool
    
        override fun onBind(tool: MyCustomTool) {
            checkbox.isChecked = tool.value
            checkbox.setOnCheckedChangeListener { _, isChecked -> tool.value = isChecked }
        }
    }
    ```

    And here is the `layout_my_custom_tool.xml`. The XML layout for this custom view. It will contain a checkbox so that we can change a boolean configuration value.
    
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <CheckBox xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/checkbox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Is checked"
        />
    ```

3. **Register your new dev tool (tell the library that your dev tool-type exists)** 

    To do this, you'll have to call one line at the app start. 
    ```kotlin
    DevToolsViewRegistry.register(MyCustomTool::class) { MyCustomToolLayout(it) }
    ```
    ⚠️ You cannot register the same tool twice, you have to call this line just one time per Application scope.
    I am calling it inside the Application `onCreate` method.
    
4. **Create a custom tools source**
    
    In order to use your custom tool, you'll need to create a custom source to be able to provide its instance to the library.

    ```kotlin
     val myCustomToolSource = object :DevToolsSource {
        override fun getReader(): DevToolsReader {
            return object: DevToolsReader {
                override fun getDevTools(): Map<String, DevTool<*>> {
                    return mapOf(
                        "my-custom-tool" to MyCustomTool().apply {
                            title = "Custom tool"
                            description = "A custom dev tool implemented inside the library consumer"
                            canBeDisabled = true
                            defaultEnabledValue = false
                        }
                    )
                }
            }
    
        }
    }
    ```
5. **And finally** 🎉

    Use your tool inside any dev tools instance
    ```Kotlin
    val tools = DevTools.create("MY_CUSTOM_TOOLS", myCustomToolSource)
    ```
   
### Custom dev tools store

All the library prebuilt dev tools are using a SharedPreferences store. However, you can create your own stores and use them inside your custom tools.

You just have to extend [ToolStore](../../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/core/ToolStore.kt) and provide your own implementation.

Here is an example of a custom store that also saves the data to shared preferences.

```kotlin
private val sharedPrefs = SampleApplication.application.getSharedPreferences(
    "MY_CUSTOM_TOOL_STORE",
    Context.MODE_PRIVATE
)

class MyCustomToolStore(private val tool: MyCustomTool): ToolStore<Boolean> {
    override var isEnabled: Boolean
        get() = sharedPrefs.getBoolean("is_enabled_${tool.key}", tool.defaultEnabledValue)
        set(value) { sharedPrefs.edit().putBoolean("is_enabled_${tool.key}", value).commit() }

    override var value: Boolean
        get() = sharedPrefs.getBoolean(tool.key, tool.getDefaultValue())
        set(value) { sharedPrefs.edit().putBoolean(tool.key, value).commit() }
}
```

And this is how you can use your store.

```kotlin
class MyCustomTool : DevTool<Boolean>() {
    override val store: ToolStore<Boolean> = MyCustomToolStore(this)

    override fun getDefaultValue(): Boolean {
        return false // Suppose that I want the default value to always be true.
    }
}
```
