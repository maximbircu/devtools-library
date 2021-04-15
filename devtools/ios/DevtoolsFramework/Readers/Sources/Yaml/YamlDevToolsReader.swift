import devtools
import Yams

final class YamlDevToolsReader: DevToolsReader {
    private let fileName: String

    init(fileName: String) {
        self.fileName = fileName
    }

    func getDevTools() -> [String: DevTool] {
        return getTools()
    }

    private func getTools() -> [String: DevTool] {
        guard let node = try? Yams.compose(yaml: FileReader.readFile(fileName: fileName)) else { return [:] }
        return parseNode(node: node)
    }

    private func parseNode(node: Node) -> [String: DevTool] {
        return node.mapping?.reduce(into: [String: DevTool]()) { result, node in
            guard let devToolName = node.key.string,
                  let devTool = decodeDevTool(node: node.value) else { return }
            result[devToolName] = devTool
        } ?? [:]
    }

    private func decodeDevTool(node: Node) -> DevTool? {
        guard let devToolConfig = node.mapping else { return nil }
        return YamlSchemaDevtoolProducer.createDevTool(yamlTag: node.tag.description, config: devToolConfig)
    }
}
