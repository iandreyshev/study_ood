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
        items.add(position, mParagraph)
    }

    override fun undo() {
        items.removeAt(position)
    }
}
