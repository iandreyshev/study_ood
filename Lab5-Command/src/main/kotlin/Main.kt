import command.DocumentCommandQueue
import document.Document
import io.DocumentInterpreter
import io.FileManager
import java.io.IOException

class Main {
    companion object {
        private const val COMMANDS_MEMORY_SIZE = 10
        private const val WORK_DIRECTORY = "root"

        private val mCommandsQueue = DocumentCommandQueue(COMMANDS_MEMORY_SIZE)
        private val mFileManager = FileManager(WORK_DIRECTORY)
        private val mDocument = Document(mCommandsQueue, mFileManager)
        private val mInterpreter = DocumentInterpreter(mDocument)

        @JvmStatic
        fun main(args: Array<String>) {
            try {
                enterLoop()
            } catch (ex: Exception) {
                println(ex.cause)
            }

            mFileManager.clear()
        }

        private fun enterLoop() {
            loop@ while (true) {
                val command = readLine()
                        ?.trim()
                        ?: throw IOException("Input is null")

                if (!mInterpreter.apply(command)) {
                    break@loop
                }
            }
        }
    }
}
