package client.designer

import client.draft.IPictureDraft
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import shape.IShape
import shape.factory.IShapeFactory

class PictureDesignerTest {
    private lateinit var mMockFactory: IShapeFactory
    private lateinit var mMockDraft: IPictureDraft
    private lateinit var mDesigner: IDesigner

    @Before
    fun setup() {
        mMockFactory = mock()
        mMockDraft = mock()
        mDesigner = PictureDesigner(mMockFactory, mMockDraft)
    }

    @Test
    fun returnNullIfFactoryReturnNull() {
        val description = listOf("")

        whenever(mMockFactory.create(description)).thenReturn(null)

        assertNull(mDesigner.addShape(description))
    }

    @Test
    fun returnNullIfFactoryReturnShapeAndDraftNotInsertIt() {
        val description = listOf("")
        val shape: IShape = mock()

        whenever(mMockFactory.create(description)).thenReturn(shape)
        whenever(mMockDraft.insert(shape)).thenReturn(false)

        assertNull(mDesigner.addShape(description))
    }

    @Test
    fun returnShapeIfFactoryReturnShapeAndDraftAddIt() {
        val description = listOf("")
        val shape: IShape = mock()

        whenever(mMockFactory.create(description)).thenReturn(shape)
        whenever(mMockDraft.insert(shape)).thenReturn(true)

        assertEquals(shape, mDesigner.addShape(description))
    }
}
