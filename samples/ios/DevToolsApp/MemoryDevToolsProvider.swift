import devtools
import DevtoolsFramework

struct MemoryDevToolsProvider {
    static let tools: [String: DevTool] = [
        "toggle-tool": toggleTool
    ]

    static var toggleTool: ToggleTool {
        let tool = ToggleTool(default: false)
        tool.key = "toggle-tool"
        tool.containerName = "Test"
        tool.title = "Toggle tool"
        tool.description = "A boolean configuration value dev tool"
        tool.canBeDisabled = true
        tool.defaultEnabledValue = false
        tool.isCritical = false

        return tool
    }
}
