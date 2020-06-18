import UIKit

public class ToggleToolLayout: DevtoolsFramework.DevToolView {

    public required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    public override func awakeFromNib() {
        super.awakeFromNib()

        containerView.addSubview(ToggleView.instantiate())
    }
}

//extension UIView {
//    /// Used to intercept placeholder view's decoded from storyboards
//    /// and replace them with real ones to flatten the view hieararchy
//    override open func awakeAfter(using aDecoder: NSCoder) -> Any? {
//        guard !translatesAutoresizingMaskIntoConstraints,
//            let nibType = type(of: self) as? (UIView & NibLoadable).Type else { return self }
//
//        let replacement = nibType.instantiate()
////        guard type(of: replacement) == nibType else { return self }
//
//        transferProperties(to: replacement)
//        let replacementConstraints = reparentedConstraints(to: replacement)
//        replacement.addConstraints(replacementConstraints)
//
//        return replacement
//    }
//
//    private func reparentedConstraints(to target: UIView) -> [NSLayoutConstraint] {
//        return constraints.map { original in
//            let first = self == original.firstItem as? UIView ? target : original.firstItem
//            let second = self == original.secondItem as? UIView ? target : original.secondItem
//            let constraint = NSLayoutConstraint(
//                item: first!,
//                attribute: original.firstAttribute,
//                relatedBy: original.relation,
//                toItem: second,
//                attribute: original.secondAttribute,
//                multiplier: original.multiplier,
//                constant: original.constant)
//            constraint.priority = original.priority
//            constraint.shouldBeArchived = original.shouldBeArchived
//            constraint.identifier = original.identifier
//            constraint.isActive = original.isActive
//
//            return constraint
//        }
//    }
//
//    private func transferProperties(to target: UIView) {
//        target.translatesAutoresizingMaskIntoConstraints = translatesAutoresizingMaskIntoConstraints
//        target.autoresizingMask = autoresizingMask
//        target.isHidden = isHidden
//        target.tag = tag
//        target.isUserInteractionEnabled = isUserInteractionEnabled
//        target.frame = frame
//        target.bounds = bounds
//        target.clipsToBounds = clipsToBounds
//    }
//}
