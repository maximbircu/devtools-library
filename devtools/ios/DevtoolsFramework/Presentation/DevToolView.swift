import UIKit

public class DevToolView: UIView, NibLoadable {
    @IBOutlet private var optionName: UILabel!
    @IBOutlet private var checkBox: CheckBox!
    @IBOutlet var containerView: UIView!

    public var containerTool: DevToolPresentable!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)

//        addSubview(DevToolView.instantiate(withOwner: self))
//        containerView.addSubview(Self.instantiate(withOwner: self))
    }

    public override func awakeFromNib() {
        super.awakeFromNib()
        
        print("DevToolView awake from nib")
    }

    override open func awakeAfter(using aDecoder: NSCoder) -> Any? {
        let toggleView = super.awakeAfter(using: aDecoder) as! UIView
        guard !toggleView.translatesAutoresizingMaskIntoConstraints else { return self }
        let devToolView = DevToolView.instantiate() as! DevToolView
        transferProperties(to: devToolView)
        let replacementConstraints = reparentedConstraints(to: devToolView)
        devToolView.addConstraints(replacementConstraints)

        devToolView.containerView.transferProperties(to: toggleView)
//        replacementConstraints = devToolView.containerView.reparentedConstraints(to: toggleView)
//        toggleView.addConstraints(replacementConstraints)
        devToolView.containerView.addSubview(toggleView)

        toggleView.topAnchor.constraint(equalTo: devToolView.topAnchor, constant: 0).isActive = true
        toggleView.bottomAnchor.constraint(equalTo: devToolView.bottomAnchor, constant: 0).isActive = true
        toggleView.leadingAnchor.constraint(equalTo: devToolView.leadingAnchor, constant: 0).isActive = true
        toggleView.trailingAnchor.constraint(equalTo: devToolView.trailingAnchor, constant: 0).isActive = true

        return devToolView
    }
}
