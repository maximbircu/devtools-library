# Text
A text dev tool allows you to store, update, and consume `string`, `integer`, and `floating-point` configuration values.

<table>
    <tr>
        <th>🤖 Android</th>
        <th>🍏 iOS</th>
    </tr>
    <tr>
        <td width="50%">
            <img width="300" alt="text-tool-string" align="left" src="https://user-images.githubusercontent.com/12527390/80484673-c862b800-8960-11ea-81fb-43159c004050.png" />
            <img width="300" alt="text-tool-integer" align="left"  src="https://user-images.githubusercontent.com/12527390/80484702-d7496a80-8960-11ea-8025-de3abacbadcf.png" />
            <img width="300" alt="text-tool-floating-point" src="https://user-images.githubusercontent.com/12527390/80484758-eb8d6780-8960-11ea-8198-2945091d1609.png" />
        </td>
        <td width="50%">
            Not ready yet.
        </td>
    </tr>
</table>

## Parameters

| Name                |            Type            | Default | Optional | Description                                                                                                           |
|---------------------|:--------------------------:|:-------:|:--------:|-----------------------------------------------------------------------------------------------------------------------|
| title               |          `string`          |   `""`  |    ➕    | A tool title that will be displayed inside the configuration screen.                                                  |
| description         |          `string`          |   `""`  |    ➕    | A short description explaining what the configuration value is doing and what changes might bring its update.         |
| canBeDisabled       |          `boolean`         | `false` |    ➕    | Will add a checkbox to the tool, which will allow disabling it.                                                       |
| defaultEnabledValue |          `boolean`         |  `true` |    ➕    | The tool will be enabled by default if true and disabled vice-versa.                                                  |
| isCritical          |          `boolean`         | `false` |    ➕    | The tool which will have it set to true will trigger critical updates.                                                |
| hint                |          `string`          |   `""`  |    ➕    | A short configuration value description. It will be shown as a hint inside the input field whenever it will be empty. |
| default             | `string` `integer` `float` |   n/a   |    ➖    | The default configuration value, any text or number.                                                                  |                                                            |

## Sources

### Yaml
Below is an example of a text tool configuration in YML format. Note that you should use the `!text` type.

```Yaml
text-tool: !text {
  title: "Text tool (String)",
  description: "A text configuration value tool",
  canBeDisabled: true,
  defaultEnabledValue: false,
  isCritical: false,
  default: "Here can go any text value",
  hint: "String config value"
}
```

### Json
Below is an example of a text tool configuration in JSON Schema format. Note that you should add the `"type": "string"` to the configuration object to make it map to a text tool.
```Json
"text-tool": {
  "type": "string",
  "title": "Text tool (String)",
  "description": "A text configuration value tool",
  "canBeDisabled": true,
  "defaultEnabledValue": false,
  "isCritical": false,
  "default": "String config value",
  "hint": "String config value"
}
```

### Memory
#### 🍏 iOS
```Swift
TBA 
```
#### 🤖 Android
```Kotlin
val tool = TextTool(default = "Here can go any text value", hint = "String config value")

tool.title = "Text tool (String)"
tool.description = "A text configuration value tool"
tool.canBeDisabled = true
tool.defaultEnabledValue = false
tool.isCritical = false
```
