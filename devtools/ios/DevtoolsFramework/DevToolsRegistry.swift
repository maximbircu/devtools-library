import UIKit
import devtools

struct DevToolsRegistry {
    static func getDevtoolView(tool: DevTool) -> UIView {
        switch tool {
            case is ToggleTool:
                return ToggleView.instantiate()
            default:
                return DevToolView.instantiate()
        }
    }
}
