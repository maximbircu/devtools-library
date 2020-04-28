# Documentation

<img width="400" align="right" alt="dev-tools" src="https://user-images.githubusercontent.com/12527390/80514191-daf0e780-8988-11ea-911f-15d16e6b88a1.png"/>

* [🚀 Quick start](quickstart/quick-start.md) 
    * [🍏 iOS](quickstart/quick-start-ios.md) 
    * [🤖 Android](quickstart/quick-start-android.md) 
* [Prebuilt dev tool types](#prebuilt-dev-tool-types)
* 👷 [Custom dev tool types](#-custom-dev-tool-types)
    * [🍏 iOS](customtools/custom-tools-ios.md)
    * [🤖 Android](customtools/custom-tools-android.md)
* [Configuration sources](#configuration-sources)
    * [Prebuilt sources](#prebuilt-sources)
    * 👷 [Custom sources](#-custom-sources)
* [Public API](#public-api)
* [Configuration screen](#configuration-screen)
    * [🍏 iOS](./configscreen/ios-config-screen.md)
    * [🤖 Android](./configscreen/android-config-screen.md)
        * [How to use](./configscreen/android-config-screen.md#how-to-use)
        * [Theming](./configscreen/android-config-screen.md#theming)
* [Startup arguments](#startup-arguments)
    * [🍏 iOS](./startuparguments/ios-startup-arguments.md)
    * [🤖 Android](./startuparguments/android-startup-arguments.md)
* 👷 [For developers](#-for-developers)
* 📜 [License](../README.md#license)

<br />

The scope of the library is to help a library consumer to manage its configuration values. `DevTool` represents the main, and the single library domain object, which wraps a configuration value and encapsulates different actions(i.e., `persist`, `update`...) that could be performed with it.

As the configuration values might be of different types, there is a set of different prebuilt `DevTool` implementations for each configuration value type in part. 

<br />

## Prebuilt dev tool types
The currently prebuilt list of dev tool types makes it possible to handle all configuration values of primitive types like `String`, `Integer`, and `Floating-Point`. 
Reference types are not supported yet.

| Name                           | Config&nbsp;value&nbsp;type          | Yaml | Json | Memory | Description                                                                                                  |                                                                                                                                                  |
|--------------------------------|----------------------------|:----:|:----:|:------:|--------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| [Toggle](tools/toggle-tool.md) | `Boolean`                  |  ➕  |  ➕  |   ➕   | Allows to store, update, and consume boolean config values.                                                  | <img width="600" alt="toggle-tool" src="https://user-images.githubusercontent.com/12527390/80479540-d19b5700-8957-11ea-8f06-293b75a6db6c.png" /> |
| [Text](tools/text-tool.md)     | `String` `Integer` `Float` |  ➕  |  ➕  |   ➕   | Allows to store, update, and consume text and numeric config values.                                         | <img width="600" alt="text-tool" src="https://user-images.githubusercontent.com/12527390/80484673-c862b800-8960-11ea-81fb-43159c004050.png" />   |
| [Enum](tools/enum-tool.md)     | `String` `Integer` `Float` |  ➕  |  ➕  |   ➕   | Allows to store, update, and consume text and numeric config values. You set a limited config value options. | <img width="600" alt="enum-tool" src="https://user-images.githubusercontent.com/12527390/80527242-b9016000-899c-11ea-8ed7-d7f17d4f10b4.png" />   |
| [Time](tools/time-tool.md)     | `Long`                     |  ➕  |  ➖  |   ➕   | Allows to store, update, and consume time config values. The config value is stored as Long in ms.           | <img width="600" alt="time-tool" src="https://user-images.githubusercontent.com/12527390/80527422-0087ec00-899d-11ea-89eb-9c5afce8f7ce.png" />   |
| [Group](tools/group-tool.md)   | `DevTool`                  |  ➕  |  ➕  |   ➕   | Allows to group tools that belong to the same context.                                                       | <img width="600" alt="group-tool" src="https://user-images.githubusercontent.com/12527390/80527585-42189700-899d-11ea-96cc-d993f31608cf.png" />  |

## 👷 Custom dev tool types
In case the prebuilt dev tools are not sufficient, a library consumer might extend the library with its own dev tools.

[<img width="64" src="https://user-images.githubusercontent.com/12527390/80728962-3d282480-8b10-11ea-9d64-94b19b16437e.png"/>](customtools/custom-tools-ios.md)
[<img width="64" src="https://user-images.githubusercontent.com/12527390/80728603-cbe87180-8b0f-11ea-916f-df88789ce0be.png"/>](customtools/custom-tools-android.md)

## Configuration sources

Currently, the library supports 3 dev tools sources Yaml, Json, and Memory. However, in case you 
need to use a custom one, you might add it very merely. Check how to do this [here](#-custom-sources).

### Prebuilt sources

<table class="tg">
    <tr>
        <td></td>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <th>Yaml</th>
        <td width="50%">
            <pre lang="kotlin">val src = DevToolsSources.yaml(
   assetManager = assets,
   fileName = "dev-tools.yml"
)</pre>
        </td>
        <td width="50%">
            <pre lang="Swift">Not ready yet.</pre>
        </td>
    </tr>
    <tr>
        <th>Json</th>
        <td width="50%">
            <pre lang="kotlin">val src = DevToolsSources.json(
   assetManager = assets,
   fileName = "dev-tools.json"
)</pre>
        </td>
        <td width="50%">
            <pre lang="Swift">Not ready yet.</pre>
        </td>
    </tr>
    <tr>
        <th>Memory</th>
        <td width="50%">
            <pre lang="kotlin">val tools: Map&ltString, DevTool&lt*&gt&gt = mapOf(
   "toggle-tool" to ToggleTool(default = true),
   "text-tool" to TextTool(default = 3.0),
   "time-tool" to TimeTool(days = 1)
)
val src = DevToolsSources.memory(tools)</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

<br/>

### 👷 Custom sources

To add a new custom source you just need to:

1. Create a custom dev tools reader

    The reader is the place where you should gather the dev tools data from your own source and
    convert it to a collection of key-value pairs. Where the key is a unique dev tool key and the 
    value is the actual dev tool.

    <table class="tg">
        <tr>
            <td>🤖 Android</td>
            <td>🍏 iOS</td>
        </tr>
        <tr>
            <td width="50%">
                 <pre lang="Kotlin">class MyCustomDevToolsReader : DevToolsReader {
        override fun getDevTools(): Map<String, DevTool<*>> {
            return mapOf(
                "toggle-tool" to ToggleTool(default = true),
                "text-tool" to TextTool(default = 3.0),
                "time-tool" to TimeTool(days = 1)
            )
        }
    }</pre>
            </td>
            <td width="50%">
                <pre lang="swift">Not ready yet.</pre>
            </td>
        </tr>
    </table>

2. Create the actual source

    You can do this by implementing [DevToolsSource](../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/core/reader/DevToolsSource.kt)
    
    <table class="tg">
        <tr>
            <td>🤖 Android</td>
            <td>🍏 iOS</td>
        </tr>
        <tr>
            <td width="50%">
                 <pre lang="Kotlin">class MyCustomSource : DevToolsSource {
        override fun getReader(): DevToolsReader {
            return MyCustomDevToolsReader()
        }
    }</pre>
            </td>
            <td width="50%">
                <pre lang="swift">Not ready yet.</pre>
            </td>
        </tr>
    </table>
    
## Public API
To access and manipulate your configuration values programmatically from your's app code you'll need
too have access to a [DevTools](../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/DevTools.kt) instance.

**Create a dev tools instance**

You'll need to provide at least 2 parameters to create a new [DevTools](../devtools/common/src/commonMain/kotlin/com/maximbircu/devtools/common/DevTools.kt) instance which are:

1. `name`: a unique for your app instance dev tools id;
2. `devToolsSources`: a list of dev tools sources;
3. `onConfigUpdate` (optional): a config values update listener.

<table class="tg">
    <tr>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <td width="50%">
             <pre lang="Kotlin">val source = DevToolsSources.yaml(assets, "dev-tools.yml")
val devtools = DevTools.create("TOOLS", source)<br />
// or you can provide more sources<br />
val memory = DevToolsSources.memory(tools)
val yml = DevToolsSources.yaml(assets, "dev-tools.yml")
val json = DevToolsSources.json(assets, "dev-tools.json")
val devtools = DevTools.create("TOOLS", memory, yml, json)</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

**Access configuration**

<table class="tg">
    <tr>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <td width="50%">
             <pre lang="Kotlin">val value: Boolean = devtools.getValue("toggle-tool")</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

**React on configuration changes**

The call back will be invoked whenever a new configuration value gets updated.
`isCriticalUpdate` will be true just in case at least one dev tool that you marked as critical using 
the critical flag will be updated.

<table class="tg">
    <tr>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <td width="50%">
             <pre lang="Kotlin">devtools.onConfigUpdated = { isCriticalUpdate -> 
    /* React on configuration changes*/ 
}</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

**Access group child configuration value**
<table class="tg">
    <tr>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <td width="50%">
             <pre lang="Kotlin">val value: Boolean = devtools.getGroup("toggle-tool")
             .getValue("toggle-tool")<br/>             
// or<br/>
val value: Boolean = devtools
             .getGroup("toggle-tool")
             .getGroup("toggle-inner-group")
             .getValue("toggle-tool")</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

**Check if configuration is enabled**
<table class="tg">
    <tr>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <td width="50%">
             <pre lang="Kotlin">val isEnabled = devtools.isEnabled("toggle-tool")<br/>
// or<br/>
val isEnabled = devtools
            .getGroup("toggle-tool")
            .getGroup("toggle-inner-group")
            .isEnabled("toggle-tool")</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

**Get all configuration as JSON**

Note that the lambda parameter is nothing else than a simple predicate(a filter). You can use it
to filter out the configuration values that will reach the final JSON result. 

For example, you can filter and get just the enabled config, as shown in the example.

<table class="tg">
    <tr>
        <td>🤖 Android</td>
        <td>🍏 iOS</td>
    </tr>
    <tr>
        <td width="50%">
             <pre lang="Kotlin">devtools.getAllConfigAsJson { tool -> tool.isEnabled }</pre>
        </td>
        <td width="50%">
            <pre lang="swift">Not ready yet.</pre>
        </td>
    </tr>
</table>

## Configuration screen
One of the easiest ways to update a configuration value is a good user interface. The library is able to generate a configuration screen dynamically based on your configuration file.

[<img width="64" src="https://user-images.githubusercontent.com/12527390/80728962-3d282480-8b10-11ea-9d64-94b19b16437e.png"/>](configscreen/ios-config-screen.md)
[<img width="64" src="https://user-images.githubusercontent.com/12527390/80728603-cbe87180-8b0f-11ea-916f-df88789ce0be.png"/>](configscreen/android-config-screen.md)

## Startup arguments
Sometimes we might need to run the app preconfigured. The most common use case could be an automated test, for example. So the best way to run the app preconfigured is to run it from a terminal and pass some startup arguments. The library provides a similar feature, but to make use of it, you must integrate it first.

[<img width="64" src="https://user-images.githubusercontent.com/12527390/80728962-3d282480-8b10-11ea-9d64-94b19b16437e.png"/>](startuparguments/ios-startup-arguments.md)
[<img width="64" src="https://user-images.githubusercontent.com/12527390/80728603-cbe87180-8b0f-11ea-916f-df88789ce0be.png"/>](startuparguments/android-startup-arguments.md)

## 👷 For Developers
If you know how to fix an issue, consider opening a pull request for it. 🙏

You can read this repository’s [contributing guidelines](../CONTRIBUTING.md) to learn how to open a good pull request.

### Modules

<div style="text-align:center"><img align="right" width="500" alt="modules-dependencies" src="https://user-images.githubusercontent.com/12527390/80819650-06204480-8bde-11ea-887f-258083223545.png"/></div>

The library is composed of 5 modules:

1. **Common**: the actual multi-platform module which contains all business models and logic; 

2. **Library Android**: an Android module which contains just Android native view implementations;

3. **Library iOS**: an iOS framework which contains just iOS native view implementations;

4. **Sample Android**: an Android library consumer which serves as an Android library features demo.

5. **Sample iOS**: an iOS library consumer which serves as an iOS library features demo.

<br/> 

### Build process

| Android                                                                                                                                                                                                                                                                            | iOS                                                                                                                                                                                                                                                                                                                                                                                  |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| You may use `./gradlew assemble` to generate a new android library. <br/><br/> The Android library generation is straight forward. <br/><br/> First of all, an AAR is generated from the Common module, which is then used to generate another AAR for the Android Library itself. | The iOS framework generation is a bit more complex but not much harder than the Android one. <br/><br/> First of all, you need to build a `Fat Framework(FF)`.<br/> You can do this by running `./gradlew releaseFatFramework`. <br/><br/> After this command succeeds, you'll notice the framework inside the iOS library package. <br/><br/> Now you can build the library itself. |


### Code Quality

<table>
    <tr>
        <th>Common</th>
        <th>Android</th>
        <th>iOS</th>
    </tr>
    <tr>
        <td>
           - <a href="https://github.com/pinterest/ktlint">ktlint</a><br />
           - <a href="https://github.com/arturbosch/detekt">detekt</a>
        </td>
         <td>
           - <a href="https://github.com/pinterest/ktlint">ktlint</a><br />
           - <a href="https://github.com/arturbosch/detekt">detekt</a><br />
           - <a href="https://developer.android.com/studio/write/lint">lint</a>
        </td>
        <td>
            Not ready yet.
        </td>
    </tr>
</table>

### Handy gradle tasks

1. `./gradlew clean` will remove all build directories **together with the iOS Fat Framework**
2. `./gradlew assembe` will generate `release`/`debug` `aar`s and also the iOS `Fat Framework`
3. `./gradlew releaseFatFramework` will generate the iOS `Fat Framework`
3. `./gradlew testDebugUnitTest` runes all JVM modules unit tests
4. `./gradlew detekt ktlint lint testDebugUnitTest assembleDebug` will run all quality checks and will assemble the frameworks

In case you're working on the library you might find useful this alias:
```shell script
alias checktools='./gradlew detekt ktlint lint testDebugUnitTest assembleDebug'
```
It will run all style checks, unit tests, and will assemble all artifacts. 

### Deployment

Not ready yet.
