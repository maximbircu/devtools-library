import devtools
import Yams

final class YamlDevToolsReader: DevToolsReader {
    private let fileName: String

    init(fileName: String) {
        self.fileName = fileName
    }

    func getDevTools() -> [String: DevTool] {
        let tools = getTools()
        return tools
    }

    private func getTools() -> [String: DevTool] {
        let yamlDevTools = (try? Yams.load(yaml: FileReader.readFile(fileName: fileName)) as? [String: [String: Any]]) ?? [:]
        return yamlDevTools.mapValues {
            YamlDevTool(
                title: $0["title"] as? String,
                description: $0["description"] as? String ?? "",
                canBeDisabled: $0["canBeDisabled"] as? Bool ?? false,
                defaultEnabledValue: $0["defaultEnabledValue"] as? Bool ?? false,
                isCritical: $0["isCritical"] as? Bool ?? false
            )
        }
    }
}

private final class YamlDevTool: DevTool { }
