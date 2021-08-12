import UIKit

final class ConfigScreenCell: UITableViewCell, NibLoadable {
    static var identifier = "ConfigScreenCell"

    @IBOutlet var devToolView: DevToolView!

    func addDevToolView(toolView: UIView) {
        devToolView.containerView.addSubview(toolView)
        devToolView.containerView.attachSubview(toolView)
    }
}
