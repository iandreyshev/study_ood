package document

import serializer.DocumentSerializer

interface IDocument {
    var title: String

    val itemsCount: Int

    val canUndo: Boolean

    val canRedo: Boolean

    fun insertParagraph(text: String, position: Int = itemsCount): IParagraph

    fun insertImage(path: String, width: Int, height: Int, position: Int = itemsCount): IImage

    operator fun get(position: Int): IDocumentItem

    fun deleteItem(position: Int)

    fun undo()

    fun redo()

    fun save(path: String, serializer: DocumentSerializer)
}
