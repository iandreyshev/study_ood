package command

import document.IDocumentItem
import document.IImage
import io.IImageFileManager

class InsertImageCommand(
        private val fileManager: IImageFileManager,
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        image: IImage
) : ICommand {
    private val mImageItem: IDocumentItem = IDocumentItem.newImage(image)
    private val path: String = image.path

    override fun execute() {
        when {
            position < 0 -> throw IndexOutOfBoundsException()
            position >= items.size -> items.add(mImageItem)
            else -> items.add(position, mImageItem)
        }
        fileManager.markImageOnDelete(path, false)
    }

    override fun undo() {
        items.removeAt(position)
        fileManager.markImageOnDelete(path, true)
    }

    override fun destroy() {
        fileManager.deleteImage(path)
    }
}
