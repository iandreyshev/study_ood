package command

import document.IDocumentItem

class ReplaceTextCommand(
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        private val newText: String
) : ICommand {
    private var mTextBefore = ""

    override fun execute() {
        mTextBefore = items[position].paragraph?.text ?: mTextBefore
        items[position].paragraph?.text = newText
    }

    override fun undo() {
        items[position].paragraph?.text = mTextBefore
    }
}
