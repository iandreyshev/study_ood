package document

import command.*
import html.IHtmlConverter
import io.IFileManager
import java.io.IOException

class Document(
        private val queue: ICommandQueue,
        private val fileManager: IFileManager,
        private val htmlConverter: IHtmlConverter
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
        set(value) {
            field = htmlConverter.transform(value)
        }

    override fun insertParagraph(text: String, position: Int): IParagraph {
        position.validatePosition()
        val paragraph = Paragraph(queue, htmlConverter.transform(text))
        val command = InsertParagraphCommand(mItems, position, paragraph)
        queue.apply(command)

        return paragraph
    }

    override fun insertImage(path: String, width: Int, height: Int, position: Int): IImage {
        position.validatePosition()
        val imagePath = fileManager.copyImage(htmlConverter.transform(path))
                ?: throw IOException("Image with path $path not found.")

        val image = Image(queue, imagePath, width, height)
        val command = InsertImageCommand(fileManager, mItems, position, image)
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
