package document

interface IDocument {
    var title: String

    val itemsCount: Int

    val canUndo: Boolean

    val canRedo: Boolean

    fun insertParagraph(text: String, position: Int = itemsCount): IParagraph

    fun insertImage(path: String, width: Int, height: Int, position: Int = itemsCount): IImage

    fun resizeImage(position: Int, width: Int, height: Int)

    fun replaceText(position: Int, text: String)

    operator fun get(position: Int): IDocumentItem

    fun deleteItem(position: Int)

    fun undo()

    fun redo()

    fun save(path: String)
}
