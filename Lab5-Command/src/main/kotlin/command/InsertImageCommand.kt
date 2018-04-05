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
