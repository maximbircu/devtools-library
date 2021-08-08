import UIKit
import DevtoolsFramework
import devtools

class ViewController: UIViewController {
    private var memoryDevTools: DevTools!
    private var yamlDevTools: DevTools!
    private var jsonDevTools: DevTools!

    @IBOutlet var configScreenView: DevtoolsFramework.ConfigScreenView!

    override func viewDidLoad() {
        super.viewDidLoad()

        let memorySource = DevToolsSources.memory(devTools: MemoryDevToolsProvider.tools)
        let yamlSource = DevToolsSources.yaml(fileName: "dev-tools.yml")
        let jsonSource = DevToolsSources.json(fileName: "dev-tools.json")

        memoryDevTools = Devtools.create(name: "MEMORY", devToolsSource: memorySource)
        yamlDevTools = Devtools.create(name: "YAML", devToolsSource: yamlSource)
        jsonDevTools = Devtools.create(name: "JSON", devToolsSource: jsonSource)
    }

    override func viewWillAppear(_ animated: Bool) {
        memoryDevTools.tools.forEach { $0.value.restorePersistedState() }
        configScreenView.showDevTools(tools: memoryDevTools.tools.map { $0.value })
    }

    override func viewWillDisappear(_ animated: Bool) {
        memoryDevTools.tools.forEach { $0.value.persistState() }
    }
}
