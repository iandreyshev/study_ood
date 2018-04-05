package command

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import document.IDocumentItem
import document.IParagraph
import document.factory.IItemsFactory
import org.junit.Before
import org.junit.Test

class InsertParagraphCommandTest {
    companion object {
        private const val POSITION = 1337
    }

    private lateinit var itemsFactoryMock: IItemsFactory
    private lateinit var itemsListMock: MutableList<IDocumentItem>
    private lateinit var paragraphMock: IParagraph
    private lateinit var itemMock: IDocumentItem
    private lateinit var command: InsertParagraphCommand

    @Before
    fun setup() {
        itemsFactoryMock = mock()
        itemsListMock = mock()
        paragraphMock = mock()
        itemMock = mock()

        whenever(itemMock.paragraph).thenReturn(paragraphMock)
        whenever(itemsFactoryMock.create(paragraphMock)).thenReturn(itemMock)

        command = InsertParagraphCommand(itemsFactoryMock, itemsListMock, POSITION, paragraphMock)
    }

    @Test
    fun canAddParagraphOnExecute() {
        command.execute()

        verify(itemsListMock).add(POSITION, itemMock)
    }

    @Test
    fun canRemoveItemOnUndo() {
        command.execute()
        command.undo()

        verify(itemsListMock).add(POSITION, itemMock)
        verify(itemsListMock).removeAt(POSITION)
    }
}
