import devtools

public final class DevToolsConfiguration {
    public init(devTools: [String: DevTool<AnyObject>]) {
        initSource(devTools: devTools)
    }

    private func initSource(devTools: [String: DevTool<AnyObject>]) { }
}
