import devtools

public final class DevToolsConfiguration {
    public init(devTools: [String : DevTool]) {
        initSource(devTools: devTools)
    }

    private func initSource(devTools: [String : DevTool]) {
        let source = DevToolsSources().memory(devTools: devTools)
        let devTools = DevToolsCompanion().create(devToolsSource: KotlinArray(size: 1) { _ in source }) { }
        print(source)
        print(devTools)
    }
}
