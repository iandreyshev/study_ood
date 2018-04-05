package document

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import command.DocumentCommandQueue
import command.ICommandQueue
import html.IHtmlConverter
import io.IFileManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DocumentTest {
    companion object {
        private const val QUEUE_MEMORY = 10
    }

    private lateinit var queue: ICommandQueue
    private lateinit var fileManagerMock: IFileManager
    private lateinit var htmlConverterMock: IHtmlConverter
    private lateinit var document: IDocument

    @Before
    fun setup() {
        queue = DocumentCommandQueue(QUEUE_MEMORY)
        fileManagerMock = mock()
        htmlConverterMock = mock()
        document = Document(queue, fileManagerMock, htmlConverterMock)

        whenever(htmlConverterMock.transform(any())).then { it.arguments[0] }
    }

    @Test
    fun canSetTitleAndUndoThem() {
        val titleBefore = ""
        val titleAfter = "New title"

        assertEquals(titleBefore, document.title)

        document.title = titleAfter

        assertEquals(titleAfter, document.title)

        document.undo()

        assertEquals(titleBefore, document.title)
    }

    @Test
    fun canInsertParagraphAndRemoveItOnUndo() {
        val text = "Paragraph text"

        document.insertParagraph(text, 0)

        assertEquals(text, document[0].paragraph?.text)

        document.undo()

        assertFalse(document.canUndo)
        assertEquals(0, document.itemsCount)
    }

    @Test
    fun canInsertImageAndRemoveItOnUndo() {
        val path = "Image path"
        whenever(fileManagerMock.copyImage(path)).thenReturn(path)

        document.insertImage(path, 0, 0, 0)

        assertEquals(path, document[0].image?.path)

        document.undo()

        assertFalse(document.canUndo)
        assertEquals(0, document.itemsCount)
    }
}
