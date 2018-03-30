package command

import document.IDocumentItem
import document.IParagraph

class InsertParagraphCommand(
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        paragraph: IParagraph
) : ICommand {
    private val mParagraph: IDocumentItem = IDocumentItem.newParagraph(paragraph)

    override fun execute() {
        when {
            position < 0 -> throw IndexOutOfBoundsException()
            position >= items.size -> items.add(mParagraph)
            else -> items.add(position, mParagraph)
        }
    }

    override fun undo() {
        items.removeAt(position)
    }
}
