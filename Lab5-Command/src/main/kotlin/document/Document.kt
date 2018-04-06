package document

import command.*
import document.factory.IItemsFactory
import io.IFileManager
import serializer.DocumentSerializer
import java.io.IOException

class Document(
        private val queue: ICommandQueue,
        private val fileManager: IFileManager,
        private val itemsFactory: IItemsFactory
) : IDocument {
    override var title: String
        get() = mTitle
        set(value) {
            queue.apply(ChangeTitleCommand(value))
        }
    override val itemsCount: Int
        get() = mItems.count()
    override val canUndo: Boolean
        get() = queue.canUndo
    override val canRedo: Boolean
        get() = queue.canRedo

    private val mItems = ArrayList<IDocumentItem>()
    private var mTitle = ""

    override fun insertParagraph(text: String, position: Int): IParagraph {
        position.validatePosition()
        val paragraph = Paragraph(queue, text)
        val command = InsertParagraphCommand(itemsFactory, mItems, position, paragraph)
        queue.apply(command)

        return paragraph
    }

    override fun insertImage(path: String, width: Int, height: Int, position: Int): IImage {
        position.validatePosition()
        val imagePath = fileManager.copyImage(path)
                ?: throw IOException("Image with path $path not found.")

        val image = Image(queue, imagePath, width, height)
        val command = InsertImageCommand(fileManager, itemsFactory, mItems, position, image)
        queue.apply(command)

        return image
    }

    override fun get(position: Int): IDocumentItem {
        position.validatePosition()
        return mItems[position]
    }

    override fun deleteItem(position: Int) {
        position.validatePosition()
        queue.apply(DeleteItemCommand(fileManager, mItems, position))
    }

    override fun undo() = queue.undo()

    override fun redo() = queue.redo()

    override fun save(path: String, serializer: DocumentSerializer) {
        with(serializer) {
            setTitle(title)
            mItems.forEach { insertItem(it) }
            fileManager.saveTo(path, serialize(), extension)
        }
    }

    inner class ChangeTitleCommand(private val newTitle: String) : Command() {
        private val mTitleBeforeExecute: String = String(mTitle.toByteArray())

        override fun onExecute() {
            mTitle = newTitle
        }

        override fun onUndo() {
            mTitle = mTitleBeforeExecute
        }
    }

    private fun Int.validatePosition() {
        if (this < 0 || mItems.size < this) {
            throw IndexOutOfBoundsException("Invalid position. Position can be in range [0, ${mItems.size}]")
        }
    }
}
