import UIKit
import devtools

public class ToggleView: DevToolView, DevToolPresentable, ToggleToolView {
    public var layoutRes: String = "ToggleView"

    @IBOutlet var `switch`: UISwitch!

    private var presenter: ToggleToolPresenter!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    public override func awakeFromNib() {
        presenter = ToggleToolPresenterCompanion().create(view: self)
        if #available(iOS 13.0, *) {
            `switch`.subviews.first?.subviews.first?.backgroundColor = UIColor.gray
        } else {
            `switch`.tintColor = UIColor.gray
        }
    }

    @IBAction func onSwitchValueChanged() {
    }

    public func setValue(value: Bool) {
        `switch`.setOn(value, animated: true)
    }

    public func onBind(tool: DevTool) {

    }
}
