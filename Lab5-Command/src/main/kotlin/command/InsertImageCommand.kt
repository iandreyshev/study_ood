package command

import document.IDocumentItem
import document.IImage
import document.factory.IItemsFactory
import io.IImageFileManager

class InsertImageCommand(
        private val fileManager: IImageFileManager,
        itemsFactory: IItemsFactory,
        private val items: MutableList<IDocumentItem>,
        private val position: Int,
        image: IImage
) : Command() {
    private val mImageItem: IDocumentItem = itemsFactory.create(image)
    private val mPath: String = image.path

    override fun onExecute() {
        items.add(position, mImageItem)
        fileManager.markImageOnDelete(mPath, false)
    }

    override fun onUndo() {
        items.removeAt(position)
        fileManager.markImageOnDelete(mPath, true)
    }

    override fun onDestroyNotExecuted() {
        fileManager.deleteImage(mPath)
    }
}
