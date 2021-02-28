import UIKit

public class DevToolView: UIView, NibLoadable {
    @IBOutlet private var optionName: UILabel!
    @IBOutlet private var checkBox: CheckBox!
    @IBOutlet private(set) var containerView: UIView!

    public var containerTool: DevToolPresentable!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    public override func awakeFromNib() {
        super.awakeFromNib()

        print("DevToolView awake from nib")
    }

    override open func awakeAfter(using aDecoder: NSCoder) -> Any? {
        guard let devToolElementView = super.awakeAfter(using: aDecoder) as? UIView,
              !devToolElementView.translatesAutoresizingMaskIntoConstraints,
              let devToolView = DevToolView.instantiate() as? DevToolView else { return self }

        transferProperties(to: devToolView)

        let toolContainer: UIView = devToolView.containerView
        toolContainer.transferProperties(to: devToolElementView)
        toolContainer.addSubview(devToolElementView)

        devToolElementView.topAnchor.constraint(equalTo: toolContainer.topAnchor, constant: 0).isActive = true
        devToolElementView.bottomAnchor.constraint(equalTo: toolContainer.bottomAnchor, constant: 0).isActive = true
        devToolElementView.leadingAnchor.constraint(equalTo: toolContainer.leadingAnchor, constant: 0).isActive = true
        devToolElementView.trailingAnchor.constraint(equalTo: toolContainer.trailingAnchor, constant: 0).isActive = true

        return devToolView
    }
}
