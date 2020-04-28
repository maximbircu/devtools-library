# Enum
A text dev tool allows you to store, update, and consume `string`, `integer`, and `floating-point` configuration values but limits the allowed configuration value options.

<table>
    <tr>
        <th>🤖 Android</th>
        <th>🍏 iOS</th>
    </tr>
    <tr>
        <td width="50%">
           <img alt="enum-tool" src="https://user-images.githubusercontent.com/12527390/80527242-b9016000-899c-11ea-8ed7-d7f17d4f10b4.png" />
        </td>
        <td width="50%">
            Not ready yet.
        </td>
    </tr>
</table>

## Parameters

| Name                |    Type   | Default | Optional | Description                                                                                                   |
|---------------------|:---------:|:-------:|:--------:|---------------------------------------------------------------------------------------------------------------|
| title               |  `string` |   `""`  |    ➕    | A tool title that will be displayed inside the configuration screen.                                          |
| description         |  `string` |   `""`  |    ➕    | A short description explaining what the configuration value is doing and what changes might bring its update. |
| canBeDisabled       | `boolean` | `false` |    ➕    | Will add a checkbox to the tool, which will allow disabling it.                                               |
| defaultEnabledValue | `boolean` |  `true` |    ➕    | The tool will be enabled by default if true and disabled vice-versa.                                          |
| isCritical          | `boolean` | `false` |    ➕    | The tool which will have it set to true will trigger critical updates.                                        |
| allowCustom         | `boolean` | `false` |    ➕    | The tool will allow the user to input custom config values if true and will dine it vice-versa.               |

#### Yaml

| Name            |   Type   | Default | Optional | Description                                                                                                                                                                                         |
|-----------------|:--------:|:-------:|:--------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| options         | `object` |   null  |    ➖    | Use this parameter to provide a set of available configuration options the user should be able to select.                                                                                           |
| defaultValueKey | `string` |  `null` |    ➖    | The key of the default configuration option that should be preselected at the first app launch.                                                                                                     |
| optionsProvider | `object` |   null  |    ➕    | It's used by the enum tool to extract the available configuration options to be selected if provided. The options provided directly inside the YML configuration file will be ignored in this case. |
#### Json

| Name    |   Type   | Default | Optional | Description                                                                                                                                                                                                                                                                                 |
|---------|:--------:|:-------:|:--------:|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| enum    |  `arry`  |   null  |    ➖    | Use this parameter to provide a set of available configuration options the user should be able to select.                                                                                                                                                                                   |
| default | `string` |  `null` |    ➖    | The default configuration value which will be preselected at the first app launch. Note that we're not providing keys in comparison with the YML API; this is because we want to make use of the JSON schema enum type. The library will use the configuration values as keys in this case. |

## Sources

### Yaml

```Yaml
yml-enum-tool: !enum {
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

### Json
```Json
"enum-tool": {
  "type": "string",
  "title": "Enum tool",
  "description": "An enum configuration value tool",
  "canBeDisabled": true,
  "defaultEnabledValue": false,
  "isCritical": true,
  "default": "Second Option Value",
  "enum": [
    "First Option Value",
    "Second Option Value",
    "Third Option Value"
  ],
  "allowCustom": true
}
```

### Memory
#### 🍏 iOS
```Swift
TBA
```
#### 🤖 Android
```Kotlin
val tool = EnumTool(
    defaultValueKey = "first-option",
    allowCustom = true,
    optionsProvider = object : EnumOptionsProvider {
        override fun getOptions() = mapOf(
            "first-option" to "First Option",
            "second-option" to "Second Option",
            "third-option" to "Third Option"
        )
    }
)

tool = title = "Enum tool"
tool = description = "An enum configuration value tool"
tool = canBeDisabled = true
tool = defaultEnabledValue = false
tool = isCritical = true
```
