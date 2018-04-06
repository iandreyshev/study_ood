package io

import com.nhaarman.mockito_kotlin.*
import document.*
import junit.framework.TestCase.assertFalse
import org.junit.Before
import org.junit.Test
import serializer.DocumentSerializer

class DocumentInterpreterTest {
    private lateinit var documentMock: IDocument
    private lateinit var interpreter: DocumentInterpreter
    private lateinit var itemMock: IDocumentItem
    private lateinit var paragraphMock: IParagraph
    private lateinit var imageMock: IImage
    private lateinit var serializerMock: DocumentSerializer

    @Before
    fun setup() {
        documentMock = mock()
        itemMock = mock()
        paragraphMock = mock()
        imageMock = mock()
        itemMock = mock()
        interpreter = DocumentInterpreter(documentMock)
        serializerMock = mock()

        whenever(documentMock[any()]).thenReturn(itemMock)

        whenever(itemMock.paragraph).thenReturn(paragraphMock)
        whenever(itemMock.image).thenReturn(imageMock)
    }

    @Test
    fun canInsertParagraphAtPosition() {
        interpreter.apply("-ip 0 abc")
        verify(documentMock).insertParagraph("abc", 0)
    }

    @Test
    fun canInsertParagraphAtEnd() {
        whenever(documentMock.itemsCount).thenReturn(100)

        interpreter.apply("-ip end abc")
        verify(documentMock).insertParagraph("abc", 100)
    }

    @Test
    fun canInsertImageAtPosition() {
        interpreter.apply("-ii 0 100 100 path")
        verify(documentMock).insertImage("path", 100, 100, 0)
    }

    @Test
    fun canInsertImageAtEnd() {
        whenever(documentMock.itemsCount).thenReturn(100)

        interpreter.apply("-ii end 100 100 path")
        verify(documentMock).insertImage("path", 100, 100, 100)
    }

    @Test
    fun canSetTitleWithBlanks() {
        interpreter.apply("-st Title with blanks")
        verify(documentMock).title = "Title with blanks"
    }

    @Test
    fun canReplaceTextAtPosition() {
        interpreter.apply("-rt 10 newText")

        verify(documentMock)[10]
        verify(itemMock).paragraph
        verify(paragraphMock).text = "newText"
    }

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionIfReplaceTextOnNotParagraphItem() {
        whenever(itemMock.paragraph).thenReturn(null)
        interpreter.apply("-rt 0 newText")
    }

    @Test
    fun canResizeImageAtPosition() {
        interpreter.apply("-ri 10 100 100")

        verify(documentMock)[10]
        verify(itemMock).image
        verify(imageMock).resize(100, 100)
    }

    @Test(expected = IllegalArgumentException::class)
    fun throwExceptionIfResizeNotImageItem() {
        whenever(itemMock.image).thenReturn(null)
        interpreter.apply("-ri 0 100 100")
    }

    @Test
    fun canDeleteItemAtPosition() {
        interpreter.apply("-di 100")
        verify(documentMock).deleteItem(100)
    }

    @Test
    fun callExitOnExitCommand() {
        assertFalse(interpreter.apply("-e"))
    }
}
