import UIKit
import devtools

public class ToggleView: UIView, ToggleToolView, NibLoadable {
    public static var nibName: String = "ToggleView"

    @IBOutlet var `switch`: UISwitch!

    private var presenter: ToggleToolPresenter!

    public override func awakeFromNib() {
        super.awakeFromNib()

        presenter = ToggleToolPresenterCompanion().create(view: self)
    }

    @IBAction func onSwitchValueChanged() {
    }

    public func setValue(value: Bool) {
        `switch`.setOn(value, animated: true)
    }
}
