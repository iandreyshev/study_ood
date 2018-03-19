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
    }

    @Test
    fun canInsertImageAndRemoveItOnUndo() {

    }
}
