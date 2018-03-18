package document

import command.ChangeTitleCommand
import command.ICommandQueue

class Document(
        private val queue: ICommandQueue
) : IDocument {
    override var title: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {
            queue.apply(ChangeTitleCommand())
        }
    override val itemsCount: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val canUndo: Boolean
        get() = queue.canUndo
    override val canRedo: Boolean
        get() = queue.canRedo

    override fun insertParagraph(text: String, position: Int): IParagraph {
        TODO("insertParagraph not implemented")
    }

    override fun insertImage(path: String, width: Int, height: Int, position: Int): IImage {
        TODO("insertImage not implemented")
    }

    override fun get(index: Int): IDocumentItem {
        TODO("get not implemented")
    }

    override fun deleteItem(index: Int) {
        TODO("deleteItem not implemented")
    }

    override fun undo() = queue.undo()

    override fun redo() = queue.redo()

    override fun save(path: String) {
        TODO("save not implemented")
    }
}