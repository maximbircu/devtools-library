final class FileReader {
    static func readFile(fileName: String) throws -> String {
        let fileNameURL = URL(fileURLWithPath: fileName)
        let fileName = fileNameURL.deletingPathExtension().lastPathComponent
        let fileExtension = fileNameURL.pathExtension
        guard let filePath = Bundle.main.path(forResource: fileName, ofType: fileExtension) else {
            throw CocoaError(.fileNoSuchFile)
        }

        return try String(contentsOfFile: filePath)
    }
}
