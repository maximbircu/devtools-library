import UIKit
import devtools

public class ToggleView: DevToolView, DevToolPresentable, ToggleToolView {
    public var layoutRes: String = "ToggleView"

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

    public func onBind(tool: DevTool) {

    }
}
