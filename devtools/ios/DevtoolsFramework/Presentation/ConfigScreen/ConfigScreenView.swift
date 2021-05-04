import UIKit
import devtools

final class ConfigScreenView: UIView, devtools.ConfigScreenView, NibLoadable {
    
    @IBOutlet private var configTableView: UITableView!

    var presenter: ConfigScreenPresenter!

    func bind(devtools: DevTools) {
        presenter = ConfigScreenPresenterCompanion().create(view: self, devTools: devtools, devToolsList: self)
    }
}

extension ConfigScreenView: DevToolsListView {
    func showDevTools(tools: [DevTool]) {

    }

    var devToolViews: [devtools.DevToolView] {
        return []
    }
}

extension ConfigScreenView: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        return UITableViewCell()
    }
}
