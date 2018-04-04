package command

import document.IDocumentItem
import document.IImage
import io.IImageFileManager

class InsertImageCommand(
        private val fileManager: IImageFileManager,
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        image: IImage
) : Command() {
    private val mImageItem: IDocumentItem = IDocumentItem.newImage(image)
    private val path: String = image.path

    override fun onExecute() {
        items.add(position, mImageItem)
        fileManager.markImageOnDelete(path, false)
    }

    override fun onUndo() {
        items.removeAt(position)
        fileManager.markImageOnDelete(path, true)
    }

    override fun onDestroyNotExecuted() {
        fileManager.deleteImage(path)
    }
}
