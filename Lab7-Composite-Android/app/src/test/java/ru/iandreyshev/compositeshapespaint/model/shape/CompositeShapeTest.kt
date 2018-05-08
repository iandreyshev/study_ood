package ru.iandreyshev.compositeshapespaint.model.shape

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.canvas.ICanvas
import ru.iandreyshev.compositeshapespaint.model.container.Vec2f
import ru.iandreyshev.compositeshapespaint.model.shape.frame.IFrame
import ru.iandreyshev.compositeshapespaint.model.shape.style.IStyle

class CompositeShapeTest {

    companion object {
        private const val INNER_SHAPES_COUNT = 1234
    }

    private lateinit var mCompositeShape: IShape
    private lateinit var mInnerMocks: MutableList<IShape>

    @Before
    fun setup() {
        mInnerMocks = mutableListOf()

        mCompositeShape = newCompositeShape {
            repeat(INNER_SHAPES_COUNT) {
                val shapeMock: IShape = mock()
                shapeMock.apply {
                    whenever(composite).thenReturn(null)

                    val frameMock: IFrame = mock()
                    frameMock.apply {
                        whenever(position).thenReturn(Vec2f())
                        whenever(width).thenReturn(5f)
                        whenever(height).thenReturn(5f)
                        whenever(frame).thenReturn(this)
                    }

                    val styleMock: IStyle = mock()
                    styleMock.apply {
                        whenever(getFillColor()).thenReturn(Color.NONE)
                        whenever(getStrokeColor()).thenReturn(Color.NONE)
                        whenever(getStrokeSize()).thenReturn(0f)
                        whenever(style).thenReturn(this)
                    }
                }
                mInnerMocks.add(shapeMock)
                subShape { shapeMock }
            }
        }
    }

    @Test
    fun returnItselfOnCallComposite() {
        assertEquals(mCompositeShape, mCompositeShape.composite)
    }

    @Test
    fun canCallDrawOnInnerShapes() {
        val canvasMock: ICanvas = mock()

        mCompositeShape.draw(canvasMock)

        doWithInnerShapes {
            verify(it).draw(canvasMock)
        }
    }

    @Test
    fun canChangeFramePropertiesOfInnerShapes() {
        val newWidth = 1f
        val newHeight = 2f
        val newPosition = Vec2f(100, 200)

        mCompositeShape.frame.position = newPosition
        doWithInnerShapes {
            verify(it.frame).position = newPosition
        }

        mCompositeShape.frame.resize(newWidth, newHeight)
        doWithInnerShapes {
            verify(it.frame).resize(newWidth, newHeight)
        }
    }

    @Test
    fun canChangeStylePropertiesOfInnerShapes() {
        val newFillColor = Color.RED
        val newStrokeColor = Color.YELLOW
        val newStrokeSize = 1000f

        mCompositeShape.style.setFillColor(newFillColor)
        mCompositeShape.style.setStrokeColor(newStrokeColor)
        mCompositeShape.style.setStrokeSize(newStrokeSize)

        doWithInnerShapes {
            verify(it.style).setFillColor(newFillColor)
            verify(it.style).setStrokeColor(newStrokeColor)
            verify(it.style).setStrokeSize(newStrokeSize)
        }
    }

    private fun doWithInnerShapes(action: (IShape) -> Unit) =
            mInnerMocks.forEach(action)

    private fun newCompositeShape(buildAction: CompositeShapeBuilder.() -> Unit): IShape =
            CompositeShapeBuilder().apply(buildAction).build()

    private class CompositeShapeBuilder {
        private var mGetShapes = mutableListOf<() -> IShape>()
        private var mGetName: () -> String = { "" }

        fun name(getName: () -> String) {
            mGetName = getName
        }

        fun subShape(getShape: () -> IShape) {
            mGetShapes.add(getShape)
        }

        fun build(): IShape =
                CompositeShape(
                        mGetName(),
                        mGetShapes.map { getShape -> getShape() }
                )
    }
}
