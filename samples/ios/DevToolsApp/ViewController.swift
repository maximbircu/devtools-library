import UIKit
import DevtoolsFramework
import devtools

class ViewController: UIViewController {

    @IBOutlet var toggleView: ToggleView!
    let tool = ToggleTool(default: false)

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func viewWillAppear(_ animated: Bool) {
        tool.key = "toggle-tool"
        tool.containerName = "Test"
        tool.title = "Toggle tool"
        tool.description = "A boolean configuration value dev tool"
        tool.canBeDisabled = true
        tool.defaultEnabledValue = false
        tool.isCritical = false
        tool.restorePersistedState()
        toggleView.bind(tool: tool)
    }

    override func viewWillDisappear(_ animated: Bool) {
        tool.persistState()
    }
}
