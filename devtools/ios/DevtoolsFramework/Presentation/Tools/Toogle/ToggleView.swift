import UIKit
import devtools

final class ToggleView: UIView, ToggleToolView {
    @IBOutlet var `switch`: UISwitch!

    private var presenter: ToggleToolPresenter!

    override func awakeFromNib() {
        super.awakeFromNib()

        presenter = ToggleToolPresenterCompanion().create(view: self)
    }

    @IBAction func onSwitchValueChanged() {
    }

    func setValue(value: Bool) {
        `switch`.setOn(value, animated: true)
    }
}
