package document

interface IDocument {
    var title: String

    val itemsCount: Int

    val canUndo: Boolean

    val canRedo: Boolean

    @Throws(IllegalArgumentException::class)
    fun insertParagraph(text: String, position: Int = itemsCount): IParagraph

    @Throws(IllegalArgumentException::class)
    fun insertImage(path: String, width: Int, height: Int, position: Int = itemsCount): IImage

    @Throws(IndexOutOfBoundsException::class)
    operator fun get(position: Int): IDocumentItem

    @Throws(IndexOutOfBoundsException::class)
    fun deleteItem(position: Int)

    @Throws(IllegalStateException::class)
    fun undo()

    @Throws(IllegalStateException::class)
    fun redo()

    @Throws(IllegalArgumentException::class)
    fun save(path: String)
}
