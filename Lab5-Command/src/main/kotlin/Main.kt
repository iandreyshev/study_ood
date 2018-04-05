import command.DocumentCommandQueue
import document.Document
import document.factory.ItemsFactory
import html.ApacheHtmlConverter
import io.DocumentInterpreter
import io.FileManager
import java.io.IOException

class Main {
    companion object {
        private const val COMMANDS_MEMORY_SIZE = 10
        private const val WORK_DIRECTORY = "root"

        @JvmStatic
        fun main(args: Array<String>) {
            val commandsQueue = DocumentCommandQueue(COMMANDS_MEMORY_SIZE)
            val fileManager = FileManager(WORK_DIRECTORY)
            val htmlConverter = ApacheHtmlConverter()
            val itemsFactory = ItemsFactory()
            val document = Document(commandsQueue, fileManager, itemsFactory, htmlConverter)
            val interpreter = DocumentInterpreter(document)

            try {
                enterLoop(interpreter)
            } catch (ex: Exception) {
                println(ex.cause)
            }

            fileManager.clear()
        }

        private fun enterLoop(interpreter: DocumentInterpreter) {
            loop@ while (true) {
                val command = readLine()
                        ?.trim()
                        ?: throw IOException("Input is null")

                try {
                    if (!interpreter.apply(command)) {
                        break@loop
                    }
                } catch (ex: Exception) {
                    println("""
                        Error while execute command '$command'.
                        Reason: ${ex.message}.
                        Use '-h' to see all available commands.
                        """.trimIndent())
                }
            }
        }
    }
}
