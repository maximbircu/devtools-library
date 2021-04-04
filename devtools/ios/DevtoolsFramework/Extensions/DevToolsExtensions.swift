import devtools

public struct Devtools {
    public static func create(name: String,
                       devToolsSource: DevToolsSource...,
                       onConfigUpdate: @escaping (Bool) -> Void = { _ in }) -> DevTools {
        let sources = KotlinArray(size: Int32(devToolsSource.count)) { index in
            return devToolsSource[Int(truncating: index)]
        }

        return DevToolsCompanion().create(name: name, devToolsSource: sources) { isCriticalUpdate in
            onConfigUpdate(Bool(truncating: isCriticalUpdate))
        }
    }
}
