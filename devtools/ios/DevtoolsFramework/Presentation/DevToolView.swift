import UIKit

public class DevToolView: UIView, NibLoadable {
    public static var nibName: String = "DevToolView"

    @IBOutlet private var optionName: UILabel!
    @IBOutlet private var checkBox: CheckBox!
    @IBOutlet var containerView: UIView!

    public required init?(coder: NSCoder) {
        super.init(coder: coder)

        addSubview(loadViewFromNib()!)
    }

    public override func awakeFromNib() {
        print("DevToolView awake from nib")
    }

    func loadViewFromNib() -> UIView? {
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: DevToolView.nibName, bundle: bundle)
        return nib.instantiate(withOwner: self, options: nil).first as? UIView
    }
}
