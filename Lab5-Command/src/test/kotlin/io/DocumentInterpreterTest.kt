package io

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import document.IDocument
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class DocumentInterpreterTest {
    companion object {
        private const val INSERT_PARAGRAPH = "-ip"
        private const val EXIT = "-e"
    }

    private lateinit var documentMock: IDocument
    private lateinit var interpreter: DocumentInterpreter

    @Before
    fun setup() {
        documentMock = mock()
        interpreter = DocumentInterpreter(documentMock)
    }

    @Test
    fun insertParagraphAtPosition() {
        val isExit = interpreter.apply("$INSERT_PARAGRAPH   0   abc")

        assertFalse(isExit)
        verify(documentMock).insertParagraph("abc", 0)
    }

    @Test
    fun insertParagraphAtEnd() {
        whenever(documentMock.itemsCount).thenReturn(100)

        val isExit = interpreter.apply("$INSERT_PARAGRAPH  end  abc")

        assertFalse(isExit)
        verify(documentMock).insertParagraph("abc", 100)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun canNotApplyCommandIfPositionMoreThanItemsCount() {
        whenever(documentMock.itemsCount).thenReturn(5)

        val isApplied = interpreter.apply("$INSERT_PARAGRAPH 6 abc")

        assertFalse(isApplied)
    }

    @Test
    fun callExitOnExitCommand() {
        assertTrue(interpreter.apply(EXIT))
    }
}
