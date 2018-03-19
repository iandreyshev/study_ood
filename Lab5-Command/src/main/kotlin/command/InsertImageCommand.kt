package command

import document.IDocumentItem
import document.IImage

class InsertImageCommand(
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        image: IImage
) : ICommand {
    private val image: IDocumentItem = IDocumentItem.newImage(image)

    override fun execute() {
        if (position >= items.size) {
            items.add(image)
        } else {
            items.add(position, image)
        }
    }

    override fun undo() {
        items.removeAt(position)
    }
}
