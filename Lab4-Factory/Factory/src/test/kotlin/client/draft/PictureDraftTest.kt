package client.draft

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import shape.IShape

class PictureDraftTest {
    private lateinit var mDraft: PictureDraft

    @Before
    fun setup() {
        mDraft = PictureDraft()
    }

    @Test
    fun countIsZeroByDefault() {
        assertEquals(0, mDraft.count)
    }

    @Test
    fun countWillBeIncrementedAfterInsertingShape() {
        val shapesCount = 123456

        repeat(shapesCount) {
            val shape: IShape = mock()
            mDraft.insert(shape)
        }

        assertEquals(shapesCount, mDraft.count)
    }

    @Test
    fun notInsertOneShapeTwoTimes() {
        val shape: IShape = mock()

        repeat(100000) {
            mDraft.insert(shape)
        }

        assertEquals(1, mDraft.count)
    }

    @Test
    fun canBeIteratingByShapes() {
        val shapes = HashSet<IShape>()
        val shapesCount = 123456

        repeat(shapesCount) {
            val shape: IShape = mock()
            shapes.add(shape)
            mDraft.insert(shape)
        }

        mDraft.forEach { shape ->
            assertTrue(shapes.contains(shape))
        }
    }

    @Test
    fun canBeCleared() {
        val shapesCount = 123456

        repeat(shapesCount) {
            val shape: IShape = mock()
            mDraft.insert(shape)
        }

        mDraft.reset()

        mDraft.forEach {
            fail()
        }
        assertEquals(0, mDraft.count)
    }
}
