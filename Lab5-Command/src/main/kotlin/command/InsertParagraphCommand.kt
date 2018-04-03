package command

import document.IDocumentItem
import document.IParagraph

class InsertParagraphCommand(
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        paragraph: IParagraph
) : Command() {
    private val mParagraph: IDocumentItem = IDocumentItem.newParagraph(paragraph)

    override fun onExecute() {
        items.add(position, mParagraph)
    }

    override fun onUndo() {
        items.removeAt(position)
    }
}
