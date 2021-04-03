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
        let node = try? Yams.compose(yaml: FileReader.readFile(fileName: fileName))
        return parseNode(node: node!)
    }

    private func parseNode(node: Node) -> [String: DevTool] {
        return node.mapping?.reduce(into: [String: DevTool]()) { result, node in
            guard let devToolName = node.key.string,
                  let devTool = parseToolByTag(node: node.value) else { return }
            devTool.title = node.value.mapping!["title"]?.string ?? ""
            devTool.description = node.value.mapping!["description"]?.string ?? ""
            devTool.canBeDisabled = node.value.mapping!["canBeDisabled"]?.bool ?? false
            devTool.defaultEnabledValue = node.value.mapping!["defaultEnabledValue"]?.bool ?? false
            devTool.isCritical = node.value.mapping!["isCritical"]?.bool ?? false
            result[devToolName] = devTool
        } ?? [:]
    }

    private func parseToolByTag(node: Node) -> DevTool? {
        switch node.tag.description {
        case YamlDevToolsTypes.toggle.rawValue:
            return ToggleTool(default: node.mapping!["default"]?.bool ?? false)
        default:
            return nil
        }
    }
}
