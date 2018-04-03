package document

import command.Command
import command.ICommandQueue

class Paragraph(
        private val queue: ICommandQueue,
        text: String
) : IParagraph {
    override var text: String = text
        set(value) {
            queue.apply(ReplaceTextCommand(value))
        }

    override fun toString(): String {
        return "<p>$text</p>"
    }

    inner class ReplaceTextCommand(private val newText: String) : Command() {
        private var mTextBefore = ""

        override fun onExecute() {
            mTextBefore = text
            text = newText
        }

        override fun onUndo() {
            text = mTextBefore
        }
    }
}
