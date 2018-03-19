package document

import command.*

class Document(
        private val queue: ICommandQueue
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

    @Throws(IndexOutOfBoundsException::class)
    override fun insertParagraph(text: String, position: Int): IParagraph {
        val paragraph = Paragraph(text)
        val command = InsertParagraphCommand(mItems, position, paragraph)
        queue.apply(command)

        return paragraph
    }

    @Throws(IndexOutOfBoundsException::class)
    override fun insertImage(path: String, width: Int, height: Int, position: Int): IImage {
        val image = Image(path, width, height)
        val command = InsertImageCommand(mItems, position, image)
        queue.apply(command)

        return image
    }

    @Throws(IndexOutOfBoundsException::class)
    override fun get(position: Int): IDocumentItem = mItems[position]

    @Throws(IndexOutOfBoundsException::class)
    override fun deleteItem(position: Int) {
        mItems.removeAt(position)
    }

    override fun undo() = queue.undo()

    override fun redo() = queue.redo()

    override fun save(path: String) {
        TODO("save not implemented")
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
}
