import UIKit
import devtools

public class ToggleView: DevToolView, DevToolPresentable, ToggleToolView {
    public var layoutRes: String = "ToggleView"

    @IBOutlet var `switch`: UISwitch!

    private var presenter: ToggleToolPresenter!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)
        presenter = ToggleToolPresenterCompanion().create(view: self)
    }

    public override func awakeFromNib() {
        if #available(iOS 13.0, *) {
            `switch`.subviews.first?.subviews.first?.backgroundColor = UIColor.gray
        } else {
            `switch`.tintColor = UIColor.gray
        }
    }

    @IBAction func onSwitchValueChanged() {
        presenter.onCheckedChangeListener(isChecked: `switch`.isOn)
    }

    public func setValue(value: Bool) {
        `switch`.setOn(value, animated: true)
    }

    public func onBind(tool: DevTool) {
        guard let tool = tool as? ToggleTool else { return }
        presenter.onToolBind(tool___: tool)
    }
}
