package serializer

import document.IDocumentItem
import document.IImage
import document.IParagraph

abstract class DocumentSerializer {
    abstract val extension: String

    protected abstract fun onSetTitle(title: String)

    protected open fun onInsertParagraph(paragraph: IParagraph) {
        // Override in subclass if it needed
    }

    protected open fun onInsertImage(image: IImage) {
        // Override in subclass if it needed
    }

    fun setTitle(title: String): DocumentSerializer {
        onSetTitle(title)
        return this
    }

    fun insertItem(item: IDocumentItem): DocumentSerializer {
        val paragraph = item.paragraph
        val image = item.image

        when {
            paragraph != null -> onInsertParagraph(paragraph)
            image != null -> onInsertImage(image)
        }

        return this
    }

    abstract fun serialize(): String
}
