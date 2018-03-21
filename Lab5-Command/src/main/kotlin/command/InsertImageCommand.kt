package command

import document.IDocumentItem
import document.IImage
import io.IFileManager

class InsertImageCommand(
        private val imageId: Long,
        private val fileManager: IFileManager,
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        image: IImage
) : ICommand {
    private val image: IDocumentItem = IDocumentItem.newImage(image)

    override fun execute() {
        items.add(position, image)
        fileManager.markImageOnDelete(imageId, false)
    }

    override fun undo() {
        items.removeAt(position)
        fileManager.markImageOnDelete(imageId, true)
    }

    override fun destroy() {
        fileManager.deleteImage(imageId)
    }
}
