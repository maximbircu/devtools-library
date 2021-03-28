import devtools
import Yams

final class YamlDevToolsReader: DevToolsReader {
    private let fileName: String
    private let fileExtension: String

    init(fileName: String) {
        let fileNameURL = URL(fileURLWithPath: fileName)
        self.fileName = fileNameURL.deletingPathExtension().lastPathComponent
        self.fileExtension = fileNameURL.pathExtension
    }

    func getDevTools() -> [String: DevTool] {
        return (try? Yams.load(yaml: readYamlFile()) as? [String: DevTool]) ?? [:]
    }

    private func readYamlFile() throws -> String {
        guard let filePath = Bundle.main.path(forResource: fileName, ofType: fileExtension) else {
            throw CocoaError(.fileNoSuchFile)
        }

        return try String(contentsOfFile: filePath)
    }
}
