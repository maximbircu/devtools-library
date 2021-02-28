import UIKit

final class CheckBox: UIButton {
    var onValueChanged: ((Bool) -> Void)?

    override func awakeFromNib() {
        super.awakeFromNib()

        setupImages()
        setupAction()
    }

    private func setupImages() {
        let bundle = Bundle(for: type(of: self))
        setImage(UIImage(named: "checked_checkbox", in: bundle, compatibleWith: nil), for: .selected)
        setImage(UIImage(named: "unchecked_checkbox", in: bundle, compatibleWith: nil), for: .normal)
    }

    private func setupAction() {
        addTarget(self, action: #selector(didTap), for: .touchUpInside)
    }

    @objc private func didTap() {
        isSelected.toggle()
        onValueChanged?(isSelected)
    }
}
