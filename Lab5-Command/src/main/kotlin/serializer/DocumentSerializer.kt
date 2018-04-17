package serializer

import document.IDocumentItem
import document.IImage
import document.IParagraph
import java.io.File

abstract class DocumentSerializer {
    protected abstract val extension: String

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

    protected abstract fun onSetTitle(title: String)

    protected abstract fun onInsertParagraph(paragraph: IParagraph)

    protected abstract fun onInsertImage(image: IImage)

    protected abstract fun onSerialize(): ByteArray

    fun serializeTo(dirPath: String, fileName: String) {
        val dir = File(dirPath)
        dir.mkdirs()

        val file = File(dir, "$fileName.$extension")
        file.createNewFile()

        file.outputStream().use { stream ->
            stream.write(onSerialize())
        }
    }
}
