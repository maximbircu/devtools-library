import UIKit

final class ConfigScreenCell: UITableViewCell, NibLoadable {
    static var identifier = "ConfigScreenCell"

    func addDevToolView(toolView: UIView) {
        self.contentView.addSubview(toolView)
        self.contentView.attachSubview(toolView)
    }
}
