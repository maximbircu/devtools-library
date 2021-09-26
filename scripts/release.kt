package com.maximbircu.friday

import java.io.File
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val gradlePropertiesFileParser = GradlePropertiesFileParser()
private val changelogFileParser = ChangelogFileParser()

private val commands = listOf(
    UpdateCommonVersion(),
    BumpVersionName(),
    UpdateChangelog(),
    GenerateChangelogFile()
)

fun main(args: Array<String>) {
    val command = commands.firstOrNull { it.name == args.firstOrNull() }
    if (command == null) {
        commands.forEach { println("${it.name} - ${it.description}") }
    } else {
        command.execute()
    }
}

/*************************************************************************************
 *** Commands.
 *************************************************************************************/
class GenerateChangelogFile : Command(
    name = "--generate-changelog",
    description = "Generates a changelog.txt file"
) {
    override fun execute() {
        changelogFileParser.createChangelogFile(
            version = gradlePropertiesFileParser.version,
            fileName = "changelog.txt"
        )
    }
}

class BumpVersionName : Command(
    name = "--bump-version",
    description = "Bumps version name"
) {
    override fun execute() = gradlePropertiesFileParser.increment()
}

class UpdateChangelog : Command(
    name = "--update-changelog",
    description = "Updates changelog file for the new version"
) {
    override fun execute() {
        changelogFileParser.setReleaseDate()
        changelogFileParser.addNewVersion(gradlePropertiesFileParser.version)
    }
}

/*************************************************************************************
 *** Files parsers.
 *************************************************************************************/
class GradlePropertiesFileParser {
    private val file = File("./gradle.properties")

    private val String.isVersion: Boolean get() = contains("PROJECT_VERSION_NAME")

    val version get() = file.readLines().first { it.isVersion }.substringAfter("=")
    val nextVersion get() = version.increment()

    fun increment() {
        file.transform { line ->
            if (line.isVersion) {
                "PROJECT_VERSION_NAME=${version.increment()}"
            } else {
                line
            }
        }
    }

    private fun String.increment(): String {
        val delimiter = "."
        val split = this.split(delimiter)
        val oldPatch = split.last().toInt()
        val newPatch = oldPatch + 1
        return (split.dropLast(1) + newPatch).joinToString(separator = delimiter)
    }
}

class ChangelogFileParser {
    private val file = File("CHANGELOG.md")

    fun setReleaseDate() {
        file.transform { line ->
            if (line.contains("(In development)")) {
                line.replace("In development", today())
            } else {
                line
            }
        }
    }

    fun addNewVersion(version: String) {
        file.replaceLines { lines ->
            lines.toMutableList().apply {
                add(2, "")
                add(2, "## Version $version *(In development)*")
                add(2, "")
            }
        }
    }

    fun createChangelogFile(version: String, fileName: String) {
        val changelogFile = File(fileName)
        changelogFile.writeText("Release v1.0.1\n${getChangeLog(version)}".trim())
    }

    private fun getChangeLog(version: String): String = file.readLines().run {
        val linesWithoutHeader = drop(indexOfFirst { it.contains("Version $version") } + 1)
        val changelogEnd = linesWithoutHeader.indexOfFirst { it.contains("## Version") }
        return linesWithoutHeader.subList(0, changelogEnd).joinToString("\n").trimEnd()
    }
}

/*************************************************************************************
 *** Utils.
 *************************************************************************************/
fun File.transform(transform: (String) -> String) {
    val newLines = readLines().map(transform)
    printWriter().use { writer -> newLines.forEach(writer::println) }
}

@Suppress("TooGenericExceptionThrown")
fun File.replaceLines(modifiedLines: (List<String>) -> List<String>) {
    val newLines = modifiedLines(readLines())
    printWriter().use { writer -> newLines.forEach(writer::println) }
}

fun today(): String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

@Suppress("TooGenericExceptionThrown")
fun String.execute(): String {
    val process = Runtime.getRuntime().exec(this)
    val output = InputStreamReader(process.inputStream).readText()
    val error = InputStreamReader(process.errorStream).readText()
    if (error.isNotBlank()) throw Exception(error)
    return output.trim()
}

@Suppress("TooGenericExceptionThrown")
fun Array<String>.execute(): String {
    val process = Runtime.getRuntime().exec(this)
    val output = InputStreamReader(process.inputStream).readText()
    val error = InputStreamReader(process.errorStream).readText()
    if (error.isNotBlank()) throw Exception(error)
    return output.trim()
}

abstract class Command(val name: String, val description: String) {
    abstract fun execute()
}
