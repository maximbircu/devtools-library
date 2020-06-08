import UIKit

final class CheckBox: UIButton {
    var onValueChanged: ((Bool) -> Void)?

    override func awakeFromNib() {
        super.awakeFromNib()

        setupImages()
        setupAction()
    }

    private func setupImages() {
        setImage(UIImage(named: "checked_checkbox"), for: .selected)
        setImage(UIImage(named: "unchecked_checkbox"), for: .normal)
    }

    private func setupAction() {
        addTarget(self, action: #selector(didTap), for: .touchUpInside)
    }

    @objc private func didTap() {
        isSelected.toggle()
        onValueChanged?(isSelected)
    }
}
