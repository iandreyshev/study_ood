package command

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import document.IDocumentItem
import document.IImage
import document.factory.IItemsFactory
import io.IFileManager
import org.junit.Before
import org.junit.Test

class InsertImageCommandTest {
    companion object {
        private const val POSITION = 1337
        private const val IMAGE_PATH = "imageMock path"
    }

    private lateinit var fileManagerMock: IFileManager
    private lateinit var itemsListMock: MutableList<IDocumentItem>
    private lateinit var imageMock: IImage
    private lateinit var itemMock: IDocumentItem
    private lateinit var itemsFactoryMock: IItemsFactory
    private lateinit var command: InsertImageCommand

    @Before
    fun setup() {
        fileManagerMock = mock()
        itemsListMock = mock()
        imageMock = mock()
        itemMock = mock()
        itemsFactoryMock = mock()

        whenever(imageMock.path).thenReturn(IMAGE_PATH)
        whenever(itemMock.image).thenReturn(imageMock)
        whenever(itemsFactoryMock.create(imageMock)).thenReturn(itemMock)

        command = InsertImageCommand(fileManagerMock, itemsFactoryMock, itemsListMock, POSITION, imageMock)
    }

    @Test
    fun addItemAndMarkFileNotDeletedOnExecute() {
        command.execute()

        verify(itemsListMock).add(POSITION, itemMock)
        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, false)
    }

    @Test
    fun removeItemAndMarkFileDeletedOnUndo() {
        command.execute()
        command.undo()

        verify(itemsListMock).removeAt(POSITION)
        verify(fileManagerMock).markImageOnDelete(IMAGE_PATH, true)
    }

    @Test
    fun deleteFileIfDestroyNotExecuted() {
        command.execute()
        command.undo()
        command.destroy()

        verify(fileManagerMock).deleteImage(IMAGE_PATH)
    }
}
