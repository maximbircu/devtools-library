import UIKit
import DevtoolsFramework
import devtools

class ViewController: UIViewController {
    private var memoryDevTools: DevTools!
    private var yamlDevTools: DevTools!
    private var jsonDevTools: DevTools!

    @IBOutlet var toggleView: ToggleView!

    override func viewDidLoad() {
        super.viewDidLoad()

        let devToolsSource = DevToolsSources()

        let memorySource = devToolsSource.memory(devTools: MemoryDevToolsProvider.tools)
        let yamlSource = devToolsSource.yaml(fileName: "dev-tools.yml")

        let devTools = DevToolsCompanion()
        let memoryArraySource = KotlinArray(size: 1) { _ in memorySource }
        memoryDevTools = devTools.create(name: "MEMORY", devToolsSource: memoryArraySource) { _ in }
        yamlDevTools = devTools.create(name: "JSON", devToolsSource: KotlinArray(size: 1) { _ in yamlSource }) { _ in }
    }

    override func viewWillAppear(_ animated: Bool) {
        memoryDevTools.tools.forEach { $0.value.restorePersistedState() }
        guard let toggle = memoryDevTools.tools["toggle-tool"] else { return }
        toggleView.bind(tool: toggle)
    }

    override func viewWillDisappear(_ animated: Bool) {
        memoryDevTools.tools.forEach { $0.value.persistState() }
    }
}
