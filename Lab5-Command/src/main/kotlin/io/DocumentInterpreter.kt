package io

import document.IDocument

class DocumentInterpreter(private val document: IDocument) : IDocumentActionParser {
    private val mCommands: HashMap<String, (String) -> Boolean> = hashMapOf(
            Pair("InsertParagraph", ::insertParagraph),
            Pair("InsertImage", ::insertImage),
            Pair("SetTitle", ::setTitle),
            Pair("List", ::printElements),
            Pair("ReplaceText", ::replaceText),
            Pair("ResizeImage", ::resizeImage),
            Pair("DeleteItem", ::deleteItem),
            Pair("Undo", ::undo),
            Pair("Redo", ::redo),
            Pair("Save", ::save),
            Pair("Help", ::help),
            Pair("Exit", ::exit)
    )

    override fun apply(command: String): Boolean {
        val defaultCommand = String(command.toByteArray())
        return mCommands[command.read()]?.invoke(command)
                ?: kotlin.run {
                    println("""
                        Invalid command '$defaultCommand'.
                        Call 'Help' to see all available commands.
                    """.trimIndent())
                    return true
                }
    }

    private fun insertParagraph(properties: String): Boolean {
        return true
    }

    private fun insertImage(properties: String): Boolean {
        return true
    }

    private fun setTitle(properties: String): Boolean {
        return true
    }

    private fun printElements(s: String): Boolean {
        return true
    }

    private fun replaceText(properties: String): Boolean {
        return true
    }

    private fun resizeImage(properties: String): Boolean {
        return true
    }

    private fun deleteItem(properties: String): Boolean {
        return true
    }

    private fun undo(s: String): Boolean {
        return true
    }

    private fun redo(s: String): Boolean {
        return true
    }

    private fun save(path: String): Boolean {
        return true
    }

    private fun help(s: String): Boolean {
        return true
    }

    private fun exit(s: String): Boolean = false

    private fun String.read(): String {
        val args = split(Regex("[ ]+"), 1)
        replace(args[0], "")
        return args[0]
    }

    private val String.asPosition: Int?
        get() {
            return try {
                toInt()
            } catch (ex: Exception) {
                null
            }
        }
}
