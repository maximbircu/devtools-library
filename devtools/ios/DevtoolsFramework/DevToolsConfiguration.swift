import devtools

public final class DevToolsConfiguration {
    public init(devTools: [String: DevTool]) {
        initSource(devTools: devTools)
    }

    private func initSource(devTools: [String: DevTool]) {
        let source = DevToolsSources().memory(devTools: devTools)
        _ = DevToolsCompanion().create(name: "MEMORY", devToolsSource: KotlinArray(size: 1) { _ in source }) { _ in }
    }
}
