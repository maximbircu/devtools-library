# Group
A dev tool allows you to group together a set of dev tools that are relevant and share the same context.

<table>
    <tr>
        <th>🤖 Android</th>
        <th>🍏 iOS</th>
    </tr>
    <tr>
        <td width="50%">
           <img  alt="group-tool" src="https://user-images.githubusercontent.com/12527390/80527585-42189700-899d-11ea-96cc-d993f31608cf.png" />
        </td>
        <td width="50%">
            Not ready yet.
        </td>
    </tr>
</table>

## Parameters

| Name  |   Type   | Default | Optional | Description                                                           |
|-------|:--------:|:-------:|:--------:|-----------------------------------------------------------------------|
| title | `string` |   `""`  |    ➕    | A tool title that will be displayed inside the configuration screen.  |

#### Yaml

| Name  |   Type   | Default | Optional | Description                                            |
|-------|:--------:|:-------:|:--------:|--------------------------------------------------------|
| tools | `object` |   null  |    ➖    | A list of dev tool objects you want to group together. |

#### Json

| Name       |   Type   | Default | Optional | Description                                            |
|------------|:--------:|:-------:|:--------:|--------------------------------------------------------|
| properties | `object` |   null  |    ➖    | A list of dev tool objects you want to group together. |

## Sources

### Yaml
Below is presented an example of a group tool configuration in YML format. Note that you should use the `!group` type.
```Yaml
tools-group: !group {
  title: "Tools group",
  tools: {
    toggle-tool: !toggle {
      title: "Toggle tool",
      default: true
    },
    text-tool: !text {
      title: "Text tool (String)",
      default: "Here can go any text value"
    },
    time-tool: !time {
      title: "Time tool",
      days: 1,
      hours: 2
    },
    enum-tool: !enum {
      title: "Enum tool",
      defaultValueKey: first-option,
      options: {
        first-option: "First Option Value",
        second-option: "Second Option Value",
        third-option: "Third Option Value",
      }
    }
  }
}
```
### Json
Below is an example of a group tool configuration in JSON Schema format. Note that you should add the `"type": "object"` to the configuration object to make it map to a group tool.
```Json
"tools-group": {
  "type": "object",
  "title": "Tools group",
  "properties": {
    "toggle-tool": {
      "type": "boolean",
      "title": "Toggle tool",
      "default": true
    },
    "text-tool": {
      "type": "string",
      "title": "Text tool (String)",
      "default": " String config value"
    },
    "enum-tool": {
      "type": "string",
      "title": "Enum tool",
      "default": "Second Option Value",
      "enum": [
        "First Option Value",
        "Second Option Value",
        "Third Option Value"
      ]
    }
  }
}
```
### Memory
#### 🍏 iOS
```Swift
TBA 
```
#### 🤖 Android
```Kotlin
val tool = GroupTool(
    tools = mapOf(
        "toggle-tool" to ToggleTool(default = false).apply { title = "Toggle tool" },
        "text-tool" to TextTool(
            default = "Here can go any text value"
        ).apply { title = "Text tool (String)" },
        "time-tool" to TimeTool(
            days = 1,
            hours = 2,
            minutes = 3,
            seconds = 4,
            milliseconds = 5
        ).apply { title = "Time tool" },
        "enum-tool" to EnumTool(
            defaultValueKey = "first-option",
            optionsProvider = object : EnumOptionsProvider {
                override fun getOptions() = mapOf(
                    "first-option" to "First Option",
                    "second-option" to "Second Option",
                    "third-option" to "Third Option"
                )
            }
        ).apply { title = "Enum tool" }
    )
).apply { title = "Tools group" }
```
