# Toggle
A toggle dev tool allows you to store, update, and consume boolean configuration values.

<table>
    <tr>
        <th>🤖 Android</th>
        <th>🍏 iOS</th>
    </tr>
    <tr>
        <td width="50%">
            <img alt="toggle-tool" src="https://user-images.githubusercontent.com/12527390/80479540-d19b5700-8957-11ea-8f06-293b75a6db6c.png" />
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
| default             | `boolean` | `false` |    ➕    | The default boolean configuration value.                                                                      |

## Sources

### Yaml
Below is an example of a toggle tool configuration in YML format. Note that you should use the `!toggle` type.
```Yaml
toggle-tool: !toggle {
  title: "Toggle tool",
  description: "A boolean configuration value tool",
  canBeDisabled: true,
  defaultEnabledValue: false,
  isCritical: true,
  default: true
}
```

### Json
Below is an example of a toggle tool configuration in JSON Schema format. Note that you should add the `"type": "boolean"` to the configuration object to make it map to a toggle tool.
```Json
"toggle-tool": {
  "type": "boolean",
  "title": "Toggle tool",
  "description": "A boolean configuration value tool",
  "canBeDisabled": true,
  "defaultEnabledValue": false,
  "isCritical": true,
  "default": true
}
```

### Memory
#### 🍏 iOS
```Swift
TBA 
```
#### 🤖 Android
```Kotlin
val tool = ToggleTool(default = false)

tool = title = "Toggle tool"
tool = description = "A boolean configuration value dev tool"
tool = canBeDisabled = true
tool = defaultEnabledValue = false
tool = isCritical = true
```
