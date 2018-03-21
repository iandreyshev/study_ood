package document

import command.DocumentCommandQueue
import command.ICommandQueue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DocumentTest {
    companion object {
        private const val QUEUE_MEMORY = 10
    }

    private lateinit var queue: ICommandQueue
    private lateinit var document: IDocument

    @Before
    fun setup() {
        queue = DocumentCommandQueue(QUEUE_MEMORY)
        document = Document(queue)
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

        document.insertImage(path, 0, 0, 0)

        assertEquals(path, document[0].image?.path)

        document.undo()

        assertFalse(document.canUndo)
        assertEquals(0, document.itemsCount)
    }
}
