package document

import command.ICommandQueue
import command.ReplaceTextCommand

class Paragraph(
        private val queue: ICommandQueue,
        text: String
) : IParagraph {
    private var mText: String = text

    override var text: String
        get() = mText
        set(value) {
            val command = ReplaceTextCommand(text, value) { newText ->
                mText = newText
            }
            queue.apply(command)
        }
}
