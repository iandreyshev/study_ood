package command

import document.IDocumentItem
import document.IImage
import io.IImageFileManager

class DeleteItemCommand(
        private val fileManager: IImageFileManager,
        private val items: MutableList<IDocumentItem>,
        private val position: Int
) : ICommand {
    private val item: IDocumentItem = items[position]
    private var mBehavior: ICommand

    init {
        val image = item.image

        mBehavior = when {
            item.paragraph != null -> DeleteParagraph()
            image != null -> DeleteImage(image)
            else -> throw NotImplementedError()
        }
    }

    override fun execute() = mBehavior.execute()

    override fun undo() = mBehavior.undo()

    override fun destroy() = mBehavior.destroy()

    inner class DeleteParagraph : ICommand {
        override fun execute() {
            items.removeAt(position)
        }

        override fun undo() {
            items.add(position, item)
        }
    }

    inner class DeleteImage(private val image: IImage) : ICommand {
        override fun execute() {
            fileManager.markImageOnDelete(image.path, true)
            items.removeAt(position)
        }

        override fun undo() {
            fileManager.markImageOnDelete(image.path, false)
            items.add(position, item)
        }

        override fun destroy() {
            fileManager.markImageOnDelete(image.path, false)
        }
    }
}
