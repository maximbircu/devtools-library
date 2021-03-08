import devtools

public protocol DevToolPresentable: DevToolView {
    func onBind(tool: DevTool)
}
