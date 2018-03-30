package document

import command.*
import io.IFileManager
import java.io.IOException

class Document(
        private val queue: ICommandQueue,
        private val fileManager: IFileManager
) : IDocument {
    private var mTitle = ""
    private val mItems = ArrayList<IDocumentItem>()

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

    override fun insertParagraph(text: String, position: Int): IParagraph {
        position.validatePosition()
        val paragraph = Paragraph(text)
        val command = InsertParagraphCommand(mItems, position, paragraph)
        queue.apply(command)

        return paragraph
    }

    override fun insertImage(path: String, width: Int, height: Int, position: Int): IImage {
        position.validatePosition()
        val imagePath = fileManager.copyImage(path)
                ?: throw IOException("Image with path $path not found.")

        val image = Image(imagePath, width, height)
        val command = InsertImageCommand(fileManager, mItems, position, image)
        queue.apply(command)

        return image
    }

    override fun replaceText(position: Int, text: String) {
        position.validatePosition()
        queue.apply(ReplaceTextCommand(mItems, position, text))
    }

    override fun resizeImage(position: Int, width: Int, height: Int) {
        position.validatePosition()
        queue.apply(ResizeImageCommand(mItems, position, width, height))
    }

    override fun get(position: Int): IDocumentItem = mItems[position]

    override fun deleteItem(position: Int) {
        position.validatePosition()
        queue.apply(DeleteItemCommand(fileManager, mItems, position))
    }

    override fun undo() = queue.undo()

    override fun redo() = queue.redo()

    override fun save(path: String) {
        val html = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>$title</title>
                    <meta charset="utf-8" />
                </head>
                <body>
                    <h1>$title</h1>
                    ${mItems.joinToString("")}
                </body>
            </html>""".trimIndent()

        fileManager.saveTo(path, html)
    }

    inner class ChangeTitleCommand(private val newTitle: String) : ICommand {
        private val mTitleBeforeExecute: String = String(mTitle.toByteArray())

        override fun execute() {
            mTitle = newTitle
        }

        override fun undo() {
            mTitle = mTitleBeforeExecute
        }
    }

    private fun Int.validatePosition() {
        if (this < 0 || mItems.size < this) {
            throw IndexOutOfBoundsException("Invalid position. Position can be in range [0, ${mItems.size}]")
        }
    }
}
