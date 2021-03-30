import devtools

public extension DevToolsSources {
    func yaml(fileName: String) -> DevToolsSource {
        return YamlDevToolsSource(fileName: fileName)
    }

    func json(fileName: String) -> DevToolsSource {
        return json(jsonString: (try? FileReader.readFile(fileName: fileName)) ?? "")
    }
}
