import devtools

public extension DevToolsSources {
    static func memory(devTools: [String: DevTool]) -> DevToolsSource {
        return DevToolsSources().memory(devTools: devTools)
    }

    static func yaml(fileName: String) -> DevToolsSource {
        return YamlDevToolsSource(fileName: fileName)
    }

    static func json(fileName: String) -> DevToolsSource {
        return DevToolsSources().json(jsonString: (try? FileReader.readFile(fileName: fileName)) ?? "")
    }
}
