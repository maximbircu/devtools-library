import UIKit
import devtools

public final class ConfigScreenView: UIView, devtools.ConfigScreenView, NibLoadable {
    @IBOutlet private var configTableView: UITableView!

    private var devtools: [DevTool] = []

    var presenter: ConfigScreenPresenter!

    public override func awakeFromNib() {
        super.awakeFromNib()
        registerCollectionNibs()
    }

    func bind(devtools: DevTools) {
        presenter = ConfigScreenPresenterCompanion().create(view: self, devTools: devtools, devToolsList: self)
    }

    private func registerCollectionNibs() {
        configTableView.register(ConfigScreenCell.nib, forCellReuseIdentifier: ConfigScreenCell.identifier)
    }
}

extension ConfigScreenView: DevToolsListView {
    public func showDevTools(tools: [DevTool]) {
        devtools = tools
        devtools.forEach{ $0.restorePersistedState() }
        configTableView.reloadData()
    }

    public var devToolViews: [devtools.DevToolView] {
        return []
    }
}

extension ConfigScreenView: UITableViewDataSource {
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return devtools.count
    }

    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: ConfigScreenCell.identifier, for: indexPath)
        let devTool = devtools[indexPath.item]
        let devToolView = DevToolsRegistry.getDevtoolView(tool: devTool)
        devToolView.translatesAutoresizingMaskIntoConstraints = false
        (cell as? ConfigScreenCell)?.addDevToolView(toolView: devToolView)
        // TODO: Add devtool bind for seting presenters.
        //(devToolView as? DevToolPresentable)?.bind(tool: devTool)

        return cell
    }
}

extension ConfigScreenView: UITableViewDelegate {
    public func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 120
    }
}
