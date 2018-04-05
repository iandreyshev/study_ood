package command

import com.nhaarman.mockito_kotlin.*
import document.IDocumentItem
import document.IImage
import document.IParagraph
import io.IFileManager
import org.junit.Before
import org.junit.Test

class DeleteItemCommandTest {
    companion object {
        private const val POSITION = 1337
        private const val IMAGE_PATH = "image path"
    }

    private lateinit var fileManagerMock: IFileManager
    private lateinit var itemMock: IDocumentItem
    private lateinit var paragraphMock: IParagraph
    private lateinit var imageMock: IImage
    private lateinit var itemsListMock: MutableList<IDocumentItem>
    private lateinit var command: DeleteItemCommand

    @Before
    fun setup() {
        fileManagerMock = mock()
        itemMock = mock()
        paragraphMock = mock()
        imageMock = mock()
        itemsListMock = mock()

        whenever(itemsListMock[POSITION]).thenReturn(itemMock)

        whenever(itemMock.paragraph).thenReturn(paragraphMock)
        whenever(itemMock.image).thenReturn(imageMock)

        whenever(imageMock.path).thenReturn(IMAGE_PATH)
    }

    @Test
    fun removeItemAtSpecifiedPositionOnDeleteParagraph() {
        createDeleteParagraphCommand()
        whenever(itemMock.image).thenReturn(null)

        command.execute()

        verify(itemsListMock).removeAt(POSITION)
    }

    @Test
    fun addItemAtOldPositionOnReturnParagraph() {
        createDeleteParagraphCommand()
        whenever(itemMock.image).thenReturn(null)

        command.execute()
        command.undo()

        verify(itemsListMock).add(POSITION, itemMock)
    }

    @Test
    fun removeItemAtSpecifiedPositionOnDeleteImageAndMarkFileToDelete() {
        createDeleteImageCommand()
        whenever(itemMock.paragraph).thenReturn(null)

        command.execute()

        verify(itemsListMock).removeAt(POSITION)
        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, true)
    }

    @Test
    fun addItemAtSpecifiedPositionOnReturnImageAndMarkFileAsAlive() {
        createDeleteImageCommand()
        whenever(itemMock.paragraph).thenReturn(null)

        command.execute()
        command.undo()

        verify(itemsListMock).removeAt(POSITION)
        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, true)
        verify(itemsListMock).add(POSITION, itemMock)
        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, false)
    }

    @Test
    fun notCallDeleteImageIfCommandDestroyAfterUndo() {
        createDeleteImageCommand()
        whenever(itemMock.paragraph).thenReturn(null)

        command.execute()
        command.undo()
        command.destroy()

        verify(itemsListMock)[POSITION]
        verify(itemsListMock).removeAt(POSITION)
        verify(itemsListMock).add(POSITION, itemMock)
        verifyNoMoreInteractions(itemsListMock)

        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, true)
        verify(fileManagerMock, times(2)).markImageOnDelete(IMAGE_PATH, false)
        verifyNoMoreInteractions(fileManagerMock)
    }

    @Test
    fun callDeleteImageIfCommandDestroyAfterExecuted() {
        createDeleteImageCommand()
        whenever(itemMock.paragraph).thenReturn(null)

        command.execute()
        command.destroy()

        verify(itemsListMock)[POSITION]
        verify(itemsListMock).removeAt(POSITION)
        verifyNoMoreInteractions(itemsListMock)

        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, true)
        verify(fileManagerMock).deleteImage(IMAGE_PATH)
        verifyNoMoreInteractions(fileManagerMock)
    }

    private fun createDeleteParagraphCommand() {
        whenever(itemMock.image).thenReturn(null)
        command = DeleteItemCommand(fileManagerMock, itemsListMock, POSITION)
    }

    private fun createDeleteImageCommand() {
        whenever(itemMock.paragraph).thenReturn(null)
        command = DeleteItemCommand(fileManagerMock, itemsListMock, POSITION)
    }
}
