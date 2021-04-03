import devtools
import Yams

final class YamlSchemaDevtoolProducer {
    static func createDevTool(yamlTag: String, config: Node.Mapping) -> DevTool? {
        let devTool = parseToolByTag(yamlTag: yamlTag, config: config)
        devTool?.title = config["title"]?.string
        devTool?.description = config["description"]?.string ?? ""
        devTool?.canBeDisabled = config["canBeDisabled"]?.bool ?? true
        devTool?.defaultEnabledValue = config["defaultEnabledValue"]?.bool ?? false
        devTool?.isCritical = config["isCritical"]?.bool ?? true

        return devTool
    }

    private static func parseToolByTag(yamlTag: String, config: Node.Mapping) -> DevTool? {
        let toolDefaultValue = config["default"]
        switch yamlTag {
        case YamlDevToolsTypes.toggle.rawValue:
            return ToggleTool(default: toolDefaultValue?.bool ?? false)
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
                defaultValueKey: toolDefaultValue?.string,
                allowCustom: config["allowCustom"]?.bool ?? false,
                optionsProvider: EnumToolOptionsProvider(options: config["options"]?.array() ?? [])
            )
        default:
            return nil
        }
    }
}

extension YamlSchemaDevtoolProducer {
    class EnumToolOptionsProvider: EnumOptionsProvider {
        let options: [ScalarConstructible]

        init(options: [ScalarConstructible]) {
            self.options = options
        }

        func getOptions() -> [String : String] {
            options.reduce(into: <#T##Result#>, <#T##updateAccumulatingResult: (inout Result, ScalarConstructible) throws -> ()##(inout Result, ScalarConstructible) throws -> ()#>)
        }
    }
}
