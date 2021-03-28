import devtools

public extension DevToolsSources {
    func yaml(fileName: String) -> DevToolsSource {
        return YamlDevToolsSource(fileName: fileName)
    }
}
