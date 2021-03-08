import Foundation

public protocol Identifiable {
    static var identifier: String { get }
}

public extension Identifiable {
    static var identifier: String {
        return String(describing: self)
    }
}
