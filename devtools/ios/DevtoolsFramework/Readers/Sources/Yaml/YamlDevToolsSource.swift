import devtools

final class YamlDevToolsSource: DevToolsSource {
    private let fileName: String

    init(fileName: String) {
        self.fileName = fileName
    }

    func getReader() -> DevToolsReader {
        return YamlDevToolsReader(fileName: fileName)
    }
}
