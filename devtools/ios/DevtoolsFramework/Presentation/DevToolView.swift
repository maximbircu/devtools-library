import UIKit

public class DevToolView: UIView, NibLoadable {
    @IBOutlet private var optionName: UILabel!
    @IBOutlet private var checkBox: CheckBox!
    @IBOutlet var containerView: UIView!

    public var containerTool: DevToolPresentable!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)

        addSubview(DevToolView.instantiate(withOwner: self))
        containerView.addSubview(Self.instantiate(withOwner: self))
    }

    public override func awakeFromNib() {
        print("DevToolView awake from nib")
    }
}
