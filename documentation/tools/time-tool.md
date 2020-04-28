# Time

A time dev tool allows you to store, update, and consume long(time in ms) configuration values.

<table>
    <tr>
        <th>🤖 Android</th>
        <th>🍏 iOS</th>
    </tr>
    <tr>
        <td width="50%">
           <img alt="time-dev-tool" src="https://user-images.githubusercontent.com/12527390/80527422-0087ec00-899d-11ea-89eb-9c5afce8f7ce.png" />
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
| days                |   `long`  |   `0`   |    ➕    | Default time configuration value in days.                                                                     |
| hours               |   `long`  |   `0`   |    ➕    | Default time configuration value in hours.                                                                    |
| minutes             |   `long`  |   `0`   |    ➕    | Default time configuration value in minutes.                                                                  |
| seconds             |   `long`  |   `0`   |    ➕    | Default time configuration value in seconds.                                                                  |
| milliseconds        |   `long`  |   `0`   |    ➕    | Default time configuration value in milliseconds.                                                             |

## Sources

### Yml
Below is an example of a time tool configuration in YML format. Note that you should use the `!time` type.

```Yml
yml-time-tool: !time {
  title: "Time tool",
  description: "A time configuration value tool",
  canBeDisabled: true,
  defaultEnabledValue: false,
  isCritical: true,
  days: 1,
  hours: 2,
  minutes: 3,
  seconds: 4,
  milliseconds: 5,
}
```

### 👷 Json
Not supported yet 😞

Consider opening a pull request to add this feature. 🙏

You can read this repository’s [contributing guidelines](../CONTRIBUTING.md) to learn how to open a good pull request.

### Memory
#### 🍏 iOS
```Swift
TBA 
```
#### 🤖 Android
```Kotlin
val tool = TimeTool(days = 1, hours = 2, minutes = 3, seconds = 4, milliseconds = 5)

tool = title = "Time tool"
tool = description = "A time configuration value tool"
tool = canBeDisabled = true
tool = defaultEnabledValue = false
tool = isCritical = true
```
