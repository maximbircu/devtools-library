import UIKit
import devtools

public class DevToolView: UIView, devtools.DevToolView, NibLoadable {
    @IBOutlet private var optionName: UILabel!
    @IBOutlet private var checkBox: CheckBox!
    @IBOutlet private(set) var containerView: UIView!


    private var presenter: DevToolPresenter!
    internal var containerTool: DevToolPresentable!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)
        presenter = DevToolPresenterCompanion().create(view: self)
    }

    override open func awakeAfter(using aDecoder: NSCoder) -> Any? {
        guard let devToolElementView = super.awakeAfter(using: aDecoder) as? DevToolPresentable,
              !devToolElementView.translatesAutoresizingMaskIntoConstraints,
              let devToolView = DevToolView.instantiate() as? DevToolView else { return self }

        transferProperties(to: devToolView)

        devToolView.containerTool = devToolElementView
        let toolContainer: UIView = devToolView.containerView
        toolContainer.transferProperties(to: devToolElementView)
        toolContainer.addSubview(devToolElementView)

        devToolElementView.topAnchor.constraint(equalTo: toolContainer.topAnchor, constant: 0).isActive = true
        devToolElementView.bottomAnchor.constraint(equalTo: toolContainer.bottomAnchor, constant: 0).isActive = true
        devToolElementView.leadingAnchor.constraint(equalTo: toolContainer.leadingAnchor, constant: 0).isActive = true
        devToolElementView.trailingAnchor.constraint(equalTo: toolContainer.trailingAnchor, constant: 0).isActive = true

        return devToolView
    }

    public func bind(tool: DevTool) {
        presenter.onToolBind(tool: tool)
        containerTool.onBind(tool: tool)
    }

    public func hideEnableToggle() {
        checkBox.isHidden = true
    }

    public func refreshToolData(tool: DevTool) {
        bind(tool: tool)
    }

    public func setTitle(title: String?) {
        optionName.text = title
    }

    public func setToolEnableState(isEnabled: Bool) {

    }

    public func showEnableToggle() {
        checkBox.isHidden = false
    }

    public func showHelpDialog(tool: DevTool) {

    }

    public func showToolContextMenu() {

    }
}
