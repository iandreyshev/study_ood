package command

import com.nhaarman.mockito_kotlin.mock
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test

class DocumentCommandQueueTest {
    companion object {
        private const val MEMORY_SIZE = 100
    }

    private lateinit var mQueue: DocumentCommandQueue

    @Before
    fun setup() {
        mQueue = DocumentCommandQueue(MEMORY_SIZE)
    }

    @Test
    fun undoAndRedoDisabledByDefault() {
        assertFalse(mQueue.canUndo)
        assertFalse(mQueue.canRedo)
    }

    @Test
    fun canReturnUndoRedoFlags() {
        mQueue.apply(mock())
        assertTrue(mQueue.canUndo)

        mQueue.undo()
        assertTrue(mQueue.canRedo)
    }

    @Test
    fun canRememberMemorySizeCommandsCount() {
        repeat(10 * MEMORY_SIZE) {
            mQueue.apply(mock())
        }

        repeat(MEMORY_SIZE) {
            mQueue.undo()
        }

        assertFalse(mQueue.canUndo)

        repeat(MEMORY_SIZE) {
            assertTrue(mQueue.canRedo)
            mQueue.redo()
        }

        assertFalse(mQueue.canRedo)
    }

    @Test
    fun applyingNewCommandAfterUndoDisabledRedo() {
        mQueue.apply(mock())
        mQueue.undo()

        assertTrue(mQueue.canRedo)

        mQueue.apply(mock())

        assertFalse(mQueue.canRedo)
    }

    @Test
    fun canApplyOneCommandTwoTimes() {
        val command: ICommand = mock()

        repeat(MEMORY_SIZE) {
            mQueue.apply(command)
        }

        repeat(MEMORY_SIZE) {
            assertTrue(mQueue.canUndo)
            mQueue.undo()
        }
    }
}
