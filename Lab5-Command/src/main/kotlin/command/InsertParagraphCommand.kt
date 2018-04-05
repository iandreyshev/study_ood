package command

import document.IDocumentItem
import document.IParagraph
import document.factory.IItemsFactory

class InsertParagraphCommand(
        itemsFactory: IItemsFactory,
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        paragraph: IParagraph
) : Command() {
    private val mParagraph: IDocumentItem = itemsFactory.create(paragraph)

    override fun onExecute() {
        items.add(position, mParagraph)
    }

    override fun onUndo() {
        items.removeAt(position)
    }
}
