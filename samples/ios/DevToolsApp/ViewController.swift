import UIKit
import DevtoolsFramework
import devtools

class ViewController: UIViewController {

    @IBOutlet var toggleView: ToggleView!

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func viewWillAppear(_ animated: Bool) {
        let tool = ToggleTool(default: false)
        tool.value = false
        toggleView.bind(tool: tool)
    }
}
