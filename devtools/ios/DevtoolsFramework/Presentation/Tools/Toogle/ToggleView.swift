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
    }

    override open func awakeAfter(using aDecoder: NSCoder) -> Any? {
//        let superView = super.awakeAfter(using: aDecoder) as! DevToolView
//
//        superView.containerView.addSubview(ToggleView.instantiate())
//
//        return superView
        super.awakeAfter(using: aDecoder)
    }

    @IBAction func onSwitchValueChanged() {
    }

    public func setValue(value: Bool) {
        `switch`.setOn(value, animated: true)
    }

    public func onBind(tool: DevTool) {

    }
}
