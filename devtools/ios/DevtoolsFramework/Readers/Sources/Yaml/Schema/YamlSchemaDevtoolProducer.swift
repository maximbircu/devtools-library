import devtools
import Yams

final class YamlSchemaDevtoolProducer {
    static func createDevTool(yamlTag: String, config: Node.Mapping) -> DevTool<AnyObject>? {
        guard let devTool = parseToolByTag(yamlTag: yamlTag, config: config) as? DevTool<AnyObject> else { return nil }
        devTool.title = config["title"]?.string
        devTool.description_ = config["description"]?.string ?? ""
        devTool.canBeDisabled = config["canBeDisabled"]?.bool ?? true
        devTool.defaultEnabledValue = config["defaultEnabledValue"]?.bool ?? false
        devTool.isCritical = config["isCritical"]?.bool ?? true

        return devTool
    }

    private static func parseToolByTag(yamlTag: String, config: Node.Mapping) -> Any? {
        let toolDefaultValue = config["default"]
        switch yamlTag {
        case YamlDevToolsTypes.toggle.rawValue:
            return ToggleTool(default: toolDefaultValue?.bool ?? false) as DevTool<KotlinBoolean>
        case YamlDevToolsTypes.text.rawValue:
            return TextTool(default: toolDefaultValue?.string ?? "", hint: config["hint"]?.string)
        case YamlDevToolsTypes.time.rawValue:
            return TimeTool(
                days: Int64(config["days"]?.int ?? 0),
                hours: Int64(config["hours"]?.int ?? 0),
                minutes: Int64(config["minutes"]?.int ?? 0),
                seconds: Int64(config["seconds"]?.int ?? 0),
                milliseconds: Int64(config["milliseconds"]?.int ?? 0)
            )
        case YamlDevToolsTypes.enum.rawValue:
            return EnumTool(
                defaultValueKey: config["defaultValueKey"]?.string,
                allowCustom: config["allowCustom"]?.bool ?? false,
                optionsProvider: EnumToolOptionsProvider(options: config["options"])
            )
        case YamlDevToolsTypes.group.rawValue:
            return GroupTool(tools: parseGroup(tools: config["tools"]))
        default:
            return nil
        }
    }
}

extension YamlSchemaDevtoolProducer {
    class EnumToolOptionsProvider: EnumOptionsProvider {
        let options: Node?

        init(options: Node?) {
            self.options = options
        }

        func getOptions() -> [String: String] {
            return options?.mapping?.reduce(into: [:]) { result, item in
                guard let option = item.key.string,
                      let optionValue = item.value.string else { return }
                result?[option] = optionValue
            } ?? [:]
        }
    }
}

extension YamlSchemaDevtoolProducer {
    private static func parseGroup(tools: Node?) -> [String: DevTool<AnyObject>] {
        return tools?.mapping?.reduce(into: [String: DevTool]()) { result, node in
            guard let toolName = node.key.string,
                  let config = node.value.mapping else { return }
            result[toolName] = parseToolByTag(
                yamlTag: node.value.tag.description,
                config: config
            ) as? DevTool<AnyObject>
        } ?? [:]
    }
}
