package io

import document.IDocument

class DocumentInterpreter(private val document: IDocument) : DocumentActionParser() {
    private val mCommands: HashMap<String, Command> = hashMapOf(
            Pair("-ip", Command(::insertParagraph, 2)),
            Pair("-ii", Command(::insertImage, 4)),
            Pair("-st", Command(::setTitle, 1)),
            Pair("-rt", Command(::replaceText, 2)),
            Pair("-ri", Command(::resizeImage, 3)),
            Pair("-di", Command(::deleteItem, 1)),
            Pair("-s", Command(::save, 1)),
            Pair("-l", Command(::printElements)),
            Pair("-u", Command(::undo)),
            Pair("-r", Command(::redo)),
            Pair("-h", Command(::help)),
            Pair("-e", Command(::exit))
    )
    private var mIsExit = false

    override fun getCommand(command: String): Command? = mCommands[command]

    override fun isExit(): Boolean = mIsExit

    private fun setTitle() {
        document.title = stringAt(0)
    }

    private fun insertParagraph() {
        val position = when (stringAt(0)) {
            "end" -> document.itemsCount
            else -> intAt(0)
        }
        document.insertParagraph(stringAt(1), position)
    }

    private fun insertImage() {
        val position = when (stringAt(0)) {
            "end" -> document.itemsCount
            else -> intAt(0)
        }
        document.insertImage(stringAt(3), intAt(1), intAt(2), position)
    }

    private fun replaceText() {
        document.replaceText(intAt(0), stringAt(1))
    }

    private fun resizeImage() {
        document.resizeImage(intAt(0), intAt(1), intAt(2))
    }

    private fun deleteItem() = document.deleteItem(intAt(0))

    private fun printElements() {
        fun printElements() {
            repeat(document.itemsCount) { itemIndex ->
                val image = document[itemIndex].image
                val paragraph = document[itemIndex].paragraph
                val line = "$itemIndex. " + when {
                    image != null -> "Image: ${image.width} ${image.height} ${image.path}"
                    paragraph != null -> "Paragraph: ${paragraph.text}"
                    else -> ""
                }

                println(line)
            }
        }

        if (!document.title.isEmpty()) {
            println("Title: ${document.title}")
        }
        printElements()
    }

    private fun undo() = document.undo()

    private fun redo() = document.redo()

    private fun save() = document.save(stringAt(0))

    private fun help() = println("""
        Available commands:
        -st <text> . . . . . . . . . . . . . . . . . Set page title with <text>
        -ip <position>|end <text>. . . . . . . . . . Insert paragraph at <position> or at end with <text>
        -ii <position>|end <width> <height> <path> . Insert image
        -rt <position> <text>. . . . . . . . . . . . Replace <text> of the paragraph at <position>
        -ri <position> <width> <height>. . . . . . . Resize image
        -di <position> . . . . . . . . . . . . . . . Delete item at position
        -l . . . . . . . . . . . . . . . . . . . . . Print elements list
        -h . . . . . . . . . . . . . . . . . . . . . Print all available commands
        -u . . . . . . . . . . . . . . . . . . . . . Undo last executed command
        -r . . . . . . . . . . . . . . . . . . . . . Redo last unexecuted command
        -s <path>. . . . . . . . . . . . . . . . . . Save document to directory <path>
        -e . . . . . . . . . . . . . . . . . . . . . Exit
    """.trimIndent())

    private fun exit() {
        mIsExit = true
    }
}
