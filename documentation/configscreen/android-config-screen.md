[<img width="90" align="right" src="https://user-images.githubusercontent.com/12527390/80737506-4ae3a700-8b1c-11ea-92b7-a137982595ea.png"/>](#)

# Configuration screen
One of the easiest ways to update a configuration value will be an excellent user interface. The library is able to generate a configuration screen dynamically based on your configuration file.

## How to use
To show the screen, you just need to create a new activity for it and attach it to any view, as shown in the sample below.

```Kotlin
class DevToolsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentView = findViewById<ViewGroup>(android.R.id.content)
        DevToolsConfigurationScreen.attachToView(contentView, MyApplication.application.devtools)
    }
}
```
You can attach this config screen view to any other view from your app.

## Theming
The android library is based on material design. There is a [Theme.DevTools](../../devtools/android/src/main/res/values/styles.xml#L3) that you might extend inside your app to update the colors, text appearance, and shaping.
