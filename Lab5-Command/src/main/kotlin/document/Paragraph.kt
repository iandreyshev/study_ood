package document

import command.Command
import command.ICommandQueue

class Paragraph(
        private val queue: ICommandQueue,
        text: String
) : IParagraph {
    private var mText: String = text

    override var text: String
        get() = mText
        set(value) {
            queue.apply(ReplaceTextCommand(value))
        }

    override fun toString(): String {
        return "<p>$text</p>"
    }

    inner class ReplaceTextCommand(private val newText: String) : Command() {
        private var mTextBefore = ""

        override fun onExecute() {
            mTextBefore = mText
            mText = newText
        }

        override fun onUndo() {
            mText = mTextBefore
        }
    }
}
