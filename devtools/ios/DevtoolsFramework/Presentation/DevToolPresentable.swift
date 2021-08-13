import devtools

public protocol DevToolPresentable: DevToolView {
    var devToolView: DevToolPresentable? { get }
    func onBind(tool: DevTool)
}

public extension DevToolPresentable where Self: UIView & NibLoadable {
    var devToolView: DevToolPresentable? {
        return Self.instantiate() as? DevToolPresentable
    }
}
